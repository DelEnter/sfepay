package com.ecpss.action.pay.dcc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DccUtil {
	private DCCMessage dccMessage;
 public DCCMessage getDcc(){
	 return this.dccMessage;
 }
public DCCMessage getDccMessage() {
    byte [] byts=this.dccMessage.getMessage(); 	
    InputStream in = null;
	OutputStream outs=null;
	
	  
    try {
		Socket sockets = new Socket("30.197.81.253", 6789);
		if (sockets.isConnected()) {
			outs = sockets.getOutputStream();				
 			outs.write(byts);
			in = sockets.getInputStream();
		byte[] bytss=new byte[300];
		    in.read(bytss);
		    AnalysisDCC cm=new AnalysisDCC();
		    cm.analysisDcc(this.getHexString(bytss));		    
          this.dccMessage=cm.getCmesag();
		}
		
	} catch (UnknownHostException e) {

		e.printStackTrace();
	} catch (IOException e) {

		e.printStackTrace();
	}
	finally {
		try {

			outs.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		    
	
	return dccMessage;
}
public  String getHexString( byte[] b) {
	String tem="";
	   for (int i = 0; i < b.length; i++) {    
	     String hex = Integer.toHexString(b[i] & 0xFF);    
	     if (hex.length() == 1) {    
	       hex = '0' + hex;    
	     }    
	    tem+=hex.toUpperCase();    
	   }    
	  return tem;
	}	
public void setDccMessage(DCCMessage dccMessage) {
	this.dccMessage = dccMessage;
}

}
