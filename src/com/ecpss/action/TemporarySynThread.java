package com.ecpss.action;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;

public class TemporarySynThread extends Thread {
	public volatile boolean exit = false;
	Logger logger = Logger.getLogger(TemporarySynThread.class.getName());
	private String res_orderStatus;
	private String orderNo;
	private String remark;
	private String postUrl;
	int i=0;
	public TemporarySynThread(String postUrl,String orderNo,String status,String remark){
		this.orderNo=orderNo;
		this.res_orderStatus=status;
		this.remark=remark;
		this.postUrl=postUrl;
	}
	public void run() {
		 while (!exit){
			 HttpClient client = new HttpClient(); 
			 client.setConnectionTimeout((int)20*1000);
			 client.setTimeout((int)20*1000);
			 PostMethod method = new UTF8PostMethod(postUrl); 
			 method.setRequestHeader("Referer", "www.sfepay.com");
			 i++;
			 if(i<=10){
				try {
					 logger.info("同步交易结果等待。。。");
					// Thread.sleep(6*1000);
					 logger.info("第"+i+"次推送交易结果开始。。。");
		             NameValuePair[] query_data = new NameValuePair[5];
		             /*query_data[0]=new NameValuePair("orderNo",orderNo);
		             query_data[1]=new NameValuePair("res_orderStatus",res_orderStatus);
		             query_data[2]=new NameValuePair("remark",remark);*/
		             query_data[0]=new NameValuePair("appid","1900012291");
		             query_data[1]=new NameValuePair("mch_id","wxce926ea78004260e");
		             query_data[2]=new NameValuePair("sign","1C826654D7DDBA531ACEDFDB8904DF65");
		             query_data[3]=new NameValuePair("fee_type","USD");
		             query_data[4]=new NameValuePair("date","20180925");
		             method.setRequestBody(query_data);
		             client.executeMethod(method);
		             String content = method.getResponseBodyAsString();
		             if("0000".equals((content+"").trim())){
		            	 logger.info("推送同步交易成功！第"+i+"次");
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
				 logger.info("第10次推送同步交易失败！order："+orderNo);
			 }
		}
		 
		
	}
	public static class UTF8PostMethod extends PostMethod{     
	    public UTF8PostMethod(String url){     
	    super(url);     
	    }     
	    @Override     
	    public String getRequestCharSet() {     
	        //return super.getRequestCharSet();     
	        return "UTF-8";     
	    }  
	}  

}
