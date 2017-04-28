package com.youxianji.service.orderpay;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.ICartInfoDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.util.Constants;
import com.youxianji.web.util.ErrorEnum;

public class BasePayOrder {
	
	private ICartInfoDAO cartInfoDAO;
	
	private IOrderDetailDAO orderDetailDAO;
	
	private IActivityProdInfoDAO activityProdInfoDAO;
	
	public BasePayOrder(
			ICartInfoDAO cartInfoDAO,
			IOrderDetailDAO orderDetailDAO,
			IActivityProdInfoDAO activityProdInfoDAO) {
		
		this.cartInfoDAO = cartInfoDAO;
		this.orderDetailDAO = orderDetailDAO;
		this.activityProdInfoDAO = activityProdInfoDAO;
	}
	
	public BasePayOrder(){
		
	}

	
	public Map<String, String> doPay(PayParamsBean payParamsBean) throws BaseException{
	    return null;	
	}
	
	/**
	  * 支付时做必要的检查
	  * @return
	  */
	public void doPayNecessaryCheck(WeChatUserInfo wechatUser,BaseOrderInfo baseOrder,
			List<OrderInfo> orderInfoList,UserInfo user,String payPass) throws BaseException{
		
		checkCommon(wechatUser, baseOrder, orderInfoList, user);
		
		if(ObjectTools.isNullByString(baseOrder.getPaytype())){
			  ErrorEnum.valueOf("FAIL_5555").changetMessage("订单支付方式不能为空");
	          throw new BaseException(ErrorEnum.FAIL_5555);
	    }
		
	    //用户["+orderMuserName+"订单["+orderSn+"]已做支付！
		if(!Constants.ORDER_NO_PAY.equals(baseOrder.getOrderstate())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该订单号状态不能支付");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		
		
	}
	
	 /**
	  * 提交订单校验
	  * @return
	  */
	public void doCommitNecessaryCheck(WeChatUserInfo wechatUser,BaseOrderInfo baseOrder
			,List<OrderInfo> orderInfoList
			,UserInfo user) throws BaseException{
		checkCommon(wechatUser, baseOrder, orderInfoList,user);
	}
	
	private void checkCommon(WeChatUserInfo wechatUser,BaseOrderInfo baseOrder,List<OrderInfo> orderInfoList,UserInfo user){
		//判断订单是否存在
		if(baseOrder == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该订单号在系统中不存在");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		//判断订单金额
		if(baseOrder.getSellprice().compareTo(new BigDecimal("0")) <= 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该订单号金额必须大于0");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		for(OrderInfo order:orderInfoList){
			 if(order.getSellprice().compareTo(new BigDecimal("0")) <= 0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage(order.getOrdersn()+"分单金额必须大于0");
				throw new BaseException(ErrorEnum.FAIL_5555);
			 }
			 if("1".equals(order.getIsOnecent())){
			    	//只有购物车为空的时候才能够添加
			    	String cartCount = cartInfoDAO.getCartInfoCount(user.getUserId());
			    	if(cartCount != null && Integer.valueOf(cartCount) > 1){
			    		ErrorEnum.valueOf("FAIL_5555").changetMessage("一分购商品只能单独购买");
						throw new BaseException(ErrorEnum.FAIL_5555);
			    	}
			    	//判断是否购买1分够产品
			    	if("1".equals(user.getIsOnecent())){
						ErrorEnum.valueOf("FAIL_5555").changetMessage("您已购买过一分购商品啦~~ ^_^");
						throw new BaseException(ErrorEnum.FAIL_5555);
					}
			    	
			  }
			 
			 List<OrderDetail> orderDetailList = orderDetailDAO.getDetailListByOrderSn(order.getOrdersn());
			 ActivityProdInfo activityProd = null;
			 for(OrderDetail od:orderDetailList){
		    	 if("1".equals(od.getProd().getIsfreshman())
		    			 && "0".equals(user.getIsfreshman())){
		    		ErrorEnum.valueOf("FAIL_5555").changetMessage("您不是新人,不能购买新人专享商品");
					throw new BaseException(ErrorEnum.FAIL_5555);
		    	 }
		    	 if(!"1".equals(od.getProd().getState())){
		    		ErrorEnum.valueOf("FAIL_5555").changetMessage("商品"+od.getProd().getProdname()+"已下架");
					throw new BaseException(ErrorEnum.FAIL_5555);
		    	 }
		    	 activityProd = activityProdInfoDAO.getActiveByProdId(od.getProd().getProdid());
		         if(activityProd != null && "1".equals(od.getProd().getIsactivity())){
		        	 if(od.getQuantity() > activityProd.getLimitcount()){
		        		 ErrorEnum.valueOf("FAIL_5555").changetMessage("您只能购买"+activityProd.getLimitcount()+"个活动商品");
					     throw new BaseException(ErrorEnum.FAIL_5555);
		        	 }
		         }
			     
			 }
		}
		
	     
	   //支付用户["+userName+"]和订单所属用户["+orderMuserName+"]不一致！
		if(baseOrder.getUserInfo() == null 
				|| !baseOrder.getUserInfo().getUserId().equals(user.getUserId())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该订单所属用户与当前支付用户不一致");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		// 用户额度不足！
		if(user.getAmount().compareTo(baseOrder.getSellprice()) < 0 
				&& "3".equals(baseOrder.getPaytype())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您的账户余额不足");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
				
		
	}

	public void setCartInfoDAO(ICartInfoDAO cartInfoDAO) {
		this.cartInfoDAO = cartInfoDAO;
	}

	public void setOrderDetailDAO(IOrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}


}
