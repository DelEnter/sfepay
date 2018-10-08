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

public class IPassPayUtil{
	Logger logger = Logger.getLogger(IPassPayUtil.class.getName());
	private static String postUrl="https://www.ipasspay.biz/index.php/Gateway/securepay";
	public void get(IPassPayMessage trade){
	 NameValuePair mid  =new BasicNameValuePair("mid",trade.getMid());
	 NameValuePair oid  = new BasicNameValuePair("oid",trade.getOid());
	 NameValuePair site_id  = new BasicNameValuePair("site_id",trade.getSite_id());
	 NameValuePair order_amount  = new BasicNameValuePair("order_amount",trade.getOrder_amount());
	 NameValuePair order_currency  = new BasicNameValuePair("order_currency",trade.getOrder_currency());
	 NameValuePair hash_info  = new BasicNameValuePair("hash_info",trade.getHash_info());
	 NameValuePair card_no  = new BasicNameValuePair("card_no",trade.getCard_no());
	 NameValuePair card_ex_month  = new BasicNameValuePair("card_ex_month",trade.getCard_ex_month());
	 //NameValuePair Amount  = new BasicNameValuePair("locale",trade.getAmount());//非必填
	 //NameValuePair Signature  = new BasicNameValuePair("ET_RECEIVING_ADD",trade.getSignature());//非必填
	 NameValuePair card_ex_year  = new BasicNameValuePair("card_ex_year",trade.getCard_ex_year());
	 NameValuePair card_cvv  = new BasicNameValuePair("card_cvv",trade.getCard_cvv());
	 NameValuePair bill_email  = new BasicNameValuePair("bill_email",trade.getBill_email());
	 NameValuePair bill_phone  = new BasicNameValuePair("bill_phone",trade.getBill_phone());
	 NameValuePair bill_country  = new BasicNameValuePair("bill_country",trade.getBill_country());
	 NameValuePair bill_state  = new BasicNameValuePair("bill_state",trade.getBillingstate());
	 NameValuePair bill_city  = new BasicNameValuePair("bill_city",trade.getBill_city());
	 NameValuePair bill_street  = new BasicNameValuePair("bill_street",trade.getBill_street());
	 NameValuePair bill_zip  = new BasicNameValuePair("bill_zip",trade.getBill_zip());
	 NameValuePair bill_firstname  = new BasicNameValuePair("bill_firstname",trade.getBill_firstname());
	 NameValuePair bill_lastname  = new BasicNameValuePair("bill_lastname",trade.getBill_lastname());
	 NameValuePair syn_url  = new BasicNameValuePair("syn_url",trade.getSyn_url());
	 NameValuePair asyn_url  = new BasicNameValuePair("asyn_url",trade.getAsyn_url());
	 NameValuePair source_ip  = new BasicNameValuePair("source_ip",trade.getSource_ip());
	 NameValuePair source_url  = new BasicNameValuePair("source_url",trade.getSource_url());
	 NameValuePair gateway_version  = new BasicNameValuePair("gateway_version",trade.getGateway_version());
	 NameValuePair uuid  = new BasicNameValuePair("uuid",trade.getUuid());
	 //UUID uuid2 = UUID.randomUUID();
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(mid);
		nvps1.add(oid);
		nvps1.add(site_id);//70227403
		nvps1.add(order_amount);
		nvps1.add(order_currency);
		nvps1.add(hash_info);
		
		nvps1.add(card_no);
		nvps1.add(card_ex_month);
		nvps1.add(card_ex_year);
		nvps1.add(card_cvv);

		nvps1.add(bill_email);
		nvps1.add(bill_phone);
		nvps1.add(bill_country);
		nvps1.add(bill_state);
		nvps1.add(bill_city);

		nvps1.add(bill_street);
		nvps1.add(bill_zip);
		nvps1.add(bill_firstname);
		nvps1.add(bill_lastname);
		nvps1.add(syn_url);
		nvps1.add(asyn_url);
		nvps1.add(source_ip);
		nvps1.add(source_url);
		nvps1.add(gateway_version);
		nvps1.add(uuid);
				
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(mid);
		nvps2.add(oid);
		nvps2.add(site_id);//70227403
		nvps2.add(order_amount);
		nvps2.add(order_currency);
		nvps2.add(hash_info);

		nvps2.add(bill_email);
		nvps2.add(bill_phone);
		nvps2.add(bill_country);
		nvps2.add(bill_state);
		nvps2.add(bill_city);

		nvps2.add(bill_street);
		nvps2.add(bill_zip);
		nvps2.add(bill_firstname);
		nvps2.add(bill_lastname);
		nvps2.add(syn_url);
		nvps2.add(asyn_url);
		nvps2.add(source_ip);
		nvps2.add(source_url);
		nvps2.add(gateway_version);
		nvps2.add(uuid);
		
		try{
			IPassPayUtil h = new IPassPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			JSONObject jasonObject = JSONObject.fromObject(result);
            Map<String, Object> map= (Map) jasonObject;
            
            if("1".equals(map.get("status")+"")){
            	trade.setInfo(map.get("info")+"");
            	trade.setOrder_status(jasonObject.getJSONObject("data").getString("order_status"));
            	trade.setPid(jasonObject.getJSONObject("data").getString("pid"));
            	trade.setBilling_desc(jasonObject.getJSONObject("data").getString("billing_desc"));
            }else{
            	trade.setInfo(map.get("info")+"");
            	trade.setOrder_status("6");
            	trade.setPid("");
            	trade.setBilling_desc("");
            }
           
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				IPassPayUtil h = new IPassPayUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map<String, Object> map= (Map) jasonObject;
	            if("1".equals(map.get("status")+"")){
	            	trade.setInfo(map.get("info")+"");
	            	trade.setOrder_status(jasonObject.getJSONObject("data").getString("order_status"));
	            	trade.setPid(jasonObject.getJSONObject("data").getString("pid"));
	            	trade.setBilling_desc(jasonObject.getJSONObject("data").getString("billing_desc"));
	            }else{
	            	trade.setInfo(map.get("info")+"");
	            	trade.setOrder_status("6");
	            	trade.setPid("");
	            	trade.setBilling_desc("");
	            }
			} catch (Exception ex) {
				ex.printStackTrace();
	            trade.setInfo("");
	            trade.setOrder_status("");
	            trade.setPid("");
	            trade.setBilling_desc("");
			}
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getOrder_status()))){
	            trade.setInfo("timeout");
	            trade.setOrder_status("sfe01");
	            trade.setPid("");
	            trade.setBilling_desc("");

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
