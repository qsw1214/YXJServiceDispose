package org.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import base.cn.web.facade.bean.PublicBean;

import com.youxianji.util.GsonTools;

public class HttpTools {

	public String excute(String url,List<NameValuePair> formparams)throws Exception{
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		try{
			UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); 
			HttpPost post = new HttpPost(url);
			post.setEntity(reqEntity);
			
			HttpResponse httpResponse = httpClient.execute(post);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					return EntityUtils.toString(entity,"UTF-8");
				}
			}else{
				throw new Exception("statusCode = " + statusCode);
			}
		}finally{
			if(httpClient != null){
				httpClient.close();
			}
		}
		return null;
	}
	
	public static void main(String []args){
		PublicBean pb = new PublicBean();
		pb.setCommand("1001");
		pb.setOs("android");
		pb.setIp("192.168.1.1");
		pb.setTime_stamp("201402191740");
		String publicString = GsonTools.getJsonString(pb);
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		formparams.add(new BasicNameValuePair("public", publicString)); 
		formparams.add(new BasicNameValuePair("telephone","13671277367"));
		formparams.add(new BasicNameValuePair("password","889912345"));
		formparams.add(new BasicNameValuePair("name","席智星"));
		formparams.add(new BasicNameValuePair("userid","13111317170000"));
		
		String url = "http://localhost:8080/91ShopMall/controller/mobile/server.shtml";
		
		HttpTools tools = new HttpTools();
		try {
			String str = tools.excute(url,formparams);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
