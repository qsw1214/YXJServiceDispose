package org.test.facade.trade;

import org.test.facade.BaseFacade;

public class TestGetCartInfoFacade extends BaseFacade {
	
	public TestGetCartInfoFacade() {
		super("4001");
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
		TestGetCartInfoFacade test=new TestGetCartInfoFacade();
		//test.getParams().put("configflag","1");

		
		System.out.println(test.sendRequest());
	}
}
