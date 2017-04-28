package com.youxianji.service.impl;

import java.math.BigDecimal;
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
import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.dao.IOrderprocessinfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.ISubStoreInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IUserThirdPayDAO;
import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.facade.trade.bean.PayOnecentOrderResponseBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.Orderprocessinfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.UserThirdPay;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.service.IPayOnecentOrderService;
import com.youxianji.service.ISettleCartInfoV2Service;
import com.youxianji.service.orderpay.BasePayOrder;
import com.youxianji.service.orderpay.PayContext;
import com.youxianji.service.orderpay.PayParamsBean;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Service("payOnecentOrderService")
public class PayOnecentOrderServiceImpl extends BasePayOrder implements IPayOnecentOrderService {
	private Logger logger = Logger.getLogger(PayOnecentOrderServiceImpl.class);
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
    private ISettleCartInfoV2Service settleCartInfoV2Service;
    @Resource
    private IBaseOrderInfoDAO baseOrderInfoDAO;
    
	@Override
	public Map<String, String> doOnecentPayOrder(PayOnecentOrderResponseBean responseBean,String prodid, String payType,
			String userId, String payPass, String imei, String ip,String os,String deviceNo,String invitecode)
			throws BaseException {
		
	    ActivityProdInfo activeInfo = activityProdInfoDAO.getActiveByProdId(prodid);
	    logger.info("一分购标识："+activeInfo.getIsonecent());
	    
	    UserInfo userInfo = userInfoDAO.getById(userId);
	    ProdInfo prodInfo = prodInfoDAO.getById(prodid);
		String orderSn = IdGenerator.instance().generate(os);
		
		List<OrderInfo> onecentOrderList = orderInfoDAO.getOnecentOrderList(userId);
		if(onecentOrderList!= null && onecentOrderList.size()>0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您已购买或存在待支付的一分购订单");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		//查询是否存在该订单号
		if(orderInfoDAO.getByOrderSn(orderSn) != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("订单号重复，请重新结算");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
	    OrderInfo order = bulidOrderInfo(orderSn,activeInfo.getActivePrice(),activeInfo.getActivePrice(),deviceNo,userInfo,prodInfo.getBusinessUser(),payType);
	    
	    order.setInvitecode(invitecode);
	    order.setOrdertype("C");
	    
	    BaseOrderInfo baseOrder = settleCartInfoV2Service.doBulidBaseOrderInfo(IdGenerator.instance().generate(os), order);
	    
	   //保存订单
	    order.setBaseordersn(baseOrder);
	    baseOrder.setPaytype(payType);
	    baseOrder.setOrderstate(Constants.ORDER_NO_PAY);
  		baseOrderInfoDAO.insert(baseOrder);
  		orderInfoDAO.insert(order);
  		//保存订单详细
  		orderDetailDAO.save(bulidOrderDetail(prodInfo,orderSn,order.getSellprice()));
  		
  		
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
		return pay(baseOrder, userInfo, imei, ip);
	}
	
	public Map<String,String> pay(BaseOrderInfo baseOrder,UserInfo user,String imei,String ip)
			throws BaseException {
		 Map<String,String>  retMap = new HashMap<String,String>();

		 List<OrderInfo> orderList = orderInfoDAO.getListByBaseOrderSn(baseOrder.getBaseordersn());
	        
		   
		 WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(user.getUserId());
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
	

	private OrderDetail bulidOrderDetail(ProdInfo prodInfo,String orderSn,BigDecimal detailPrice){
		 OrderDetail orderDetail = new OrderDetail();
		 orderDetail.setDetailid(UUIDGenerator.getUUID());
	     orderDetail.setIsreview("0");
	     orderDetail.setOrdersn(orderSn);
	     orderDetail.setProd(prodInfo);
	     orderDetail.setProdname(prodInfo.getProdname());
	     orderDetail.setQuantity(1);
	     orderDetail.setSellprice(detailPrice);
	     
	     return orderDetail;
	}
	
	private OrderInfo bulidOrderInfo(String orderSn,BigDecimal primeprice,
			BigDecimal sellprice,String deviceNo,
			UserInfo userInfo,BusinessUserInfo businessUser,String payType){
		   OrderInfo order = new OrderInfo();
		   order.setCouponflag("0");
		   order.setCouponamt(new BigDecimal("0"));
		   order.setDeductamt(new BigDecimal("0"));
		   order.setFirstorderamt(new BigDecimal("0"));
		   order.setServicefee(new BigDecimal("0"));
		   order.setDeliveryfee(new BigDecimal("0"));
		   order.setSuccessprice(primeprice);
		   order.setReceiveaddress(null);
		   order.setReceivename(null);
		   order.setReceivetelephone(null);
		   order.setReceivetime(null);
		   order.setRemark(null);
		   order.setPaytime(null);
		   order.setPayflag("2");
		   order.setIsOnecent("1");
		   order.setPaytype(payType);
		   order.setCargocode("");
		   
		   //计算配送 费
		   order.setDeviceno(deviceNo);
		   order.setOrdersn(orderSn);
		   order.setOrderstate(Constants.ORDER_NO_PAY);
		   order.setOrdertime(new Date());
		   order.setPrimeprice(primeprice);
		   order.setSellprice(sellprice);
		   order.setUserInfo(userInfo);
		   order.setBusinessUser(businessUser);
		   
		   return order;
	}
	
}
