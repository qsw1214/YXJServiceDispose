package org.test.facade;

public class TestThread extends Thread {
	private BaseFacade base;
	public TestThread(){
		
	}
	public TestThread(BaseFacade base) {
		this.base=base;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(base.sendRequest());
	}
}
