package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试获取用户发起砍价信息接口
 * <p>command=3014</p>
 * @author Administrator
 *
 */
public class TestGetGroupbuyProdInfoFacade extends BaseFacade{
	public TestGetGroupbuyProdInfoFacade() {
		super("3014");
	}
	public static void main(String[] args) {
		TestGetGroupbuyProdInfoFacade test = new TestGetGroupbuyProdInfoFacade();
		System.out.println(test.sendRequest());
	}

}
