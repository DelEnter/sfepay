package vpn;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class TestZMTUtil {
	private static String payUrl = "https://admin.ftpbill.com/AdMiN/WebSiteServlet";
	Logger logger = Logger.getLogger(TestZMTUtil.class.getName());
	public void paySfe(ZMTTradeurlMessage trade){
		 HttpClient client = new HttpClient(); 
		 PostMethod method = new PostMethod(payUrl); 
		try {
	         NameValuePair[] query_data = new NameValuePair[2];
	         query_data[0]=new NameValuePair("MerNo","1426");
	         query_data[1]=new NameValuePair("webSite",trade.getWebSite());
	        
	    
	         method.setRequestBody(query_data);
	         logger.info("提交数据：merNo:"+"1426"+" Amount:"+trade.getWebSite());
	         client.executeMethod(method);
	         String content = method.getResponseBodyAsString();
	         logger.info("返回数据："+content.trim());
/*	         JSONObject  jasonObject = JSONObject.fromObject(content);
	         Map map = (Map)jasonObject;
	         msg.setStatus(map.get("status").toString());
	         msg.setDesc(map.get("desc").toString());
	         msg.setSign(map.get("sign")+"");
	         msg.setSerialNo(map.get("serialNo")+"");*/
	         
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			method.releaseConnection();
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getTrade_status()))){
	            trade.setMid("");
	            trade.setTrade_status("");
			}
		}
	}

}
