package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 测试登录接口
 * <p>command=1002</p>
 * */
public class TestLoginFacade extends BaseFacade {
	
	public TestLoginFacade() {
		super("1002");
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
		TestLoginFacade test=new TestLoginFacade();
		
		test.getParams().put("telephone", "13718989524");
		test.getParams().put("verifycode", "736311");
		//test.getParams().put("openid", "2");
		System.out.println(test.sendRequest());
	}
}
