package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.util.ObjectTools;
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
import com.youxianji.dao.IOrderprocessinfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.ISubStoreInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IUserThirdPayDAO;
import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.dao.IYxjBuserCartInfoDAO;
import com.youxianji.facade.trade.bean.CommitOrderRequestBean;
import com.youxianji.facade.trade.bean.SwitchPayTypeRequestBean;
import com.youxianji.facade.trade.bean.SwitchPayTypeResponseBean;
import com.youxianji.facade.trade.bean.json.OrderDetailBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.DeductAmtRules;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.SubStoreInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.UserThirdPay;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.service.IPayOrderService;
import com.youxianji.service.ISettleCartInfoV2Service;
import com.youxianji.service.IYxjUserCouponGrantConfigService;
import com.youxianji.service.orderpay.BasePayOrder;
import com.youxianji.service.orderpay.PayContext;
import com.youxianji.service.orderpay.PayParamsBean;
import com.youxianji.util.CalculateDistance;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.util.OrderSendTicketThread;
import com.youxianji.util.ProdInfoLockManager;
import com.youxianji.web.util.ErrorEnum;

@Service("payOrderService")
public class PayOrderServiceImpl implements IPayOrderService {
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
    private IProdInfoDAO prodInfoDAO;
    @Resource
    private IDeliveryFeeRulesDAO deliveryFeeRulesDAO;
    @Resource
    private IDeductAmtRulesDAO deductAmtRulesDAO;
    @Resource 
    private ISettleCartInfoV2Service settleCartInfoV2Service;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IYxjBuserCartInfoDAO yxjBuserCardInfoDAO;
    @Resource
    private IBaseOrderInfoDAO baseOrderInfoDAO;
    @Resource
    private IYxjUserCouponGrantConfigService yxjUserCouponGrantConfigService;

	@Override
	public void doCommitOrder(CommitOrderRequestBean requestBean)
			throws BaseException {
		String baseOrderSn = requestBean.getOrdersn();
        String addressId = requestBean.getAddressid();
        String orderRemark = requestBean.getOrderremark();
        String distributionInterval = requestBean.getDistributiontime();
        String couponid = requestBean.getCouponid();
        
       CouponUseInfo couponUseInfo = null;
       //BigDecimal couponSinceAmt = new BigDecimal("0");
       BigDecimal couponAmt = new BigDecimal("0");
       if(!ObjectTools.isNullByString(couponid)){
    	   Date currTime = new Date();
    	   couponUseInfo = couponUseInfoDAO.getUsedCoupon(couponid);
    	   if(couponUseInfo == null){
        	   ErrorEnum.valueOf("FAIL_5555").changetMessage("该代金券不可使用");
               throw new BaseException(ErrorEnum.FAIL_5555);
           }
    	   if(currTime.compareTo(couponUseInfo.getUseBeginTime()) < 0
    			   && currTime.compareTo(couponUseInfo.getUseEndTime()) > 0){
        	   ErrorEnum.valueOf("FAIL_5555").changetMessage("该代金券不在有效期内");
               throw new BaseException(ErrorEnum.FAIL_5555);
           }
    	   //couponSinceAmt = couponUseInfo.getSinceMoney();
    	   couponAmt = couponUseInfo.getCouponAmount();
       }
       
        UserInfo user = userInfoDAO.getById(requestBean.getUserid());
        
        
        /*if(ObjectTools.isNullByString(distributionInterval)){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("请选择配送时间段");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }*/
        DeliveryAddress address = deliveryAddressDAO.getAddressById(addressId);
        
        if(address == null && !"8004".equals(requestBean.getPublicBean().getCommand())){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("配送商品请选择收货地址");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        
        BaseOrderInfo baseOrder = baseOrderInfoDAO.getByBaseOrderSn(baseOrderSn);
        //计算实际支付金额
        baseOrder.setSellprice(baseOrder.getSellprice().subtract(couponAmt));
        baseOrder.setDeductamt(baseOrder.getDeductamt().add(couponAmt));
        if(Constants.ORDER_NO_PAY.equals(baseOrder.getOrderstate())){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("订单重复提交");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        baseOrder.setDistributionInterval(distributionInterval);
        baseOrder.setPaytype(requestBean.getPaytype());
        if(address != null){
        	baseOrder.setReceiveaddress(address.getReceiveaddress());
        	baseOrder.setReceivename(address.getReceivename());
        	baseOrder.setReceivephone(address.getTelephone());
        	baseOrder.setReceivecity(address.getCity());
        	baseOrder.setReceivedistrict(address.getDistrict());
        	baseOrder.setReceiveprovince(address.getProvince());
        }
        baseOrder.setRemark(orderRemark);
        baseOrder.setOrderstate(Constants.ORDER_NO_PAY);
        baseOrderInfoDAO.update(baseOrder);
        
        
        List<OrderInfo> orderList = orderInfoDAO.getListByBaseOrderSn(baseOrderSn);
        
        WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(requestBean.getUserid());
        BasePayOrder basePayOrder = new BasePayOrder(cartInfoDAO,orderDetailDAO,activityProdInfoDAO);
        basePayOrder.doCommitNecessaryCheck(wechatUser, baseOrder, orderList, user);
       
        //查询获取订单
        boolean couponFlag = false;
        for(OrderInfo order:orderList){
            order.setDistributionInterval(distributionInterval);
        	if(couponUseInfo != null && !couponFlag && order.getSellprice().compareTo(couponAmt)>0){
            	order.setSellprice(order.getSellprice().subtract(couponAmt));
            	order.setCouponamt(couponAmt);
            	order.setCouponflag("1");
            	
            	couponUseInfo.setOrdersn(order.getOrdersn());
            	couponUseInfo.setState("2");
            	couponUseInfoDAO.update(couponUseInfo);
            	couponFlag = true;
            }
            if(address != null){
            	 order.setReceiveaddress(address.getReceiveaddress());
                 order.setReceivename(address.getReceivename());
                 order.setReceivetelephone(address.getTelephone());
                 order.setReceivebuilding(address.getBuildingname());
                 order.setReceivelatitude(address.getLatitude());
                 order.setReceivelongitude(address.getLongitude());
                 order.setReceivecity(address.getCity());
                 order.setReceivedistrict(address.getDistrict());
                 order.setReceiveprovince(address.getProvince());
                 //计算前置仓
                 SubStoreInfo storeInfo = findListInArea(address.getLatitude(),address.getLongitude());
                 order.setStore( storeInfo == null?calSubStore(address.getLongitude(),address.getLatitude()):storeInfo);
            }
           
            order.setRemark(orderRemark);
            order.setOrderstate(Constants.ORDER_NO_PAY);
        	int cargocode = (int)(Math.random()*(9999-1000+1))+1000;
            order.setCargocode(String.valueOf(cargocode));
            order.setPaytype(requestBean.getPaytype());
            
            
            if(order.getSellprice().compareTo(new BigDecimal("0")) <= 0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage(order.getOrdersn()+"分单金额必须大于0");
				throw new BaseException(ErrorEnum.FAIL_5555);
			 }
            
            orderInfoDAO.update(order);
            
            this.doDisposeStock(order,requestBean.getChannel());
            
            //记录订单流程
    	    Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
    	    orderprocessinfo.setOrdersn(order.getOrdersn());
    	    orderprocessinfo.setProcessdesc("订单提交成功");
    	    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
    	    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
    	    orderprocessinfo.setProcessremark("订单号:"+order.getOrdersn());
    	    orderprocessinfo.setLogisticsstate("0");
    	    orderprocessinfoDAO.insert(orderprocessinfo);
    	    
        }
        if(couponUseInfo != null && !couponFlag){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("分单的金额，不符合红包使用条件");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        
	}
	
	
	public void doDisposeStock(OrderInfo order,String channel) throws BaseException{
		 List<OrderDetail> orderDetailList = orderDetailDAO.getDetailListByOrderSn(order.getOrdersn());
	     for(OrderDetail od:orderDetailList){
	    	 
	    	ProdInfoLockManager prodInfoLockManager = ProdInfoLockManager.getInstance();
	 		Lock lock = prodInfoLockManager.getProdInfoLock(od.getProd().getProdid());
	 		lock.lock();
	 		try{
		    	 //判断库存
		    	 if(od.getProd().getProdstock() != null && od.getProd().getProdstock() != -9999 ){
		    		 if(od.getProd().getProdstock() < od.getQuantity()){
			    		 ErrorEnum.valueOf("FAIL_5555").changetMessage("商品"+od.getProd().getProdname()+"库存不足");
						 throw new BaseException(ErrorEnum.FAIL_5555);
			    	 }
		    		 
		    		 //更新商品库存
			    	 prodInfoDAO.updateSubStock(od.getProd().getProdid(), String.valueOf(od.getQuantity()));
		    	 }
	 			
	 		}finally{
	 			lock.unlock();
	 		}
		 	
	 		if("B".equals(channel)){
	 			//清除优鲜季商家端购物车
	 			yxjBuserCardInfoDAO.deleteByProdId(od.getProd().getProdid(),order.getUserInfo().getUserId());
	 		}else{
		 		//清除优鲜季商城购物车
		 	    cartInfoDAO.deleteByProdId(od.getProd().getProdid(),order.getUserInfo().getUserId());
	 		}
	    	 
	    	 
	     }
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

	@Override
	public Map<String,String> doPayOrder(String baseOrderSn,String payType
			,String userId,String payPass,String imei,String ip)
			throws BaseException {
		 Map<String,String>  retMap = new HashMap<String,String>();
		
		 WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(userId);
		 BaseOrderInfo baseOrder = baseOrderInfoDAO.getByBaseOrderSn(baseOrderSn);
		 UserInfo user = userInfoDAO.getById(userId);
		 
		 List<OrderInfo> orderList = orderInfoDAO.getListByBaseOrderSn(baseOrder.getBaseordersn());
		 //做必要的校验
		 BasePayOrder basePayOrder = new BasePayOrder(cartInfoDAO,orderDetailDAO,activityProdInfoDAO);
		 basePayOrder.doPayNecessaryCheck(wechatUser,baseOrder,orderList, user, payPass);
		 
		 baseOrder.setPaytype(payType);
		 //记录第三方订单
		 recordBaseOrder(baseOrder, user);
		 
		 //做支付
		 PayParamsBean payParams = new PayParamsBean();
		 payParams.setImei(imei);
		 payParams.setIp(ip);
		 if(wechatUser != null){
			 payParams.setOpenid(wechatUser.getOpenid());
		 }
		 payParams.setBaseOrder(baseOrder);
		 payParams.setPayPass(payPass);
		 
		 retMap = new PayContext(baseOrder.getPaytype()).doPay(payParams);
		 
		 updateOrder(baseOrder,orderList, user);
		 
		 return retMap;
	}

	private void updateOrder(BaseOrderInfo baseOrder,List<OrderInfo> orderList,UserInfo user){
		
		Date currentTime = new Date();
		if("3".equals(baseOrder.getPaytype())){
			 baseOrder.setPaytime(currentTime);
			 baseOrder.setOrderstate(Constants.ORDER_YES_PAY);
			 baseOrderInfoDAO.update(baseOrder);
			 
			 for(OrderInfo order:orderList){
				 order.setPaytime(currentTime);
				 order.setOrderstate(Constants.ORDER_YES_PAY);
				 orderInfoDAO.update(order);
				
				 //如果是一份购订单
				 if("1".equals(order.getIsOnecent()) && "0".equals(user.getIsOnecent())){
					user.setIsOnecent("1");
					userInfoDAO.updateUser(user);
				 }else if("1".equals(user.getIsfreshman())){
					user.setIsfreshman("0");
					userInfoDAO.updateUser(user);
				 }
				 
				 //更改代金券使用状态
				 if("1".equals(order.getCouponflag())
						 && order.getCouponamt().doubleValue() > 0){
						couponUseInfoDAO.updateFinished(user.getUserId(), order.getOrdersn()); 
				 }
            
				 
				Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
			    orderprocessinfo.setOrdersn(order.getOrdersn());
			    orderprocessinfo.setProcessdesc("订单支付成功");
			    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
			    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			    orderprocessinfo.setLogisticsstate("1");
			    orderprocessinfoDAO.insert(orderprocessinfo);
			    
			 }
			 
			 //发放代金券
		    new OrderSendTicketThread(
		    		yxjUserCouponGrantConfigService
		    		, user.getUserId()
		    		, baseOrder.getBaseordersn())
		    .start();
			
		}else{
			baseOrderInfoDAO.update(baseOrder);
		}
		 
		 
		 
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
	
	

	@Override
	public SwitchPayTypeResponseBean doSwitchPayType(
			SwitchPayTypeRequestBean requestBean) throws BaseException {
		SwitchPayTypeResponseBean responseBean = new SwitchPayTypeResponseBean();
		
		BaseOrderInfo baseOrder = baseOrderInfoDAO.getByBaseOrderSn(requestBean.getOrdersn());
		baseOrder.setPrimeprice(new BigDecimal("0"));
		baseOrder.setDeliveryfee(new BigDecimal("0"));
		baseOrder.setSellprice(new BigDecimal("0"));
		baseOrder.setDeductamt(new BigDecimal("0"));
		baseOrder.setPaytype(requestBean.getPaytype());
		
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
		
		List<OrderInfo> orderInfoList = orderInfoDAO.getListByBaseOrderSn(requestBean.getOrdersn());
		BigDecimal normalProdAmt = new BigDecimal("0");
		for(OrderInfo order:orderInfoList){
			//给订单所有金额字段 先都默认为0 共8个
		    order.setPrimeprice(new BigDecimal("0"));
		    order.setDeliveryfee(new BigDecimal("0"));
		    order.setSellprice(new BigDecimal("0"));
		    order.setSuccessprice(new BigDecimal("0"));
		    order.setPaytype(requestBean.getPaytype());
			   
			List<OrderDetail> orderDetailList = 
					orderDetailDAO.getDetailListByOrderSn(order.getOrdersn());
			
			BigDecimal actuPrice = new BigDecimal("0");//实际销售总金额
			BigDecimal detailPrice = new BigDecimal("0");
			BigDecimal primeprice = new BigDecimal("0");//实际商品总金额
			BigDecimal orderWeigth = new BigDecimal("0");
			BigDecimal sumdeductAmt = new BigDecimal("0");
			
			for(OrderDetail od:orderDetailList){
				ProdInfo prodInfo = od.getProd();
				int buyQuantity = od.getQuantity();
				orderWeigth = orderWeigth.add(new BigDecimal(prodInfo.getWeight()).multiply(new BigDecimal(buyQuantity)));
				
				if(!"3".equals(requestBean.getPaytype())){
					if(od.getSellprice().doubleValue()>0){
						DeductAmtRules deductAmtRules = deductAmtRulesDAO.getByProdId(
				    			prodInfo.getProdid(),String.valueOf(od.getQuantity()));
				    	
				    	if(deductAmtRules != null){
				    		sumdeductAmt = sumdeductAmt.add(deductAmtRules.getDeductamt());
				    	}
						
						ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
						if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
					    	 //如果是活动商品
						    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
						    actuPrice = actuPrice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
						    detailPrice = activeInfo.getActivePrice();
					    }else if("1".equals(prodInfo.getIsfreshman())){
					    	actuPrice = actuPrice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
					    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
					    	detailPrice = prodInfo.getFreshmanprice();
					    }else{
					    	actuPrice = actuPrice.add(prodInfo.getValuePrice().multiply(new BigDecimal(buyQuantity)));
					    	primeprice = primeprice.add(prodInfo.getValuePrice().multiply(new BigDecimal(buyQuantity)));
					    	detailPrice = prodInfo.getValuePrice();
					    	if(deductAmtRules == null){
					    	     normalProdAmt = normalProdAmt.add(prodInfo.getValuePrice().multiply(new BigDecimal(buyQuantity)));
					    	}
					    	od.setSellprice(detailPrice);
		                    orderDetailDAO.doSwitchOrderDetailPrice(od);
					    }
					}
					
				}else{
					if(od.getSellprice().doubleValue()>0){
						DeductAmtRules deductAmtRules = deductAmtRulesDAO.getByProdId(
				    			prodInfo.getProdid(),String.valueOf(od.getQuantity()));
					    	
				    	if(deductAmtRules != null){
				    		sumdeductAmt = sumdeductAmt.add(deductAmtRules.getDeductmember());
				    	}
					    	
						
						ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
						if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
					    	 //如果是活动商品
						    actuPrice = actuPrice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
						    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
						    detailPrice = activeInfo.getActivePrice();
					    }else if("1".equals(prodInfo.getIsfreshman())){
					    	actuPrice = actuPrice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
					    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
					    	detailPrice = prodInfo.getFreshmanprice();
					    }else{
					    	actuPrice = actuPrice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
					    	primeprice = primeprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
					    	detailPrice = prodInfo.getSellPrice();
					    	od.setSellprice(detailPrice);
					    	if(deductAmtRules == null){
				    		    normalProdAmt = normalProdAmt.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity))); 
				    	    }
					    	
		                    orderDetailDAO.doSwitchOrderDetailPrice(od);
					    }
					}
					
				}
				
			}
			
			order.setPrimeprice(primeprice);//设置实际商品总金额
			
			//配送费使用总优惠后的金额计算
			 if("1".equals(order.getSourcetype())){
				 order.setDeliveryfee(settleCartInfoV2Service
							.calcDeliverFee(actuPrice.subtract(sumdeductAmt)
									,order.getBusinessUser(),orderWeigth));
			   }
			
			order.setDeductamt(sumdeductAmt);
			
			order.setSuccessprice(actuPrice
					.add(order.getDeliveryfee()));
			
		    order.setSellprice(actuPrice
					.add(order.getDeliveryfee())
					.subtract(order.getDeductamt()));
		    
		   
		    
		    orderInfoDAO.doSwitchOrderAmt(order);
		    
		    baseOrder.setPrimeprice(
					   baseOrder.getPrimeprice().add(order.getPrimeprice()));
		    baseOrder.setSellprice(
					   baseOrder.getSellprice().add(order.getSellprice()));
			baseOrder.setDeliveryfee(
					   baseOrder.getDeliveryfee().add(order.getDeliveryfee()));
			baseOrder.setDeductamt(
					   baseOrder.getDeductamt().add(order.getDeductamt()));
			
			
			 //构建返回详细信息
		    retDetailBeanList.addAll(bulidRetDetailBean(orderDetailList));
		    
		}
		
		baseOrderInfoDAO.update(baseOrder);
		
		responseBean.setDeliverfee(baseOrder.getDeliveryfee().toString());
		responseBean.setDetaillist(retDetailBeanList);
		responseBean.setSellprice(baseOrder.getSellprice().toString());
		responseBean.setDiscountamt(baseOrder.getDeductamt().toString());
		
		//增加红包
		List<VoucherBean> voucherList = settleCartInfoV2Service.getReturnVoucherBean(baseOrder.getUserInfo().getUserId()
				, normalProdAmt, orderInfoList);
		
		if(voucherList != null && voucherList.size() > 0){
			responseBean.setTicketList(voucherList);
	
			responseBean.setTicketdetail(voucherList.get(0));
		}
		
		return responseBean;
	}
	
	public List<OrderDetailBean> bulidRetDetailBean(List<OrderDetail>  orderDetailList){
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
	
		for(OrderDetail orderDetail:orderDetailList){
		    OrderDetailBean retDetailBean = new OrderDetailBean();
		    ProdInfo prodInfo = prodInfoDAO.getById(orderDetail.getProd().getProdid());
			retDetailBean.setImageurl(prodInfo.getImageUrl());
			retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
			retDetailBean.setProdname(orderDetail.getProdname());
			retDetailBean.setProdnum(orderDetail.getProd().getProdid());
			retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
			retDetailBean.setSellflag(prodInfo.getPreSellTag());
			int buyQuantity = orderDetail.getQuantity();
			retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(buyQuantity)).toString());
			retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
			
			retDetailBeanList.add(retDetailBean);
		}
         
		return retDetailBeanList;
	}
	
	
	public OrderDetailBean bulidRetDetailBean(OrderDetail orderDetail,ProdInfo prodInfo){
	    OrderDetailBean retDetailBean = new OrderDetailBean();
		retDetailBean.setImageurl(prodInfo.getImageUrl());
		retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
		retDetailBean.setProdname(prodInfo.getProdname());
		retDetailBean.setProdnum(prodInfo.getProdid());
		retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
		retDetailBean.setSellflag(prodInfo.getPreSellTag());
		retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(orderDetail.getQuantity())).toString());
		retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
		
		return retDetailBean;
	}
	
	

	
}
