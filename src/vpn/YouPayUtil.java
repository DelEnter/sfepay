package vpn;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class YouPayUtil {
	private static String payUrl = "https://pay.kcheckout.com/gateway";
	Logger logger = Logger.getLogger(YouPayUtil.class.getName());
	public void paySfe(YouPayMessage msg){
		 HttpClient client = new HttpClient(); 
		 PostMethod method = new PostMethod(payUrl); 
		try {
	         NameValuePair[] query_data = new NameValuePair[39];
	         query_data[0]=new NameValuePair("MerNo",msg.getMerNo());
	         query_data[1]=new NameValuePair("BillNo",msg.getBillNo());
	         query_data[2]=new NameValuePair("Amount",msg.getAmount());
	         query_data[3]=new NameValuePair("Currency",msg.getCurrency());
	         query_data[4]=new NameValuePair("Language",msg.getLanguage());
	         query_data[5]=new NameValuePair("ReturnURL",msg.getReturnURL());
	         query_data[6]=new NameValuePair("MD5info",msg.getMD5info());
	         query_data[7]=new NameValuePair("ipAddr",msg.getIpAddr());
	         query_data[8]=new NameValuePair("Remark",msg.getRemark());
	         query_data[9]=new NameValuePair("tradeUrl",msg.getTradeUrl());
	         query_data[10]=new NameValuePair("TradeDate",msg.getTradeDate());
	         query_data[11]=new NameValuePair("cardNum",msg.getCardNum());
	         query_data[12]=new NameValuePair("cvv2",msg.getCvv2());
	         query_data[13]=new NameValuePair("month",msg.getMonth());
	         query_data[14]=new NameValuePair("year",msg.getYear());
	         query_data[15]=new NameValuePair("cardbank",msg.getCardbank());
	         query_data[16]=new NameValuePair("cardType",msg.getCardType());
	         query_data[17]=new NameValuePair("firstname",msg.getFirstname());
	         query_data[18]=new NameValuePair("lastname",msg.getLastname());
	         query_data[19]=new NameValuePair("email",msg.getEmail());
	         query_data[20]=new NameValuePair("country",msg.getCountry());
	         query_data[21]=new NameValuePair("state",msg.getState());
	         query_data[22]=new NameValuePair("city",msg.getCity());
	         query_data[23]=new NameValuePair("address",msg.getAddress());
	         query_data[24]=new NameValuePair("phone",msg.getPhone());
	         query_data[25]=new NameValuePair("zipcode",msg.getZipcode());
	         query_data[26]=new NameValuePair("shippingFirstName",msg.getShippingFirstName());
	         query_data[27]=new NameValuePair("shippingLastName",msg.getShippingLastName());
	         query_data[28]=new NameValuePair("shippingEmail",msg.getShippingEmail());
	         query_data[29]=new NameValuePair("shippingPhone",msg.getShippingPhone());
	         query_data[30]=new NameValuePair("shippingZipcode",msg.getShippingZipcode());
	         query_data[31]=new NameValuePair("shippingAddress",msg.getShippingAddress());
	         query_data[32]=new NameValuePair("shippingCity",msg.getShippingCity());
	         query_data[33]=new NameValuePair("shippingSstate",msg.getShippingSstate());
	         query_data[34]=new NameValuePair("shippingCountry",msg.getShippingCountry());
	         query_data[35]=new NameValuePair("products",msg.getProducts());
	         query_data[36]=new NameValuePair("productCategory",msg.getProductCategory());
	         query_data[37]=new NameValuePair("address2",msg.getAddress2());
	         query_data[38]=new NameValuePair("shippingAddress2",msg.getShippingAddress2());
	         method.setRequestBody(query_data);
	         logger.info("提交数据：merNo:"+msg.getMerNo()+" Amount:"+msg.getAmount()+" Currency:"+msg.getCurrency());
	         client.executeMethod(method);
	         String content = method.getResponseBodyAsString();
	         logger.info("返回数据："+content.trim());
	         JSONObject  jasonObject = JSONObject.fromObject(content);
	         Map map = (Map)jasonObject;
	         msg.setStatus(map.get("status").toString());
	         msg.setDesc(map.get("desc").toString());
	         msg.setSign(map.get("sign")+"");
	         msg.setSerialNo(map.get("serialNo")+"");
	         
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			method.releaseConnection();
		}finally{
			if(StringUtils.isBlank(msg.getStatus())){
				msg.setStatus("0");
				msg.setRemark("youPay timeOut!");
			}
		}
	}

}
