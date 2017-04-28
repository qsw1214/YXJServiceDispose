package base.cn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * <p>创建人：席智星  </p> 
 * <p>创建时间：2016年4月26日  </p> 
 * <p>类描述： 手机App接口参数BEAN </p>  
 */ 

@Documented  
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME) 
@Inherited
public @interface InterfaceParam {

	String command() default "";
	
	String comment() default "";
}
