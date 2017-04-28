package org.test.facade.system;

import org.test.facade.BaseFacade;

public class TestGetDeliveryAddressFacade extends BaseFacade{
	
	public TestGetDeliveryAddressFacade(){
		super("1009");
	}

	public static void main(String []args){
		TestGetDeliveryAddressFacade test = new TestGetDeliveryAddressFacade();
		System.out.println(test.sendRequest());
	}
}
