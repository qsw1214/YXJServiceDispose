package org.test.facade.system;

import org.test.facade.BaseFacade;
/**
 * 测试意见建议业务处理
 * <p>command=1008</p>
 * @author Administrator
 *
 */
public class TestAdviceSuggestionFacade extends BaseFacade{
	
	public TestAdviceSuggestionFacade(){
		super("1008");
	}
	public static void main(String[] args) {
		TestAdviceSuggestionFacade test = new TestAdviceSuggestionFacade();
		test.getParams().put("userid", "007aa743f97743648d345126a19ff0fb");
		test.getParams().put("content", "你好");
		test.getParams().put("contact", "13718989524");
		System.out.println(test.sendRequest());
	}

}
