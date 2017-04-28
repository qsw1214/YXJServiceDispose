package org.test.facade.trade;

import org.test.facade.BaseFacade;

public class TestEditCartInfoFacade extends BaseFacade {
	
	public TestEditCartInfoFacade() {
		super("4002");
	}
	
	/*@Override
	public BaseResponse getResponseBean() {
		LoginResponseBean response=new LoginResponseBean();
		UserInfoData data=new UserInfoData();
		data.setAmount(1000.1);
		data.setJymoney(88);
		data.setShopnum("02201046");
		data.setUserid("222222222222222");
//		response.setData(data);
		return response;
	}*/

	public static void main(String []args){
		TestEditCartInfoFacade test=new TestEditCartInfoFacade();
		test.getParams().put("configflag","1");
		test.getParams().put("cartid", "");
		test.getParams().put("prodid", "1");
		test.getParams().put("quantity", "3");
		
		System.out.println(test.sendRequest());
	}
}
