package com.youxianji.network.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class ConnectUtil {
	private static  Logger logger = Logger.getLogger(ConnectUtil.class);
	public String excute(String url,List<NameValuePair> formparams)throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)//连接超时时间（连接初始化时间）
													 .setSocketTimeout(30000)//读取数据超时时间
													 .build();
		try{
			UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); 
			HttpPost post = new HttpPost(url);
			post.setEntity(reqEntity);
			post.setConfig(config);
			
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try{
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == 200){
					HttpEntity entity = httpResponse.getEntity();
					if(entity != null){
						return EntityUtils.toString(entity,"UTF-8");
					}else{
						logger.info("entity返回为空！！");
					}
				}else{
					throw new Exception("statusCode = " + statusCode);
				}
			}finally{
				httpResponse.close();
			}
		}finally{
			if(httpClient != null){
				httpClient.close();
			}
		}
		return null;
	}
	
	/*private CloseableHttpClient getHttpClient(){
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();        
		cm.setMaxTotal(6);//连接池最大并发连接数        
		cm.setDefaultMaxPerRoute(3);//单路由最大并发数(即指定的某一个ip)
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		
		return httpClient;
	}*/
}
