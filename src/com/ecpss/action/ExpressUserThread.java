package com.ecpss.action;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;

public class ExpressUserThread extends Thread {
	public volatile boolean exit = false;
	Logger logger = Logger.getLogger(ExpressUserThread.class.getName());	
	
	private CommonService commonService;
	private InternationalMerchant mer;
	
	private String pt_code;//平台标识
	private String customercode;//平台商户号
	private String address;//	地址
	private String true_name;//真实姓名
	private String phone;//电话
	private String postUrl;
		
	int i=0;		
	
	public ExpressUserThread(CommonService commonService,InternationalMerchant mer,String pt_code, String customercode,
			String address, String true_name, String phone, String postUrl) {
		super();
		this.commonService = commonService;
		this.mer = mer;
		this.pt_code = pt_code;
		this.customercode = customercode;
		this.address = address;
		this.true_name = true_name;
		this.phone = phone;
		this.postUrl = postUrl;
	}
	
	public void run() {
		 while (!exit){
			 HttpClient client = new HttpClient(); 
			 client.setConnectionTimeout((int)20*1000);
			 client.setTimeout((int)20*1000);
			 PostMethod method = new UTF8PostMethod(postUrl); 
			 method.setRequestHeader("authenticate", "123456");
			 i++;
			 if(i<=10){
				try {
					 logger.info("同步商户信息结果等待。。。");
					 Thread.sleep(6*1000);
					 logger.info("第"+i+"次同步商户信息开始。。。");
		             NameValuePair[] query_data = new NameValuePair[5];
		             query_data[0]=new NameValuePair("pt_code",pt_code);		             
		             query_data[1]=new NameValuePair("customercode",customercode);
		             query_data[2]=new NameValuePair("address",address);
		             query_data[3]=new NameValuePair("phone",phone);
		             query_data[4]=new NameValuePair("true_name",true_name);
		             method.setRequestBody(query_data);
		             client.executeMethod(method);
		           
		             String content = method.getResponseBodyAsString();
		             
		             JSONObject json=JSONObject.fromObject(content);

		             if("0".equals(json.get("status"))){
		            	 logger.info("********蜂皇注册用户状态************,蜂皇商户号"+json.getJSONObject("data").getString("customercode")+"   同步商户信息状态：" +json.get("status")+"   蜂皇用户名：" +json.getJSONObject("data").getString("username")+"   蜂皇密码：" +json.getJSONObject("data").getString("pwd"));
		            	 logger.info("同步商户信息成功！第"+i+"次");
		            	 exit=true;
		             }else{
		            	 logger.info("同步商户信息状态：" +json.get("status")+"   蜂皇用户名：" +json.get("message"));
		             }
		           
		            mer.setExpmerchantno(Long.parseLong(json.getJSONObject("data").getString("customercode")));			            
		     		mer.setExpuername(json.getJSONObject("data").getString("username"));
		     		mer.setExppassword(json.getJSONObject("data").getString("pwd"));
		     		mer.setExpopenstatus("1");
		     		commonService.update(mer);
		     		
		             method.releaseConnection();
				} catch (Exception e) {
					// TODO: handle exception					
					e.printStackTrace();
					method.releaseConnection();
				}
			 }else{
				 exit=true;
				 logger.info("第10次同步商户信息失败！");
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
