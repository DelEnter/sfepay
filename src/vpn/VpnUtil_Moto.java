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


public class VpnUtil_Moto {
	/*
	 * moto交易
	 */
	private MotoDCCMessage dccmessage = new MotoDCCMessage();
	private String str = "";
	private static String postUrl="https://i.bocsolution.com/GatewayT/bgsmoto";
	Logger logger = Logger.getLogger(VpnUtil_Moto.class.getName());
	public String getDccMessage(MotoDCCMessage dccmessag, String type)
			throws HttpException, IOException, KeyManagementException,
			NoSuchAlgorithmException {
		dccmessag.setBocs_ReturnURL("https://www.sfepay.com/");
		HttpPost httpPost = new HttpPost(postUrl);//moto交易

		NameValuePair Trans_Type = null;
		NameValuePair Merchant_Id = null;
		NameValuePair Author_Str = null;
		NameValuePair Terminal_Id = null;
		NameValuePair Invoice_No = null;
		NameValuePair Currency_Code_T = null;
		NameValuePair Amount_Loc = null;
		NameValuePair Card_No = null;
		NameValuePair Exp_Date = null;
		NameValuePair Order_No = null;
		NameValuePair Custom = null;
		NameValuePair Trans_Model = null;
		NameValuePair CSC = null;
		NameValuePair Conversion_Rate = null;
		NameValuePair Currency_Code = null;
		NameValuePair Amount_For = null;
		NameValuePair Ref_No = null;
		NameValuePair Amount_Ref = null;
		NameValuePair Tran_Date_Ori = null;
		NameValuePair Amount_Ori = null;
		NameValuePair Auth_Code = null;
		NameValuePair end_ReturnURL = null;
		NameValuePair bocs_ReturnURL = null;
		NameValuePair CUST_FNAME = null;
		NameValuePair CUST_LNAME = null;
		NameValuePair CUST_ADDR1 = null;
		NameValuePair CUST_CITY = null;
		NameValuePair CUST_STPR_CD = null;
		NameValuePair CUST_CNTRY_CD = null;
		NameValuePair CUST_POSTAL_CD = null;
		NameValuePair CUST_EMAIL = null;
		NameValuePair CUST_IP_ADDR = null;
//		NameValuePair CUST_HOME_PHONE = null;
		NameValuePair SHIP_FNAME = null;
		NameValuePair SHIP_LNAME = null;
		NameValuePair SHIP_ADDR1 = null;
		NameValuePair SHIP_CITY = null;
		NameValuePair SHIP_STPR_CD = null;
		NameValuePair SHIP_CNTRY_CD = null;
		NameValuePair SHIP_POSTAL_CD = null;
		NameValuePair SHIP_EMAIL = null;
		NameValuePair SHIP_IP_ADDR = null;
//		NameValuePair SHIP_HOME_PHONE = null;
		
		if (dccmessag.getTrans_Type() != null) {
			Trans_Type = new BasicNameValuePair("Trans_Type",
					dccmessag.getTrans_Type());
		}
		if (dccmessag.getMerchant_Id() != null) {
			Merchant_Id = new BasicNameValuePair("Merchant_Id",
					dccmessag.getMerchant_Id());
		}
		if (dccmessag.getAuthor_Str() != null) {
			Author_Str = new BasicNameValuePair("Author_Str",
					dccmessag.getAuthor_Str());
		}
		if (dccmessag.getTerminal_Id() != null) {
			Terminal_Id = new BasicNameValuePair("Terminal_Id",
					dccmessag.getTerminal_Id());
		}
		if (dccmessag.getInvoice_No() != null) {
			Invoice_No = new BasicNameValuePair("Invoice_No",
					dccmessag.getInvoice_No());
		}
		if (dccmessag.getCurrency_Code_T() != null) {
			Currency_Code_T = new BasicNameValuePair("Currency_Code_T",
					dccmessag.getCurrency_Code_T());
		}
		if (dccmessag.getAmount_Loc() != null) {
			Amount_Loc = new BasicNameValuePair("Amount_Loc",
					dccmessag.getAmount_Loc());
		}
		if (dccmessag.getCard_No() != null) {
			Card_No = new BasicNameValuePair("Card_No", dccmessag.getCard_No());
		}
		if (dccmessag.getExp_Date() != null) {
			Exp_Date = new BasicNameValuePair("Exp_Date",
					dccmessag.getExp_Date());
		}
		if (dccmessag.getOrder_No() != null) {
			Order_No = new BasicNameValuePair("Order_No",
					dccmessag.getOrder_No());
		}
		if (dccmessag.getCustom() != null) {
			Custom = new BasicNameValuePair("Custom", dccmessag.getCustom());
		}
		if (dccmessag.getTrans_Model() != null) {
			Trans_Model = new BasicNameValuePair("Trans_Model",
					dccmessag.getTrans_Model());
		}
		if (dccmessag.getCSC() != null) {
			CSC = new BasicNameValuePair("CSC", dccmessag.getCSC());
		}
		if (dccmessag.getConversion_Rate() != null) {
			Conversion_Rate = new BasicNameValuePair("Conversion_Rate",
					dccmessag.getConversion_Rate());
		}
		if (dccmessag.getCurrency_Code() != null) {
			Currency_Code = new BasicNameValuePair("Currency_Code",
					dccmessag.getCurrency_Code());
		}
		if (dccmessag.getAmount_For() != null) {
			Amount_For = new BasicNameValuePair("Amount_For",
					dccmessag.getAmount_For());
		}
		if (dccmessag.getAmount_Ref() != null) {
			Amount_Ref = new BasicNameValuePair("Amount_Ref",
					dccmessag.getAmount_Ref());
		}
		if (dccmessag.getRef_No() != null) {
			Ref_No = new BasicNameValuePair("Ref_No", dccmessag.getRef_No());
		}
		if (dccmessag.getTran_Date_Ori() != null) {
			Tran_Date_Ori = new BasicNameValuePair("Tran_Date_Ori",
					dccmessag.getTran_Date_Ori());
		}
		if (dccmessag.getAmount_Ori() != null) {
			Amount_Ori = new BasicNameValuePair("Amount_Ori",
					dccmessag.getAmount_Ori());
		}
		if (dccmessag.getAuth_Code() != null) {
			Auth_Code = new BasicNameValuePair("Auth_Code",
					dccmessag.getAuth_Code());
		}
		if (dccmessag.getEnd_ReturnURL() != null) {
			end_ReturnURL = new BasicNameValuePair("end_ReturnURL",
					dccmessag.getEnd_ReturnURL());
		}
		if (dccmessag.getBocs_ReturnURL()!=null) {
			bocs_ReturnURL = new BasicNameValuePair("bocs_ReturnURL",
					dccmessag.getBocs_ReturnURL());
		}
		if (dccmessag.getCUST_FNAME()!=null) {
			CUST_FNAME = new BasicNameValuePair("CUST_FNAME",
					dccmessag.getCUST_FNAME());
		}
		if (dccmessag.getCUST_LNAME()!=null) {
			CUST_LNAME = new BasicNameValuePair("CUST_LNAME",
					dccmessag.getCUST_LNAME());
		}
		if (dccmessag.getCUST_ADDR1()!=null) {
			CUST_ADDR1 = new BasicNameValuePair("CUST_ADDR1",
					dccmessag.getCUST_ADDR1());
		}
		if (dccmessag.getCUST_CITY()!=null) {
			CUST_CITY = new BasicNameValuePair("CUST_CITY",
					dccmessag.getCUST_CITY());
		}
		if (dccmessag.getCUST_STPR_CD()!=null) {
			CUST_STPR_CD = new BasicNameValuePair("CUST_STPR_CD",
					dccmessag.getCUST_STPR_CD());
		}
		if (dccmessag.getCUST_CNTRY_CD()!=null) {
			CUST_CNTRY_CD = new BasicNameValuePair("CUST_CNTRY_CD",
					dccmessag.getCUST_CNTRY_CD());
		}
		if (dccmessag.getCUST_POSTAL_CD()!=null) {
			CUST_POSTAL_CD = new BasicNameValuePair("CUST_POSTAL_CD",
					dccmessag.getCUST_POSTAL_CD());
		}
		if (dccmessag.getCUST_EMAIL()!=null) {
			CUST_EMAIL = new BasicNameValuePair("CUST_EMAIL",
					dccmessag.getCUST_EMAIL());
		}
		if (dccmessag.getCUST_IP_ADDR()!=null) {
			CUST_IP_ADDR = new BasicNameValuePair("CUST_IP_ADDR",
					dccmessag.getCUST_IP_ADDR());
		}
//		if (dccmessag.getCUST_HOME_PHONE()!=null) {
//			CUST_HOME_PHONE = new BasicNameValuePair("CUST_HOME_PHONE",
//					dccmessag.getCUST_HOME_PHONE());
//		}
		if (dccmessag.getSHIP_FNAME()!=null) {
			SHIP_FNAME = new BasicNameValuePair("SHIP_FNAME",
					dccmessag.getSHIP_FNAME());
		}
		if (dccmessag.getSHIP_LNAME()!=null) {
			SHIP_LNAME = new BasicNameValuePair("SHIP_LNAME",
					dccmessag.getSHIP_LNAME());
		}
		if (dccmessag.getSHIP_ADDR1()!=null) {
			SHIP_ADDR1 = new BasicNameValuePair("SHIP_ADDR1",
					dccmessag.getSHIP_ADDR1());
		}
		if (dccmessag.getSHIP_CITY()!=null) {
			SHIP_CITY = new BasicNameValuePair("SHIP_CITY",
					dccmessag.getSHIP_CITY());
		}
		if (dccmessag.getSHIP_STPR_CD()!=null) {
			SHIP_STPR_CD = new BasicNameValuePair("SHIP_STPR_CD",
					dccmessag.getSHIP_STPR_CD());
		}
		if (dccmessag.getSHIP_CNTRY_CD()!=null) {
			SHIP_CNTRY_CD = new BasicNameValuePair("SHIP_CNTRY_CD",
					dccmessag.getSHIP_CNTRY_CD());
		}
		if (dccmessag.getSHIP_POSTAL_CD()!=null) {
			SHIP_POSTAL_CD = new BasicNameValuePair("SHIP_POSTAL_CD",
					dccmessag.getSHIP_POSTAL_CD());
		}
		if (dccmessag.getSHIP_EMAIL()!=null) {
			SHIP_EMAIL = new BasicNameValuePair("SHIP_EMAIL",
					dccmessag.getSHIP_EMAIL());
		}
		if (dccmessag.getSHIP_IP_ADDR()!=null) {
			SHIP_IP_ADDR = new BasicNameValuePair("SHIP_IP_ADDR",
					dccmessag.getSHIP_IP_ADDR());
		}
//		if (dccmessag.getSHIP_HOME_PHONE()!=null) {
//			SHIP_HOME_PHONE = new BasicNameValuePair("SHIP_HOME_PHONE",
//					dccmessag.getSHIP_HOME_PHONE());
//		}
//		NameValuePair returnUrl = new BasicNameValuePair("bocs_ReturnURL",
//				dccmessag.getBocs_ReturnURL());

		NameValuePair HASH = new BasicNameValuePair("HASH", dccmessag.getHash());
		NameValuePair submit = new BasicNameValuePair("submit", "Continue");

		// post.setParameter("referer", "http://localhost/sfe");

		// dcc 
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		List<NameValuePair> nvpslog = new ArrayList<NameValuePair>();
		nvps1.add(Trans_Type);
		nvps1.add(Merchant_Id);
		nvps1.add(Author_Str);
		nvps1.add(Terminal_Id);
		nvps1.add(Invoice_No);
		nvps1.add(Order_No);
		nvps1.add(Custom);
		nvps1.add(bocs_ReturnURL);
		nvps1.add(HASH);
		//日志保留
		nvpslog.add(Trans_Type);
		nvpslog.add(Merchant_Id);
		nvpslog.add(Author_Str);
		nvpslog.add(Terminal_Id);
		nvpslog.add(Invoice_No);
		nvpslog.add(Order_No);
		nvpslog.add(Custom);
		nvpslog.add(bocs_ReturnURL);
		nvpslog.add(HASH);
		String result = "";
		//查汇
		if (type.equals("1")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvpslog.add(Currency_Code_T);
			nvpslog.add(Amount_Loc);
		}
		// DCC MOTO
		if (type.equals("2")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
			nvpslog.add(Trans_Model);
			nvpslog.add(Currency_Code_T);
			nvpslog.add(Amount_Loc);
			nvpslog.add(Currency_Code);
			nvpslog.add(Amount_For);
		}
		if (type.equals("21")) {
			nvps1.add(CUST_FNAME);
			nvps1.add(CUST_LNAME);
			nvps1.add(CUST_ADDR1);
			nvps1.add(CUST_CITY);
//			nvps1.add(CUST_STPR_CD);
			nvps1.add(CUST_CNTRY_CD);
			nvps1.add(CUST_POSTAL_CD);
			nvps1.add(CUST_EMAIL);
			nvps1.add(CUST_IP_ADDR);
//			nvps1.add(CUST_HOME_PHONE);
			nvps1.add(SHIP_FNAME);
			nvps1.add(SHIP_LNAME);
			nvps1.add(SHIP_ADDR1);
			nvps1.add(SHIP_CITY);
//			nvps1.add(SHIP_STPR_CD);
			nvps1.add(SHIP_CNTRY_CD);
			nvps1.add(SHIP_POSTAL_CD);
			nvps1.add(SHIP_EMAIL);
			nvps1.add(SHIP_IP_ADDR);
//			nvps1.add(SHIP_HOME_PHONE);
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
			nvpslog.add(CUST_FNAME);
			nvpslog.add(CUST_LNAME);
			nvpslog.add(CUST_ADDR1);
			nvpslog.add(CUST_CITY);
			nvpslog.add(CUST_CNTRY_CD);
			nvpslog.add(CUST_POSTAL_CD);
			nvpslog.add(CUST_EMAIL);
			nvpslog.add(CUST_IP_ADDR);
			nvpslog.add(SHIP_FNAME);
			nvpslog.add(SHIP_LNAME);
			nvpslog.add(SHIP_ADDR1);
			nvpslog.add(SHIP_CITY);
			nvpslog.add(SHIP_CNTRY_CD);
			nvpslog.add(SHIP_POSTAL_CD);
			nvpslog.add(SHIP_EMAIL);
			nvpslog.add(SHIP_IP_ADDR);
			nvpslog.add(Trans_Model);
			nvpslog.add(Currency_Code_T);
			nvpslog.add(Amount_Loc);
			nvpslog.add(Currency_Code);
			nvpslog.add(Amount_For);
		}
		// EDC MOTO
		if (type.equals("3")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
		}
	
		if (type.equals("4")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
		}
	
		if (type.equals("5")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Ori);
			nvps1.add(Amount_Loc);
			nvps1.add(Tran_Date_Ori);
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
			nvpslog.add(Currency_Code_T);
			nvpslog.add(Amount_Ori);
			nvpslog.add(Amount_Loc);
			nvpslog.add(Tran_Date_Ori);
			nvpslog.add(Ref_No);
		}
		
		if (type.equals("6")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
		}
		
		if (type.equals("7")) {
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
			nvps1.add(submit);
		}
		
		if (type.equals("8")) {
		}
		
		if (type.equals("9")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Auth_Code);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
		}
		
		if (type.equals("10")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Auth_Code);
		}
	
		if (type.equals("11")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
		}
		//2.5p EDC
		if (type.equals("12")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
//			nvps1.add(Currency_Code);
//			nvps1.add(Amount_For);
			nvps1.add(end_ReturnURL);
			//2.5P DCC
		}if(type.equals("13")){
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
			nvps1.add(end_ReturnURL);			
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps1, "UTF-8"));
		System.out.println("上送报文"+nvps1);
		logger.info("上送报文"+nvpslog);//日志文件
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if(postUrl.toLowerCase().startsWith("https")){
				httpclient = getInstance(httpclient);
			}
			HttpResponse response = httpclient.execute(httpPost);
			//System.out.println("sttus:"+response.getStatusLine());
			//输出http post响应码
			//输出响应信息
			//Header[] heads = response.getAllHeaders();  
	        // 打印所有响应头  
	        //for(Header h:heads){  
	        //    System.out.println(h.getName()+":"+h.getValue());  
	       // }  
			HttpEntity ent = response.getEntity();
			//获取响应内容
			String a = EntityUtils.toString(ent);
			//输出响应报文
			logger.info("返回报文"+a);
			if(type.equals("12")||type.equals("13")){
				result=a;
			}
			else
			{
			//Header[] headers = response.getHeaders("Location");
			//for (Header header : headers) {
			//	if (StringUtils.isNotBlank(header.getValue())) {
			//		result = header.getValue();
				result = a;
				//}
			//}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
		System.out.println("queryString:" + queryString);
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int i = token.indexOf('=');
			if (i > 0) {
				try {
					String key = token.substring(0, i);
					if (key.indexOf("?") != -1) {
						key = key.substring(key.indexOf("?") + 1, key.length());
					}
					String value = URLDecoder.decode(token.substring(i + 1,
							token.length()));
					map.put(key, value);
				} catch (Exception ex) {
				}
			}
		}
		return map;
	}
	public String null2unknown(String in, Map responseFields) {
		if (in == null || in.length() == 0
				|| (String) responseFields.get(in) == null) {
			return "No Value Returned";
		} else {
			return (String) responseFields.get(in);
		}
	}

	public MotoDCCMessage getDCCvalue(MotoDCCMessage dccMessage, String type)
			throws HttpException, IOException {
		try {

			String tem = this.getDccMessage(dccMessage, type);
			//Map mp = this.createMapFromResponse(tem.substring(
			//		tem.indexOf("?") + 1, tem.length()));
			Map mp = this.createMapFromResponse(tem);

			this.dccmessage.setAmount_For(null2unknown("Amount_For", mp));
			this.dccmessage.setAmount_Loc(null2unknown("Amount_Loc", mp));
			this.dccmessage.setAuthor_Str(null2unknown("Author_Str", mp));
			this.dccmessage
					.setBocs_ReturnURL(null2unknown("Bocs_ReturnURL", mp));
			this.dccmessage.setCard_No(null2unknown("Card_No", mp));
			this.dccmessage.setConversion_Rate(null2unknown("Conversion_Rate",
					mp));
			this.dccmessage.setCSC(null2unknown("CSC", mp));
			this.dccmessage.setCurrency_Code(null2unknown("Currency_Code", mp));
			this.dccmessage.setCurrency_Code_T(null2unknown("Currency_Code_T",
					mp));
			this.dccmessage.setCustom(null2unknown("Custom", mp));
			this.dccmessage.setExp_Date(null2unknown("Exp_Date", mp));
			this.dccmessage.setInvoice_No(null2unknown("Invoice_No", mp));
			this.dccmessage.setMerchant_Id(null2unknown("Merchant_Id", mp));
			this.dccmessage.setOrder_No(null2unknown("Order_No", mp));
			this.dccmessage.setTerminal_Id(null2unknown("Terminal_Id", mp));
			this.dccmessage.setTrans_Model(null2unknown("Trans_Model", mp));
			this.dccmessage.setTrans_Type(null2unknown("Trans_Type", mp));
			this.dccmessage.setRef_No(null2unknown("Ref_No", mp));
			this.dccmessage.setAmount_Ref(null2unknown("Amount_Ref", mp));
			this.dccmessage.setTran_Date_Ori(null2unknown("Tran_Date_Ori", mp));
			this.dccmessage.setResp_Code(null2unknown("Resp_Code", mp));
			this.dccmessage.setAuth_Code(null2unknown("Auth_Code", mp));
			this.dccmessage.setCurrency(null2unknown("Currency", mp));
			this.dccmessage.setToken_id(null2unknown("token_id", mp));
			this.dccmessage.setSend_Url(null2unknown("Send_Url", mp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.dccmessage;
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
