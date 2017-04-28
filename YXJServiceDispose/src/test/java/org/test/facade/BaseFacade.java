package org.test.facade;

import static com.youxianji.web.util.MobileResponse.send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.net.URLCodec;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.test.HttpTools;

import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.IFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.facade.bean.PublicBean;

import com.youxianji.util.GsonTools;
import com.youxianji.util.des.DesUtil;

public class BaseFacade extends AbsFacade implements IFacade {
	private String command;
	
	private Map<String, String> params;
	
	List<NameValuePair> formparams=null;
	
	//private static String url = "http://182.92.242.57:8989/YXJServiceDispose/mobile/serverControl";
	private static String url = "http://192.168.1.72:8080/YXJServiceDispose/mobile/serverControl";
	
	public BaseFacade() {
	}
	public BaseFacade(String command) {
		this.command=command;
		params=new HashMap<String, String>();
		params.put("telephone", "13671277367");
		params.put("userid", "6999a0947bd84f1783c70bcf33159542");
	}
	
	public BaseResponse execute(BaseRequest baseRequest) {
		send(response,this.getResponseBean());
		return null;
	}
	/**
	 * 设置返回参数
	 * */
	public BaseResponse getResponseBean(){
		return new BaseResponse();
	}
	/**/
	public void init() throws Exception{
		//初始化公共参数
		PublicBean pb = new PublicBean();
		pb.setCommand(command);
		pb.setIp("192.168.1.10");
		pb.setImei("");
		pb.setOs("android");
		pb.setOs_version("4.1.2 JZO54K");
		pb.setApp_version("1.0");
		pb.setTime_stamp("201402191626");
		
		formparams = new ArrayList<NameValuePair>();

		URLCodec codec = new URLCodec();
		//非公共请求参数
		for(Map.Entry<String, String> entry:params.entrySet()){
			formparams.add(new BasicNameValuePair(entry.getKey(), codec.encode(entry.getValue())));
		}
		//生成签名
		Map <String,String> sendMap = SignTool.getParamMap(formparams);
		String encBefore = SignTool.setEncBefore(sendMap);
		pb.setSign(SignTool.getSign(encBefore,params.get("telephone")));
		String publicString = GsonTools.getJsonString(pb);
		//公共参数部分
		formparams.add(new BasicNameValuePair("public", publicString));
		
		//生成业务参数DES加密串
		String businessParam = DesUtil.encrypt(encBefore);
		
		//业务参数加密
		formparams.add(new BasicNameValuePair("businessparam", businessParam));
		
	}
	public String sendRequest(){
		String str=null;
		
		try {
			this.init();
			HttpTools tools = new HttpTools();
			
			str = tools.excute(url,formparams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public static void main(String[] args) {
		BaseFacade test=new BaseFacade("1001");
		test.getParams().put("paypass","123465");
		System.out.println(test.sendRequest());
	}
}
