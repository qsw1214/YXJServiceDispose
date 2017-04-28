package com.youxianji.util.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;

import com.youxianji.network.util.ConnectUtil;
import com.youxianji.util.Constants;
import com.youxianji.util.GsonTools;
import com.youxianji.util.PropertyManager;
import com.youxianji.util.bill.parambean.BUserParam;
import com.youxianji.util.bill.parambean.CUserParam;
import com.youxianji.util.bill.parambean.GiroParam;
import com.youxianji.util.bill.parambean.InsideGiroParam;
import com.youxianji.web.util.ErrorEnum;

public class BillTransact {
	static PropertyManager propertyManager = PropertyManager.instance();
	private static String buserUrl = propertyManager.getValue(Constants.BILL_PROPERTIES,"buser.bill.url");
	private static String cuserUrl = propertyManager.getValue(Constants.BILL_PROPERTIES,"cuser.bill.url");
	private static String giroUrl = propertyManager.getValue(Constants.BILL_PROPERTIES,"giro.bill.url");
	private static String couponGiroUrl = propertyManager.getValue(Constants.BILL_PROPERTIES,"coupon.giro.url");
	private static String insideGiroUrl = propertyManager.getValue(Constants.BILL_PROPERTIES,"inside.giro.url");
	
	//B商户账务处理
	public static void busUserTransact(BUserParam buserParam) throws BaseException{
		ConnectUtil connUtil = new ConnectUtil();
		
	    String json_buserBills = GsonTools.getJsonString(buserParam);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		formparams.add(new BasicNameValuePair("json_buserBills", json_buserBills)); 
		
		String returnStr = null;
		try {
			returnStr = connUtil.excute(buserUrl, formparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(returnStr == null || "".equals(returnStr)){
			throw new BaseException("疑问交易，请联系技术核实！");
		}
		ReturnMsg retMsg = GetStateDesc.setRtrunBean(returnStr);
		if(!"00".equals(retMsg.getReturnCode()) && !"82".equals(retMsg.getReturnCode())){
			//如果返回不是“交易成功”或“重复提交”，就抛出异常
			throw new BaseException(retMsg.getMessage());
		}

		
	}
	
    //M用户账务处理
	public static void mobileUserTransact(CUserParam cuserParam) throws BaseException{
		ConnectUtil connUtil = new ConnectUtil();
		String json_cuserBills = GsonTools.getJsonString(cuserParam);
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		formparams.add(new BasicNameValuePair("json_cuserBills", json_cuserBills)); 
		
		String returnStr = null;
		try {
			returnStr = connUtil.excute(cuserUrl, formparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(returnStr == null || "".equals(returnStr)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("疑问交易，请联系技术核实！");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		ReturnMsg retMsg = GetStateDesc.setRtrunBean(returnStr);
		if(!"00".equals(retMsg.getReturnCode()) && !"82".equals(retMsg.getReturnCode())){
			//如果返回不是“交易成功”或“重复提交”，就抛出异常
			ErrorEnum.valueOf("FAIL_5555").changetMessage(retMsg.getMessage());
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
	}
	
	
    //转账处理
	public static void transferTransact(GiroParam giroParam) throws BaseException{
		ConnectUtil connUtil = new ConnectUtil();
		
	    String json_giroBills = GsonTools.getJsonString(giroParam);
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		formparams.add(new BasicNameValuePair("json_giroBills", json_giroBills)); 
		
		String returnStr = null;
		try {
			returnStr = connUtil.excute(giroUrl, formparams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(returnStr == null || "".equals(returnStr)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("疑问交易，请联系技术核实！");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		ReturnMsg retMsg = GetStateDesc.setRtrunBean(returnStr);
		if(!"00".equals(retMsg.getReturnCode()) && !"82".equals(retMsg.getReturnCode())){
			//如果返回不是“交易成功”或“重复提交”，就抛出异常
			ErrorEnum.valueOf("FAIL_5555").changetMessage(retMsg.getMessage());
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
	}
	
	 //转账处理
		public static void transferCouponTransact(GiroParam giroParam) throws BaseException{
			ConnectUtil connUtil = new ConnectUtil();
		    String json_giroBills = GsonTools.getJsonString(giroParam);
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
			formparams.add(new BasicNameValuePair("json_couponGiroBills", json_giroBills)); 
			String returnStr = null;
			try {
				returnStr = connUtil.excute(couponGiroUrl, formparams);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(returnStr == null || "".equals(returnStr)){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("疑问交易，请联系技术核实！");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			ReturnMsg retMsg = GetStateDesc.setRtrunBean(returnStr);
			if(!"00".equals(retMsg.getReturnCode()) && !"82".equals(retMsg.getReturnCode())){
				//如果返回不是“交易成功”或“重复提交”，就抛出异常
				ErrorEnum.valueOf("FAIL_5555").changetMessage(retMsg.getMessage());
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}
		
		
		//内部转账处理
		public static void insideTransferTransact(InsideGiroParam insideGiroParam) throws BaseException{
			ConnectUtil connUtil = new ConnectUtil();
			
		    String json_insideUserAmountGiroBills = GsonTools.getJsonString(insideGiroParam);
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
			formparams.add(new BasicNameValuePair("json_insideUserAmountGiroBills", json_insideUserAmountGiroBills)); 
			
			String returnStr = null;
			try {
				returnStr = connUtil.excute(insideGiroUrl, formparams);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(returnStr == null || "".equals(returnStr)){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("疑问交易，请联系技术核实！");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			ReturnMsg retMsg = GetStateDesc.setRtrunBean(returnStr);
			if(!"00".equals(retMsg.getReturnCode()) && !"82".equals(retMsg.getReturnCode())){
				//如果返回不是“交易成功”或“重复提交”，就抛出异常
				ErrorEnum.valueOf("FAIL_5555").changetMessage(retMsg.getMessage());
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}
		
		
		
		public static void main(String args[]){
			BillTransact transact = new BillTransact();
			/*CUserParam cuserParam = new CUserParam();
			
			cuserParam.setUserId("118126a9e6e74bb584465b702b10c13e");
			cuserParam.setOperatesn(IdGenerator.instance().generate("1"));
			cuserParam.setOperatemoney(new BigDecimal(0.01));
			cuserParam.setBillsflag("1");
			cuserParam.setOperatedesc("商品购买");
			
			BUserParam buserParam = new BUserParam();
			buserParam.setBuserId("1");
			buserParam.setOperatesn(IdGenerator.instance().generate("1"));
			buserParam.setOperatemoney(new BigDecimal(0.01));
			buserParam.setBillsflag("0");
			buserParam.setOperatedesc("商城销售");
			
			GiroParam giroParam = new GiroParam();
			giroParam.setCuserParam(cuserParam);
			giroParam.setBuserParam(buserParam);*/
			
			String orderSn = IdGenerator.instance().generate("1");
			CUserParam cuserParam = new CUserParam();
			cuserParam.setUserId("118126a9e6e74bb584465b702b10c13e");
			cuserParam.setOperatesn(orderSn);
			cuserParam.setOperatemoney(new BigDecimal(0.01));
			cuserParam.setBillsflag("1");
			cuserParam.setOperatedesc("商品购买");
			
			BUserParam buserParam = new BUserParam();
			buserParam.setBuserId("1");
			buserParam.setOperatesn(orderSn);
			buserParam.setOperatemoney(new BigDecimal(0.01));
			buserParam.setBillsflag("0");
			buserParam.setOperatedesc("商城销售");
			
			GiroParam giroParam = new GiroParam();
			giroParam.setCuserParam(cuserParam);
			giroParam.setBuserParam(buserParam);
			transact.transferTransact(giroParam);
		}
		
}
