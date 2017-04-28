package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试获取用户发起砍价信息接口
 * <p>command=k001</p>
 * @author Administrator
 *
 */
public class TestGetBargainProdInfoFacade extends BaseFacade{
	public TestGetBargainProdInfoFacade() {
		super("k007");
	}
	public static void main(String[] args) {
		TestGetBargainProdInfoFacade test = new TestGetBargainProdInfoFacade();
		System.out.println(test.sendRequest());
	}

}
