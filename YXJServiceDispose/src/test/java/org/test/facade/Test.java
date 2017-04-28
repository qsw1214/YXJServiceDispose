package org.test.facade;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.net.SocketServer;
import org.test.facade.system.TestLoginFacade;
import org.test.facade.system.TestQueryUserInfoFacade;
import org.test.facade.trade.TestEditCartInfoFacade;

import com.youxianji.util.DateUtil;


public class Test {
	public static void main(String[] args) {
		
		TestEditCartInfoFacade test1=new TestEditCartInfoFacade();
		test1.getParams().put("configflag","1");
		test1.getParams().put("cartid", "");
		test1.getParams().put("prodid", "1");
		test1.getParams().put("quantity", "1");
		
		for(int i=0;i<15;i++){
			new TestThread(test1).start();
		}
		//System.out.println("dd");
		
        /*Calendar cale = Calendar.getInstance();
	    //
		Date date_sixmonth = cale.getTime();
		
		System.out.println(DateUtil.toStr(date_sixmonth,"yyyy-MM-dd HH:mm:ss"));

		cale.add(Calendar.DATE, -6);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		System.out.println(DateUtil.toStr(cale.getTime(),"yyyy-MM-dd HH:mm:ss"));
		*/
	}
	public void server(){
		ServerSocket server=null;
		try {
			server=new ServerSocket(3823);
			int count=1;
			while(true){
				Socket socket=server.accept();
				new SocketThread(count,socket).start();
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
