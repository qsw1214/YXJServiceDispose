package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.IBaseOrderInfoDAO;
import com.youxianji.dao.ICartInfoDAO;
import com.youxianji.dao.ICouponUseInfoDAO;
import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.dao.IGroupbuyDAO;
import com.youxianji.dao.IGroupbuyDetailDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.dao.IOrderprocessinfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.ISubStoreInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IUserThirdPayDAO;
import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.dao.IYxjUserBarginDetailDAO;
import com.youxianji.dao.IYxjUserBarginInfoDAO;
import com.youxianji.dao.IYxjUserBarginRulesDAO;
import com.youxianji.dao.IYxjUserEmployeeDAO;
import com.youxianji.facade.bargain.bean.BuyOrderNowResponseBean;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.pojo.Groupbuy;
import com.youxianji.pojo.GroupbuyDetail;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.SubStoreInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.UserThirdPay;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserBarginDetail;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IBuyOrderNowService;
import com.youxianji.service.IPayOrderService;
import com.youxianji.service.ISettleCartInfoV2Service;
import com.youxianji.service.orderpay.BasePayOrder;
import com.youxianji.service.orderpay.PayContext;
import com.youxianji.service.orderpay.PayParamsBean;
import com.youxianji.util.CalculateDistance;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Service("buyOrderNowService")
public class BuyOrderNowServiceImpl extends BasePayOrder implements IBuyOrderNowService {
	private Logger logger = Logger.getLogger(BuyOrderNowServiceImpl.class);
	@Resource
	private IDeliveryAddressDAO deliveryAddressDAO;
	@Resource
	private IOrderInfoDAO orderInfoDAO;
	@Resource
	private ICouponUseInfoDAO couponUseInfoDAO;
    @Resource
    private IWeChatUserInfoDAO weChatUserInfoDAO;
    @Resource
    private ICartInfoDAO cartInfoDAO;
    @Resource
    private IUserInfoDAO userInfoDAO;
    @Resource
	private IUserThirdPayDAO userThirdPayDAO;
    @Resource
   	private ISubStoreInfoDAO subStoreInfoDAO;
    @Resource
   	private IOrderprocessinfoDAO orderprocessinfoDAO;
    @Resource
    private IOrderDetailDAO orderDetailDAO;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IProdInfoDAO prodInfoDAO;
    @Resource
    private IYxjUserBarginInfoDAO yxjUserBarginInfoDAO;
    @Resource
    private IYxjUserBarginRulesDAO yxjUserBarginRulesDAO;
    @Resource
    private IYxjUserBarginDetailDAO yxjUserBarginDetailDAO;
    @Resource
    private ISettleCartInfoV2Service settleCartInfoV2Service;
    @Resource
    private IBaseOrderInfoDAO baseOrderInfoDAO;
    @Resource
    private IGroupbuyDetailDAO groupbuyDetailDAO;
	@Resource
	private IYxjUserEmployeeDAO yxjUserEmployeeDAO; 
	@Resource
	private IGroupbuyDAO groupbuyDAO;
	@Resource
	private IPayOrderService payOrderService;
    
	@Override
	public Map<String, String> doBuyOrderNow(
			BuyOrderNowResponseBean responseBean,String prodid, String payType,
			String userId, String addressid, String imei
			, String ip,String os,String deviceNo
			,String bargainid,String groupbuyid
			,String quantity
			,String invitecode)
			throws BaseException {
		
		//获取团购特惠商品
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GroupbuyDetail> groupbuyDetailList = groupbuyDetailDAO.findGroupbuyDetailIsSpecial(groupbuyid, prodid);
		if(groupbuyDetailList.size() > 0){
			Groupbuy dbgroupbuy = groupbuyDAO.findGroupbuyById(groupbuyid);
			//如果是特惠商品，每人只能购买一次，一次购买一个
			
			Map<String,String> queryMap = new HashMap<String,String>();
			queryMap.put("userId", userId);
			queryMap.put("prodId", prodid);
			queryMap.put("beginTime",sdf.format(dbgroupbuy.getBegintime()));
			queryMap.put("endTime",sdf.format(dbgroupbuy.getEndtime()));
			List<OrderDetail> orderDetailList = orderDetailDAO.getOrderDetailByCondition(queryMap);
			if(orderDetailList.size() > 0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("您在活动期间已经购买过该特惠商品，每人限购一次");
	        	throw new BaseException(ErrorEnum.FAIL_5555);
			}
			if(Integer.valueOf(quantity) >= 2){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("此商品为特惠商品，每人每次只能购买一个");
	        	throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}
		 //查询获取收货地址
        DeliveryAddress address = deliveryAddressDAO.getAddressById(addressid);
        if(address == null){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("请选择收货地址");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        
       
       UserInfo userInfo = userInfoDAO.getById(userId);
	   ProdInfo prodInfo = prodInfoDAO.getById(prodid);
	   String orderSn = IdGenerator.instance().generate(os);
	
		
		//查询是否存在该订单号
		if(orderInfoDAO.getByOrderSn(orderSn) != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("订单号重复，请重新结算");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
	    OrderInfo order = bulidOrderInfo(orderSn,deviceNo
	    		,userInfo,prodInfo
	    		,payType,address);
	    
	    //根据不同情况保存相应的价格
	    insertPrice(bargainid, groupbuyid, order, prodInfo,userId,quantity,invitecode);
	    
  		BaseOrderInfo baseOrder = settleCartInfoV2Service.doBulidBaseOrderInfo(IdGenerator.instance().generate(os), order);
  		
  		baseOrder.setPaytype(payType);
  		baseOrder.setOrderstate(Constants.ORDER_NO_PAY);
  		baseOrderInfoDAO.insert(baseOrder);
  		
  		baseOrder.setReceiveaddress(address.getReceiveaddress());
    	baseOrder.setReceivename(address.getReceivename());
    	baseOrder.setReceivephone(address.getTelephone());
    	baseOrder.setReceivecity(address.getCity());
    	baseOrder.setReceivedistrict(address.getDistrict());
    	baseOrder.setReceiveprovince(address.getProvince());
    	
    	baseOrderInfoDAO.update(baseOrder);
    	
  		 //保存订单
  		order.setBaseordersn(baseOrder);
  		orderInfoDAO.insert(order);
  		//保存订单详细
  		orderDetailDAO.save(bulidOrderDetail(prodInfo,orderSn,order.getSellprice(),quantity));
  		
  		
  	   //记录订单流程
	    Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
	    orderprocessinfo.setOrdersn(order.getOrdersn());
	    orderprocessinfo.setProcessdesc("订单提交成功");
	    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
	    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    orderprocessinfo.setProcessremark("订单号:"+order.getOrdersn());
	    orderprocessinfo.setLogisticsstate("1");
	    orderprocessinfoDAO.insert(orderprocessinfo);
	    
	    //返回订单号
	    responseBean.setOrdersn(baseOrder.getBaseordersn());
	    
	    payOrderService.doDisposeStock(order, "C");//减库存
	    
	    Map<String, String> retMap = "3".endsWith(payType)? null:pay(baseOrder, userInfo, imei, ip);
	    		
		return retMap;
	}
	
	
	private void insertPrice(String bargainid,String groupbuyid
			,OrderInfo order
			,ProdInfo prodInfo
			,String userId
			,String quantity
			,String invitecode) throws BaseException{
		  
		  if(!ObjectTools.isNullByString(bargainid)){
	        	 List<YxjUserBarginDetail>  barginDetailList = yxjUserBarginDetailDAO.findBargainDetailByBargainId(bargainid);
	     		if(barginDetailList != null && barginDetailList.size() < 5){
	             	ErrorEnum.valueOf("FAIL_5555").changetMessage("砍价活动需满5人参与，才可下单");
	             	throw new BaseException(ErrorEnum.FAIL_5555);
	             }
	             		
	     		YxjUserBarginInfo yxjUserBarginInfo = yxjUserBarginInfoDAO.findBargainInfoById(bargainid);
	     	    
	     		List<OrderInfo> obarginOrderList = orderInfoDAO.getBarginInfoList(userId);
	     		if(obarginOrderList!= null && obarginOrderList.size()>0){
	     			YxjUserBarginRules rules = yxjUserBarginRulesDAO.findBargainRulesById(yxjUserBarginInfo.getRulesId());
	     			for(OrderInfo oi:obarginOrderList){
	     				
	     				if(oi.getOrdertime().compareTo(rules.getBegintime()) >= 0
	     						&& oi.getOrdertime().compareTo(rules.getEndtime()) <= 0){
	     					ErrorEnum.valueOf("FAIL_5555").changetMessage("您已参加过当前时段砍价活动");
	     		    		throw new BaseException(ErrorEnum.FAIL_5555);
	     				}
	     			}
	     		}
	     		
	     	   order.setBargainamt(yxjUserBarginInfo.getTotalMoney());
	  		   order.setPrimeprice(prodInfo.getValuePrice().subtract(yxjUserBarginInfo.getTotalMoney()));
	  		   order.setSellprice(prodInfo.getValuePrice().subtract(yxjUserBarginInfo.getTotalMoney()));
	  		   order.setSuccessprice(prodInfo.getValuePrice().subtract(yxjUserBarginInfo.getTotalMoney()));

	        }else if(!ObjectTools.isNullByString(groupbuyid)){
	        	List<GroupbuyDetail> gounpbuyDetailList = groupbuyDetailDAO
	        			.findGroupbuyDetailByProdid(groupbuyid, prodInfo.getProdid());
	        	
	            if(gounpbuyDetailList == null || gounpbuyDetailList.size() <= 0
	            		|| gounpbuyDetailList.size() > 1){
	            	ErrorEnum.valueOf("FAIL_5555").changetMessage("团购数量有误");
			    	throw new BaseException(ErrorEnum.FAIL_5555);
	            }
	            GroupbuyDetail groupByDetail = gounpbuyDetailList.get(0);
	            
	            if(ObjectTools.isNullByString(invitecode)){
	            	ErrorEnum.valueOf("FAIL_5555").changetMessage("团购邀请码不能为空");
			    	throw new BaseException(ErrorEnum.FAIL_5555);
	            }
	            
	            if("1".equals(groupByDetail.getSpecialFlag())){
	            	if(!invitecode.equals(groupByDetail.getInvitecode())){
	 	    			ErrorEnum.valueOf("FAIL_5555").changetMessage("请输入正确的特惠邀请码");
	 	    			throw new BaseException(ErrorEnum.FAIL_5555);
	 	    		}
	            }else{
	            	 if(yxjUserEmployeeDAO.getObject(invitecode) == null){
	 	    			ErrorEnum.valueOf("FAIL_5555").changetMessage("请输入正确的团购邀请码");
	 	    			throw new BaseException(ErrorEnum.FAIL_5555);
	 	    		}
	            }
	           
	            BigDecimal sellprice = groupByDetail.getGroupbuyPrice()
	            		.multiply(new BigDecimal(quantity));
	            order.setPrimeprice(sellprice);
		  		order.setSellprice(sellprice);
		  		order.setSuccessprice(sellprice);
		  		order.setInvitecode(invitecode);

	        }
	        
		  
		  
	}
	
	public Map<String,String> pay(BaseOrderInfo baseOrder,UserInfo user,String imei,String ip)
			throws BaseException {
		 Map<String,String>  retMap = new HashMap<String,String>();

		 WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(user.getUserId());
		 //做必要的校验
	      
	     List<OrderInfo> orderList = orderInfoDAO.getListByBaseOrderSn(baseOrder.getBaseordersn());
	        
		 BasePayOrder basePayOrder = new BasePayOrder(cartInfoDAO,orderDetailDAO,activityProdInfoDAO);
		 basePayOrder.doPayNecessaryCheck(wechatUser,baseOrder,orderList, user, null);
		 
		 //记录第三方订单
		 recordBaseOrder(baseOrder, user);
		 
		 //做支付
		 PayParamsBean payParams = new PayParamsBean();
		 payParams.setImei(imei);
		 payParams.setIp(ip);
		 payParams.setOpenid(wechatUser.getOpenid());
		 payParams.setBaseOrder(baseOrder);
		 retMap = new PayContext(baseOrder.getPaytype()).doPay(payParams);
		 
		 return retMap;
	}
 
	private void recordBaseOrder(BaseOrderInfo baseOrder,UserInfo user) throws BaseException{
		 if(userThirdPayDAO.getUserThirdPayByOrderSn(baseOrder.getBaseordersn()) == null && !"3".equals(baseOrder.getPaytype())){
			 UserThirdPay thirdPay = new UserThirdPay();
			 thirdPay.setOrdersn(baseOrder.getBaseordersn());
			 thirdPay.setPayid(UUIDGenerator.getUUID());
			 thirdPay.setPaymoney(baseOrder.getSellprice());
			 thirdPay.setPlatform(baseOrder.getPaytype());
			 thirdPay.setState("1");
			 thirdPay.setUserid(user.getUserId());
			 thirdPay.setPaytype(baseOrder.getOrdertype());
			 userThirdPayDAO.insert(thirdPay);
		 }
	}

	private OrderDetail bulidOrderDetail(ProdInfo prodInfo,String orderSn,BigDecimal detailPrice,String quantity){
		 OrderDetail orderDetail = new OrderDetail();
		 orderDetail.setDetailid(UUIDGenerator.getUUID());
	     orderDetail.setIsreview("0");
	     orderDetail.setOrdersn(orderSn);
	     orderDetail.setProd(prodInfo);
	     orderDetail.setProdname(prodInfo.getProdname());
	     int intquantity = ObjectTools.isNullByString(quantity)?1:Integer.parseInt(quantity);
	     orderDetail.setQuantity(intquantity);
	     orderDetail.setSellprice(detailPrice.divide(new BigDecimal(String.valueOf(intquantity))));
	     orderDetail.setSuppilerid(prodInfo.getSuppilerid());
	     
	     return orderDetail;
	}
	
	private OrderInfo bulidOrderInfo(
			String orderSn,String deviceNo,
			UserInfo userInfo,ProdInfo prodInfo,
			String payType,DeliveryAddress address){
		   OrderInfo order = new OrderInfo();
		   order.setCouponflag("0");
		   order.setCouponamt(new BigDecimal("0"));
		   order.setDeductamt(new BigDecimal("0"));
		   order.setFirstorderamt(new BigDecimal("0"));
		   order.setServicefee(new BigDecimal("0"));
		   order.setDeliveryfee(new BigDecimal("0"));
		   order.setReceiveaddress(null);
		   order.setReceivename(null);
		   order.setReceivetelephone(null);
		   order.setReceivetime(null);
		   order.setRemark(null);
		   order.setPaytime(null);
		   order.setPayflag("1");
		   order.setIsOnecent("0");
		   order.setPaytype(payType);
		   order.setCargocode("");
		   order.setInvitecode(null);
		   order.setOrdertype("C");
		   
		   order.setSourcetype(prodInfo.getStockflag());
		   
		   order.setBargainamt(new BigDecimal("0"));
		   order.setPrimeprice(new BigDecimal("0"));
		   order.setSellprice(new BigDecimal("0"));
		   order.setSuccessprice(new BigDecimal("0"));
		   
    	   order.setReceiveaddress(address.getReceiveaddress());
           order.setReceivename(address.getReceivename());
           order.setReceivetelephone(address.getTelephone());
           
           order.setReceivecity(address.getCity());
           order.setReceivedistrict(address.getDistrict());
           order.setReceiveprovince(address.getProvince());
       	
           order.setReceivebuilding(address.getBuildingname());
           order.setReceivelatitude(address.getLatitude());
           order.setReceivelongitude(address.getLongitude());
           
           
           //计算前置仓
           SubStoreInfo storeInfo = findListInArea(address.getLatitude(),address.getLongitude());
           order.setStore( storeInfo == null?calSubStore(address.getLongitude(),address.getLatitude()):storeInfo);
           
           int cargocode = (int)(Math.random()*(9999-1000+1))+1000;
           order.setCargocode(String.valueOf(cargocode));
		   
		   //计算配送 费
		   order.setDeviceno(deviceNo);
		   order.setOrdersn(orderSn);
		   order.setOrderstate(Constants.ORDER_NO_PAY);
		   order.setOrdertime(new Date());
		
		   order.setUserInfo(userInfo);
		   order.setBusinessUser(prodInfo.getBusinessUser());
		   
		   order.setSuppilerid(prodInfo.getSuppilerid());
		   
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
	
}
