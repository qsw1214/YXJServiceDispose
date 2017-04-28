package org.test.facade.system;

import org.test.facade.BaseFacade;

/**
 * 获取短信验证码接口
 * <p>command=1003</p>
 * */
public class TestApplyVerifyFacade extends BaseFacade {
	
	public TestApplyVerifyFacade() {
		super("1003");
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
		
		
		TestApplyVerifyFacade test=new TestApplyVerifyFacade();
		
		System.out.println(test.sendRequest());
	}
}
