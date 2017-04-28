package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试获取用户发起砍价信息接口
 * <p>command=k002</p>
 * @author Administrator
 *
 */
public class TestGetUserAssistBargainInfoFacade extends BaseFacade{
	public TestGetUserAssistBargainInfoFacade() {
		super("k002");
	}
	public static void main(String[] args) {
		TestGetUserAssistBargainInfoFacade test = new TestGetUserAssistBargainInfoFacade();
		test.getParams().put("userid", "36e39fbd143b47078be923736b6e0515");
		test.getParams().put("telephone", "15701625270");
		test.getParams().put("bargainid", "54307e3ce9164dd4a7865b3c88e4a7d3");
		System.out.println(test.sendRequest());
	}

}
