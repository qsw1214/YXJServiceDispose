package org.test.facade;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.youxianji.util.MD5Tools;

public class SignTool {

	public static String getSign(String encBefore,String telephone){
		//生成Sign
		return MD5Tools.getKeyedDigest(encBefore, telephone);
	}
	
	//生成参数Map
	public static Map <String,String> getParamMap(List<NameValuePair> formparams){
		Map <String,String>sendMap = new HashMap<String,String>();
		for(NameValuePair valuePair:formparams){
			sendMap.put(valuePair.getName(),valuePair.getValue());
		}
		
		return sendMap;
	}
	
//	组织加密原串
	public static String setEncBefore(Map<String,String> map){
//		对参数进行排序
		Object[] keys = map.keySet().toArray();
		Arrays.sort(keys);
		
//		组织加密原串
		StringBuffer dataBuf = new StringBuffer();
		for (int j = 0; j < keys.length; j++) {
			if (!keys[j].equals("public")) {
				dataBuf.append(keys[j] + "=" + map.get(keys[j]) + "&");
			}
		}
		String encBeforeStr = dataBuf.substring(0,dataBuf.length()-1);
		
		return encBeforeStr;
	}
}
