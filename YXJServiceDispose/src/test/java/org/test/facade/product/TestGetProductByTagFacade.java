package org.test.facade.product;

import org.test.facade.BaseFacade;
/**
 * 测试按标签获取商品接口
 * <p>command=3008</p>
 * @author Administrator
 *
 */
public class TestGetProductByTagFacade extends BaseFacade{
	
	public TestGetProductByTagFacade(){
		super("3008");
	}
	public static void main(String[] args) {
		TestGetProductByTagFacade test = new TestGetProductByTagFacade();
		test.getParams().put("userid", "007aa743f97743648d345126a19ff0fb");
		test.getParams().put("telephone", "13671277367");
		test.getParams().put("tagid", "1");
		System.out.println(test.sendRequest());

	}
}
