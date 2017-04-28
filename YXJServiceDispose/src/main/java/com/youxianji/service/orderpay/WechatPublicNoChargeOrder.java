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
import com.youxianji.util.wechat.common.publicno.HttpsRequest;
import com.youxianji.util.wechat.common.publicno.Signature;
import com.youxianji.util.wechat.protocol.pay_protocol.PubPayReqData;
import com.youxianji.util.wechat.service.IServiceRequest;
import com.youxianji.web.util.ErrorEnum;

/**
 * 
 * 微信App支付
 * @author Administrator
 *
 */
public class WechatPublicNoChargeOrder{
 
	Logger logger = Logger.getLogger(WechatPublicNoChargeOrder.class);
	
	private static PropertyManager propertyManager = PropertyManager.instance();
	private static String wxRequestUrl = propertyManager.getValue(Constants.YXJ_PROPERTIES,"wechat.requesturl");
	private static String charge_notify_url = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.charge.notifyurl");
	private static String appid = propertyManager.getValue(Constants.YXJ_PROPERTIES,"wechat.appid");
	private static String charge_body = "优鲜季会员充值";
	private static String mch_id = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.mchid");
	private static String trade_type = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.tradetype");		
	private static String timelong = propertyManager.getValue(Constants.YXJ_PROPERTIES, "wechat.time");	

	public Map<String,String> doWechatPublicNoCharge(String chargesn,BigDecimal amount,String openid,String imei,String ip)  throws BaseException {
      
		Map<String,String> secSignMap = parsePreResult(chargesn,amount,
				openid,null,ip);
        String secSign = null;
		try {
			secSign = Signature.getSignMap(secSignMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
        secSignMap.put("paysign",secSign);	
        System.out.println("微信公众号支付签名："+secSign);
		
        return secSignMap;
	}
	private String preRequestDisposed(String chargeSn,BigDecimal chargeMoney, String openid,String tmei,String ip){
		if("".equals(tmei)){
			tmei = null;
		}
		
		Date currDate = new Date();
	    Calendar cal=Calendar.getInstance();
	    cal.setTime(currDate);
	    cal.add(Calendar.MINUTE, Integer.valueOf(timelong));
		    
		String startTime = DateUtil.toStr(currDate, "yyyyMMddHHmmss");
		String endTime = DateUtil.toStr(cal.getTime(), "yyyyMMddHHmmss");
		//签名 
		PubPayReqData sp = new PubPayReqData(appid,mch_id,
				openid,charge_body, null,chargeSn,chargeMoney.multiply(new BigDecimal("100")).intValue(),
				tmei, ip, startTime, endTime, null,charge_notify_url,trade_type);
	
		String returnMsg = "";
		IServiceRequest ir;
		//请求微信进行，并返回预处理结果
		try {
			ir = new HttpsRequest();
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
	private Map<String,String> parsePreResult(String chargeSn,BigDecimal chargeMoney,String openid,String tmei,String ip){
		String returnMsg = preRequestDisposed(chargeSn,chargeMoney,openid,tmei,ip);
		logger.info("获取微信返回信息："+returnMsg);
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
		Map<String,String> secSignMap = new HashMap<String,String>();
		secSignMap.put("appId",appid_);	
		secSignMap.put("timeStamp",times);
		secSignMap.put("nonceStr",noncestr);
		secSignMap.put("package","prepay_id="+prepayId);	
		secSignMap.put("signType","MD5");	
		
		return secSignMap;
	}
	

}
