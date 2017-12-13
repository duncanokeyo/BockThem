package com.dans.apps.block;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * simply forwards data to and fro the client and server and vice versa
 * @author dan
 *
 */
public class StreamTransporter implements Runnable{
    //where to read content
    private final OutputStream out;
    //where to forward content.
    private final InputStream in;
    int uid;
    boolean run=true;
    
    
    public StreamTransporter(OutputStream out,InputStream in) {
    	this.in=in;
    	this.out=out; 	
	}
    
	public void run() {
		int total=0;
		try{
			if(in!=null){
				byte[]buffer=new byte[4096];
				int len;
				
				while((len=in.read(buffer))!=-1){
					total=total+len;
					out.write(buffer,0,len);
				}
				
			}
		}catch(IOException e){
			
		}finally{
			run=false;
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
