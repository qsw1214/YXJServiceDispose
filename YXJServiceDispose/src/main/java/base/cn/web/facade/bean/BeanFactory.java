package base.cn.web.facade.bean;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import base.cn.util.ObjectTools;
import base.cn.web.AppContextManager;

import com.google.gson.Gson;







import static base.cn.web.facade.bean.ObjMapTools.getValue;


public class BeanFactory {

	private static AppContextManager appManager = AppContextManager.instance();
	
	public BaseRequest getNewBean(String command,Map <String,Object>values) throws Exception{
		Object obj = createBeanObject(command);
		setPropertyValue(obj,values);
		return (BaseRequest)obj;
	}
	
	/**
	 * 通过反射动态获取请求Bean对象
	 * */
	private Object createBeanObject(String command) throws Exception{
		Object paramBean = appManager.getParamBean(command);
		if(ObjectTools.isNullByObject(paramBean)){
			throw new Exception("command:" + command + "不存在");
		}
		return paramBean;
	}
	
	/**
	 * 通过内省为Bean对象赋值
	 * */
	private void setPropertyValue(Object obj,Map <String,Object>values) throws Exception{
		//获取值对象的所有属性集合
		PropertyDescriptor []descs = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
		for(PropertyDescriptor desc : descs){
			if(values.containsKey(desc.getName())){
				Object value = getValue(desc.getName(),values);
				setValue(obj,desc,value);
			}
		}
	}
	
	/**
	 * 设置属性值
	 * */
	private void setValue(Object obj,PropertyDescriptor desc,Object value) throws Exception{
		if(ObjectTools.isNullByObject(value)){
			return;
		}
		Method setterMethod = desc.getWriteMethod();
		if(ObjectTools.isNullByObject(setterMethod)){
			return;
		}
		setterMethod.setAccessible(true);
		String fieldName = desc.getName();
		
		Class fieldClazz = desc.getPropertyType();
	
		if(fieldClazz.isAssignableFrom(List.class)){
			Field f = obj.getClass().getDeclaredField(fieldName);
			Type fc = f.getGenericType();
			Gson gson = new Gson();
			//如果是泛型参数的类型
			if(fc instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType)f.getGenericType();
				value = gson.fromJson(value.toString(), pt);
			}else{
				value = gson.fromJson(value.toString(), fc);
			}
		}
		setterMethod.invoke(obj, ConvertUtils.convert(value, desc.getPropertyType()));
	}
}