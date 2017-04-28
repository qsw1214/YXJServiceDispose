package org.test.facade.system;

import org.test.facade.BaseFacade;
/**
 * 测试待付款订单列表
 * @author admin
 *
 */
public class TestWaitPayOrderListFacade extends BaseFacade {
	public TestWaitPayOrderListFacade(){
		super("2005");
	}
	public static void mian(String[] args){
		TestWaitPayOrderListFacade test = new TestWaitPayOrderListFacade();
		test.getParams().put("page", "1");
		test.getParams().put("userid", "0238a10af8654fe9b2510dcc7dfeb81f");
		test.getParams().put("telephone", "15701625270");
		System.out.println(test.sendRequest());
	}
}
