package org.test.facade.system;

import org.test.facade.BaseFacade;
/**
 * 测试设置密码接口
 *  <p>command=1004</p>
 * @author rendingding
 *
 */
public class TestConfigPayPassFacade extends BaseFacade {
	
	public TestConfigPayPassFacade() {
		super("1004");
	}
	public static void main(String[] args){
		TestConfigPayPassFacade test=new TestConfigPayPassFacade();
		test.getParams().put("verifycode", "504515");
		test.getParams().put("userid", "1");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("password", "123456");
		
		System.out.println(test.sendRequest());
	}
}
