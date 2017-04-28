package base.cn.web.facade.bean;

import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;

import base.cn.util.ObjectTools;

public class ObjMapTools {

	/**
	 * 通过key从map参数对象中获取数据值
	 * @throws DecoderException 
	 * */
	public static Object getValue(String key,Map <String,Object>values) throws DecoderException{
		Object objValue = values.get(key);
		if(ObjectTools.isNullByObject(objValue)){
			return null;
		}
		if(objValue instanceof Integer) {
			return objValue;
		}
		if(objValue instanceof Double) {
			return objValue;
		}
		if(objValue instanceof String) {
			//如果是字符型，做URL解码
			URLCodec codec = new URLCodec();
			return codec.decode(objValue);
		}
		if(objValue instanceof String[]) {
			return ((String[])objValue)[0];
		}
		
		return null;
	}
}
