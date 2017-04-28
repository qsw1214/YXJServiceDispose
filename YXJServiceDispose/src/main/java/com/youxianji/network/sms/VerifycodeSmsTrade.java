package com.youxianji.network.sms;

import static com.youxianji.util.Constants.SMS_PROPERTIES;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.youxianji.network.sms.vo.AbsSmsRequestVO;
import com.youxianji.network.sms.vo.AbsSmsResponseVO;
import com.youxianji.network.sms.vo.VerifycodeRequest;
import com.youxianji.network.sms.vo.VerifycodeResponse;
import com.youxianji.util.PropertyManager;

/**
 * 发送手机验证码短信息
 * */
public class VerifycodeSmsTrade extends AbsSmsTrade{
	
	private static PropertyManager propManager = PropertyManager.instance();
	private static String url = propManager.getValue(SMS_PROPERTIES, "sms.cmpp.url", false);

	public VerifycodeSmsTrade() {
		super(url);
	}
	
	public VerifycodeSmsTrade(String url) {
		super(url);
	}

	@Override
	protected List<NameValuePair> getRequestParams(AbsSmsRequestVO requestVO) {
		VerifycodeRequest request = (VerifycodeRequest)requestVO;
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("mobiles", request.getMobiles()));
		formparams.add(new BasicNameValuePair("content", request.getContent()));
		formparams.add(new BasicNameValuePair("sendType", request.getSendType()));
		
		return formparams;
	}

	@Override
	protected AbsSmsResponseVO toParseResponse(String responseString) {
		if(responseString == null || "".equals(responseString)){
			return null;
		}
		
		VerifycodeResponse response = new VerifycodeResponse();
		response.setReturnCode(responseString);
		
		return response;
	}

}