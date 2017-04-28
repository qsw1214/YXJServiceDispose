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
import com.youxianji.dao.ICartInfoDAO;
import com.youxianji.dao.ICouponUseInfoDAO;
import com.youxianji.dao.IDeductAmtRulesDAO;
import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.dao.IDeliveryFeeRulesDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IWmsBizzDeptSupplyerDAO;
import com.youxianji.facade.trade.bean.SettleCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartDetailBean;
import com.youxianji.facade.trade.bean.json.OrderDetailBean;
import com.youxianji.facade.trade.bean.json.ReceiveAddrBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.CartInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.DeductAmtRules;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.pojo.DeliveryFeeRules;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ISettleCartInfoV2Service;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Service("settleCartInfoV2Service")
public class SettleCartInfoV2ServiceImpl implements ISettleCartInfoV2Service {
	Logger logger = Logger.getLogger(SettleCartInfoV2ServiceImpl.class);
    @Resource
    private IUserInfoDAO userInfoDAO;
    @Resource
    private ICartInfoDAO cartInfoDAO;
    @Resource
    private IOrderInfoDAO orderInfoDAO;
    @Resource 
    private IOrderDetailDAO orderDetailDAO;
    @Resource
    private ICouponUseInfoDAO couponUseInfoDAO;
    @Resource
    private IDeliveryAddressDAO deliveryAddressDAO;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IDeliveryFeeRulesDAO deliveryFeeRulesDAO;
    @Resource
    private IProdInfoDAO prodInfoDAO;
    @Resource
    private IBaseOrderInfoDAO baseOrderInfoDAO;
    @Resource
    private IWmsBizzDeptSupplyerDAO wmsBizzDeptSupplyerDAO;
    @Resource 
    private IDeductAmtRulesDAO deductAmtRulesDAO;
	
	@Override
	public SettleCartInfoResponseBean doSettleCartInfo(String userId,String os
			,String deviceNo,List<CartDetailBean> detailList,String invitecode)
			throws BaseException {
        //这里需要加一些校验
		UserInfo userInfo = userInfoDAO.getById(userId);
		String baseOrderSn = IdGenerator.instance().generate(os);
		
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		
		//查询是否存在该订单号
		if(orderInfoDAO.getByOrderSn(baseOrderSn) != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("订单号重复，请重新结算");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		Map<String,List<CartInfo>> suppilerCartInfoMap = buildSplitOrderMap(detailList);
		Map<String,List<OrderDetail>> detailMap = new HashMap<>();
		BigDecimal normalProdAmt = new BigDecimal("0");
		for(Map.Entry<String, List<CartInfo>> entry : suppilerCartInfoMap.entrySet()){
			BusinessUserInfo businessUser = null; 
			List<OrderDetail>  orderDetailList = new ArrayList<OrderDetail>();
			String payflag = null;
			boolean deductFlag = false;
			BigDecimal primeprice = new BigDecimal("0");
			BigDecimal sellprice = new BigDecimal("0");
			BigDecimal detailPrice = new BigDecimal("0");
			BigDecimal orderWeigth = new BigDecimal("0");
			BigDecimal sumdeductAmt = new BigDecimal("0");
			String orderSn = IdGenerator.instance().generate(os);
			String sourcetype = null;
			for(CartInfo cartInfo:entry.getValue()){
				
			    ProdInfo prodInfo = cartInfo.getProdInfo();
                if(sourcetype == null){
                	sourcetype = prodInfo.getStockflag();
                }
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
	            
	        	DeductAmtRules deductAmtRules = deductAmtRulesDAO.getByProdId(
		    			prodInfo.getProdid(),String.valueOf(cartInfo.getProdQuantity()));
	        	
			    if(deductAmtRules != null){
			    	deductFlag = true;
			    	sumdeductAmt = sumdeductAmt.add(deductAmtRules.getDeductmember());
			    }
			    
			    orderWeigth = orderWeigth.add(new BigDecimal(prodInfo.getWeight()).multiply(new BigDecimal(cartInfo.getProdQuantity())));
			    
			    ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
			    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
			    	
			    	//如果是活动商品
					checkActivityProd(activeInfo, prodInfo
					    		 , cartInfo.getProdQuantity());
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
				     
				     //使用代金券时,去掉满减商品
				     if(!deductFlag){
				    	 normalProdAmt = normalProdAmt.add(prodInfo.getSellPrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
				     }
				    
			    }
			    
			    //创建订单详细
			    OrderDetail orderDetail = bulidOrderDetail(
			    		cartInfo,orderSn,detailPrice);
			    
			    orderDetailList.add(orderDetail);

			    //构建返回详细信息
			   retDetailBeanList.add(bulidRetDetailBean(orderDetail,prodInfo));
			}
			
			OrderInfo ordeInfo = bulidOrderInfo(baseOrderSn,orderSn,primeprice,sellprice
					,deviceNo,userInfo,businessUser
					,deductFlag,payflag,orderDetailList
					,sourcetype,orderWeigth,sumdeductAmt);
			
			if(!"OWNSTOCK".equals(entry.getKey())){
				ordeInfo.setSuppilerid(entry.getKey());
			}
					
			orderInfoList.add(ordeInfo);
			detailMap.put(orderSn, orderDetailList);
		    
		}
		
	
		BaseOrderInfo baseOrder = bulidBaseOrderInfo(baseOrderSn,orderInfoList);
		
		
		baseOrderInfoDAO.insert(baseOrder);
		
		for(OrderInfo order:orderInfoList){
			this.saveALLOrder(order, detailMap.get(order.getOrdersn()));
		}
		
		
		List<VoucherBean> voucherList = getReturnVoucherBean(userId, normalProdAmt, orderInfoList);
	    
		return bulidResponseBean(baseOrder,voucherList,retDetailBeanList);
	}
	
	
	 private void checkActivityProd(ActivityProdInfo activeInfo
	    		,ProdInfo prodInfo,int prodQuantity) throws BaseException{
	    	if(activeInfo == null){
	 	    	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"活动产品状态不正确");
	     		throw new BaseException(ErrorEnum.FAIL_5555);
	 	    }
		    if(prodQuantity > activeInfo.getLimitcount()){
		    	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"不能购买多个活动商品");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
		    }
	    	
	    }
	
	private Map<String,List<CartInfo>> buildSplitOrderMap(List<CartDetailBean> detailList){
		Map<String,List<CartInfo>> suppilerCartInfoMap = new HashMap<>();
		List<CartInfo> cartInfoList = null;
		for(CartDetailBean cartBean:detailList){
			CartInfo cartInfo = cartInfoDAO.getById(cartBean.getCartid());
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
	
	public BigDecimal calcDeliverFee(BigDecimal sellprice,BusinessUserInfo businessUser,BigDecimal orderWeigth){
		List<DeliveryFeeRules> list =deliveryFeeRulesDAO.getListByBuserId(businessUser.getBuserId());
	    BigDecimal deductAmt = new BigDecimal("0");
		
	    for(int i=0;i<list.size();i++){
			DeliveryFeeRules dr = list.get(i);
			
			
				if(sellprice.compareTo(dr.getSinceprice()) >= 0){
					if(dr.getLimitweight().compareTo(new BigDecimal("0")) >= 0
							&& orderWeigth.compareTo(dr.getLimitweight().multiply(new BigDecimal("1000"))) > 0){
					    deductAmt = dr.getDeliverfee();
					    
					}else if(dr.getLimitweight().compareTo(new BigDecimal("0")) < 0){
					    deductAmt = dr.getDeliverfee();
					}
					
					 break;
				}	
				
			
			
			
		}
		return deductAmt;
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
    
	public SettleCartInfoResponseBean bulidResponseBean(BaseOrderInfo baseOrder
			,List<VoucherBean> voucherList,List<OrderDetailBean> retDetailBeanList){
		SettleCartInfoResponseBean responseBean = new SettleCartInfoResponseBean();
		
		responseBean.setDeliverfee(baseOrder.getDeliveryfee().toString());
		responseBean.setDiscountamt(baseOrder.getDeductamt().toString());
		responseBean.setOrdersn(baseOrder.getBaseordersn());
		ReceiveAddrBean receiveaddress = new ReceiveAddrBean();
		List<DeliveryAddress> delAddressList =  
				deliveryAddressDAO.getAddressListByUserId(baseOrder.getUserInfo().getUserId());
		if(delAddressList != null){
			for(DeliveryAddress addr:delAddressList){
				if("1".equals(addr.getIsdefault())){
					receiveaddress.setReceiveaddress(addr.getReceiveaddress());
					receiveaddress.setReceiveid(addr.getDaid());
					receiveaddress.setReceivename(addr.getReceivename());
					receiveaddress.setReceivephone(addr.getTelephone());
					receiveaddress.setReceiveprovince(addr.getProvince());
					receiveaddress.setReceivecity(addr.getCity());
					receiveaddress.setReceivedistrict(addr.getDistrict());
					responseBean.setReceiveaddress(receiveaddress);
					break;
				}
			}
		}
		
		responseBean.setSellprice(baseOrder.getSellprice().toString());
		
		//22:00前下单，则返回1 明天 22:00之后下单，则返回2 后天
		Date today = new Date();
		if(today.compareTo(DateUtil.toDate(DateUtil.toStr(today, "yyyy-MM-dd")+" 22:00:00","yyyy-MM-dd HH:mm:ss")) > 0 ){
			responseBean.setSendday("2");
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextSomeDay(today, 2), "MM-dd"));
		}else{
			responseBean.setSendday("1");
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextDay(today), "MM-dd"));
		}
		
		responseBean.setDetaillist(retDetailBeanList);
		
		if(voucherList != null && voucherList.size() > 0){
			responseBean.setTicketList(voucherList);
	
			responseBean.setTicketdetail(voucherList.get(0));
		}
		
		  
		return responseBean;
	}
	
	
	public List<VoucherBean> getReturnVoucherBean(String userId
			,BigDecimal normalProdAmt,List<OrderInfo> orderInfoList){
		
		List<VoucherBean> ticketList = new ArrayList<>();
		VoucherBean voucherBean = null;
		List<CouponUseInfo> couponUseList = couponUseInfoDAO.getMatchUsefulCouponList(
				userId, normalProdAmt);
		//查找是否有符合条件的红包
		boolean flag = false;
		CouponUseInfo couponUse = null;
		
		for(CouponUseInfo cu:couponUseList){
			//分单金额必须大于等于红包金额
			for(OrderInfo order:orderInfoList){
				if(order.getSellprice().compareTo(cu.getCouponAmount()) >= 0){
					couponUse = cu;
					flag = true;
				}
			}
		
			if(flag){
				voucherBean = new VoucherBean();
				voucherBean.setDatebegin(DateUtil.toStr(couponUse.getUseBeginTime(), "yyyy-MM-dd"));
				voucherBean.setDateend(DateUtil.toStr(couponUse.getUseEndTime(), "yyyy-MM-dd"));
				voucherBean.setDescrib(couponUse.getCouponRuleInfo().getCouponDesc());
				voucherBean.setTicketid(couponUse.getCuid());
				voucherBean.setTickettitle(couponUse.getCoupontitle());
				voucherBean.setValue(couponUse.getCouponAmount().toString());
				
				ticketList.add(voucherBean);
			}
		}
		
		
		
		return ticketList;
	}
	

	
	public OrderDetailBean bulidRetDetailBean(OrderDetail  orderDetail,ProdInfo prodInfo){
	
	    OrderDetailBean retDetailBean = new OrderDetailBean();
		retDetailBean.setImageurl(prodInfo.getImageUrl());
		retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
		retDetailBean.setProdname(orderDetail.getProdname());
		retDetailBean.setProdnum(orderDetail.getProd().getProdid());
		retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
		retDetailBean.setSellflag(prodInfo.getPreSellTag());
		retDetailBean.setSellprice(orderDetail.getSellprice()
				.multiply(new BigDecimal(orderDetail.getQuantity())).toString());
		retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
		
         
		return retDetailBean;
	}
	
	
	public OrderDetail bulidOrderDetail(CartInfo cartInfo,String orderSn,BigDecimal detailPrice){
		 OrderDetail orderDetail = new OrderDetail();
		 orderDetail.setDetailid(UUIDGenerator.getUUID());
	     orderDetail.setIsreview("0");
	     orderDetail.setOrdersn(orderSn);
	     orderDetail.setProd(cartInfo.getProdInfo());
	     orderDetail.setProdname(cartInfo.getProdInfo().getProdname());
	     orderDetail.setQuantity(cartInfo.getProdQuantity());
	     orderDetail.setSellprice(detailPrice);
	     orderDetail.setRedundancefir("N");
	     orderDetail.setSuppilerid(cartInfo.getProdInfo().getSuppilerid());
	     
	     return orderDetail;
	}
	
	@Override
	public BaseOrderInfo doBulidBaseOrderInfo(String baseordersn,OrderInfo orderInfo){
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		orderInfoList.add(orderInfo);
		return bulidBaseOrderInfo(baseordersn, orderInfoList);
	}
	
	private BaseOrderInfo bulidBaseOrderInfo(String baseordersn,List<OrderInfo> orderInfoList){
		
		   BaseOrderInfo baseOrder = new BaseOrderInfo();

		   baseOrder.setDeliveryfee(new BigDecimal("0"));
		   baseOrder.setSellprice(new BigDecimal("0"));
		   baseOrder.setPrimeprice(new BigDecimal("0"));
		   baseOrder.setDeductamt(new BigDecimal("0"));
		   baseOrder.setReceivetime(null);
		   baseOrder.setRemark(null);
		   baseOrder.setPaytime(null);
		   baseOrder.setPaytype("3");//默认余额支付
		   
		   //计算配送 费
		   baseOrder.setBaseordersn(baseordersn);
		   baseOrder.setOrderstate(Constants.ORDER_INIT);
		   baseOrder.setOrdertime(new Date());
		   
		   baseOrder.setPrimeprice(new BigDecimal("0"));
		   baseOrder.setSellprice(new BigDecimal("0"));
		   baseOrder.setDeliveryfee(new BigDecimal("0"));
		   baseOrder.setBargainamt(new BigDecimal("0"));
		   String orderType = null;
		   for(OrderInfo order:orderInfoList){
			   if(baseOrder.getUserInfo() == null)
				   baseOrder.setUserInfo(order.getUserInfo());

			   if(baseOrder.getBusinessUser() == null)
				   baseOrder.setBusinessUser(order.getBusinessUser());
			   
			   if(baseOrder.getDeviceno() == null)
				   baseOrder.setDeviceno(order.getDeviceno());
			   
			   if(orderType == null){
				   orderType = order.getOrdertype();
			   }
			   
			   baseOrder.setPrimeprice(
					   baseOrder.getPrimeprice().add(order.getPrimeprice()));
			   baseOrder.setSellprice(
					   baseOrder.getSellprice().add(order.getSellprice()));
			   baseOrder.setDeliveryfee(
					   baseOrder.getDeliveryfee().add(order.getDeliveryfee()));
			   baseOrder.setDeductamt(
					   baseOrder.getDeductamt().add(order.getDeductamt()));
			   
		   }
		   
		   baseOrder.setOrdertype(orderType);
		  
			
		   return baseOrder;
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
	public OrderInfo bulidOrderInfo(String baseordersn,String orderSn,BigDecimal primeprice,
			BigDecimal sellprice,String deviceNo,
			UserInfo userInfo,BusinessUserInfo businessUser
			,boolean deductFlag,String payflag
			,List<OrderDetail>  orderDetailList
			,String sourcetype
			,BigDecimal orderWeigth
			,BigDecimal deductAmt){
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
		   order.setPaytype("3");//默认余额支付
		   order.setPayflag(payflag);
		   order.setOrdertype("C");
		   order.setSourcetype(sourcetype);
		   
		   
		   BaseOrderInfo baseorder = new BaseOrderInfo();
		   baseorder.setBaseordersn(baseordersn);
		   order.setBaseordersn(baseorder);
		   
		   //计算配送 费
		   order.setDeviceno(deviceNo);
		   order.setOrdersn(orderSn);
		   order.setOrderstate(Constants.ORDER_INIT);
		   order.setOrdertime(new Date());
		   order.setUserInfo(userInfo);
		   order.setBusinessUser(businessUser);
			
		   order.setPrimeprice(primeprice);
		   order.setSellprice(sellprice);
		   order.setDeductamt(deductAmt);//满减金额
		   
		   
		   if("1".equals(sourcetype)){
			   order.setDeliveryfee(
						calcDeliverFee(sellprice.subtract(deductAmt)
								,order.getBusinessUser(),orderWeigth));
		   }
		  
			 
		   order.setSuccessprice(sellprice
					.add(order.getDeliveryfee()));
			
		   order.setSellprice(sellprice
					.add(order.getDeliveryfee()) // + 配送费
					.subtract(order.getDeductamt())); // - 满减金额
		   
			
		   order.setIsOnecent("0");
		   order.setInvitecode(null);
		   
		   return order;
	}
	
	private void saveALLOrder(OrderInfo order,List<OrderDetail> orderDetailList){
		//保存订单
		orderInfoDAO.insert(order);
		//保存订单详细
		for(OrderDetail od:orderDetailList){
			orderDetailDAO.save(od);
		}
	}

	@Override
	public BaseOrderInfo doBulidBaseOrderInfo(String baseordersn,
			List<OrderInfo> orderInfoList) {
		return bulidBaseOrderInfo(baseordersn, orderInfoList);
	}


}
