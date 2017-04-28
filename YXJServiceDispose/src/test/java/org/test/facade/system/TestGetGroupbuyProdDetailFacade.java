package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试获取用户发起砍价信息接口
 * <p>command=3014</p>
 * @author Administrator
 *
 */
public class TestGetGroupbuyProdDetailFacade extends BaseFacade{
	public TestGetGroupbuyProdDetailFacade() {
		super("3015");
	}
	public static void main(String[] args) {
		TestGetGroupbuyProdDetailFacade test = new TestGetGroupbuyProdDetailFacade();
		test.getParams().put("groupbuyid", "1703201228380001");
		test.getParams().put("prodid", "1612171117270024");
		System.out.println(test.sendRequest());
	}

}
