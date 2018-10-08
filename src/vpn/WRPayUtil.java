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

public class WRPayUtil {
	Logger logger = Logger.getLogger(WRPayUtil.class.getName());
	private static String postUrl="https://security.leaderspay.com/payment/api/payment";
	public void get(WRPayMessage trade){
	 NameValuePair transType  =new BasicNameValuePair("transType",trade.getTransType());
	 NameValuePair apiType  = new BasicNameValuePair("apiType",trade.getApiType());
	 NameValuePair transModel  = new BasicNameValuePair("transModel",trade.getTransModel());
	 NameValuePair EncryptionMode  = new BasicNameValuePair("EncryptionMode",trade.getEncryptionMode());
	 NameValuePair CharacterSet  = new BasicNameValuePair("CharacterSet",trade.getCharacterSet());
	 NameValuePair merNo  = new BasicNameValuePair("merNo",trade.getMerNo());
	 NameValuePair terNo  = new BasicNameValuePair("terNo",trade.getTerNo());
	 NameValuePair amount  = new BasicNameValuePair("amount",trade.getAmount());
	 NameValuePair currencyCode  = new BasicNameValuePair("currencyCode",trade.getCurrencyCode());
	 NameValuePair orderNo  = new BasicNameValuePair("orderNo",trade.getOrderNo());
	 NameValuePair goodsString  = new BasicNameValuePair("goodsString",trade.getGoodsString());
	 NameValuePair cardCountry  = new BasicNameValuePair("cardCountry",trade.getCardCountry());
	 NameValuePair cardState  = new BasicNameValuePair("cardState",trade.getCardState());
	 NameValuePair cardCity  = new BasicNameValuePair("cardCity",trade.getCardCity());
	 NameValuePair cardAddress  = new BasicNameValuePair("cardAddress",trade.getCardAddress());
	 NameValuePair cardZipCode  = new BasicNameValuePair("cardZipCode",trade.getCardZipCode());
	 NameValuePair cardFullName  = new BasicNameValuePair("cardFullName",trade.getCardFullName());
	 NameValuePair cardFullPhone  = new BasicNameValuePair("cardFullPhone",trade.getCardFullPhone());
	 NameValuePair cardEmail  = new BasicNameValuePair("cardEmail",trade.getCardEmail());
	 NameValuePair grCountry  = new BasicNameValuePair("grCountry",trade.getGrCountry());
	 NameValuePair grState  = new BasicNameValuePair("grState",trade.getGrState());
	 NameValuePair grCity  = new BasicNameValuePair("grCity",trade.getGrCity());
	 NameValuePair grAddress  = new BasicNameValuePair("grAddress",trade.getGrAddress());
	 NameValuePair grZipCode  = new BasicNameValuePair("grZipCode",trade.getGrZipCode());
	 NameValuePair grphoneNumber  = new BasicNameValuePair("grphoneNumber",trade.getGrphoneNumber());
	 NameValuePair grPerName  = new BasicNameValuePair("grPerName",trade.getGrPerName());
	 NameValuePair grEmail  = new BasicNameValuePair("grEmail",trade.getGrEmail());
	 NameValuePair cardNO  = new BasicNameValuePair("cardNO",trade.getCardNO());
	 NameValuePair expYear  = new BasicNameValuePair("expYear",trade.getExpYear());
	 NameValuePair expMonth  = new BasicNameValuePair("expMonth",trade.getExpMonth());
	 NameValuePair cvv  = new BasicNameValuePair("cvv",trade.getCvv());
	 NameValuePair payIP  = new BasicNameValuePair("payIP",trade.getPayIP());
	 NameValuePair merMgrURL  = new BasicNameValuePair("merMgrURL",trade.getMerMgrURL());
	 NameValuePair returnURL  = new BasicNameValuePair("returnURL",trade.getReturnURL());
	 NameValuePair notifyURL  = new BasicNameValuePair("notifyURL",trade.getNotifyURL());
	 NameValuePair webInfo  = new BasicNameValuePair("webInfo",trade.getWebInfo());
	 NameValuePair language  = new BasicNameValuePair("language",trade.getLanguage());
	 NameValuePair merremark  = new BasicNameValuePair("merremark",trade.getMerremark());
	 NameValuePair hashcode  = new BasicNameValuePair("hashcode",trade.getHashCode());
	
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(transType);
		nvps1.add(apiType);
		nvps1.add(transModel);
		nvps1.add(EncryptionMode);
		nvps1.add(CharacterSet);
		nvps1.add(merNo);
		nvps1.add(terNo);
		nvps1.add(amount);
		nvps1.add(currencyCode);
		nvps1.add(orderNo);
		nvps1.add(goodsString);
		nvps1.add(cardCountry);
		nvps1.add(cardState);
		nvps1.add(cardCity);
		nvps1.add(cardAddress);
		nvps1.add(cardZipCode);
		nvps1.add(cardFullName);
		nvps1.add(cardFullPhone);
		nvps1.add(cardEmail);
		nvps1.add(grCountry);
		nvps1.add(grState);
		nvps1.add(grCity);
		nvps1.add(grAddress);
		nvps1.add(grZipCode);
		nvps1.add(grEmail);
		nvps1.add(grphoneNumber);
		nvps1.add(grPerName);
		nvps1.add(cardNO);
		nvps1.add(expYear);
		nvps1.add(expMonth);
		nvps1.add(cvv);
		nvps1.add(payIP);
		nvps1.add(merMgrURL);
		nvps1.add(returnURL);
		nvps1.add(notifyURL);
		nvps1.add(webInfo);
		nvps1.add(language);
		nvps1.add(merremark);
		nvps1.add(hashcode);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(transType);
		nvps2.add(apiType);
		nvps2.add(transModel);
		nvps2.add(EncryptionMode);
		nvps2.add(CharacterSet);
		nvps2.add(merNo);
		nvps2.add(terNo);
		nvps2.add(amount);
		nvps2.add(currencyCode);
		nvps2.add(orderNo);
		nvps2.add(goodsString);
		nvps2.add(cardCountry);
		nvps2.add(cardState);
		nvps2.add(cardCity);
		nvps2.add(cardAddress);
		nvps2.add(cardZipCode);
		nvps2.add(cardFullName);
		nvps2.add(cardFullPhone);
		nvps2.add(cardEmail);
		nvps2.add(grCountry);
		nvps2.add(grState);
		nvps2.add(grCity);
		nvps2.add(grAddress);
		nvps2.add(grZipCode);
		nvps2.add(grEmail);
		nvps2.add(grphoneNumber);
		nvps2.add(grPerName);
		nvps2.add(payIP);
		nvps2.add(merMgrURL);
		nvps2.add(returnURL);
		nvps2.add(notifyURL);
		nvps2.add(webInfo);
		nvps2.add(language);
		nvps2.add(merremark);
		nvps2.add(hashcode);
		try{
			WRPayUtil h = new WRPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			JSONObject jasonObject = JSONObject.fromObject(result);
            Map<String, Object> map= (Map) jasonObject;
            trade.setRespCode(map.get("respCode")+"");
            trade.setTradeNo(map.get("tradeNo")+"");
            trade.setRespMsg(map.get("respMsg")+"");
            trade.setAcquirer(map.get("acquirer")+"");
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				WRPayUtil h = new WRPayUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map<String, Object> map= (Map) jasonObject;
	            trade.setRespCode(map.get("respCode")+"");
	            trade.setTradeNo(map.get("tradeNo")+"");
	            trade.setRespMsg(map.get("respMsg")+"");
	            trade.setAcquirer(map.get("acquirer")+"");
			} catch (Exception ex) {
				ex.printStackTrace();
				trade.setRespCode("");
				trade.setRespMsg("");
			}
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getRespCode()))){
				trade.setRespCode("sfe01");
				trade.setRespMsg("timeout!");
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
