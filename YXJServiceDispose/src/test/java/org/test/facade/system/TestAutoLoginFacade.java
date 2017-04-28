package org.test.facade.system;

import org.test.facade.BaseFacade;
/**
 * 测试自动登录接口
 * <p>command=1001</p>
 * @author Administrator
 *
 */
public class TestAutoLoginFacade extends BaseFacade {
	
	public TestAutoLoginFacade(){
		super("1001");
	}
	public static void main(String[] args){
		TestAutoLoginFacade test = new TestAutoLoginFacade();
		test.getParams().put("openid", "o7D2TwYXmJHqYTgENdvAWav0ZK6o");
		System.out.println(test.sendRequest());
	}
}
