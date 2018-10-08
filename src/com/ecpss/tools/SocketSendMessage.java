package com.ecpss.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SocketSendMessage {
	
	
	public static void main(String[] args){
		URL url;
		try {
			url = new URL("http://192.168.1.23:8888/pay/payresult.jsp");
		
			URLConnection connection = url.openConnection(); 
			connection.setDoOutput(true); 
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
			out.write("BillNo=1271334061500&Amount=95.0&Currency=1&Succeed=88&Result=Successful&MD5info=874E1B2DD43ABAB8E7357E62E66BCB2E"); //这里组织提交信息 
			
			out.flush(); 
			out.close(); 
			//获取返回数据 
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			String line = null; 
			StringBuffer content= new StringBuffer(); 
			while((line = in.readLine()) != null) 
			{ 
			   //line为返回值，这就可以判断是否成功、 
			    content.append(line);
			    //System.out.println(content);
			} 
		in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
