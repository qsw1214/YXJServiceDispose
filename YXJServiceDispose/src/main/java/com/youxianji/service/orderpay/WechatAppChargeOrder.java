package com.youxianji.service.orderpay;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import base.cn.exception.BaseException;

import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.util.PropertyManager;
import com.youxianji.util.wechat.common.RandomStringGenerator;
import com.youxianji.util.wechat.common.app.AppHttpsRequest;
import com.youxianji.util.wechat.common.app.AppSignature;
import com.youxianji.util.wechat.protocol.pay_protocol.ScanPayReqData;
import com.youxianji.util.wechat.service.IServiceRequest;
import com.youxianji.web.util.ErrorEnum;

/**
 * 
 * 微信App支付
 * @author Administrator
 *
 */
public class WechatAppChargeOrder{
 
	Logger logger = Logger.getLogger(WechatAppChargeOrder.class);
	
	 static PropertyManager propertyManager = PropertyManager.instance();
	 private static String wxRequestUrl = propertyManager.getValue(Constants.YXJ_PROPERTIES,"wechat.requesturl");
	 private static String notify_url = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.charge.notifyurl");
	 private static String appid = propertyManager.getValue(Constants.YXJ_PROPERTIES,"wechat.app.appid");
	 private static String body = "优鲜季App充值";
	 private static String mch_id = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.app.mchid");
	 private static String trade_type = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.app.tradetype");		
	 private static String timelong = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.time");	
	 private static String packagestr = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.packagestr");
	
	public Map<String,String> doWechatAppCharge(String chargesn,BigDecimal amount,String imei,String ip)  throws BaseException {
      
        Map<String,String> secSignMap = parsePreResult(chargesn,amount,imei,ip);
        String secSign = null;
		try {
			secSign = AppSignature.getSignMap(secSignMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        secSignMap.put("paysign",secSign);	
        logger.info("APP支付签名："+secSign);
		
        return secSignMap;
	}
	
	/**
	 * 请求微信
	 * @param order
	 * @param tmei
	 * @param ip
	 * @return
	 */
	private String preRequestDisposed(String chargesn,BigDecimal amount,String tmei,String ip){
		//根据userid获取openid
		if("".equals(tmei)){
			tmei = null;
		}
		
		Date currDate = new Date();
	    Calendar cal=Calendar.getInstance();
	    cal.setTime(currDate);
	    cal.add(Calendar.MINUTE, Integer.valueOf(timelong));
		    
		String startTime = DateUtil.toStr(currDate, "yyyyMMddHHmmss");
		String endTime = DateUtil.toStr(cal.getTime(), "yyyyMMddHHmmss");
		//第一次签名 
		ScanPayReqData sp = new ScanPayReqData(appid,mch_id,
				null, body, null, chargesn,
				amount.multiply(new BigDecimal("100")).intValue(),
				tmei, ip, startTime, endTime, null,notify_url,trade_type);
		String returnMsg = "";
		
		IServiceRequest ir;
		//请求微信进行，并返回预处理结果
		try {
			ir = new AppHttpsRequest();
			returnMsg = ir.sendPost(wxRequestUrl, sp);
		} catch (UnrecoverableKeyException | KeyManagementException
				| NoSuchAlgorithmException | KeyStoreException | IOException e) {
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	/**
	 * 解析微信返回结果
	 * @param order
	 * @param tmei
	 * @param ip
	 * @return
	 */
	private Map<String,String> parsePreResult(String chargesn,BigDecimal amount,String tmei,String ip){
		String returnMsg = preRequestDisposed(chargesn,amount,tmei,ip);
		logger.info("APP支付获取微信返回信息："+returnMsg);
		if(returnMsg == null || "".equals(returnMsg)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("微信返回信息为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		Document returnXml = null;
		try {
			returnXml = DocumentHelper.parseText(returnMsg);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//如果返回结果为NULL
		if(returnXml == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("微信返回信息解析为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		//解析返回结果
		Element rootEle = returnXml.getRootElement();
		if(rootEle == null || rootEle.element("appid") == null || rootEle.element("mch_id") == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("微信返回解析信息不正确");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}else{
			if(rootEle.element("err_code") != null){
				ErrorEnum.valueOf("FAIL_5555").changetMessage(rootEle.element("err_code_des").getData().toString());
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}
		return buildRetMap(rootEle);
	}
	
	private Map<String,String> buildRetMap(Element rootEle){
		
		String appid_ = (String)rootEle.element("appid").getData();
		String times = String.valueOf(new Date().getTime()/1000);
		String noncestr = RandomStringGenerator.getRandomStringByLength(32);
		String prepayId = (String)rootEle.element("prepay_id").getData();
		String mechid = (String)rootEle.element("mch_id").getData();
		Map<String,String> secSignMap = new HashMap<String,String>();
		
		secSignMap.put("appid",appid_);	
		secSignMap.put("noncestr",noncestr);
		secSignMap.put("package",packagestr);	
		secSignMap.put("partnerid",mechid);
		secSignMap.put("prepayid", prepayId);
		secSignMap.put("timestamp",times);
		
		return secSignMap;
	}
	

}
