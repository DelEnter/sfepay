package vpn;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import vpn.VpnUtil_Moto;

public class PayClubUtil{
	Logger logger = Logger.getLogger(PayClubUtil.class.getName());
	private static String postUrl="https://int1.payclub.com/payment.jsp";
//	private static String postUrl="https://int1.payclub.com/payment_test.jsp";
	public void get(PayClubMessage trade){
	 NameValuePair p_mid  =new BasicNameValuePair("p_mid",trade.getP_mid());
	 NameValuePair p_account_num  = new BasicNameValuePair("p_account_num",trade.getP_account_num());
	 NameValuePair p_transaction_type  = new BasicNameValuePair("p_transaction_type",trade.getP_transaction_type());
	 NameValuePair p_order_num  = new BasicNameValuePair("p_order_num",trade.getP_order_num());
	 NameValuePair p_currency  = new BasicNameValuePair("p_currency",trade.getP_currency());
	 NameValuePair p_amount  = new BasicNameValuePair("p_amount",trade.getP_amount());
	 NameValuePair p_card_num  = new BasicNameValuePair("p_card_num",trade.getP_card_num());
	 NameValuePair p_card_expmonth  = new BasicNameValuePair("p_card_expmonth",trade.getP_card_expmonth());
	 NameValuePair p_card_expyear  = new BasicNameValuePair("p_card_expyear",trade.getP_card_expyear());
	 //NameValuePair Amount  = new BasicNameValuePair("locale",trade.getAmount());//非必填
	 //NameValuePair Signature  = new BasicNameValuePair("ET_RECEIVING_ADD",trade.getSignature());//非必填
	 NameValuePair p_card_csc  = new BasicNameValuePair("p_card_csc",trade.getP_card_csc());
	 NameValuePair p_card_issuingbank  = new BasicNameValuePair("p_card_issuingbank",trade.getP_card_issuingbank());
	 NameValuePair p_firstname  = new BasicNameValuePair("p_firstname",trade.getP_firstname());
	 NameValuePair p_lastname  = new BasicNameValuePair("p_lastname",trade.getP_lastname());
	 NameValuePair p_user_email  = new BasicNameValuePair("p_user_email",trade.getP_user_email());
	 NameValuePair p_user_phone  = new BasicNameValuePair("p_user_phone",trade.getP_user_phone());
	 NameValuePair p_user_ipaddress  = new BasicNameValuePair("p_user_ipaddress",trade.getP_user_ipaddress());
	 NameValuePair p_trans_url  = new BasicNameValuePair("p_trans_url",trade.getP_trans_url());
	 NameValuePair p_return_url  = new BasicNameValuePair("p_return_url",trade.getP_return_url());
	 NameValuePair p_bill_country  = new BasicNameValuePair("p_bill_country",trade.getP_bill_country());
	 NameValuePair p_bill_state  = new BasicNameValuePair("p_bill_state",trade.getP_bill_state());
	 NameValuePair p_bill_city  = new BasicNameValuePair("p_bill_city",trade.getP_bill_city());
	 NameValuePair p_bill_address  = new BasicNameValuePair("p_bill_address",trade.getP_bill_address());
	 NameValuePair p_bill_zip  = new BasicNameValuePair("p_bill_zip",trade.getP_bill_zip());
	 NameValuePair p_ship_firstname  = new BasicNameValuePair("p_ship_firstname",trade.getP_ship_firstname());
	 NameValuePair p_ship_lastname  = new BasicNameValuePair("p_ship_lastname",trade.getP_ship_lastname());
	 NameValuePair p_ship_country  = new BasicNameValuePair("p_ship_country",trade.getP_ship_country());
	 NameValuePair p_ship_state  = new BasicNameValuePair("p_ship_state",trade.getP_ship_state());
	 NameValuePair p_ship_city  = new BasicNameValuePair("p_ship_city",trade.getP_bill_city());
	 NameValuePair p_ship_address  = new BasicNameValuePair("p_ship_address",trade.getP_ship_address());
	 NameValuePair p_ship_zip  = new BasicNameValuePair("p_ship_zip",trade.getP_ship_zip());
	 NameValuePair p_product_name  = new BasicNameValuePair("p_product_name",trade.getP_product_name());
	 NameValuePair p_product_num  = new BasicNameValuePair("p_product_num",trade.getP_product_num());
	 NameValuePair p_product_desc  = new BasicNameValuePair("p_product_desc",trade.getP_product_desc());
	 NameValuePair p_signmsg  = new BasicNameValuePair("p_signmsg",trade.getP_signmsg());
	 
	 //UUID uuid2 = UUID.randomUUID();
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(p_mid);
		nvps1.add(p_account_num);
		nvps1.add(p_transaction_type);
		nvps1.add(p_order_num);
		nvps1.add(p_currency);
		nvps1.add(p_amount);
		
		nvps1.add(p_card_num);
		nvps1.add(p_card_expmonth);
		nvps1.add(p_card_expyear);
		nvps1.add(p_card_csc);		
		nvps1.add(p_card_issuingbank);
		
		nvps1.add(p_firstname);
		nvps1.add(p_lastname);
		nvps1.add(p_user_email);
		nvps1.add(p_user_phone);
		nvps1.add(p_user_ipaddress);
		nvps1.add(p_trans_url);
		nvps1.add(p_return_url);
		
		nvps1.add(p_bill_country);
		nvps1.add(p_bill_state);
		nvps1.add(p_bill_city);
		nvps1.add(p_bill_address);
		nvps1.add(p_bill_zip);
		
		nvps1.add(p_ship_firstname);
		nvps1.add(p_ship_lastname);		
		nvps1.add(p_ship_country);
		nvps1.add(p_ship_state);
		nvps1.add(p_ship_city);
		nvps1.add(p_ship_address);
		nvps1.add(p_ship_zip);
		
		nvps1.add(p_product_name);
		nvps1.add(p_product_num);
		nvps1.add(p_product_desc);
		
		nvps1.add(p_signmsg);
				
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(p_mid);
		nvps2.add(p_account_num);
		nvps2.add(p_transaction_type);
		nvps2.add(p_order_num);
		nvps2.add(p_currency);
		nvps2.add(p_amount);
		
		/*nvps2.add(p_card_num);
		nvps2.add(p_card_expmonth);
		nvps2.add(p_card_expyear);
		nvps2.add(p_card_csc);*/
		nvps2.add(p_card_issuingbank);
		
		nvps2.add(p_firstname);
		nvps2.add(p_lastname);
		nvps2.add(p_user_email);
		nvps2.add(p_user_phone);
		nvps2.add(p_user_ipaddress);
		nvps2.add(p_trans_url);
		nvps2.add(p_return_url);
		
		nvps2.add(p_bill_country);
		nvps2.add(p_bill_state);
		nvps2.add(p_bill_city);
		nvps2.add(p_bill_address);
		nvps2.add(p_bill_zip);
		nvps2.add(p_ship_firstname);
		nvps2.add(p_ship_lastname);
		
		nvps2.add(p_ship_country);
		nvps2.add(p_ship_state);
		nvps2.add(p_ship_city);
		nvps2.add(p_ship_address);
		nvps2.add(p_ship_zip);
		
		nvps2.add(p_product_name);
		nvps2.add(p_product_num);
		nvps2.add(p_product_desc);
		
		nvps2.add(p_signmsg);
		
		try{
			PayClubUtil h = new PayClubUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			JSONObject jasonObject = JSONObject.fromObject(result);
            Map<String, Object> map= (Map) jasonObject;
            
            trade.setP_pay_info(map.get("p_pay_info")+"");
        	trade.setP_pay_result(map.get("p_pay_result")+"");
        	trade.setP_trans_num(map.get("p_trans_num")+"");
        	trade.setP_remark(map.get("p_remark")+"");
           
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				PayClubUtil h = new PayClubUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map<String, Object> map= (Map) jasonObject;
	            	trade.setP_pay_info(map.get("p_pay_info")+"");
	            	trade.setP_pay_result(map.get("p_pay_result")+"");
	            	trade.setP_trans_num(map.get("p_trans_num")+"");
	            	trade.setP_remark(map.get("p_remark")+"");
			} catch (Exception ex) {
				ex.printStackTrace();
            	trade.setP_pay_info("");
            	trade.setP_pay_result("");
            	trade.setP_trans_num("");
            	trade.setP_remark("");
			}
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getP_pay_result()))){
            	trade.setP_pay_info("timeout");
            	trade.setP_pay_result("sfe01");
            	trade.setP_trans_num("");
            	trade.setP_remark("");
			}
		}
	}
	public String httpPost(List<NameValuePair> nvps, String url)
			throws Exception {
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		HttpClient httpclient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 125*1000);
		HttpConnectionParams.setSoTimeout(httpclient.getParams(), 125*2000);
		if(url.toLowerCase().startsWith("https")){
			httpclient = getInstance(httpclient);
		}
		HttpResponse response = httpclient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity);
		}
		if(statusCode==301||statusCode==302){
			Header[] headers = response.getHeaders("Location");
			for(Header header:headers){
				if(header.getName().equals("Location")){
					String res = this.httpPost(nvps, header.getValue());
					return res;
				}
			}
		}
		return result;
	}
	
	private static X509TrustManager trustManager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	public static HttpClient getInstance(HttpClient client)
			throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("https", 443, ssf));
		ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(
				registry);
		return new DefaultHttpClient(mgr, client.getParams());
	}
	
}
