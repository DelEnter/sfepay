package com.ecpss.action;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import vpn.CaibaoUtil;


public class PayNoticeThread extends Thread {
	public volatile boolean exit = false;
	Logger logger = Logger.getLogger(PayNoticeThread.class.getName());
	String merchantOrderNo;
	String tradeMoneyType;
	double ordercount;
	int responseCode;
	String message;
	String email;
	String ip;
	int i=0;
	public PayNoticeThread(String orderNo,String type,double count,int resCode,String mes,String email,String ip){
		this.merchantOrderNo=orderNo;
		this.tradeMoneyType=type;
		this.ordercount=count;
		this.responseCode=resCode;
		this.message=mes;
		this.email=email;
		this.ip=ip;
	}
	public void run() {
		 while (!exit){
			 HttpClient client = new HttpClient(); 
			 client.setConnectionTimeout((int)10*1000);
			 client.setTimeout((int)10*1000);
			 PostMethod method = new PostMethod("http://www.etemall.com/xing/notice.php"); 
			 i++;
			 if(i<=10){
				try {
					 logger.info("进入推送支付结果等待。。。");
					 Thread.sleep(30*1000);
					 logger.info("第"+i+"次推送支付结果开始。。。");
		             NameValuePair[] query_data = new NameValuePair[7];
		             query_data[0]=new NameValuePair("BillNo",merchantOrderNo);
		             query_data[1]=new NameValuePair("Currency",tradeMoneyType);
		             query_data[2]=new NameValuePair("Amount",String.valueOf(ordercount));
		             query_data[3]=new NameValuePair("Succeed",responseCode+"");
		             query_data[4]=new NameValuePair("Result",message);
		             query_data[5]=new NameValuePair("email",email);
		             query_data[6]=new NameValuePair("ip",ip);
		             method.setRequestBody(query_data);
		             client.executeMethod(method);
		             String content = method.getResponseBodyAsString();
		             if("0000".equals(content)||i==10){
		            	 logger.info("推送结果成功！第"+i+"次");
		            	 exit=true;
		             }
		             method.releaseConnection();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					method.releaseConnection();
				}
			 }else{
				 exit=true;
				 logger.info("第10次推送同步交易失败！order："+merchantOrderNo);
			 }
		}
		
	}

	
}
