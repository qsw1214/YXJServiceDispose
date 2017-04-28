package com.youxianji.network.sms;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;

import com.youxianji.network.sms.vo.AbsSmsRequestVO;
import com.youxianji.network.sms.vo.AbsSmsResponseVO;
import com.youxianji.network.util.ConnectUtil;
import com.youxianji.network.util.ParseStrUtil;

public abstract class AbsSmsTrade {
	
	private String url;//短信接口URL
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public AbsSmsTrade(String url){
		this.url = url;
	}
	
	/**
	 * 生成请求原文
	 * */
	protected abstract List<NameValuePair> getRequestParams(AbsSmsRequestVO requestVO);
	
	/**
	 * 发送请求数据并接收返回
	 * @param requestVO
	 * @return
	 * @throws Exception
	 */
	public AbsSmsResponseVO execute(AbsSmsRequestVO requestVO)throws Exception{
		List<NameValuePair> formparams = this.getRequestParams(requestVO);
		this.log.info("短信请求串:" + ParseStrUtil.setEncBefore(formparams));
		
		ConnectUtil connUtil = new ConnectUtil();
		String responseString = connUtil.excute(url, formparams);
		this.log.info("短信应答串:" + responseString);
		
		return this.toParseResponse(responseString);
		
	}
	
	/**
	 * 解析应答串
	 * */
	protected abstract AbsSmsResponseVO toParseResponse(String responseString);
}
