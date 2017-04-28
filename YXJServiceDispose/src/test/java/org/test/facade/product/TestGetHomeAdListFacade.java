package org.test.facade.product;

import org.test.facade.BaseFacade;

public class TestGetHomeAdListFacade extends BaseFacade {
	
	public TestGetHomeAdListFacade() {
		super("3005");
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
		TestGetHomeAdListFacade test=new TestGetHomeAdListFacade();
		//test.getParams().put("uptypeid","1");
		
		System.out.println(test.sendRequest());
	}
}
