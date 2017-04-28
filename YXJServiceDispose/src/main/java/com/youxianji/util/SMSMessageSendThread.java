package com.youxianji.util;


import static com.youxianji.util.Constants.SMS_PROPERTIES;

import com.youxianji.network.sms.SmsSendUtil;
public class SMSMessageSendThread extends Thread {

	private String mobiles;
	private String content;
	private String smsType;
	private String channelType;
	
	public SMSMessageSendThread(String smsType,String channelType,String mobiles,String content){
		this.smsType = smsType;
		this.channelType = channelType;
		this.mobiles = mobiles;
		this.content = content;
	}
	
	@Override
	public void run() {
		
		try {
			SmsSendUtil util = new SmsSendUtil();
			if(smsType.equals("YX")){
				util.sendSms(PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.choice.agentcode", false)
						,channelType,mobiles, content);
		}else if(smsType.equals("DH")){
				util.sendSms(PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.choice.agentDHcode", false)
						,channelType,mobiles, content);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		String content = "您的订单已发货，（@expressCompany@） 运单号为：@expressNo@";
		System.out.println(content.replace("@expressCompany@", "ccc")
				.replace("@expressNo@", "123"));
		SMSMessageSendThread smsThread = new SMSMessageSendThread("YX","2",
				"13718989524",content.replace("@expressCompany@", "ccc")
				.replace("@expressNo@", "123"));
		smsThread.start();
		
		
	}
	

}
