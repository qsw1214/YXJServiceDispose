package com.youxianji.network.sms;

import static com.youxianji.util.Constants.SMS_PROPERTIES;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.youxianji.network.util.ConnectUtil;
import com.youxianji.util.PropertyManager;

/**
 * 短信发送Uitl
 * 
 * @author xyUser
 */
public class SmsSendUtil {
	
	private static final String cmpp_url;
	
	private static final String yx_url;
	
	private static final String dh_url;
	
	private static final String dhChannel_url;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private static ExecutorService pool = Executors.newFixedThreadPool(10);  
	
	static {
		cmpp_url = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.cmpp.url", false);
		yx_url = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.yunxin.url", false);
		dh_url = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.dahan.url", false);
		
		dhChannel_url = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.dahan.chanellurl", false);
	}
	
	/**
	 * 发送短信操作
	 */
	public Boolean sendSms(String agentCode,String channel,final String phone, final String msg){
		this.log.info("使用的短信接口为："+agentCode);
		Boolean flag = false;
		if("CL".equals(agentCode)){
			flag = this.sendByCmpp(phone, msg);
		}else if("YX".equals(agentCode)){
			flag =  this.sendByYunxin(channel,phone, msg);
		}else if("DH".equals(agentCode)){
			flag =  this.sendByDaHan(channel,phone, msg);
		}
		
		return flag;
	}

	/**
	 * CMPP通道发送短信
	 * 
	 * @param phone
	 * @param msg
	 * 
	 * @return String
	 */
	private Boolean sendByCmpp(final String phone, final String msg) {
		String result = send(cmpp_url, phone, msg);
		if(!"0".equals(result)){
			log.info("手机号["+phone+"]发送短信错误，返回信息："+result);
			return false;
		}else{
		    return true;	
		}
	}
	
	/**
	 * 云信通道发送短信
	 * 
	 * @param phone
	 * @param msg
	 * 
	 * @return String
	 */
	private Boolean sendByYunxin(final String channel,final String phone, final String msg) {
		
		String result = send(yx_url, phone, msg);
		if(!"0".equals(result)){
			log.info("手机号["+phone+"]发送短信错误，返回信息："+result);
			return false;
		}else{
		    return true;	
		}
	}
	/**
	 * 大汉三通通道发送短信
	 * 
	 * @param phone
	 * @param msg
	 * 
	 * @return String
	 */
	private Boolean sendByDaHan(final String channel,final String phone, final String msg) {
		String result = "";
		if(channel.equals("1")){
			result = send(yx_url, phone, msg);
		}else if(channel.equals("2")){
			result = send(dhChannel_url, phone, msg);
		}
		if(!"0".equals(result)){
			log.info("手机号["+phone+"]发送短信错误，返回信息："+result);
			return false;
		}else{
		    return true;	
		}
	}
	private String send(final String url, final String phone, final String msg) {
		SendCallable callable = new SendCallable(url, phone, msg);
		Future<String> future = pool.submit(callable);
		
		String result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private class SendCallable implements Callable<String> {
		
		private String url = null;
		
		private String phone = null;
		
		private String msg = null;
		
		public SendCallable(String url, String phone, String msg) {
			this.url = url;
			this.phone = phone;
			this.msg = msg;
		}
		
		@Override
		public String call() throws Exception {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			//设置参数
			formParams.add(new BasicNameValuePair("mobiles", phone)); 
			formParams.add(new BasicNameValuePair("content", msg));
			formParams.add(new BasicNameValuePair("sendType", "0"));
			//请求发送
			ConnectUtil conn = new ConnectUtil();
			String result = null;
			try {
				result = conn.excute(url, formParams);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			//返回结果
			return result;
		}
	}
	
	public static void main(String[] args) {
		SmsSendUtil util = new SmsSendUtil();
		util.sendByCmpp("15210207356", "ccccccccccccccccccccc");
	}
}
