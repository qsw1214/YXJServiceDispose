package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试获取用户发起砍价信息接口
 * <p>command=k001</p>
 * @author Administrator
 *
 */
public class TestGetUserLaunchBargainInfoFacade extends BaseFacade{
	public TestGetUserLaunchBargainInfoFacade() {
		super("k001");
	}
	public static void main(String[] args) {
		TestGetUserLaunchBargainInfoFacade test = new TestGetUserLaunchBargainInfoFacade();
		test.getParams().put("userid", "0238a10af8654fe9b2510dcc7dfeb81f");
		test.getParams().put("telephone", "15701625270");
		test.getParams().put("openid", "o7D2TwTruIVV65AqvvU9ZCrk5fas");
		System.out.println(test.sendRequest());
	}

}
