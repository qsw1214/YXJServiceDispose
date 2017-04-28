package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试生成发起砍价记录接口
 * <p>command=k004</p>
 * @author yzl
 *
 */
public class TestAddLaunchBargainInfoFacade extends BaseFacade{
	public TestAddLaunchBargainInfoFacade() {
		super("k004");
	}
	public static void main(String[] args) {
		TestAddLaunchBargainInfoFacade test = new TestAddLaunchBargainInfoFacade();
		//test.getParams().put("userid", "04d5e8a0ebb04b88a5376f8c9251b904");
		test.getParams().put("userid", "ac574be6932f4a19b52e52fa439a8e35");
		test.getParams().put("telephone", "15222197160");
		System.out.println(test.sendRequest());
	}

}
