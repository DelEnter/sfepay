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

public class VpnUtil {
	/*
	 * ���ߣ� ũ����
	 */
	private DCCMessage dccmessage = new DCCMessage();
	private String str = "";
	private static String postUrl="https://i.bocsolution.com/GatewayT/bigs2";
	public String getDccMessage(DCCMessage dccmessag, String type)
			throws HttpException, IOException {

		dccmessag.setBocs_ReturnURL("http://172.21.66.11/sfe");
		HttpPost httpPost = new HttpPost(postUrl);
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
		NameValuePair bocs_ReturnURL = null;
		NameValuePair end_ReturnURL = null;

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
		NameValuePair returnUrl = new BasicNameValuePair("bocs_ReturnURL",
				dccmessag.getBocs_ReturnURL());

		NameValuePair HASH = new BasicNameValuePair("HASH", dccmessag.getHash());
		NameValuePair submit = new BasicNameValuePair("submit", "Continue");

		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(Trans_Type);
		nvps1.add(Merchant_Id);
		nvps1.add(Author_Str);
		nvps1.add(Terminal_Id);
		nvps1.add(Invoice_No);

		nvps1.add(Order_No);
		nvps1.add(Custom);
		nvps1.add(returnUrl);
		nvps1.add(HASH);
		String result = "";

		// dcc ��ѯ
		if (type.equals("1")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
		}
		// DCC ����
		if (type.equals("2")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
		}
		// EDC����
		if (type.equals("3")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
		}
		// ���׳���
		if (type.equals("4")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
		}
		// �����˿�
		if (type.equals("5")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Ori);
			nvps1.add(Amount_Loc);
			nvps1.add(Tran_Date_Ori);
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
		}
		// ���׳���
		if (type.equals("6")) {
			// post.setRequestBody(new NameValuePair[] {
			// Trans_Type,Merchant_Id,Author_Str,Terminal_Id
			// ,Invoice_No,Currency_Code_T,Amount_Loc,Card_No,Order_No,Custom,returnUrl,HASH});
		}
		// ���ײ�ѯ
		if (type.equals("7")) {
			nvps1.add(Card_No);
			nvps1.add(Ref_No);
			nvps1.add(submit);
		}
		// ���׽���
		if (type.equals("8")) {
		}
		// ���Ԥ��Ȩdcc
		if (type.equals("9")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Auth_Code);
			nvps1.add(Currency_Code);
			nvps1.add(Amount_For);
		}
		// ���Ԥ��edc
		if (type.equals("10")) {
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Auth_Code);
		}
		// Ԥ��Ȩ
		if (type.equals("11")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(Card_No);
			nvps1.add(Exp_Date);
			nvps1.add(CSC);
		}
		if (type.equals("12")) {
			nvps1.add(Trans_Model);
			nvps1.add(Currency_Code_T);
			nvps1.add(Amount_Loc);
			nvps1.add(bocs_ReturnURL);
			nvps1.add(end_ReturnURL);
//			nvps1.add(Currency_Code);
//			nvps1.add(Amount_For);
		}

		httpPost.setEntity(new UrlEncodedFormEntity(nvps1, "UTF-8"));
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if(postUrl.toLowerCase().startsWith("https")){
				httpclient = getInstance(httpclient);
			}
			HttpResponse response = httpclient.execute(httpPost);
			Header[] headers = response.getHeaders("Location");
			for (Header header : headers) {
				if (StringUtils.isNotBlank(header.getValue())) {
					result = header.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ��������
	private Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
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
					// Do Nothing and keep looping through data
				}
			}
		}
		return map;
	}

	// ȡֵ
	public String null2unknown(String in, Map responseFields) {
		if (in == null || in.length() == 0
				|| (String) responseFields.get(in) == null) {
			return "No Value Returned";
		} else {
			return (String) responseFields.get(in);
		}
	}

	/*
	 * type 1 ��ѯ���� 2 dcc���� 3 Edc ���� 4 ���׳��� 5 �����˿� 6 ���׳��� 7 ���ײ�ѯ 8 ���׽��� 9
	 * ���Ԥ��Ȩdcc 10���Ԥ��edc 11 Ԥ��Ȩ
	 */
	public DCCMessage getDCCvalue(DCCMessage dccMessage, String type)
			throws HttpException, IOException {
		String tem = this.getDccMessage(dccMessage, type);
		Map mp = this.createMapFromResponse(tem);
		this.dccmessage.setAmount_For(null2unknown("Amount_For", mp));
		this.dccmessage.setAmount_Loc(null2unknown("Amount_Loc", mp));
		this.dccmessage.setAuthor_Str(null2unknown("Author_Str", mp));
		this.dccmessage.setBocs_ReturnURL(null2unknown("Bocs_ReturnURL", mp));
		this.dccmessage.setCard_No(null2unknown("Card_No", mp));
		this.dccmessage.setConversion_Rate(null2unknown("Conversion_Rate", mp));
		this.dccmessage.setCSC(null2unknown("CSC", mp));
		this.dccmessage.setCurrency_Code(null2unknown("Currency_Code", mp));
		this.dccmessage.setCurrency_Code_T(null2unknown("Currency_Code_T", mp));
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
