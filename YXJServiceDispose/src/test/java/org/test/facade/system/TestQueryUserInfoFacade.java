package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试查看账户信息接口
 * <p>command=1006</p>
 * @author Administrator
 *
 */
public class TestQueryUserInfoFacade extends BaseFacade{
	public TestQueryUserInfoFacade() {
		super("1006");
	}
	public static void main(String[] args) {
		TestQueryUserInfoFacade test = new TestQueryUserInfoFacade();
		test.getParams().put("userid", "1");
		test.getParams().put("telephone", "13671277367");
		System.out.println(test.sendRequest());
	}

}
