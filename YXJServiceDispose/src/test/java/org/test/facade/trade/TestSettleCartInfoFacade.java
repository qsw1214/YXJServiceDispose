package org.test.facade.trade;

import org.test.facade.BaseFacade;

import com.youxianji.util.GsonTools;

public class TestSettleCartInfoFacade extends BaseFacade {
	
	public TestSettleCartInfoFacade() {
		super("4003");
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
		TestSettleCartInfoFacade test=new TestSettleCartInfoFacade();
		DetailBean db = new DetailBean();
		db.setCartid("6e1638bb1cbb48bb812248f717bc65c0");
		test.getParams().put("detaillist",GsonTools.getJsonString(db));
		System.out.println(test.sendRequest());
	}
	
	
}

class DetailBean{
	private String cartid;

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	
	
}
