package vpn;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HarbinPayUtil {
	/*
	 * 2方交易
	 */
	private static String postUrl="http://221.207.229.3:9080/hrpaypt/gateway/two_party_payment";
	private static String refundUrl="http://221.207.229.3:9080/hrpaypt/gateway/refund";
	private static String queryUrl="http://221.207.229.3:9080/hrpaypt/gateway/order_query";
	Logger logger = Logger.getLogger(HarbinPayUtil.class.getName());
	public String getHarMessage(HarbinPayMessage mes, String type)
			throws HttpException, IOException, KeyManagementException,
			NoSuchAlgorithmException {
		HttpPost httpPost=null;
		NameValuePair inputCharset  =new BasicNameValuePair("inputCharset",mes.getInputCharset());
		NameValuePair pickupUrl  =new BasicNameValuePair("pickupUrl",mes.getPickupUrl());
		 NameValuePair receiveUrl  =new BasicNameValuePair("receiveUrl",mes.getReceiveUrl());
		 NameValuePair version  =new BasicNameValuePair("version",mes.getVersion());
		 NameValuePair signType  =new BasicNameValuePair("signType",mes.getSignType());
		 NameValuePair merchantId  =new BasicNameValuePair("merchantId",mes.getMerchantId());
		 NameValuePair orderNo  =new BasicNameValuePair("orderNo",mes.getOrderNo());
		 NameValuePair orderAmount  =new BasicNameValuePair("orderAmount",mes.getOrderAmount());
		 NameValuePair orderCurrency  =new BasicNameValuePair("orderCurrency",mes.getOrderCurrency());
		 NameValuePair orderDatetime  =new BasicNameValuePair("orderDatetime",mes.getOrderDatetime());
		 NameValuePair extTL  =new BasicNameValuePair("extTL",mes.getExtTL());
		 NameValuePair payType  =new BasicNameValuePair("payType",mes.getPayType());
		 NameValuePair issuerId  =new BasicNameValuePair("issuerId",mes.getIssuerId());
		 NameValuePair tradeNature  =new BasicNameValuePair("tradeNature",mes.getTradeNature());
		 NameValuePair signMsg=null;
		 if(type.equals("1")){
			signMsg  =new BasicNameValuePair("signMsg",mes.getQueryHash());
		 }else{
		    signMsg  =new BasicNameValuePair("signMsg",mes.getHash());
		 }
		 NameValuePair refundAmount  =new BasicNameValuePair("refundAmount",mes.getRefundAmount());
		 NameValuePair originalOrderNo  =new BasicNameValuePair("originalOrderNo",mes.getOriginalOrderNo());
		 NameValuePair queryDatetime  =new BasicNameValuePair("queryDatetime",mes.getQueryDatetime());
		
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		List<NameValuePair> nvpslog = new ArrayList<NameValuePair>();
		nvps1.add(version);
		nvps1.add(signType);
		nvps1.add(merchantId);
		nvps1.add(orderNo);
		nvps1.add(orderDatetime);
		nvps1.add(signMsg);
		if (type.equals("13")) {
			httpPost = new HttpPost(postUrl);
			nvps1.add(inputCharset);
			nvps1.add(pickupUrl);
			nvps1.add(receiveUrl);
			nvps1.add(orderAmount);
			nvps1.add(orderCurrency);
			nvps1.add(extTL);
			nvps1.add(payType);
			nvps1.add(issuerId);
			nvps1.add(tradeNature);
			nvpslog.add(inputCharset);
			nvpslog.add(pickupUrl);
			nvpslog.add(receiveUrl);
			nvpslog.add(orderAmount);
			nvpslog.add(orderCurrency);
			nvpslog.add(payType);
			nvpslog.add(issuerId);
			nvpslog.add(tradeNature);
			
		}
		if (type.equals("3")) {
			httpPost = new HttpPost(refundUrl);
			nvps1.add(refundAmount);
			nvps1.add(originalOrderNo);
			nvpslog.add(refundAmount);
			nvpslog.add(originalOrderNo);
		}
		if (type.equals("1")) {
			httpPost = new HttpPost(queryUrl);
			nvps1.add(queryDatetime);
			nvpslog.add(queryDatetime);
		}
		nvpslog.add(version);
		nvpslog.add(signType);
		nvpslog.add(merchantId);
		nvpslog.add(orderNo);
		nvpslog.add(orderDatetime);
		nvpslog.add(signMsg);
		String result = "";
		httpPost.setEntity(new UrlEncodedFormEntity(nvps1, "UTF-8"));
		System.out.println("上送报文"+nvps1);
		logger.info("上送报文"+nvpslog);//日志文件
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if(postUrl.toLowerCase().startsWith("https")){
				httpclient = getInstance(httpclient);
			}
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity ent = response.getEntity();
			//获取响应内容
			result= EntityUtils.toString(ent);
			//输出响应报文
			logger.info("返回报文"+result);
			if(StringUtils.isNotBlank(result)){
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map map= (Map) jasonObject;
	            if(type.equals("13")||type.equals("1")){
	            	mes.setRes_paymentOrderId(null2unknown("paymentOrderId", map));
	            	mes.setRes_payAmount(null2unknown("payAmount", map));
		            mes.setRes_payResult(null2unknown("payResult", map));
		            mes.setRes_payDatetime(null2unknown("payDatetime", map));
		            mes.setRes_authNo(null2unknown("authNo", map));
		            mes.setRes_error(null2unknown("error", map));
	            }
	            if(type.equals("3")){
	            	mes.setRes_refundAmount(null2unknown("refundAmount", map));
	            	mes.setRes_refundDatetime(null2unknown("refundDatetime", map));
	            	mes.setRes_refundResult(null2unknown("refundResult", map));
	            	mes.setRes_errorMessage(null2unknown("errorMessage", map));
	            }
	            mes.setRes_orderNo(null2unknown("orderNo", map));
	            mes.setRes_orderDatetime(null2unknown("orderDatetime", map));
	            mes.setRes_orderAmount(null2unknown("orderAmount", map));
	            mes.setRes_errorCode(null2unknown("errorCode", map));
	            mes.setRes_returnDatetime(null2unknown("returnDatetime", map));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String null2unknown(String in, Map responseFields) {
		if (in == null || in.length() == 0
				|| (String) responseFields.get(in) == null) {
			return "No Value Returned";
		} else {
			return (String) responseFields.get(in);
		}
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
