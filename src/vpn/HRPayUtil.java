package vpn;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class HRPayUtil {
	Logger logger = Logger.getLogger(HRPayUtil.class.getName());
	private static String postUrl="http://60.205.124.93:8080/redirectjc/getResult1";
	public void get(HRPayMessage trade){
	 NameValuePair merNo  =new BasicNameValuePair("merNo",trade.getMerNo());
	 NameValuePair transType  = new BasicNameValuePair("transType",trade.getTransType());
	 NameValuePair amount  = new BasicNameValuePair("amount",trade.getAmount());
	 NameValuePair currencyCode  = new BasicNameValuePair("currencyCode",trade.getCurrencyCode());
	 NameValuePair orderNo  = new BasicNameValuePair("orderNo",trade.getOrderNo());
	 NameValuePair siteUrl  = new BasicNameValuePair("siteUrl",trade.getSiteUrl());
	 NameValuePair webInfo  = new BasicNameValuePair("webInfo",trade.getWebInfo());
	 NameValuePair language  = new BasicNameValuePair("language",trade.getLanguage());
	 NameValuePair cardCountry  = new BasicNameValuePair("cardCountry",trade.getCardCountry());
	 NameValuePair cardState  = new BasicNameValuePair("cardState",trade.getCardState());
	 NameValuePair cardCity  = new BasicNameValuePair("cardCity",trade.getCardCity());
	 NameValuePair cardAddress  = new BasicNameValuePair("cardAddress",trade.getCardAddress());
	 NameValuePair cardZipCode  = new BasicNameValuePair("cardZipCode",trade.getCardZipCode());
	 NameValuePair payIP  = new BasicNameValuePair("payIP",trade.getPayIP());
	 NameValuePair cardFirstName  = new BasicNameValuePair("cardFirstName",trade.getCardFirstName());
	 NameValuePair cardLastName=null;
	 if(StringUtils.isNotBlank(trade.getCardLastName())){
		 cardLastName  = new BasicNameValuePair("cardLastName",trade.getCardLastName());
	 }else{
		 cardLastName  = new BasicNameValuePair("cardLastName"," David");
		 
	 }
	 NameValuePair cardFullPhone  = new BasicNameValuePair("cardFullPhone",trade.getCardFullPhone());
	 NameValuePair grCountry  = new BasicNameValuePair("grCountry",trade.getGrCountry());
	 NameValuePair grState  = new BasicNameValuePair("grState",trade.getGrState());
	 NameValuePair grCity  = new BasicNameValuePair("grCity",trade.getGrCity());
	 NameValuePair grAddress  = new BasicNameValuePair("grAddress",trade.getGrAddress());
	 NameValuePair grZipCode  = new BasicNameValuePair("grZipCode",trade.getGrZipCode());
	 NameValuePair grEmail  = new BasicNameValuePair("grEmail",trade.getGrEmail());
	 NameValuePair grphoneNumber  = new BasicNameValuePair("grphoneNumber",trade.getGrphoneNumber());
	 NameValuePair grFirstName  = new BasicNameValuePair("grFirstName",trade.getGrFirstName());
	 NameValuePair grLastName=null;
	 if(StringUtils.isNotBlank(trade.getGrLastName())){
		 grLastName  = new BasicNameValuePair("grLastName",trade.getGrLastName());
	 }else{
		 grLastName  = new BasicNameValuePair("grLastName","David");
		 
	 }
	 NameValuePair pName  = new BasicNameValuePair("pName",trade.getpName());
	 NameValuePair hashCode  = new BasicNameValuePair("hashCode",trade.getHashCode());
	 NameValuePair cardNO  = new BasicNameValuePair("cardNO",trade.getCardNO());
	 NameValuePair expYear  = new BasicNameValuePair("expYear",trade.getExpYear());
	 NameValuePair expMonth  = new BasicNameValuePair("expMonth",trade.getExpMonth());
	 NameValuePair cvv  = new BasicNameValuePair("cvv",trade.getCvv());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(merNo);
		nvps1.add(transType);
		nvps1.add(amount);
		nvps1.add(currencyCode);
		nvps1.add(orderNo);
		nvps1.add(siteUrl);
		nvps1.add(webInfo);
		nvps1.add(language);
		nvps1.add(cardCountry);
		nvps1.add(cardState);
		nvps1.add(cardCity);
		nvps1.add(cardAddress);
		nvps1.add(cardZipCode);
		nvps1.add(payIP);
		nvps1.add(cardFirstName);
		nvps1.add(cardLastName);
		nvps1.add(cardFullPhone);
		nvps1.add(grCountry);
		nvps1.add(grState);
		nvps1.add(grCity);
		nvps1.add(grAddress);
		nvps1.add(grZipCode);
		nvps1.add(grEmail);
		nvps1.add(grphoneNumber);
		nvps1.add(grFirstName);
		nvps1.add(grLastName);
		nvps1.add(pName);
		nvps1.add(hashCode);
		nvps1.add(cardNO);
		nvps1.add(expYear);
		nvps1.add(expMonth);
		nvps1.add(cvv);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(merNo);
		nvps2.add(transType);
		nvps2.add(amount);
		nvps2.add(currencyCode);
		nvps2.add(orderNo);
		nvps2.add(siteUrl);
		nvps2.add(webInfo);
		nvps2.add(language);
		nvps2.add(cardCountry);
		nvps2.add(cardState);
		nvps2.add(cardCity);
		nvps2.add(cardAddress);
		nvps2.add(cardZipCode);
		nvps2.add(payIP);
		nvps2.add(cardFirstName);
		nvps2.add(cardLastName);
		nvps2.add(cardFullPhone);
		nvps2.add(grCountry);
		nvps2.add(grState);
		nvps2.add(grCity);
		nvps2.add(grAddress);
		nvps2.add(grZipCode);
		nvps2.add(grEmail);
		nvps2.add(grphoneNumber);
		nvps2.add(grFirstName);
		nvps2.add(grLastName);
		nvps2.add(pName);
		nvps2.add(hashCode);
		try{
			HRPayUtil h = new HRPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			JSONObject jasonObject = JSONObject.fromObject(result);
            Map<String, Object> map= (Map) jasonObject;
            trade.setRes_respCode(map.get("respCode")+"");
            trade.setRes_tradeNo(map.get("tradeNo")+"");
            trade.setRes_respMsg(map.get("respMsg")+"");
            trade.setRes_acquirer(map.get("acquirer")+"");
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				HRPayUtil h = new HRPayUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map<String, Object> map= (Map) jasonObject;
	            trade.setRes_respCode(map.get("respCode")+"");
	            trade.setRes_tradeNo(map.get("tradeNo")+"");
	            trade.setRes_respMsg(map.get("respMsg")+"");
	            trade.setRes_acquirer(map.get("acquirer")+"");
			} catch (Exception ex) {
				ex.printStackTrace();
				trade.setRes_respCode("");
				trade.setRes_respMsg("");
			}
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getRes_respCode()))){
				trade.setRes_respCode("sfe01");
				trade.setRes_respMsg("timeout!");
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
