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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import vpn.VpnUtil_Moto;

public class HJPayUtil {
	Logger logger = Logger.getLogger(HJPayUtil.class.getName());
	private static String postUrl="http://101.200.2.118:8080/redirect/getResult";
	public void get(HJPayMessage trade){
		
	 NameValuePair AcctNo  =new BasicNameValuePair("AcctNo",trade.getAcctNo());
	 NameValuePair Agent_AcctNo  = new BasicNameValuePair("Agent_AcctNo",trade.getAgent_AcctNo());
	 NameValuePair OrderID  = new BasicNameValuePair("OrderID",trade.getOrderID());
	 NameValuePair CurrCode  = new BasicNameValuePair("CurrCode",trade.getCurrCode());
	 NameValuePair Amount  = new BasicNameValuePair("Amount",trade.getAmount());
	 NameValuePair IPAddress  = new BasicNameValuePair("IPAddress",trade.getIpAddress());
	 NameValuePair CardType  = new BasicNameValuePair("CardType",trade.getCardType());
	 NameValuePair CardPAN  = new BasicNameValuePair("CardPAN",trade.getCardPAN());
	 NameValuePair CName  = new BasicNameValuePair("CName",trade.getCname());
	 NameValuePair ExpDate  = new BasicNameValuePair("ExpDate",trade.getExpDate());
	 NameValuePair CVV2  = new BasicNameValuePair("CVV2",trade.getCvv2());
//	 NameValuePair PName  = new BasicNameValuePair("PName",trade.getPname());
	 NameValuePair PName  = new BasicNameValuePair("PName","shoesbag");
//	 NameValuePair Issuer  = new BasicNameValuePair("Issuer",trade.getIssuer());
	 NameValuePair IssCountry  = new BasicNameValuePair("IssCountry",trade.getIssCountry());
	 NameValuePair BAddress  = new BasicNameValuePair("BAddress",trade.getBaddress());
	 NameValuePair BCity  = new BasicNameValuePair("BCity",trade.getBcity());
	 NameValuePair PostCode  = new BasicNameValuePair("PostCode",trade.getPostCode());
	 NameValuePair IVersion  = new BasicNameValuePair("IVersion",trade.getIversion());
	 NameValuePair Telephone  = new BasicNameValuePair("Telephone",trade.getTelephone());
	 NameValuePair RetURL  = new BasicNameValuePair("RetURL",trade.getRetURL());
	 NameValuePair Email  = new BasicNameValuePair("Email",trade.getEmail());
	 NameValuePair HashValue  = new BasicNameValuePair("HashValue",trade.getHashValue());
//	 NameValuePair BrowserDate  = new BasicNameValuePair("BrowserDate",trade.getBrowserDate());
	 NameValuePair BrowserUserAgent  = new BasicNameValuePair("BrowserUserAgent",trade.getBrowserUserAgent());
	 NameValuePair CommodityBrand  = new BasicNameValuePair("CommodityBrand",trade.getCommodityBrand());
	 NameValuePair ShipName  = new BasicNameValuePair("ShipName",trade.getShipName());
	 NameValuePair ShipAddress  = new BasicNameValuePair("ShipAddress",trade.getShipAddress());
	 NameValuePair ShipCity  = new BasicNameValuePair("ShipCity",trade.getShipCity());
	 NameValuePair Shipstate  = new BasicNameValuePair("Shipstate",trade.getShipstate());
	 NameValuePair ShipCountry  = new BasicNameValuePair("ShipCountry",trade.getShipCountry());
	 NameValuePair ShipPostCode  = new BasicNameValuePair("ShipPostCode",trade.getShipPostCode());
	 NameValuePair Shipphone  = new BasicNameValuePair("Shipphone",trade.getShipphone());
	 NameValuePair TxnType  = new BasicNameValuePair("TxnType",trade.getTxnType());
	 NameValuePair TxnID  = new BasicNameValuePair("TxnID",trade.getTxnID());
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(AcctNo);
		nvps1.add(Agent_AcctNo);
		nvps1.add(OrderID);
		nvps1.add(CurrCode);
		nvps1.add(Amount);
		nvps1.add(IPAddress);
		nvps1.add(CardType);
		nvps1.add(CardPAN);
		nvps1.add(CName);
		nvps1.add(ExpDate);
		nvps1.add(CVV2);
		nvps1.add(PName);
		nvps1.add(IssCountry);
		nvps1.add(BAddress);
		nvps1.add(BCity);
		nvps1.add(PostCode);
		nvps1.add(IVersion);
		nvps1.add(Telephone);
		nvps1.add(RetURL);
		nvps1.add(Email);
		nvps1.add(HashValue);
		nvps1.add(BrowserUserAgent);
		nvps1.add(CommodityBrand);
		nvps1.add(ShipName);
		nvps1.add(ShipAddress);
		nvps1.add(ShipCity);
		nvps1.add(Shipstate);
		nvps1.add(ShipCountry);
		nvps1.add(ShipPostCode);
		nvps1.add(Shipphone);
		nvps1.add(TxnType);
		nvps1.add(TxnID);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(AcctNo);
		nvps2.add(Agent_AcctNo);
		nvps2.add(OrderID);
		nvps2.add(CurrCode);
		nvps2.add(Amount);
		nvps2.add(IPAddress);
		nvps2.add(CardType);
		nvps2.add(CName);
		nvps2.add(PName);
		nvps2.add(IssCountry);
		nvps2.add(BAddress);
		nvps2.add(BCity);
		nvps2.add(PostCode);
		nvps2.add(IVersion);
		nvps2.add(Telephone);
		nvps2.add(RetURL);
		nvps2.add(Email);
		nvps2.add(HashValue);
		nvps2.add(BrowserUserAgent);
		nvps2.add(CommodityBrand);
		nvps2.add(ShipName);
		nvps2.add(ShipAddress);
		nvps2.add(ShipCity);
		nvps2.add(Shipstate);
		nvps2.add(ShipCountry);
		nvps2.add(ShipPostCode);
		nvps2.add(Shipphone);
		nvps2.add(TxnType);
		nvps2.add(TxnID);
		try{
			HJPayUtil h = new HJPayUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			String resMsg[]=result.split("&");
			for(int i=0;i<resMsg.length;i++){
	         String res[]=resMsg[i].split("=");
	         if("\"Parameter1".equals(res[0])){
	        	 trade.setRes_acctNo(res[1]);
	         }
	         if("Parameter2".equals(res[0])){
	        	 trade.setRes_orderNo(res[1]);
	         }
	         if("Parameter3".equals(res[0])){
	        	 trade.setRes_queOrderNo(res[1]);
	         }
	         if("Parameter4".equals(res[0])){
	        	 trade.setRes_success(res[1]);
	         }
	         if("Parameter5".equals(res[0])){
	        	 trade.setRes_message(res[1]);
	         }
	         if("Parameter6".equals(res[0])){
	        	 trade.setRes_currCode(res[1]);
	         }
	         if("Parameter7".equals(res[0])){
	        	 trade.setRes_amount(res[1]);
	         }
	         if("Parameter8".equals(res[0])){
	        	 trade.setRes_hash(res[1]);
	         }
	         if("Parameter9".equals(res[0])){
	        	 trade.setRes_rmbAmount(res[1]);
	         }
	         if("Parameter10".equals(res[0])){
	        	 trade.setRes_tradeTime(res[1]);
	         }
	         if("Parameter11".equals(res[0])){
	        	 trade.setRes_billaddress(res[1]);
	         }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			if(StringUtils.isBlank(String.valueOf(trade.getRes_success()))){
				trade.setRes_success("sfe01");
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
