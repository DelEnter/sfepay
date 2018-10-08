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

public class HJWPayUtil {
	Logger logger = Logger.getLogger(HJWPayUtil.class.getName());
	private static String postUrl="http://101.200.207.66:8080/redirect2/getResult";
	public void get(HJWPayMessage trade){
		
	 NameValuePair merchantId  =new BasicNameValuePair("merchantId",trade.getMerchantId());
	 NameValuePair amount  = new BasicNameValuePair("amount",trade.getAmount());
	 NameValuePair currency  = new BasicNameValuePair("currency",trade.getCurrency());
	 NameValuePair billNo  = new BasicNameValuePair("billNo",trade.getBillNo());
	 NameValuePair cardAsn  = new BasicNameValuePair("cardAsn",trade.getCardAsn());
	 NameValuePair validity  = new BasicNameValuePair("validity",trade.getValidity());
	 NameValuePair cvv  = new BasicNameValuePair("cvv",trade.getCvv());
	 NameValuePair cardType  = new BasicNameValuePair("cardType",trade.getCardType());
	 NameValuePair srcUrl  = new BasicNameValuePair("srcUrl",trade.getSrcUrl());
	 NameValuePair md5Data  = new BasicNameValuePair("md5Data",trade.getMd5Data());
	 NameValuePair firstName  = new BasicNameValuePair("firstName",trade.getFirstName());
	 NameValuePair lastName  = new BasicNameValuePair("lastName",trade.getLastName());
//	 NameValuePair PName  = new BasicNameValuePair("lastName","shoesbag");
//	 NameValuePair Issuer  = new BasicNameValuePair("Issuer",trade.getIssuer());
	 NameValuePair address  = new BasicNameValuePair("address",trade.getAddress());
	 NameValuePair remark  = new BasicNameValuePair("remark",trade.getRemark());
	 NameValuePair email  = new BasicNameValuePair("email",trade.getEmail());
	 NameValuePair telephone  = new BasicNameValuePair("telephone",trade.getTelephone());
	 NameValuePair jcTradeId  = new BasicNameValuePair("jcTradeId",trade.getJcTradeId());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(merchantId);
		nvps1.add(amount);
		nvps1.add(billNo);
		nvps1.add(md5Data);
		if("1".equals(trade.getTradType())){
			nvps1.add(currency);
			nvps1.add(cardAsn);
			nvps1.add(validity);
			nvps1.add(cvv);
			nvps1.add(cardType);
			nvps1.add(srcUrl);
			nvps1.add(firstName);
			nvps1.add(lastName);
			nvps1.add(address);
			nvps1.add(remark);
			nvps1.add(email);
			nvps1.add(telephone);
		}
		if("2".equals(trade.getTradType())){
			nvps1.add(jcTradeId);
		}
		
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(merchantId);
		nvps2.add(amount);
		nvps2.add(billNo);
		nvps2.add(md5Data);
		if("1".equals(trade.getTradType())){
			nvps2.add(cardType);
			nvps2.add(srcUrl);
			nvps2.add(currency);
			nvps2.add(firstName);
			nvps2.add(lastName);
			nvps2.add(address);
			nvps2.add(remark);
			nvps2.add(email);
			nvps2.add(telephone);
		}
		if("2".equals(trade.getTradType())){
			nvps2.add(jcTradeId);
		}
		try{
			HJWPayUtil h = new HJWPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			if(StringUtils.isNotBlank(result)){
				JSONObject jasonObject = JSONObject.fromObject(result);
	            Map map= (Map) jasonObject;
	            trade.setRes_merchantId(null2unknown("merchantId", map));
	            trade.setRes_billNo(null2unknown("billNo", map));
	            if("1".equals(trade.getTradType())){
	            trade.setRes_cardAsn(null2unknown("cardAsn", map));
	            trade.setRes_currency(null2unknown("currency", map));
	            trade.setRes_rmb(null2unknown("rmb", map));
	            trade.setRes_jcTime(null2unknown("jcTime", map));
	            trade.setRes_remark(null2unknown("remark", map));
	            }
	            trade.setRes_amount(null2unknown("amount", map));
	            trade.setRes_jcTradeId(null2unknown("jcTradeId", map));
	            trade.setRes_responseCode(null2unknown("responseCode", map));
	            trade.setRes_addMsg(null2unknown("addMsg", map));
	            trade.setRes_md5Data(null2unknown("md5Data", map));
			}
            logger.info("返回数据：merchantId:"+trade.getRes_merchantId()+"&billNo:"+trade.getRes_billNo()+"&amount:"+trade.getRes_amount()+"&currency:"+trade.getRes_currency()+"&rmb:"+trade.getRes_rmb()+"&jcTradeId:"+trade.getRes_jcTradeId()+"&jcTime:"+trade.getRes_jcTime()+"&responseCode:"+trade.getRes_responseCode()+"&addMsg:"+trade.getRes_addMsg()+"&remark:"+trade.getRes_remark()+"&md5Data:"+trade.getRes_md5Data());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getRes_responseCode()))){
				trade.setRes_responseCode("sfe01");
				trade.setRes_addMsg("timeout!");
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
	public String null2unknown(String in, Map responseFields) {
		if (in == null || in.length() == 0
				|| (String) responseFields.get(in) == null) {
			return "No Value Returned";
		} else {
			return (String) responseFields.get(in);
		}
	}
	
}
