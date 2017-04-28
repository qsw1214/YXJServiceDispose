package base.cn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**   
 * <p>创建人：席智星  </p> 
 * <p>创建时间：2016年4月26日  </p> 
 * <p>类描述： 手机App接口业务处理门面 </p>  
 */ 

@Documented  
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME) 
@Inherited
public @interface Facade {
	
	String command() default "";
	
	String comment() default "";

}
