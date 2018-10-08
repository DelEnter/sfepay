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

public class ZMTPayUtil{
	Logger logger = Logger.getLogger(ZMTPayUtil.class.getName());
	private static String postUrl="https://payment.e-psp.com/payment/TPInterface";
	public void get(ZMTPayMessage trade){
	 NameValuePair merNo  =new BasicNameValuePair("merNo",trade.getMerNo());
	 NameValuePair billNo  = new BasicNameValuePair("billNo",trade.getBillNo());
	 NameValuePair payCurrency  = new BasicNameValuePair("payCurrency",trade.getPayCurrency());
	 NameValuePair amount  = new BasicNameValuePair("amount",trade.getAmount());
	 NameValuePair currency  = new BasicNameValuePair("currency",trade.getCurrency());
	 NameValuePair website  = new BasicNameValuePair("website",trade.getWebsite());
	 NameValuePair ie_language  = new BasicNameValuePair("ie_language",trade.getIe_language());
	 NameValuePair drawee  = new BasicNameValuePair("drawee",trade.getDrawee());
	 //NameValuePair Amount  = new BasicNameValuePair("locale",trade.getAmount());//非必填
	 //NameValuePair Signature  = new BasicNameValuePair("ET_RECEIVING_ADD",trade.getSignature());//非必填
	 NameValuePair ET_GOODS  = new BasicNameValuePair("ET_GOODS",trade.getET_GOODS());
	 NameValuePair remark  = new BasicNameValuePair("remark",trade.getRemark());
	 NameValuePair cardNo  = new BasicNameValuePair("cardNo",trade.getCardNo());
	 NameValuePair cardExpireMonth  = new BasicNameValuePair("cardExpireMonth",trade.getCardExpireMonth());
	 NameValuePair cardExpireYear  = new BasicNameValuePair("cardExpireYear",trade.getCardExpireYear());
	 NameValuePair firstName  = new BasicNameValuePair("firstName",trade.getFirstName());
	 NameValuePair lastName  = new BasicNameValuePair("lastName",trade.getLastName());
	 NameValuePair address  = new BasicNameValuePair("address",trade.getAddress());
	 NameValuePair city  = new BasicNameValuePair("city",trade.getCity());
	 NameValuePair ip  = new BasicNameValuePair("ip",trade.getIp());
	 NameValuePair cvv2  = new BasicNameValuePair("cvv2",trade.getCvv2());
	 NameValuePair zip  = new BasicNameValuePair("zip",trade.getZip());
	 NameValuePair country  = new BasicNameValuePair("country",trade.getCountry());
	 NameValuePair email  = new BasicNameValuePair("email",trade.getEmail());
	 NameValuePair phone  = new BasicNameValuePair("phone",trade.getPhone());
	 NameValuePair issuingBank  = new BasicNameValuePair("issuingBank",trade.getIssuingBank());
	 NameValuePair SHA256info  = new BasicNameValuePair("SHA256info",trade.getSHA256info());
	 NameValuePair state  = new BasicNameValuePair("state",trade.getBillingstate());
	
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(merNo);
		nvps1.add(billNo);
		nvps1.add(payCurrency);//70227403
		nvps1.add(amount);
		nvps1.add(currency);
		nvps1.add(website);
		nvps1.add(ie_language);
		nvps1.add(drawee);
		nvps1.add(ET_GOODS);
		nvps1.add(remark);

		nvps1.add(firstName);
		nvps1.add(lastName);
		nvps1.add(address);
		nvps1.add(city);
		nvps1.add(ip);

		nvps1.add(zip);
		nvps1.add(country);
		nvps1.add(email);
		nvps1.add(phone);
		nvps1.add(issuingBank);
		nvps1.add(SHA256info);
		nvps1.add(state);
				
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();

		nvps2.add(merNo);
		nvps2.add(billNo);
		nvps2.add(payCurrency);//70227403
		nvps2.add(amount);
		nvps2.add(currency);
		nvps2.add(website);
		nvps2.add(ie_language);
		nvps2.add(drawee);
		nvps2.add(ET_GOODS);
		nvps2.add(remark);
		nvps2.add(cardNo);
		nvps2.add(cardExpireMonth);
		nvps2.add(cardExpireYear);
		nvps2.add(firstName);
		nvps2.add(lastName);
		nvps2.add(address);
		nvps2.add(city);
		nvps2.add(ip);
		nvps2.add(cvv2);
		nvps2.add(zip);
		nvps2.add(country);
		nvps2.add(email);
		nvps2.add(phone);
		nvps2.add(issuingBank);
		nvps2.add(SHA256info);
		nvps2.add(state);
		
		try{
			ZMTPayUtil h = new ZMTPayUtil();
			logger.info("提交数据:"+nvps1.toString());
			String result = h.httpPost(nvps2,postUrl);
			logger.info("返回数据："+result);
			String resMsg[]=result.split("&");
	 		trade.setBankInfo(resMsg[6]);
	 		trade.setRespCode(resMsg[3]);	
	 		trade.setTradeNo(resMsg[1]);
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				ZMTPayUtil h = new ZMTPayUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				String resMsg[]=result.split("&");		 		
		 		trade.setBankInfo(resMsg[6]);
		 		trade.setRespCode(resMsg[3]);	
		 		trade.setTradeNo(resMsg[1]);
			} catch (Exception ex) {
				ex.printStackTrace();
	            trade.setBankInfo("");
	            trade.setRespCode("");
	            trade.setTradeNo("");
			}
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getRespCode()))){
	            trade.setRespCode("sfe01");
	            trade.setBankInfo("timeout!");
	            trade.setTradeNo("");
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
