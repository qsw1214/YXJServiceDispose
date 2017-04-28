package org.test.facade.system;

import org.test.facade.BaseFacade;

public class TestGetBandBankInfoFacade extends BaseFacade {
	public TestGetBandBankInfoFacade(){
		super("c003");
	}

	public static void main(String[] args) {
		TestGetBandBankInfoFacade test = new TestGetBandBankInfoFacade();
		test.getParams().put("userid", "1703111102420003");
		test.getParams().put("telephone", "13333333333");
		//test.getParams().put("cashamount", "1");
		
		System.out.println(test.sendRequest());
	}

}
