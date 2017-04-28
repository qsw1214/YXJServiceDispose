package com.youxianji.util;

import static com.youxianji.util.Constants.SMS_PROPERTIES;

import com.youxianji.network.sms.SmsSendUtil;


public class SMSYunxinThread extends Thread{
	
	private String mobiles;
	private String content;
	
	public SMSYunxinThread(String mobiles,String content){
		this.mobiles = mobiles;
		this.content = content;
	}
	
	@Override
	public void run() {
		
		try {
			//发送短信
			String agentCode = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.choice.agentcode");
			SmsSendUtil smsSend = new SmsSendUtil();
			smsSend.sendSms(agentCode,PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.channel.hangye", false),mobiles, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		
		
	}
}
