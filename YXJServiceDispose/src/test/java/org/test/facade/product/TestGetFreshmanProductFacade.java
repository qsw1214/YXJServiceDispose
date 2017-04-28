package org.test.facade.product;

import org.test.facade.BaseFacade;

/**
 * 测试获取新人专属商品接口
 * <p>command=3006</p>
 * @author Administrator
 *
 */
public class TestGetFreshmanProductFacade extends BaseFacade{
	public TestGetFreshmanProductFacade(){
		super("3006");
	}
	public static void main(String[] args) {
		TestGetFreshmanProductFacade test = new TestGetFreshmanProductFacade();
		test.getParams().put("userid", "007aa743f97743648d345126a19ff0fb");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("prodid", "1");
		System.out.println(test.sendRequest());
	}

}
