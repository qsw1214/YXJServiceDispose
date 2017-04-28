package com.youxianji.service.orderpay;

import java.math.BigDecimal;
import java.util.Map;

import base.cn.exception.BaseException;

import com.youxianji.pojo.BaseOrderInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.util.bill.BillTransact;
import com.youxianji.util.bill.parambean.BUserParam;
import com.youxianji.util.bill.parambean.CUserParam;
import com.youxianji.util.bill.parambean.GiroParam;
import com.youxianji.web.util.ErrorEnum;

public class UserAccountPayOrder extends BasePayOrder implements PayOrderInterface{
	

	@Override
	public Map<String,String> doPay(PayParamsBean payParamsBean) throws BaseException  {
		
		
		BaseOrderInfo baseOrder = payParamsBean.getBaseOrder();
		
		doUserAccountCheck(baseOrder, baseOrder.getUserInfo() , payParamsBean.getPayPass());
		
		//用户给商户转钱
		if(baseOrder.getSellprice().compareTo(new BigDecimal("0")) >0){
			transferTransact(baseOrder);
		}

		return null;
	}
	
	public void transferTransact(BaseOrderInfo baseOrder){
        CUserParam cuserParam = new CUserParam();
		cuserParam.setUserId(baseOrder.getUserInfo().getUserId());
		cuserParam.setOperatesn(baseOrder.getBaseordersn());
		cuserParam.setOperatemoney(baseOrder.getSellprice());
		cuserParam.setBillsflag("1");
		cuserParam.setOperatedesc("商品购买");
		
		BUserParam buserParam = new BUserParam();
		buserParam.setBuserId(baseOrder.getBusinessUser().getBuserId());
		buserParam.setOperatesn(baseOrder.getBaseordersn());
		buserParam.setOperatemoney(baseOrder.getSellprice());
		buserParam.setBillsflag("0");
		buserParam.setOperatedesc("商城销售");
		
		GiroParam giroParam = new GiroParam();
		giroParam.setCuserParam(cuserParam);
		giroParam.setBuserParam(buserParam);
		
		BillTransact.transferTransact(giroParam);
	}
	
	
	public void doUserAccountCheck(BaseOrderInfo baseOrder,UserInfo user,String payPass) throws BaseException {
        //用户支付密码错误！
		if(payPass == null || !payPass.equals(user.getPayPass())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("用户支付密码错误");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		// 用户额度不足！
		if(user.getAmount().compareTo(baseOrder.getSellprice()) < 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您的账户余额不足");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
	}

	
	
	
	

}
