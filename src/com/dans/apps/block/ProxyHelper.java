package com.dans.apps.block;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

public class ProxyHelper {
	
	public void onHttpConnectionReceived(String header,InputStream in,OutputStream out){
		if(header!=null){
		String [] keyValues = header.split("\r\n");
		String host = null;
		//parse for the value of host
		for(String s:keyValues){
			//System.out.println("Key : Values ->"+s);
			String [] keyValue = s.split(":");
			if(keyValue[0].toLowerCase().trim().equals("host")){
				host=keyValue[1].trim();
			}
		}
		
		System.out.println("Checking remote host "+host);
		if(!isBadWebSite(host)){
			//forward connection to and fro
			System.out.println("A good domain .. establishing connection with the remote server");
			try {
				establishConnection(header, host, in, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//drop connection 
			System.out.println("A bad domain. Dropping connection ..... ");
			try {
				out.write(Constants.ErrorResponse.getBytes());
				out.flush();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		}
	}
	
	
	public boolean isBadWebSite(String remoteHost){
		return WebSiteChecker.checkIfBadHost(remoteHost);
	}
	
	
	public void establishConnection(String data,String host, InputStream in, OutputStream out) throws Exception{
		Socket serverSocket = new Socket();
		SocketAddress address = null;
		if(Thread.interrupted()){
			return;
		}
		serverSocket.bind(address);
		
		System.out.println("Initiating connection to the remote server");
		Long startTime = System.nanoTime();
		serverSocket.connect(new InetSocketAddress(host,80));
		Long endTime = System.nanoTime();
		Long connectionTime = endTime-startTime;
		
		Long time= TimeUnit.SECONDS.convert(connectionTime, TimeUnit.NANOSECONDS);
		System.out.println("Connection to the remote server initiated ( connection time : "+connectionTime+") nanoseconds");
		
		System.out.println();
		
		InputStream serverInputStream = serverSocket.getInputStream();
		OutputStream serverOutPutStream = serverSocket.getOutputStream();
		
		serverOutPutStream.write(data.getBytes());
		//server to client
		StreamTransporter serverToClient = new StreamTransporter(out, serverInputStream);
		//client to server
		StreamTransporter clientToServer = new StreamTransporter(serverOutPutStream, in);
		
		Thread serverToClientThread = new Thread(serverToClient);
		Thread clientToServerThread = new Thread(clientToServer);
		
		serverToClientThread.start();
		clientToServerThread.start();
		
		
		
	}

}
