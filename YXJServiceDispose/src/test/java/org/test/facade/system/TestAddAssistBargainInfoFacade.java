package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试生成帮助砍价记录接口
 * <p>command=k005</p>
 * @author yzl
 *
 */
public class TestAddAssistBargainInfoFacade extends BaseFacade{
	public TestAddAssistBargainInfoFacade() {
		super("k005");
	}
	public static void main(String[] args) {
		TestAddAssistBargainInfoFacade test = new TestAddAssistBargainInfoFacade();
		test.getParams().put("userid", "04494b943c3040d9928f8cce95e92429");
		test.getParams().put("bargainid", "ef5be887379a4992bceb6f3932ee0f3e");
		System.out.println(test.sendRequest());
	}

}
