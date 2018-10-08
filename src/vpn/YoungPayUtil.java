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


public class YoungPayUtil {
	/*
	 * 2方交易
	 */
	private static String postUrl="https://ypt.youngpayment.com.cn/transaction/do2PTrans";
	Logger logger = Logger.getLogger(YoungPayUtil.class.getName());
	public String getHarMessage(YoungPayMessage mes, String type)
			throws HttpException, IOException, KeyManagementException,
			NoSuchAlgorithmException {
		HttpPost httpPost=null;
		 NameValuePair transType  =new BasicNameValuePair("transType",mes.getTransType());
		 NameValuePair ip  =new BasicNameValuePair("ip",mes.getIp());
		 NameValuePair cardType  =new BasicNameValuePair("cardType",mes.getCardType());
		 NameValuePair cardNo  =new BasicNameValuePair("cardNo",mes.getCardNo());
		 NameValuePair expireDate  =new BasicNameValuePair("expireDate",mes.getExpireDate());
		 NameValuePair cvcCode  =new BasicNameValuePair("cvcCode",mes.getCvcCode());
		 NameValuePair merId  =new BasicNameValuePair("merId",mes.getMerId());
		 NameValuePair b2mOrder  =new BasicNameValuePair("b2mOrder",mes.getB2mOrder());
		 NameValuePair b2mBank  =new BasicNameValuePair("b2mBank","");
		 NameValuePair b2mWebsite  =new BasicNameValuePair("b2mWebsite","sfepay.com");
		 NameValuePair b2mFee  =new BasicNameValuePair("b2mFee",mes.getB2mFee());
		 NameValuePair b2mCur  =new BasicNameValuePair("b2mCur","CNY");
		 NameValuePair b2mReturnUrl  =new BasicNameValuePair("b2mReturnUrl",mes.getB2mReturnUrl());
		 NameValuePair b2mNotifyUrl  =new BasicNameValuePair("b2mNotifyUrl",mes.getB2mNotifyUrl());
		 NameValuePair b2mSignure  =new BasicNameValuePair("b2mSignure",mes.getB2mSignure());
		 NameValuePair b2mCargoCountry  =new BasicNameValuePair("b2mCargoCountry",mes.getB2mCargoCountry());
		 NameValuePair b2mHolderCountry  =new BasicNameValuePair("b2mHolderCountry",mes.getB2mHolderCountry());
		 NameValuePair b2mCargoName  =new BasicNameValuePair("b2mCargoName",mes.getB2mCargoName());
		 NameValuePair b2mHolderName  =new BasicNameValuePair("b2mHolderName",mes.getB2mHolderName());
		 NameValuePair b2mPhone  =new BasicNameValuePair("b2mPhone",mes.getB2mPhone());
		 NameValuePair b2mCargoEmail  =new BasicNameValuePair("b2mCargoEmail",mes.getB2mCargoEmail());
		 NameValuePair b2mCargoState  =new BasicNameValuePair("b2mCargoState",mes.getB2mCargoState());
		 NameValuePair b2mCargoCity  =new BasicNameValuePair("b2mCargoCity",mes.getB2mCargoCity());
		 NameValuePair b2mCargoAddr =null;
		 if(mes.getB2mCargoAddr().length()>50){
			 b2mCargoAddr  =new BasicNameValuePair("B2mCargoAddr",mes.getB2mCargoAddr().substring(0, 50));
		 }else{
			b2mCargoAddr  =new BasicNameValuePair("B2mCargoAddr",mes.getB2mCargoAddr()); 
		 }
		 NameValuePair b2mCargoZip  =new BasicNameValuePair("B2mCargoZip",mes.getB2mCargoZip());
		 NameValuePair b2mHolderEmail  =new BasicNameValuePair("b2mHolderEmail",mes.getB2mHolderEmail());
		 NameValuePair b2mHolderState  =new BasicNameValuePair("b2mHolderState",mes.getB2mHolderState());
		 NameValuePair b2mHolderCity  =new BasicNameValuePair("b2mHolderCity",mes.getB2mHolderCity());
		 NameValuePair b2mHolderAddr  =new BasicNameValuePair("B2mHolderAddr",mes.getB2mHolderAddr());
		 NameValuePair b2mHolderZip  =new BasicNameValuePair("B2mHolderZip",mes.getB2mHolderZip());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		List<NameValuePair> nvpslog = new ArrayList<NameValuePair>();
		nvps1.add(merId);
		nvps1.add(transType);
		nvps1.add(b2mOrder);
		nvps1.add(cardNo);
		nvps1.add(cardType);
		nvps1.add(b2mBank);
		nvps1.add(expireDate);
		nvps1.add(cvcCode);
		nvps1.add(b2mFee);
		nvps1.add(b2mCur);
		nvps1.add(b2mWebsite);
		nvps1.add(b2mReturnUrl);
		nvps1.add(b2mNotifyUrl);
		nvps1.add(ip);
		nvps1.add(b2mCargoCountry);
		nvps1.add(b2mHolderCountry);
		nvps1.add(b2mCargoName);
		nvps1.add(b2mHolderName);
		nvps1.add(b2mPhone);
		nvps1.add(b2mCargoEmail);
		nvps1.add(b2mCargoState);
		nvps1.add(b2mCargoCity);
		nvps1.add(b2mCargoAddr);
		nvps1.add(b2mCargoZip);
		nvps1.add(b2mHolderEmail);
		nvps1.add(b2mHolderState);
		nvps1.add(b2mHolderCity);
		nvps1.add(b2mHolderAddr);
		nvps1.add(b2mHolderZip);
		nvps1.add(b2mSignure);
		nvpslog.add(merId);
		nvpslog.add(transType);
		nvpslog.add(b2mOrder);
		nvpslog.add(cardType);
		nvpslog.add(b2mBank);
		nvpslog.add(b2mFee);
		nvpslog.add(b2mCur);
		nvpslog.add(b2mWebsite);
		nvpslog.add(b2mReturnUrl);
		nvpslog.add(b2mNotifyUrl);
		nvpslog.add(ip);
		nvpslog.add(b2mCargoCountry);
		nvpslog.add(b2mHolderCountry);
		nvpslog.add(b2mCargoName);
		nvpslog.add(b2mHolderName);
		nvpslog.add(b2mPhone);
		nvpslog.add(b2mCargoEmail);
		nvpslog.add(b2mCargoState);
		nvpslog.add(b2mCargoCity);
		nvpslog.add(b2mCargoAddr);
		nvpslog.add(b2mCargoZip);
		nvpslog.add(b2mHolderEmail);
		nvpslog.add(b2mHolderState);
		nvpslog.add(b2mHolderCity);
		nvpslog.add(b2mHolderAddr);
		nvpslog.add(b2mHolderZip);
		nvpslog.add(b2mSignure);
		httpPost = new HttpPost(postUrl);
		String result = "";
		httpPost.setEntity(new UrlEncodedFormEntity(nvps1, "UTF-8"));
		logger.info("上送报文"+nvpslog);
//		logger.info("上送报文"+nvpslog);//日志文件
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
//	            if(type.equals("13")||type.equals("1")){
//	            	mes.setRes_paymentOrderId(null2unknown("paymentOrderId", map));
//	            	mes.setRes_payAmount(null2unknown("payAmount", map));
//		            mes.setRes_payResult(null2unknown("payResult", map));
//		            mes.setRes_payDatetime(null2unknown("payDatetime", map));
//		            mes.setRes_authNo(null2unknown("authNo", map));
//		            mes.setRes_error(null2unknown("error", map));
//	            }
//	            if(type.equals("3")){
//	            	mes.setRes_refundAmount(null2unknown("refundAmount", map));
//	            	mes.setRes_refundDatetime(null2unknown("refundDatetime", map));
//	            	mes.setRes_refundResult(null2unknown("refundResult", map));
//	            	mes.setRes_errorMessage(null2unknown("errorMessage", map));
//	            }
	            mes.setRes_statusCode(null2unknown("statusCode", map));
	            if(StringUtils.isNotBlank(null2unknown("errorCode", map))){
	            	mes.setRes_errorCode(null2unknown("errorCode", map).split(":")[0]+"");
	            }
//	            mes.setRes_errorCode(null2unknown("errorCode", map));
//	            mes.setRes_orderAmount(null2unknown("orderAmount", map));
//	            mes.setRes_errorCode(null2unknown("errorCode", map));
//	            mes.setRes_returnDatetime(null2unknown("returnDatetime", map));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(StringUtils.isEmpty(mes.getRes_statusCode())){
				mes.setRes_statusCode("0");
				mes.setRes_errorCode("sfe01");
			}
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
