package org.test.facade.product;

import org.test.facade.BaseFacade;

/**
 * 测试获取商品标签接口
 * <p>command=3007</p>
 * @author Administrator
 *
 */
public class TestGetProductTagsFacade extends BaseFacade{
	
	public TestGetProductTagsFacade(){
		super("3007");
	}
	public static void main(String[] args) {
		TestGetProductTagsFacade test = new TestGetProductTagsFacade();
		test.getParams().put("userid", "007aa743f97743648d345126a19ff0fb");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("tagid", "1");
		System.out.println(test.sendRequest());

	}

}
