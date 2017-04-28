package org.test.facade.system;

import org.test.facade.BaseFacade;

public class TestConfigDeliveryAddressFacade extends BaseFacade{

	public TestConfigDeliveryAddressFacade(){
		super("1010");
	}
	
	public static void main(String []args){
		TestConfigDeliveryAddressFacade test = new TestConfigDeliveryAddressFacade();
		
		test.getParams().put("configflag", "1");
		test.getParams().put("daid", "3a71eff9989440abab613089665d0a79");
		test.getParams().put("receivephone", "13671277367");
		test.getParams().put("receivename", "席智星");
		test.getParams().put("receiveaddress", "收货地址6");
		test.getParams().put("longitude", "116.647404");
		test.getParams().put("latitude", "39.906578");
		test.getParams().put("isdefault", "1");
		
		System.out.println(test.sendRequest());
	}
}
