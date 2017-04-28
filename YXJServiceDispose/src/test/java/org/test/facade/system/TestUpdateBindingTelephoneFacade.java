package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试修改绑定手机号
 * <p>command=1007</p>
 * @author rendingding
 *
 */
public class TestUpdateBindingTelephoneFacade extends BaseFacade {
	
	public TestUpdateBindingTelephoneFacade(){
		super("1007");
	}
	
	public static void main(String[] args) {
		TestUpdateBindingTelephoneFacade test = new TestUpdateBindingTelephoneFacade();
		test.getParams().put("verifycode", "043826");
		test.getParams().put("userid", "1");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("newphone", "12345678910");
		
		System.out.println(test.sendRequest());

	}

}
