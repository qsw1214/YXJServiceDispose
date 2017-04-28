package org.test.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketThread extends Thread{
	private int i;
	private Socket socket;
	public SocketThread(){
		
	}
	public SocketThread(int i,Socket socket) {
		this.socket=socket;
		this.i=i;
	}
	@Override
	public void run() {
		BufferedReader br=null;
		try {
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str=null;
			while((str=br.readLine())!=null){
				System.out.println("线程"+i+":"+str);
			}
			System.out.println("端口关闭");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
