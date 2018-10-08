package vpn;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class SfeUtil {
	private static String payUrl = "http://localhost:8080/sfepay/merchant";
	Logger logger = Logger.getLogger(SfeUtil.class.getName());
	public void paySfe(SfeMessage msg){
		 HttpClient client = new HttpClient(); 
		 PostMethod method = new UTF8PostMethod(payUrl); 
		try {
	         NameValuePair[] query_data = new NameValuePair[1];
	         query_data[0]=new NameValuePair("merNo",msg.getMerNo());
//	         query_data[1]=new NameValuePair("Amount",msg.getAmount());
//	         query_data[2]=new NameValuePair("Currency",msg.getCurrency());
//	         query_data[3]=new NameValuePair("tradeAdd",msg.getTradeAdd());
//	         query_data[4]=new NameValuePair("ReturnURL",msg.getReturnURL());
//	         query_data[5]=new NameValuePair("cardNo",msg.getCardNo());
//	         query_data[6]=new NameValuePair("cvv2",msg.getCvv2());
//	         query_data[7]=new NameValuePair("month",msg.getMonth());
//	         query_data[8]=new NameValuePair("year",msg.getYear());
//	         query_data[9]=new NameValuePair("merchantOrderNo",msg.getMerchantOrderNo());
//	         query_data[10]=new NameValuePair("MD5key",msg.getMD5key());
//	         query_data[11]=new NameValuePair("ip",msg.getIp());
//	         query_data[12]=new NameValuePair("cookie",msg.getCookie());
//	         query_data[13]=new NameValuePair("userbrowser",msg.getUserbrowser());
//	         query_data[14]=new NameValuePair("cartype",msg.getCartype());
//	         query_data[15]=new NameValuePair("country",msg.getCountry());
//	         query_data[16]=new NameValuePair("firstname",msg.getFirstname());
//	         query_data[17]=new NameValuePair("lastname",msg.getLastname());
//	         query_data[18]=new NameValuePair("email",msg.getEmail());
//	         query_data[19]=new NameValuePair("phone",msg.getPhone());
//	         query_data[20]=new NameValuePair("state",msg.getState());
//	         query_data[21]=new NameValuePair("city",msg.getCity());
//	         query_data[22]=new NameValuePair("address",msg.getAddress());
//	         query_data[23]=new NameValuePair("zipcode",msg.getZipcode());
//	         query_data[24]=new NameValuePair("products",msg.getProducts());
//	         query_data[25]=new NameValuePair("shippingFirstName",msg.getShippingFirstName());
//	         query_data[26]=new NameValuePair("shippingLastName",msg.getShippingLastName());
//	         query_data[27]=new NameValuePair("shippingAddress",msg.getShippingAddress());
//	         query_data[28]=new NameValuePair("shippingCity",msg.getShippingCity());
//	         query_data[29]=new NameValuePair("shippingSstate",msg.getShippingSstate());
//	         query_data[30]=new NameValuePair("shippingCountry",msg.getShippingCountry());
//	         query_data[31]=new NameValuePair("shippingZipcode",msg.getShippingZipcode());
//	         query_data[32]=new NameValuePair("shippingEmail",msg.getShippingEmail());
//	         query_data[33]=new NameValuePair("shippingPhone",msg.getShippingPhone());
//	         query_data[34]=new NameValuePair("tradetime",msg.getTradetime().toString());
//	         query_data[35]=new NameValuePair("cardBank",msg.getCardBank().toString());
//	         query_data[36]=new NameValuePair("xingChanel",msg.getXingChanel().toString());
//	         query_data[37]=new NameValuePair("maxMindInfo",msg.getMaxMindInfo());
	         method.setRequestBody(query_data);
	         logger.info("提交数据：merNo:"+msg.getMerNo()+" Amount:"+msg.getAmount()+" Currency:"+msg.getCurrency()+" merchantOrderNo:"+msg.getMerchantOrderNo()+" cartype:"+msg.getCartype());
	         client.executeMethod(method);
	         String content = method.getResponseBodyAsString();
	         logger.info("返回数据："+content.trim());
	         String resMsg[]=content.split("&");
	 		for(int i=0;i<resMsg.length;i++){
	 			String resPram[]=resMsg[i].split("=");
	 			if(resPram.length>1){
	 				if("succeed".equals(resPram[0]+"")){
	 					msg.setResponseCode(Integer.valueOf(resPram[1]));
	 				}
	 				if("billaddress".equals(resPram[0]+"")){
	 					msg.setBilladdress(resPram[1]);
	 				}
	 				if("remark".equals(resPram[0]+"")){
	 					msg.setRemark(resPram[1]); 
	 				}
	 			}
	 		}
	         method.releaseConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			method.releaseConnection();
		}finally{
			if(StringUtils.isBlank(String.valueOf(msg.getResponseCode()))){
				msg.setResponseCode(0);
				msg.setRemark("sfe02");
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
