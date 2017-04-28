package org.test.facade.system;

import org.test.facade.BaseFacade;
/**
 * 测试用户充值业务处理
 * <p>command=1011</p>
 * @author Administrator
 *
 */
public class TestUserRechargeFacade extends BaseFacade{
	
	public TestUserRechargeFacade(){
		super("1011");
	}
	
	public static void main(String[] args) {
		TestUserRechargeFacade  test = new TestUserRechargeFacade();
		test.getParams().put("userid", "007aa743f97743648d345126a19ff0fb");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("discountid", "0");
		test.getParams().put("normalcharge", "50");
		
		System.out.println(test.sendRequest());
	}

}
