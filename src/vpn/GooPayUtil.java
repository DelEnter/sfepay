package vpn;


import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class GooPayUtil {
	Logger logger = Logger.getLogger(GooPayUtil.class.getName());
	private static String postUrl="https://payment.goopay.cn/onlinepay";
	private static String postRefUrl="https://payment.goopay.cn/onlinepay";
	public void get(GooPayMessage msg){
		
	 NameValuePair merchantMID =new BasicNameValuePair("merchantMID",msg.getMerchantMID());
	 NameValuePair newcardtype  = new BasicNameValuePair("newcardtype",msg.getNewcardtype());
	 NameValuePair cardnum  = new BasicNameValuePair("cardnum",msg.getCardnum());
	 NameValuePair cvv2  = new BasicNameValuePair("cvv2",msg.getCvv2());
	 NameValuePair month  = new BasicNameValuePair("month",msg.getMonth());
	 NameValuePair year  = new BasicNameValuePair("year",msg.getYear());
	 NameValuePair cardbank  = new BasicNameValuePair("cardbank",msg.getCardbank());
	 NameValuePair BillNo  = new BasicNameValuePair("BillNo",msg.getBillNo());
	 NameValuePair Amount  = new BasicNameValuePair("Amount",msg.getAmount());
	 NameValuePair Currency  = new BasicNameValuePair("Currency",msg.getCurrency());
	 NameValuePair Language  = new BasicNameValuePair("Language",msg.getLanguage());
	 NameValuePair HASH  = new BasicNameValuePair("HASH",msg.getHASH());
	 NameValuePair ReturnURL  = new BasicNameValuePair("ReturnURL",msg.getReturnURL());
	 NameValuePair shippingFirstName  = new BasicNameValuePair("shippingFirstName",msg.getShippingFirstName());
	 NameValuePair shippingLastName  = new BasicNameValuePair("shippingLastName",msg.getShippingLastName());
	 NameValuePair shippingEmail  = new BasicNameValuePair("shippingEmail",msg.getShippingEmail());
	 NameValuePair shippingPhone  = new BasicNameValuePair("shippingPhone",msg.getShippingPhone());
	 NameValuePair shippingZipcode  = new BasicNameValuePair("shippingZipcode",msg.getShippingZipcode());
	 NameValuePair shippingAddress  = new BasicNameValuePair("shippingAddress",msg.getShippingAddress());
	 NameValuePair shippingCity  = new BasicNameValuePair("shippingCity",msg.getShippingCity());
	 NameValuePair shippingSstate  = new BasicNameValuePair("shippingSstate",msg.getShippingSstate());
	 NameValuePair shippingCountry  = new BasicNameValuePair("shippingCountry",msg.getShippingCountry());
	 NameValuePair products  = new BasicNameValuePair("products",msg.getProducts());
	 NameValuePair firstname  = new BasicNameValuePair("firstname",msg.getFirstname());
	 NameValuePair lastName  = new BasicNameValuePair("lastName",msg.getLastname());//lastname
	 NameValuePair email  = new BasicNameValuePair("email",msg.getEmail());
	 NameValuePair phone  = new BasicNameValuePair("phone",msg.getPhone());
	 NameValuePair zipcode  = new BasicNameValuePair("zipcode",msg.getZipcode());
	 NameValuePair address  = new BasicNameValuePair("address",msg.getAddress());
	 NameValuePair city  = new BasicNameValuePair("city",msg.getCity());
	 NameValuePair state  = new BasicNameValuePair("state",msg.getState());
	 NameValuePair country  = new BasicNameValuePair("country",msg.getCountry());
	 NameValuePair ipAddr  = new BasicNameValuePair("ipAddr",msg.getIpAddr());
	 NameValuePair website  = new BasicNameValuePair("website",msg.getWebsite());
	 NameValuePair Trans_Type  = new BasicNameValuePair("Trans_Type",msg.getTrans_Type());
	 NameValuePair oriAmount  = new BasicNameValuePair("oriAmount",msg.getOriAmount());
	 NameValuePair refundAmount  = new BasicNameValuePair("refundAmount",msg.getRefundAmount());
	 NameValuePair grn  = new BasicNameValuePair("GRN",msg.getGrn());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps1.add(merchantMID);
		nvps1.add(HASH);
		nvps1.add(BillNo);
		nvps2.add(merchantMID);
		nvps2.add(HASH);
		nvps2.add(BillNo);
		if("refund".equals(msg.getTrans_Type())){
			nvps1.add(Trans_Type);
			nvps1.add(oriAmount);
			nvps1.add(refundAmount);
			nvps1.add(grn);
			nvps2.add(Trans_Type);
			nvps2.add(oriAmount);
			nvps2.add(refundAmount);
			nvps2.add(grn);
		}else{
			nvps1.add(newcardtype);
			nvps1.add(cardnum);
			nvps1.add(cvv2);
			nvps1.add(month);
			nvps1.add(year);
			nvps1.add(cardbank);
			nvps1.add(Amount);
			nvps1.add(Currency);
			nvps1.add(Language);
			nvps1.add(ReturnURL);
			nvps1.add(shippingFirstName);
			nvps1.add(shippingLastName);
			nvps1.add(shippingEmail);
			nvps1.add(shippingPhone);
			nvps1.add(shippingZipcode);
			nvps1.add(shippingAddress);
			nvps1.add(shippingCity);
			nvps1.add(shippingSstate);
			nvps1.add(shippingCountry);
			nvps1.add(products);
			nvps1.add(firstname);
			nvps1.add(lastName);
			nvps1.add(email);
			nvps1.add(phone);
			nvps1.add(zipcode);
			nvps1.add(address);
			nvps1.add(city);
			nvps1.add(state);
			nvps1.add(country);
			nvps1.add(ipAddr);
			nvps1.add(website);
			nvps2.add(newcardtype);
			nvps2.add(cardbank);
			nvps2.add(Amount);
			nvps2.add(Currency);
			nvps2.add(Language);
			nvps2.add(ReturnURL);
			nvps2.add(shippingFirstName);
			nvps2.add(shippingLastName);
			nvps2.add(shippingEmail);
			nvps2.add(shippingPhone);
			nvps2.add(shippingZipcode);
			nvps2.add(shippingAddress);
			nvps2.add(shippingCity);
			nvps2.add(shippingSstate);
			nvps2.add(shippingCountry);
			nvps2.add(products);
			nvps2.add(firstname);
			nvps2.add(lastName);
			nvps2.add(email);
			nvps2.add(phone);
			nvps2.add(zipcode);
			nvps2.add(address);
			nvps2.add(city);
			nvps2.add(state);
			nvps2.add(country);
			nvps2.add(ipAddr);
			nvps2.add(website);
		}
		try{
			GooPayUtil h = new GooPayUtil();
			logger.info("提交数据:"+nvps1.toString());
			String result="";
			if("refund".equals(msg.getTrans_Type())){
				result = h.httpPost(nvps1,postRefUrl);
			}else{
				result = h.httpPost(nvps1,postUrl);
			}
			logger.info("返回数据："+result);
			if(StringUtils.isNotBlank(result)){
			JSONObject jasonObject = JSONObject.fromObject(result);
            Map<String, Object> map= (Map) jasonObject;
            msg.setResult(map.get("Result")+"");
            msg.setSucceed(map.get("Succeed")+"");
	        if(!"refund".equals(msg.getTrans_Type())){
	            msg.setRemark(map.get("Remark")+"");
	            msg.setGrn(map.get("GRN")+"");
	            msg.setBillingAddress(map.get("BillingAddress")+"");
	          }
			}else{
				msg.setResult("");
	            msg.setSucceed("90");
	            msg.setRemark("");
			}
		} catch (Exception e) {
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				GooPayUtil h = new GooPayUtil();
				logger.info("提交数据:"+nvps1.toString());
				String result="";
				if("refund".equals(msg.getTrans_Type())){
					result = h.httpPost(nvps1,postRefUrl);
				}else{
					result = h.httpPost(nvps1,postUrl);
				}
				logger.info("返回数据："+result);
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map<String, Object> map= (Map) jasonObject;
	            msg.setResult(map.get("Result")+"");
	            msg.setSucceed(map.get("Succeed")+"");
	            if(!"refund".equals(msg.getTrans_Type())){
		            msg.setRemark(map.get("Remark")+"");
		            msg.setGrn(map.get("GRN")+"");
		            msg.setBillingAddress(map.get("BillingAddress")+"");
	              }
				}catch(Exception ex){
					ex.printStackTrace();
					msg.setResult("");
		            msg.setSucceed("90");
		            msg.setRemark("");
				}
		}
	}
	public String httpPost(List<NameValuePair> nvps, String url)
			throws Exception {
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//		httpPost.setHeader("referer",referer);
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
