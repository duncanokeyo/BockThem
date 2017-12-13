package com.dans.apps.block;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Works as a proxy, accepts incoming connections from the web browser,
 * checks if this connection's address belongs to a website with "bad"
 * content ie explicit images etc.
 * 
 * limitations, only works for http
 * @author duncan
 *
 */
public class ProxyStarter {
	/**default listening port**/
	static int port = 3018;
	static ServerSocket serverSocket;
	static boolean run = true;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		if(args.length>0){
			port = Integer.valueOf(args[0]);
		}
		
		System.out.println(Constants.Instructions);
		Thread populate = new Thread(new Runnable() {

			public void run() {
				try {
					WebSiteChecker.populate();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		populate.start();
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				startListening();
			}
		});
		
		thread.start();//calls the run method
	}
	
	/**
	 * listens for incoming connections on 
	 * 
	 * should be called on a different thread
	 */
	public static void startListening(){
		Socket socket;
		if(serverSocket == null || serverSocket.isClosed()){
			try{
				serverSocket = new ServerSocket(port);
				//the proxy should continue listening without timeout.
				serverSocket.setSoTimeout(0);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	    System.out.println("Proxy listening on port "+port);
		while(run){
			try {
				byte [] buffer = new byte[6250];
				socket = serverSocket.accept();
				
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				
				final int bytesRead = in.read(buffer);

				String data = bytesRead > 0 ? new String(buffer, 0, bytesRead,
						"US-ASCII") : "";
				ProxyHelper helper = new ProxyHelper();
				helper.onHttpConnectionReceived(data, in, out);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
