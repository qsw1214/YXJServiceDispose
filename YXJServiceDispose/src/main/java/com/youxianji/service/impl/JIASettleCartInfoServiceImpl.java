package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.IBaseOrderInfoDAO;
import com.youxianji.dao.IDeductAmtRulesDAO;
import com.youxianji.dao.IDeductProdDAO;
import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.dao.IDeliveryFeeRulesDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.ISubStoreInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IYxjBuserCartInfoDAO;
import com.youxianji.dao.IYxjBuserShopInfoDAO;
import com.youxianji.facade.yxjia.bean.JIASettleCartInfoResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIACartDetailBean;
import com.youxianji.facade.yxjia.bean.json.JIAOrderDetailBean;
import com.youxianji.facade.yxjia.bean.json.JIAReceiveAddrBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.DeductAmtRules;
import com.youxianji.pojo.DeductProd;
import com.youxianji.pojo.DeliveryFeeRules;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.SubStoreInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserCartInfo;
import com.youxianji.pojo.YxjBuserShopInfo;
import com.youxianji.service.IJIASettleCartInfoService;
import com.youxianji.service.ISettleCartInfoV2Service;
import com.youxianji.util.CalculateDistance;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Service("jiasettleCartInfoService")
public class JIASettleCartInfoServiceImpl implements IJIASettleCartInfoService {
	Logger logger = Logger.getLogger(JIASettleCartInfoServiceImpl.class);
    @Resource
    private IUserInfoDAO userInfoDAO;
    @Resource
    private IYxjBuserCartInfoDAO cartInfoDAO;
    @Resource
    private IOrderInfoDAO orderInfoDAO;
    @Resource 
    private IOrderDetailDAO orderDetailDAO;
    @Resource
    private IDeliveryAddressDAO deliveryAddressDAO;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IDeliveryFeeRulesDAO deliveryFeeRulesDAO;
    @Resource
    private IDeductAmtRulesDAO deductAmtRulesDAO;
    @Resource
    private IDeductProdDAO deductProdDAO;
    @Resource
    private IProdInfoDAO prodInfoDAO;
    @Resource
    private IYxjBuserShopInfoDAO yxjBuserShopInfoDAO;
    @Resource
   	private ISubStoreInfoDAO subStoreInfoDAO;
    @Resource
    private ISettleCartInfoV2Service settleCartInfoV2Service;
    @Resource
    private IBaseOrderInfoDAO baseOrderInfoDAO;
	
	@Override
	public JIASettleCartInfoResponseBean doSettleCartInfo(String userId,String os
			,String deviceNo,List<JIACartDetailBean> detailList)
			throws BaseException {
        //这里需要加一些校验
		UserInfo userInfo = userInfoDAO.getById(userId);
		String baseOrderSn = IdGenerator.instance().generate(os);
		
		List<JIAOrderDetailBean> retDetailBeanList = new ArrayList<JIAOrderDetailBean>();
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		
		//查询是否存在该订单号
		if(orderInfoDAO.getByOrderSn(baseOrderSn) != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("订单号重复，请重新结算");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		Map<String,List<YxjBuserCartInfo>> suppilerCartInfoMap = buildSplitOrderMap(detailList);
		Map<String,List<OrderDetail>> detailMap = new HashMap<>();
		for(Map.Entry<String, List<YxjBuserCartInfo>> entry : suppilerCartInfoMap.entrySet()){
			
			BusinessUserInfo businessUser = null; 
			List<OrderDetail>  orderDetailList = new ArrayList<OrderDetail>();
			String payflag = null;
			boolean deductFlag = false;
			BigDecimal primeprice = new BigDecimal("0");
			BigDecimal sellprice = new BigDecimal("0");
			BigDecimal detailPrice = new BigDecimal("0");
			String orderSn = IdGenerator.instance().generate(os);
			ActivityProdInfo activeInfo = null;
			for(YxjBuserCartInfo cartInfo:entry.getValue()){
					
			    ProdInfo prodInfo = cartInfo.getProdInfo();
			    if(businessUser == null){
			    	businessUser = prodInfo.getBusinessUser();
			    }
	            if(payflag != null && !payflag.equals(prodInfo.getPayflag())){
	            	ErrorEnum.valueOf("FAIL_5555").changetMessage("配送和自提商品不能混合购买");
	        		throw new BaseException(ErrorEnum.FAIL_5555);
	            }
	            
	            if(!"1".equals(prodInfo.getState())){
	            	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"已告罄");
	        		throw new BaseException(ErrorEnum.FAIL_5555);
	            }
	            
	            
	            payflag = prodInfo.getPayflag();
	            
			    if("1".equals(prodInfo.getDeductFlag())){
			    	deductFlag = true;
			    }
			    
			    activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
			    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
			    	 //如果是活动商品
				    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			    	sellprice = sellprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			    	detailPrice = activeInfo.getActivePrice();
			    }else if("1".equals(prodInfo.getIsfreshman())){
			    	//如果是新人专享商品
			    	checkFreshman(userInfo,cartInfo.getProdQuantity());
			    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			    	sellprice = sellprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			    	detailPrice = prodInfo.getFreshmanprice();
			    }else{
			    	 //改为都按原价计算
			    	 primeprice = primeprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
				     sellprice = sellprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
				     detailPrice = prodInfo.getSellPrice();
			    }
	
			    //创建订单详细
			    OrderDetail orderDetail = bulidOrderDetail(cartInfo,orderSn,detailPrice);
			    orderDetailList.add(orderDetail);
			    
			    //构建返回详细信息
			    retDetailBeanList.add(bulidRetDetailBean(orderDetail,prodInfo));
			    
			}
			
			 // 创建订单
			OrderInfo ordeInfo = bulidOrderInfo(baseOrderSn,orderSn,primeprice,sellprice
					,deviceNo,userInfo,businessUser,deductFlag,payflag,orderDetailList);
			if(!"OWNSTOCK".equals(entry.getKey())){
				ordeInfo.setSuppilerid(entry.getKey());
			}
			
            orderInfoList.add(ordeInfo);
            
            detailMap.put(orderSn, orderDetailList);
			
			
		}
		
		BaseOrderInfo baseOrder = settleCartInfoV2Service
				.doBulidBaseOrderInfo(baseOrderSn, orderInfoList);
		baseOrder.setOrdertype("B");
		baseOrderInfoDAO.insert(baseOrder);
		
		YxjBuserShopInfo shopInfo = yxjBuserShopInfoDAO.getShopDetailByuserId(userInfo.getUserId());
		   if(shopInfo == null){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("店铺未设置，请联系管理员");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
			}
		baseOrder.setReceiveaddress(shopInfo.getShopAddress());
		baseOrder.setReceivename(shopInfo.getShopName());
		baseOrder.setReceivephone(shopInfo.getShopTelephone());
		
		baseOrder.setReceiveprovince(shopInfo.getShopProvince());
		baseOrder.setReceivecity(shopInfo.getShopCity());
		baseOrder.setReceivedistrict(shopInfo.getShopDistrict());
		
		baseOrderInfoDAO.update(baseOrder);

		
		for(OrderInfo order:orderInfoList){
			this.saveALLOrder(order, detailMap.get(order.getOrdersn()));
		}
		
		
		return bulidResponseBean(baseOrder,retDetailBeanList);
	}
	
	public Map<String,List<YxjBuserCartInfo>> buildSplitOrderMap(List<JIACartDetailBean> detailList){
		Map<String,List<YxjBuserCartInfo>> suppilerCartInfoMap = new HashMap<>();
		List<YxjBuserCartInfo> cartInfoList = null;
		for(JIACartDetailBean cartBean:detailList){
			YxjBuserCartInfo cartInfo = cartInfoDAO.getById(cartBean.getCartid());
			if(cartInfo == null){
            	ErrorEnum.valueOf("FAIL_5555").changetMessage("购物车不能为空");
        		throw new BaseException(ErrorEnum.FAIL_5555);
            }
			
		    ProdInfo prodInfo = cartInfo.getProdInfo();
		    String suppilerid = prodInfo.getSuppilerid();
		    if("1".equals(prodInfo.getStockflag())){
		    	suppilerid = "OWNSTOCK";  
		    }
		    
		    
		    if(!suppilerCartInfoMap.containsKey(suppilerid)){
		    	cartInfoList = new ArrayList<>();
		    	cartInfoList.add(cartInfo);
		    	suppilerCartInfoMap.put(suppilerid,cartInfoList);
		    }else{
		    	cartInfoList = suppilerCartInfoMap.get(suppilerid);
		    	cartInfoList.add(cartInfo);
		    	suppilerCartInfoMap.put(suppilerid,cartInfoList);
		    }
		   
		}
		
		return suppilerCartInfoMap;
	}
	
	
	public BigDecimal calcDeliverFee(BigDecimal sellprice,BusinessUserInfo businessUser){
		List<DeliveryFeeRules> list =deliveryFeeRulesDAO.getListByBuserId(businessUser.getBuserId());
	    BigDecimal deductAmt = new BigDecimal("0");
		for(int i=0;i<list.size();i++){
			DeliveryFeeRules dr = list.get(i);
			if(sellprice.compareTo(dr.getSinceprice()) >= 0){
				deductAmt = dr.getDeliverfee();
				break;
			}
		}
		return deductAmt;
	}
	
	public BigDecimal calcDeductAmt(BigDecimal sellprice){
		List<DeductAmtRules> list =deductAmtRulesDAO.getList();
	    BigDecimal deductAmt = new BigDecimal("0");
	    Date today = new Date();
		for(int i=0;i<list.size();i++){
			DeductAmtRules dr = list.get(i);
			if(today.compareTo(dr.getBegintime()) >= 0 && today.compareTo(dr.getEndtime()) <= 0){
				if(sellprice.compareTo(dr.getSinceprice()) >= 0){
					deductAmt = dr.getDeductamt();
					break;
				}
			}
		
		}
		return deductAmt;
	}
	
	
	public String queryDeductProd(BigDecimal sellprice){
		List<DeductProd> list = deductProdDAO.getList();
	    String deductProdId = null;
	    Date today = new Date();
		for(int i=0;i<list.size();i++){
			DeductProd dr = list.get(i);
			if(today.compareTo(dr.getBegintime()) >= 0 && today.compareTo(dr.getEndtime()) <= 0){
				if(sellprice.compareTo(dr.getSinceamount()) >= 0){
					deductProdId = dr.getProdid();
					break;
				}
			}
		
		}
		return deductProdId;
	}
	
    private void checkOnecent(int size,int quantity) throws BaseException{
    	
    	if(size > 1){
    		ErrorEnum.valueOf("FAIL_5555").changetMessage("一分购商品不能和其他商品同时购买");
    		throw new BaseException(ErrorEnum.FAIL_5555);
    	}
    	if(quantity >1){
    		ErrorEnum.valueOf("FAIL_5555").changetMessage("一分购商品数量不能大于1");
    		throw new BaseException(ErrorEnum.FAIL_5555);
    	}
    	
    }
    
   private void checkFreshman(UserInfo userInfo,int quantity) throws BaseException{
	    if(!"1".equals(userInfo.getIsfreshman())){
	   		ErrorEnum.valueOf("FAIL_5555").changetMessage("您不是新人,不能购买该新人专享类商品");
	   		throw new BaseException(ErrorEnum.FAIL_5555);
	   	}
	    
    	if(quantity >1){
    		ErrorEnum.valueOf("FAIL_5555").changetMessage("新人专享商品不能大于1");
    		throw new BaseException(ErrorEnum.FAIL_5555);
    	}
    	
    	
    	
    	
    }
    
	public JIASettleCartInfoResponseBean bulidResponseBean(BaseOrderInfo baseOrder,List<JIAOrderDetailBean> retDetailBeanList){
		JIASettleCartInfoResponseBean responseBean = new JIASettleCartInfoResponseBean();
		
		responseBean.setDeliverfee(baseOrder.getDeliveryfee().toString());
		responseBean.setOrdersn(baseOrder.getBaseordersn());
		responseBean.setProdtotalprice(baseOrder.getPrimeprice().toString());
		JIAReceiveAddrBean receiveaddress = new JIAReceiveAddrBean();
		
		YxjBuserShopInfo shopInfo = yxjBuserShopInfoDAO.getShopDetailByuserId(baseOrder.getUserInfo().getUserId());
		if(shopInfo == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("店铺为设置，请联系管理员");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		receiveaddress.setReceiveaddress(shopInfo.getShopAddress());
		receiveaddress.setReceiveid(null);
		receiveaddress.setReceivename(shopInfo.getShopName());
		receiveaddress.setReceivephone(shopInfo.getShopTelephone());
		responseBean.setReceiveaddress(receiveaddress);
		responseBean.setSellprice(baseOrder.getSellprice().toString());
		
		
		//22:00前下单，则返回1 明天 22:00之后下单，则返回2 后天
		Date today = new Date();
		if(today.compareTo(DateUtil.toDate(DateUtil.toStr(today, "yyyy-MM-dd")+" 22:00:00","yyyy-MM-dd HH:mm:ss")) > 0 ){
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextSomeDay(today, 2), "MM-dd"));
		}else{
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextDay(today), "MM-dd"));
		}
		
		responseBean.setDetaillist(retDetailBeanList);
		  
		return responseBean;
	}
	
	
	/*public List<VoucherBean> queryIsUseCoupon(BigDecimal sellprice
			,List<CouponUseInfo> couponUseList){
		List<VoucherBean> resultList = new ArrayList<VoucherBean>();
		if(couponUseList != null && couponUseList.size() > 0){
			for(CouponUseInfo couponUse:couponUseList){
				if(sellprice.compareTo(new BigDecimal(couponUse.getCouponRuleInfo().getSinceMoney())) > 0){

					VoucherBean ticketdetail = new VoucherBean();
					CouponRuleInfo ruleInfo = couponUse.getCouponRuleInfo();
					ticketdetail.setDatebegin(DateUtil.toStr(ruleInfo.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
					ticketdetail.setDateend(DateUtil.toStr(ruleInfo.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
					ticketdetail.setDescrib(ruleInfo.getCouponDesc());
					ticketdetail.setTicketid(couponUse.getCuid());
					ticketdetail.setTicketname(ruleInfo.getCouponName());
					ticketdetail.setValue(couponUse.getCouponAmount().toString());
					
					resultList.add(ticketdetail);
				}
			}
			
		}
		
		return resultList;
		
	}*/
	
	public JIAOrderDetailBean bulidRetDetailBean(OrderDetail orderDetail, ProdInfo prodInfo){
	
			JIAOrderDetailBean retDetailBean = new JIAOrderDetailBean();
			retDetailBean.setImageurl(prodInfo.getImageUrl());
			retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
			retDetailBean.setProdname(orderDetail.getProdname());
			retDetailBean.setProdnum(orderDetail.getProd().getProdid());
			retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
			retDetailBean.setSellflag(prodInfo.getPreSellTag());
			retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(orderDetail.getQuantity())).toString());
			retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
			
         
		return retDetailBean;
	}
	
	

	
	public OrderDetail bulidOrderDetail(YxjBuserCartInfo cartInfo,String orderSn,BigDecimal detailPrice){
		 OrderDetail orderDetail = new OrderDetail();
		 orderDetail.setDetailid(UUIDGenerator.getUUID());
	     orderDetail.setIsreview("0");
	     orderDetail.setOrdersn(orderSn);
	     orderDetail.setProd(cartInfo.getProdInfo());
	     orderDetail.setProdname(cartInfo.getProdName());
	     orderDetail.setQuantity(cartInfo.getProdQuantity());
	     orderDetail.setSellprice(detailPrice);
	     orderDetail.setRedundancefir("N");
	     orderDetail.setSuppilerid(cartInfo.getProdInfo().getSuppilerid());
	     
	     return orderDetail;
	}
	
	/**
	 * 
	 * @param orderSn
	 * @param primeprice
	 * @param sellprice
	 * @param deviceNo
	 * @param userInfo
	 * @param businessUser
	 * @param deductFlag
	 * @param payflag
	 * @param orderDetailList
	 * @return
	 */
	public OrderInfo bulidOrderInfo(String baseOrderSn,String orderSn,BigDecimal primeprice,
			BigDecimal sellprice,String deviceNo,
			UserInfo userInfo,BusinessUserInfo businessUser
			,boolean deductFlag,String payflag
			,List<OrderDetail>  orderDetailList){
		   OrderInfo order = new OrderInfo();
		   order.setCouponflag("0");
		   //给订单所有金额字段 先都默认为0 共8个
		   order.setCouponamt(new BigDecimal("0"));
		   order.setDeductamt(new BigDecimal("0"));
		   order.setFirstorderamt(new BigDecimal("0"));
		   order.setServicefee(new BigDecimal("0"));
		   order.setDeliveryfee(new BigDecimal("0"));
		   order.setSellprice(new BigDecimal("0"));
		   order.setPrimeprice(new BigDecimal("0"));
		   order.setSuccessprice(new BigDecimal("0"));
		   
		   order.setReceiveaddress(null);
		   order.setReceivename(null);
		   order.setReceivetelephone(null);
		   order.setReceivetime(null);
		   order.setRemark(null);
		   order.setPaytime(null);
		   order.setPaytype("1");//默认微信支付
		   order.setPayflag(payflag);
		   BaseOrderInfo baseorder = new BaseOrderInfo();
		   baseorder.setBaseordersn(baseOrderSn);
		   order.setBaseordersn(baseorder);
		   
		   
		   YxjBuserShopInfo shopInfo = yxjBuserShopInfoDAO.getShopDetailByuserId(userInfo.getUserId());
		   if(shopInfo == null){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("店铺未设置，请联系管理员");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
			}
		   order.setReceiveaddress(shopInfo.getShopAddress());
           order.setReceivename(shopInfo.getShopName());
           order.setReceivetelephone(shopInfo.getShopTelephone());
           
           order.setReceiveprovince(shopInfo.getShopProvince());
           order.setReceivecity(shopInfo.getShopCity());
           order.setReceivedistrict(shopInfo.getShopDistrict());
   		
           order.setReceivebuilding(null);
           order.setReceivelatitude(shopInfo.getShopLatitude());
           order.setReceivelongitude(shopInfo.getShopLongitude());
           //计算前置仓
           SubStoreInfo storeInfo = findListInArea(shopInfo.getShopLatitude(),shopInfo.getShopLongitude());
           order.setStore( storeInfo == null?calSubStore(shopInfo.getShopLongitude(),shopInfo.getShopLatitude()):storeInfo);

		   
		   
		   //计算配送 费
		   order.setDeviceno(deviceNo);
		   order.setOrdersn(orderSn);
		   order.setOrderstate(Constants.ORDER_INIT);
		   order.setOrdertime(new Date());
		   order.setUserInfo(userInfo);
		   order.setBusinessUser(businessUser);
			
		   order.setPrimeprice(primeprice);
		   order.setSellprice(sellprice);
		
		 
		    //总优惠金额 = 满减+代金券金额(二者互斥,一方有值,另一方为零)
//			//BigDecimal discountAmt = order.getDeductamt().add(order.getCouponamt());
		   /*
		    * 优惠后计算配送费
		    */
		   order.setDeliveryfee(new BigDecimal(0));
			 
		   order.setSuccessprice(sellprice
					.add(order.getDeliveryfee())
	                .add(order.getServicefee())
	                .subtract(order.getDeductamt())
	                .subtract(order.getFirstorderamt())
	                .subtract(order.getCouponamt()));
			
		   order.setSellprice(sellprice
					.add(order.getDeliveryfee())
	                .add(order.getServicefee())
	                .subtract(order.getDeductamt())
	                .subtract(order.getFirstorderamt())
	                .subtract(order.getCouponamt()));
		   
			
		   order.setIsOnecent("0");
		   order.setInvitecode(null);
		   order.setOrdertype("B");
		   
		   //this.saveALLOrder(order, orderDetailList);
			
			
		   return order;
	}
	
	private SubStoreInfo calSubStore(String latitude,String longitude) throws BaseException{
		List<SubStoreInfo> storeList = subStoreInfoDAO.findList();
		if(storeList == null || storeList.size() <= 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("不存在前置仓");
        	throw new BaseException(ErrorEnum.FAIL_5555);
		}
		SubStoreInfo minStore = storeList.get(0);
		for(int i=1;i<storeList.size();i++){
		    minStore = getMinDistance(latitude,longitude,storeList.get(i),minStore);
		}
		return minStore;
	}
	
	private SubStoreInfo findListInArea(String latitude,String longitude) throws BaseException{
		List<SubStoreInfo> storeList = subStoreInfoDAO.findListInArea(latitude, longitude);
		if(storeList != null && storeList.size() > 0){
			return storeList.get(0);
		}
		return null;
	}
	
	
	private SubStoreInfo getMinDistance(String lng1Str, String lat1Str
			, SubStoreInfo store,SubStoreInfo minStore){
		
		if(CalculateDistance.getDistance(lng1Str, lat1Str
				,store.getLongitude(),store.getLatitude()) 
				<
				CalculateDistance.getDistance(lng1Str, lat1Str
						,minStore.getLongitude(),minStore.getLatitude()) ){
			return store;
		}else{
			return minStore;
		}
	}
	
	private void saveALLOrder(OrderInfo order,List<OrderDetail>  orderDetailList){
		//保存订单
		orderInfoDAO.insert(order);
		//保存订单详细
		for(OrderDetail od:orderDetailList){
			orderDetailDAO.save(od);
		}
	}


}
