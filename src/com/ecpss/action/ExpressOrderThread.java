package com.ecpss.action;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.ecpss.action.express.ExpressInfoAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;
import com.ecpss.util.HttpsClientUtil;

public class ExpressOrderThread extends Thread {
	public volatile boolean exit = false;
	Logger logger = Logger.getLogger(ExpressOrderThread.class.getName());
	
	private String pt_code;
	private String customercode;
	private String orders;
	private String postUrl;

	private CommonService commonService;
	private InternationalTradeinfo trade;
	
	int i=0;
	
	public ExpressOrderThread(String pt_code, String customercode,
			String orders, String postUrl, CommonService commonService,
			InternationalTradeinfo trade) {
		super();
		this.pt_code = pt_code;
		this.customercode = customercode;
		this.orders = orders;
		this.postUrl = postUrl;
		this.commonService = commonService;
		this.trade = trade;
	}
	
	public void run() {
		 while (!exit){
			 HttpClient client = new HttpClient(); 
			 client.setConnectionTimeout((int)20*1000);
			 client.setTimeout((int)20*1000);
			 PostMethod method = new UTF8PostMethod(postUrl); 

			 method.setRequestHeader("authenticate", "123456");

			// method.setRequestHeader("OrderUrl", "192.168.1.188:8082");

			 i++;
			 if(i<=30){
				try {
					 logger.info("下单结果等待。。。");
					 Thread.sleep(1*1000);
					 logger.info("第"+i+"次下单开始。。。");
		             NameValuePair[] query_data = new NameValuePair[3];
		             query_data[0]=new NameValuePair("pt_code",pt_code);		             
		             query_data[1]=new NameValuePair("customercode",customercode);
		             query_data[2]=new NameValuePair("orders","["+orders+"]");
		       
		             method.setRequestBody(query_data);
		             client.executeMethod(method);
		             logger.info("********传递信息***********,平台标识："+pt_code+"   商户号："+customercode+"   订单集合："+orders);
		             String content = method.getResponseBodyAsString();
		             logger.info("********返回信息***********,content："+content);
		             
		             JSONObject json=JSONObject.fromObject(content);
		             logger.info("*******状态***********,status："+json.get("status"));

		             if("0".equals(json.getString("status"))){
		            	 logger.info("下单成功！第"+i+"次");
		            	 exit=true;
		             }
		             trade.setExpordersta(json.getString("status"));
		             trade.setExpordermess(json.getString("message"));
		             commonService.update(trade);
		             
		             method.releaseConnection();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					method.releaseConnection();
				}
			 }else{
				 exit=true;
				 logger.info("第30次下单失败！");
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
