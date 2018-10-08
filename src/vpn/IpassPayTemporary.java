package vpn;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class IpassPayTemporary {
	Logger logger = Logger.getLogger(IpassPayTemporary.class.getName());
	
	public void get(IPassPayTemporMessage trade){
	 //NameValuePair version =new BasicNameValuePair("version",trade.getVersion());
	 NameValuePair orderNo  = new BasicNameValuePair("orderNo",trade.getOrderNo());
	 NameValuePair status  = new BasicNameValuePair("res_orderStatus",trade.getRes_orderStatus());
	 NameValuePair remark  = new BasicNameValuePair("remark",trade.getRemark());

		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(orderNo);
		nvps1.add(status);
		nvps1.add(remark);

		try{
			IpassPayTemporary h = new IpassPayTemporary();
			logger.info("推送异步通知:"+nvps1.toString());
			String result = h.httpPost(nvps1,"https://www.ipasspay.biz/index.php/Thirdpay/Sfepay/notifyUrl");
			logger.info("返回数据："+result);
			if(StringUtils.isNotBlank(result)&&"0000".equals(result.toString())){
				logger.info("********异步订单推送成功************");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("返回异步通知超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				IpassPayTemporary h = new IpassPayTemporary();
				logger.info("推送异步通知:"+nvps1.toString());
				String result = h.httpPost(nvps1,"https://www.ipasspay.biz/index.php/Thirdpay/Sfepay/notifyUrl");
				logger.info("返回数据："+result);
				if(StringUtils.isNotBlank(result)&&"0000".equals(result.toString())){
					logger.info("********异步订单推送成功************");
		        }
			}catch(Exception ea){
				ea.printStackTrace();
			}
		}
	}
	public String httpPost(List<NameValuePair> nvps, String url)
			throws Exception {
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		HttpClient httpclient = new DefaultHttpClient();
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
		SSLContext ctx = SSLContext.getInstance("TLSv1.2");
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



