package vpn;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;

import vpn.VpnUtil_Moto;

public class CaibaoUtil{
	Logger logger = Logger.getLogger(CaibaoUtil.class.getName());
	private static String postUrl="https://quickly-online.com/TPInterface";

	public void get(CaibaoMessage trade){			 	
	 NameValuePair merNo =new BasicNameValuePair("merNo",trade.getMerNo());
	 NameValuePair gatewayNo  = new BasicNameValuePair("gatewayNo",trade.getGatewayNo());
	 NameValuePair orderNo  = new BasicNameValuePair("orderNo",trade.getOrderNo());
	 NameValuePair orderCurrency  = new BasicNameValuePair("orderCurrency",trade.getOrderCurrency());
	 NameValuePair orderAmount  = new BasicNameValuePair("orderAmount",trade.getOrderAmount());
	 NameValuePair returnURL  = new BasicNameValuePair("returnURL",trade.getReturnURL());
	 NameValuePair cardNo  = new BasicNameValuePair("cardNo",trade.getCardNo());
	 NameValuePair cardSecurityCode  = new BasicNameValuePair("cardSecurityCode",trade.getCardSecurityCode());
	 NameValuePair cardExpireYear  = new BasicNameValuePair("cardExpireYear",trade.getCardExpireYear());
	 NameValuePair cardExpireMonth  = new BasicNameValuePair("cardExpireMonth",trade.getCardExpireMonth());
	 NameValuePair issuingBank  = new BasicNameValuePair("issuingBank",trade.getIssuingBank());
	 NameValuePair ip  = new BasicNameValuePair("ip",trade.getIp());
	 NameValuePair email  = new BasicNameValuePair("email",trade.getEmail());
	 NameValuePair PaymentMethod  = new BasicNameValuePair("PaymentMethod",trade.getPaymentMethod());
	 NameValuePair phone  = new BasicNameValuePair("phone",trade.getPhone());
	 NameValuePair country  = new BasicNameValuePair("country",trade.getCountry());
	 NameValuePair state  = new BasicNameValuePair("state",trade.getState());
	 NameValuePair city  = new BasicNameValuePair("city",trade.getCity());
	 NameValuePair address  = new BasicNameValuePair("address",trade.getAddress());
	 NameValuePair zip=null;
	 if(StringUtils.isNotBlank(trade.getZip())){
		 zip  = new BasicNameValuePair("zip",trade.getZip());
	 }else{
		 zip  = new BasicNameValuePair("zip","90713");
	 }
	 NameValuePair signInfo  = new BasicNameValuePair("signInfo",trade.getSignInfo());
	 NameValuePair isAuthor  = new BasicNameValuePair("isAuthor",trade.getIsAuthor());
	 NameValuePair remark  = new BasicNameValuePair("remark",trade.getRemark());
	 NameValuePair firstName  = new BasicNameValuePair("firstName",trade.getFirstName());
	 NameValuePair lastName  = new BasicNameValuePair("lastName",trade.getLastName());
	 NameValuePair csid  = null;
	 if(StringUtils.isNotBlank(trade.getTcsid())){
		 csid  = new BasicNameValuePair("csid",trade.getTcsid());
	 }else{
		 csid  = new BasicNameValuePair("csid",trade.getCsid());
	 }
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(merNo);
		nvps1.add(gatewayNo);
		nvps1.add(orderNo);
		nvps1.add(orderCurrency);
		nvps1.add(orderAmount);
		nvps1.add(returnURL);
		nvps1.add(cardNo);
		nvps1.add(cardSecurityCode);
		nvps1.add(cardExpireYear);
		nvps1.add(cardExpireMonth);
		nvps1.add(issuingBank);
		nvps1.add(ip);
		nvps1.add(email);
		nvps1.add(PaymentMethod);
		nvps1.add(phone);
		nvps1.add(firstName);
		nvps1.add(lastName);
		nvps1.add(country);
		nvps1.add(state);
		nvps1.add(city);
		nvps1.add(address);
		nvps1.add(zip);
		nvps1.add(signInfo);
		nvps1.add(isAuthor);
		nvps1.add(remark);
		nvps1.add(csid);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(merNo);
		nvps2.add(gatewayNo);
		nvps2.add(orderNo);
		nvps2.add(orderCurrency);
		nvps2.add(orderAmount);
		nvps2.add(returnURL);
		nvps2.add(issuingBank);
		nvps2.add(ip);
		nvps2.add(email);
		nvps2.add(PaymentMethod);
		nvps2.add(phone);
		nvps2.add(firstName);
		nvps2.add(lastName);
		nvps2.add(country);
		nvps2.add(state);
		nvps2.add(city);
		nvps2.add(address);
		nvps2.add(zip);
		nvps2.add(signInfo);
		nvps2.add(isAuthor);
		nvps2.add(remark);
		nvps2.add(csid);
		try{
			CaibaoUtil h = new CaibaoUtil();
			logger.info("提交数据:"+nvps2.toString());
			String result = h.httpPost(nvps1,postUrl);
			logger.info("返回数据："+result);
			try {
				Document parseText = DocumentHelper.parseText(result);
				
				Element rootElement = parseText.getRootElement();
				for (Iterator i = rootElement.elementIterator(); i.hasNext();) {
					Element next = (Element) i.next();
					if (next.getName().equals("merNo")) {
						trade.setRes_merNo(next.getText().trim());
					}
					if (next.getName().equals("gatewayNo")) {
						trade.setRes_gatewayNo(next.getText().trim());
					}					
					if (next.getName().equals("tradeNo")) {
						trade.setRes_tradeNo(next.getText().trim());
					}
					if (next.getName().equals("orderNo")) {
						trade.setRes_orderNo(next.getText().trim());
					}
					if (next.getName().equals("orderCurrency")) {
						trade.setRes_orderCurrency(next.getText().trim());
					}
					if (next.getName().equals("orderAmount")) {
						trade.setRes_orderAmount(next.getText().trim());
					}
					if (next.getName().equals("orderStatus")) {
						trade.setRes_orderStatus(next.getText().trim());
					}
					if (next.getName().equals("orderInfo")) {
						trade.setRes_orderInfo( next.getText().trim());
					}
					if (next.getName().equals("signInfo")) {
						trade.setRes_signInfo(next.getText().trim());
					}	
					if (next.getName().equals("riskInfo")) {
						trade.setRes_riskInfo( next.getText().trim());
					}
					if (next.getName().equals("remark")) {
						trade.setRes_remark(next.getText().trim());
					}
					if (next.getName().equals("billAddress")) {
						trade.setRes_billAddress(next.getText().trim());
					}
					
				}
				
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("通道请求超时，3秒钟后重新发送。。。");
			try{
				Thread.sleep(3*1000);
				CaibaoUtil h = new CaibaoUtil();
				logger.info("提交数据:"+nvps2.toString());
				postUrl = "https://safeshopingmall.com/TPInterface";
				String result = h.httpPost(nvps1,postUrl);
				logger.info("返回数据："+result);
				try {
					Document parseText = DocumentHelper.parseText(result);
					
					Element rootElement = parseText.getRootElement();
					for (Iterator i = rootElement.elementIterator(); i.hasNext();) {
						Element next = (Element) i.next();
						if (next.getName().equals("merNo")) {
							trade.setRes_merNo(next.getText().trim());
						}
						if (next.getName().equals("gatewayNo")) {
							trade.setRes_gatewayNo(next.getText().trim());
						}					
						if (next.getName().equals("tradeNo")) {
							trade.setRes_tradeNo(next.getText().trim());
						}
						if (next.getName().equals("orderNo")) {
							trade.setRes_orderNo(next.getText().trim());
						}
						if (next.getName().equals("orderCurrency")) {
							trade.setRes_orderCurrency(next.getText().trim());
						}
						if (next.getName().equals("orderAmount")) {
							trade.setRes_orderAmount(next.getText().trim());
						}
						if (next.getName().equals("orderStatus")) {
							trade.setRes_orderStatus(next.getText().trim());
						}
						if (next.getName().equals("orderInfo")) {
							trade.setRes_orderInfo( next.getText().trim());
						}
						if (next.getName().equals("signInfo")) {
							trade.setRes_signInfo(next.getText().trim());
						}	
						if (next.getName().equals("riskInfo")) {
							trade.setRes_riskInfo( next.getText().trim());
						}
						if (next.getName().equals("remark")) {
							trade.setRes_remark(next.getText().trim());
						}
						if (next.getName().equals("billAddress")) {
							trade.setRes_billAddress(next.getText().trim());
						}
						
					}
					
					
				} catch (DocumentException de) {
					// TODO Auto-generated catch block
					de.printStackTrace();
				}
		}catch(Exception ea){
			ea.printStackTrace();
		}

		}finally{
			if(StringUtils.isEmpty(trade.getRes_orderStatus())){
				trade.setRes_orderStatus("0");
				trade.setRes_orderInfo("sfe01");
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



