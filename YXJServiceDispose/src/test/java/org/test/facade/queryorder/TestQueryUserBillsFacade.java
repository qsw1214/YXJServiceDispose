package org.test.facade.queryorder;

import org.test.facade.BaseFacade;

public class TestQueryUserBillsFacade extends BaseFacade{

	public TestQueryUserBillsFacade(){
		super("2003");
	}
	
	public static void main(String []args){
		TestQueryUserBillsFacade test = new TestQueryUserBillsFacade();
		test.getParams().put("page", "1");
		System.out.println(test.sendRequest());
	}
}
