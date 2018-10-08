package vpn;


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

import vpn.VpnUtil_Moto;

public class MasaPayUtil {
	Logger logger = Logger.getLogger(MasaPayUtil.class.getName());
	private static String postUrl="https://open.grepay.com/masapi/receiveMerchantOrder.htm";  
	//private static String postUrl="https://open.masapay.com/masapi/receiveMerchantOrder.htm"; 
	public void get(MasaPayMessage trade){
	 NameValuePair version =new BasicNameValuePair("version",trade.getVersion());
	 NameValuePair merchantId  = new BasicNameValuePair("merchantId",trade.getMerchantId());
	 NameValuePair charset  = new BasicNameValuePair("charset",trade.getCharset());
	 NameValuePair language  = new BasicNameValuePair("language",trade.getLanguage());
	 NameValuePair signType  = new BasicNameValuePair("signType",trade.getSignType());
	 NameValuePair merchantOrderNo  = new BasicNameValuePair("merchantOrderNo",trade.getMerchantOrderNo());
	 NameValuePair goodsName  = new BasicNameValuePair("goodsName",trade.getGoodsName());
	 NameValuePair goodsDesc  = new BasicNameValuePair("goodsDesc",trade.getGoodsDesc());
	 NameValuePair orderExchange  = new BasicNameValuePair("orderExchange",trade.getOrderExchange());
	 NameValuePair currencyCode  = new BasicNameValuePair("currencyCode",trade.getCurrencyCode());
	 NameValuePair orderAmount  = new BasicNameValuePair("orderAmount",trade.getOrderAmount());
	 NameValuePair submitTime  = new BasicNameValuePair("submitTime",trade.getSubmitTime());
	 NameValuePair expiryTime  = new BasicNameValuePair("expiryTime",trade.getExpiryTime());
	 NameValuePair bgUrl  = new BasicNameValuePair("bgUrl",trade.getBgUrl());
	 NameValuePair payMode  = new BasicNameValuePair("payMode",trade.getPayMode());
	 NameValuePair orgCode  = new BasicNameValuePair("orgCode",trade.getOrgCode());
	 NameValuePair cardNumber  = new BasicNameValuePair("cardNumber",trade.getCardNumber());
	 NameValuePair cardHolderFirstName  = new BasicNameValuePair("cardHolderFirstName",trade.getCardHolderFirstName());
	 NameValuePair cardHolderLastName  = new BasicNameValuePair("cardHolderLastName",trade.getCardHolderLastName());
	 NameValuePair cardExpirationMonth  = new BasicNameValuePair("cardExpirationMonth",trade.getCardExpirationMonth());
	 NameValuePair cardExpirationYear  = new BasicNameValuePair("cardExpirationYear",trade.getCardExpirationYear());
	 NameValuePair securityCode  = new BasicNameValuePair("securityCode",trade.getSecurityCode());
	 NameValuePair cardHolderEmail  = new BasicNameValuePair("cardHolderEmail",trade.getCardHolderEmail());
	 NameValuePair cardHolderPhoneNumber  = new BasicNameValuePair("cardHolderPhoneNumber",trade.getCardHolderPhoneNumber());
	 NameValuePair billName  = new BasicNameValuePair("billName",trade.getBillName());
	 NameValuePair billAddress  = new BasicNameValuePair("billAddress",trade.getBillAddress());
	 NameValuePair billPostalCode  = new BasicNameValuePair("billPostalCode",trade.getBillPostalCode());
	 NameValuePair billCountry  = new BasicNameValuePair("billCountry",trade.getBillCountry());
	 NameValuePair billState  = new BasicNameValuePair("billState",trade.getBillState());
	 NameValuePair billCity  = new BasicNameValuePair("billCity",trade.getBillCity());
	 NameValuePair billEmail  = new BasicNameValuePair("billEmail",trade.getBillEmail());
	 NameValuePair billPhoneNumber  = new BasicNameValuePair("billPhoneNumber",trade.getBillPhoneNumber());
	 NameValuePair shippingName  = new BasicNameValuePair("shippingName",trade.getShippingName());
	 NameValuePair shippingAddress  = new BasicNameValuePair("shippingAddress",trade.getShippingAddress());
	 NameValuePair shippingPostalCode  = new BasicNameValuePair("shippingPostalCode",trade.getShippingPostalCode());
	 NameValuePair shippingCountry  = new BasicNameValuePair("shippingCountry",trade.getShippingCountry());
	 NameValuePair shippingState  = new BasicNameValuePair("shippingState",trade.getShippingState());
	 NameValuePair shippingCity  = new BasicNameValuePair("shippingCity",trade.getShippingCity());
	 NameValuePair shippingEmail  = new BasicNameValuePair("shippingEmail",trade.getShippingEmail());
	 NameValuePair shippingPhoneNumber  = new BasicNameValuePair("shippingPhoneNumber",trade.getShippingPhoneNumber());
	 NameValuePair deviceFingerprintID  = new BasicNameValuePair("deviceFingerprintID",trade.getDeviceFingerprintID());
	 NameValuePair registerUserEmail  = new BasicNameValuePair("registerUserEmail",trade.getRegisterUserEmail());
	 NameValuePair registerTime  = new BasicNameValuePair("registerTime",trade.getRegisterTime());
	 NameValuePair registerIp  = new BasicNameValuePair("registerIp",trade.getRegisterIp());
	 NameValuePair registerTerminal  = new BasicNameValuePair("registerTerminal",trade.getRegisterTerminal());
	 NameValuePair orderIp  = new BasicNameValuePair("orderIp",trade.getOrderIp());
	 NameValuePair orderTerminal  = new BasicNameValuePair("orderTerminal",trade.getOrderTerminal());
	 NameValuePair signMsg  = new BasicNameValuePair("signMsg",trade.getSignMsg());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(version);
		nvps1.add(merchantId);
		nvps1.add(charset);
		nvps1.add(language);
		nvps1.add(signType);
		nvps1.add(merchantOrderNo);
		nvps1.add(goodsName);
		nvps1.add(goodsDesc);
		nvps1.add(orderExchange);
		nvps1.add(currencyCode);
		nvps1.add(orderAmount);
		nvps1.add(submitTime);
		nvps1.add(expiryTime);
		nvps1.add(bgUrl);
		nvps1.add(payMode);
		nvps1.add(orgCode);
		nvps1.add(cardNumber);
		nvps1.add(cardHolderFirstName);
		nvps1.add(cardHolderLastName);
		nvps1.add(cardExpirationMonth);
		nvps1.add(cardExpirationYear);
		nvps1.add(securityCode);
		nvps1.add(cardHolderEmail);
		nvps1.add(cardHolderPhoneNumber);
		nvps1.add(billName);
		nvps1.add(billAddress);
		nvps1.add(billPostalCode);
		nvps1.add(billCountry);
		nvps1.add(billState);
		nvps1.add(billCity);
		nvps1.add(billEmail);
		nvps1.add(billPhoneNumber);
		nvps1.add(shippingName);
		nvps1.add(shippingAddress);
		nvps1.add(shippingPostalCode);
		nvps1.add(shippingCountry);
		nvps1.add(shippingState);
		nvps1.add(shippingCity);
		nvps1.add(shippingEmail);
		nvps1.add(shippingPhoneNumber);
		nvps1.add(deviceFingerprintID);
		nvps1.add(registerUserEmail);
		nvps1.add(registerTime);
		nvps1.add(registerIp);
		nvps1.add(registerTerminal);
		nvps1.add(orderIp);
		nvps1.add(orderTerminal);
		nvps1.add(signMsg);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(version);
		nvps2.add(merchantId);
		nvps2.add(charset);
		nvps2.add(language);
		nvps2.add(signType);
		nvps2.add(merchantOrderNo);
		nvps2.add(goodsName);
		nvps2.add(goodsDesc);
		nvps2.add(orderExchange);
		nvps2.add(currencyCode);
		nvps2.add(orderAmount);
		nvps2.add(submitTime);
		nvps2.add(expiryTime);
		nvps2.add(bgUrl);
		nvps2.add(payMode);
		nvps2.add(orgCode);
		nvps2.add(cardHolderFirstName);
		nvps2.add(cardHolderLastName);
		nvps2.add(cardHolderEmail);
		nvps2.add(cardHolderPhoneNumber);
		nvps2.add(billName);
		nvps2.add(billAddress);
		nvps2.add(billPostalCode);
		nvps2.add(billCountry);
		nvps2.add(billState);
		nvps2.add(billCity);
		nvps2.add(billEmail);
		nvps2.add(billPhoneNumber);
		nvps2.add(shippingName);
		nvps2.add(shippingAddress);
		nvps2.add(shippingPostalCode);
		nvps2.add(shippingCountry);
		nvps2.add(shippingState);
		nvps2.add(shippingCity);
		nvps2.add(shippingEmail);
		nvps2.add(shippingPhoneNumber);
		nvps2.add(deviceFingerprintID);
		nvps2.add(registerUserEmail);
		nvps2.add(registerTime);
		nvps2.add(registerIp);
		nvps2.add(registerTerminal);
		nvps2.add(orderIp);
		nvps2.add(orderTerminal);
		nvps2.add(signMsg);
		try{
			MasaPayUtil h = new MasaPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			if(StringUtils.isNotBlank(result)){
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map map= (Map) jasonObject;
	            trade.setRes_errCode(map.get("errCode")+"");
	            trade.setRes_errMsg(map.get("errMsg")+"");
	            trade.setRes_masapayOrderNo(map.get("masapayOrderNo")+"");
	            trade.setRes_resultCode(map.get("resultCode")+"");
	            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				MasaPayUtil h = new MasaPayUtil();
				logger.info("提交数据:"+nvps2.toString());
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				if(StringUtils.isNotBlank(result)){
					JSONObject jasonObject = JSONObject.fromObject(result);
		            Map map= (Map) jasonObject;
		            trade.setRes_errCode(map.get("errCode")+"");
		            trade.setRes_errMsg(map.get("errMsg")+"");
		            trade.setRes_masapayOrderNo(map.get("masapayOrderNo")+"");
		            trade.setRes_resultCode(map.get("resultCode")+"");
		            }
				
		}catch(Exception ea){
			ea.printStackTrace();
		}

		}finally{
			if(StringUtils.isEmpty(trade.getRes_resultCode())){
				trade.setRes_resultCode("19");
				trade.setRes_errMsg("sfe01");
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



