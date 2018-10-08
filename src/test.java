import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.MalformedCookieException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import vpn.CaibaoMessage;
import vpn.CaibaoUtil;
import vpn.GooPayMessage;
import vpn.GooPayUtil;
import vpn.HRPayMessage;
import vpn.HRPayUtil;
import vpn.SfeMessage;
import vpn.SfeUtil;
import vpn.VpnUtil;
import vpn.VpnUtil_Moto;
import vpn.YouPayMessage;
import vpn.YouPayUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.PayNoticeThread;
import com.ecpss.action.TemporarySynThread;
import com.ecpss.action.pay.util.MaxMindExample;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.risk.InternationalBackMaxMind;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.MD5;


public class test extends BaseAction {

	public static void main(String[] args) {
		try {
			HashMap hm = new HashMap();
			// 加密串 license_key : UxQh0mA4aLqw 调试和正式运行时要加上,才会返回分数
			// 上海key: CxsRZ1xPPRbR;
			// 广州key: UxQh0mA4aLqw
			String domian ="yahoo.com";
			hm.put("license_key", "9kbrHiIOJ9ZS");
			hm.put("i", "172.58.97.87");
			hm.put("domain", domian);
			MD5 md5=new MD5();
			hm.put("emailMD5","3E5A735E7E43E84B2A0CE5FE63A14847");
			hm.put("custPhone", "5043339020");
			hm.put("country", "US");
			hm.put("city", "New orleans");
			hm.put("region", "Louisiana");
			hm.put("shipAddr", "2235 eads st");
		
			hm.put("postal", "70117");
			// hm.put("bin", cardnum);
			hm.put("bin", "406042");
			hm.put("binName", "Chase");

			// standard 低级
			// premium 高级
			// 正式运行的时候要用这个 premium ; standard为调试用的标准
			hm.put("requested_type", "premium");

			Hashtable ht = getmmValue(hm,new Long(1));
			String maxmindValue = (String) ht.get("values");
			System.out.println("maxmindValue--------------" + maxmindValue);
		}catch(Exception e){
			e.printStackTrace();
		}
//		InternationalMerchant merchant=new InternationalMerchant();
//		EmailInfo emailinfo = new EmailInfo();
//		CCSendMail.setSendMail("878701211@qq.com",
//				emailinfo.getResetPwdByEmail(merchant,"123456"), "xingbill@xingbill.com");
//			 System.out.println("riskValue--------------"+riskValue);
//			CaibaoUtil ut=new CaibaoUtil();
//			CaibaoMessage ms=new CaibaoMessage();
//			ms.setMerNo("20668");
//			ms.setGatewayNo("20668001");
//			ms.setOrderNo("s000001");
//			ms.setOrderCurrency("CNY");
//			ms.setOrderAmount("100.02");
//			ms.setReturnURL("www.sfepay.com");
//			ms.setCardNo("4111111111111111");
//			ms.setCardExpireYear("2016");
//			ms.setCardExpireMonth("02");
//			ms.setCardSecurityCode("212");
//			ms.setIssuingBank("bank");
//			ms.setIp("180.64.48.98");
//			ms.setEmail("12345@126.com");
//			ms.setPaymentMethod("Credit Card");
//			ms.setPhone("02112345678");
//			ms.setCountry("USA");
//			ms.setState("Canada");
//			ms.setCity("tue");
//			ms.setAddress("asfdsfsdfsfs");
//			ms.setZip("518000");
//			ms.setFirstName("heh");
//			ms.setLastName("hehe");
//			String sign=ms.getMerNo()+ms.getGatewayNo()+ms.getOrderNo()+ms.getOrderCurrency()+ms.getOrderAmount()+ms.getFirstName()+ms.getLastName() + ms.getCardNo() + ms.getCardExpireYear()+ms.getCardExpireMonth()+ms.getCardSecurityCode()+ ms.getEmail() + "j6244nJ4";
////			MessageDigest md = MessageDigest.getInstance("SHA-256");
////			md.update(sign.getBytes());
//            String strDes = getSha256(sign); // to HexString
//			ms.setSignInfo(strDes);
//			ms.setIsAuthor("");
//			ms.setRemark("");
//			ut.get(ms);
//			System.out.println(ms.getRes_orderStatus());
				
//			 HttpClient client = new HttpClient(); 
//			 PostMethod method = new PostMethod("https://180.153.98.150/directPayment"); 
//			try {
//	             NameValuePair[] query_data = new NameValuePair[1];
//	             query_data[0]=new NameValuePair("merNo", "20668");
//	             
//	             method.setRequestBody(query_data);
//	             client.executeMethod(method);
//	             String content = method.getResponseBodyAsString();
//	             System.out.println(content);
//	             method.releaseConnection();
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				method.releaseConnection();
//			}
//		vpn.MotoDCCMessage dcc2 = new vpn.MotoDCCMessage();
//		dcc2.setTrans_Type("risk");// 类型
//		dcc2.setMerchant_Id("021822858");// 42 商户编号
//		dcc2.setAuthor_Str("axs3prxw");
//		dcc2.setTerminal_Id("07992944");// 41 商户终端号
//		dcc2.setOrder_No("1001141259363588749");// 62
//		
//		dcc2.setInvoice_No(dcc2.getOrder_No().substring(
//				dcc2.getOrder_No().length() - 6,
//				dcc2.getOrder_No().length()));
//
//		dcc2.setTrans_Model("M");//moto通道
//		dcc2.setCurrency_Code_T("156");// 货币代码
//		dcc2.setAmount_Loc(buzero("123.00"));// 4
//		// 本地交易金额
//		dcc2.setCard_No("4111111111111111");// 账号2
//		dcc2.setExp_Date("1402");// 14
//		dcc2.setCSC("212");
//		dcc2.setCurrency_Code("CNY");
//		dcc2.setBocs_ReturnURL("http://172.20.66.2/sfe");
//		dcc2.setAmount_For(dcc2.getAmount_Loc());
//		dcc2.setCustom(dcc2.getOrder_No());
//		dcc2.setHashCode("bqkrxzjrvqeah1xpg1wy9amysad5cc9u");
//		//新增风险交易参数
//		dcc2.setCUST_FNAME("adb");
//		dcc2.setCUST_LNAME("abc");
//		dcc2.setCUST_CITY("adb");
//		dcc2.setCUST_ADDR1("shanghai");
//		dcc2.setCUST_CNTRY_CD("USA");
//		dcc2.setCUST_EMAIL("123456@126.com");
//		dcc2.setCUST_IP_ADDR("127.0.0.1");
////		dcc2.setCUST_HOME_PHONE(phone);
//		dcc2.setCUST_POSTAL_CD("123456");
////		dcc2.setCUST_STPR_CD(state);
//		dcc2.setSHIP_FNAME("abc");
//		dcc2.setSHIP_LNAME("abc");
//		dcc2.setSHIP_CITY("abc");
//		dcc2.setSHIP_ADDR1("abc");
//		dcc2.setSHIP_CNTRY_CD("USA");
//		dcc2.setSHIP_EMAIL("123456@126.COM");
//		dcc2.setSHIP_IP_ADDR("127.0.0.1");
////		dcc2.setSHIP_HOME_PHONE(shippingPhone);
//		dcc2.setSHIP_POSTAL_CD("123456");
////		dcc2.setSHIP_STPR_CD(shippingSstate);
//		VpnUtil_Moto vu2=new VpnUtil_Moto();
//		//VpnUtil vu2 = new VpnUtil();
//		Long tim2 = System.currentTimeMillis();
//		try {
//			// type 2 dcc交易
//			 dcc2 = vu2.getDCCvalue(dcc2, "21");
//			 System.out.println(dcc2.getResp_Code());
//		} catch (Exception e) {
//		e.printStackTrace();
//}
//		String[] re;
//        SfeUtil su = new SfeUtil();
//        SfeMessage sm = new SfeMessage();
//        sm.setMerNo("3604");
//        sm.setAmount("0.1");
//        sm.setCurrency("CNY");
////        String[] tradWeb = "http://www.baidu.com/sfepay".split("/");
//        sm.setTradeAdd("www.bestbridalstyles.com");
//        sm.setTradetime(new Date());
//        sm.setReturnURL("http://www.bestbridalstyles.com/sfepay/payment/return/ ");
//        sm.setCardNo("4520340039750362");
//        sm.setCvv2("4520");
//        sm.setYear("16");
//        sm.setMonth("04");
//        sm.setMerchantOrderNo("360400000000001");
//        sm.setMD5key("negyPVkv");
//        sm.setIp("88.6.211.102");
//        sm.setCartype("4");
//        String Agent = "";
//        try {
//          Agent = request.getHeader("User-Agent");
//          StringTokenizer st = new StringTokenizer(Agent, ";");
//          st.nextToken();
//
//          this.userbrowser = st.nextToken();
//        } catch (Exception e) {
//          this.logger.error(e);
//          this.userbrowser = Agent;
//        }
//        if (StringUtils.isBlank(this.userbrowser))
//          String "MSIE 10.0" = "MSIE 10.0";

//        sm.setUserbrowser("MSIE 10.0");
//        sm.setCookie("");
//        sm.setFirstname("MOISES");
//        sm.setLastname("FERNANDEZ MANSILLA");
//        sm.setAddress("RAVESIA DEL ROBLE");
//        sm.setCity("YEBES VALDELUZ");
//        sm.setState("Guadalajara");
//        sm.setCountry("USAUS");
//        sm.setZipcode("19139");
//        sm.setEmail("MOISESN1@HOTMAIL.COM");
//        sm.setPhone("647594788");
//        sm.setShippingFirstName("MOISES");
//        sm.setShippingLastName("FERNANDEZ MANSILLA");
//        sm.setShippingAddress("ΧΙΟΣΧΙΟΣGRCGRΜΑΜΟΥΚΑ11");
//        sm.setShippingCity("YEBES VALDELUZ");
//        sm.setShippingSstate("Guadalajara");
//        sm.setShippingCountry("USAUS");
//        sm.setShippingZipcode("19139");
//        sm.setShippingEmail("MOISESN1@HOTMAIL.COM");
//        sm.setShippingPhone("647594788");
//        sm.setProducts("NIKE AIR MAX 2015 ΑΝΔΡΙΚΑ αποφε?γων μπλε / λευκ?55.00");
//        sm.setCardBank("test");
//        sm.setXingChanel("SCA");
//        sm.setMaxmindRiskValue("0.1");
//        su.paySfe(sm);
//		try {
//			send_email();
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
//		calendar.add(Calendar.MONTH, -1); // 得到前一个月
//		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.format(calendar.getTime());
//		System.out.println(yestedayDate);
//		       Pattern pattern = Pattern.compile("\\d+");  
//		       Matcher matcher = pattern.matcher("8548535201261512220");  
//		       while (matcher.find()) {  
//		          long a=Long.parseLong(matcher.group(0));  
//		          System.out.println(a);
//		       }
//		refundSynThread rf=new refundSynThread("32131", "41231");
//		rf.start();
//			GooPayMessage msg=new GooPayMessage();
//			GooPayUtil yu=new GooPayUtil();
//			msg.setMerchantMID("2008");
//			msg.setNewcardtype("4");
//			BASE64Encoder baseE=new BASE64Encoder(); 
//			msg.setCardnum(baseE.encode("4062540301082212".getBytes()));
//			msg.setCvv2(baseE.encode("212".getBytes()));
//			msg.setYear(baseE.encode("2016".getBytes()));
//			msg.setMonth(baseE.encode("06".getBytes()));
//			msg.setCardbank("test");
//			msg.setBillNo("2008000005");
//			msg.setAmount("124.00");
//			msg.setCurrency("3");
//			msg.setLanguage("en");
////			msg.setReferer("www.sfepay.com");
//			msg.setReturnURL("www.sfepay.com");
//			MD5 md5=new MD5();
//			String md5Hash=msg.getMerchantMID()+msg.getBillNo() +msg.getCurrency() +msg.getAmount() +msg.getLanguage()+msg.getReturnURL()+"f~kzCfjg";
//			msg.setHASH(md5.getMD5ofStr(md5Hash));
//			msg.setShippingFirstName("hui");
//			msg.setShippingLastName("jia");
//			msg.setShippingEmail("123456@126.com");
//			msg.setShippingPhone("02112345678");
//			msg.setShippingZipcode("123456");
//			msg.setShippingAddress("上海");
//			msg.setShippingCity("shanghai");
//			msg.setShippingSstate("shanghai");
//			msg.setShippingCountry("USA");
//			msg.setProducts("ceshiyige");
//			msg.setFirstname("hui");
//			msg.setLastname("jia");
//			msg.setEmail("123456@126.com");
//			msg.setPhone("02112345678");
//			msg.setZipcode("123456");
//			msg.setAddress("上海");
//			msg.setCity("shanghai");
//			msg.setState("shanghai");
//			msg.setCountry("USA");
//			msg.setIpAddr("127.0.0.1");
//			yu.get(msg);
//			System.out.println(msg.getSucceed());
//
//		CaibaoMessage msg=new CaibaoMessage();
//		System.out.println(msg.getCsid());
//		String[] aaa={"11","22","33","44","55"};
//		int a= (int)(Math.random()*5)+1;
//		System.out.println(a);
//		System.out.println(aaa[a-1]);
//		MessageDigest md5;
//		BASE64Encoder base64en = new BASE64Encoder();
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//			String passwords = base64en.encode(md5.digest("abc111111".getBytes("utf-8")));
//			System.out.println(passwords);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BASE64Decoder base64=new BASE64Decoder();
//		try {
//			String cardnum=new String((base64.decodeBuffer("NDExMTExMTExMTExMTExMQ==")));
//			System.out.println(cardnum);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
//		String nowMouth = formatter.format(currentTime);
//		nowMouth=nowMouth+"-01 00:00:00";
//		System.out.println(nowMouth);
//		MD5 md5 = new MD5();
//		System.out.println(md5.getMD5ofStr("3604421204.97ENhttp://localhost/zencart/index.php?main_page=checkout_payresultnegyPVkv"));
//		String aa="4123456789012345";
//		System.out.println(aa.substring(0, 4));
//		System.out.println(aa.substring(12, aa.length()));
//		String result="aa=11&bb=22&cc=33";
//		String resMsg[]=result.split("&");
//		System.out.println(resMsg.length);
//		double aa=1.02;
//		int a=(int)(aa*100);
//		System.out.println((aa*100));
//		System.out.println(a);
//		String bb="12.01";
//		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
//		String cc = Double.valueOf((decimalFormat.format(Double
//				.valueOf(bb)))) * 100 + "";
//		System.out.println(cc);
//		InternationalMerchantChannels mc=new InternationalMerchantChannels();
//		mc.setBailCharge(1.25);
//		mc.setChannelFee(0.01);
//		Double aa=mc.getBailCharge()*(mc.getChannelFee()+1.0);
//		Double rmbmoney = (double) (Math.round((double) aa * 100) / 100.00);
//		System.out.println(rmbmoney);
//		String aa="12345678";
//		
//		System.out.println(aa.substring(0, 2));
//		System.out.println(aa.substring(2, aa.length()));
//		TemporarySynThread rf=new TemporarySynThread("https://www.xingbill.com/synTradeInfo","11271610311026316979", "1", "");
//		rf.start();
//		String md5src="1102TO11031100ENhttp://www.topnfljerseysstore.com/payment_online/submitOrderQuery.aspWBmFdhLx";
//		MD5 md5=new MD5();
//		md5src = md5.getMD5ofStr(md5src);
//		System.out.println(md5src);
//		System.out.println("1a:2b:3c".split(":")[0]+"");
//		HRPayUtil hr=new HRPayUtil();
//		HRPayMessage hrm=new HRPayMessage();
//		hrm.setMerNo("jm0130010001");
//		hrm.setTransType("sales");
//		hrm.setAmount("80.99");
//		hrm.setCurrencyCode("CNY");
//		hrm.setOrderNo("1000008");
//		hrm.setSiteUrl("www.yourshop.com");
//		hrm.setWebInfo("userAgent");
//		hrm.setLanguage("En");
//		hrm.setCardCountry("US");
//		hrm.setCardState("California");
//		hrm.setCardCity("Fremont");
//		hrm.setCardAddress("100 S San Gabriel Blvd.");
//		hrm.setCardZipCode("91000");
//		hrm.setPayIP("121.35.210.146");
//		hrm.setCardFirstName("hehe");
//		hrm.setCardLastName("hehe");
//		hrm.setCardFullPhone("13800000000");
//		hrm.setGrCountry("US");
//		hrm.setGrState("hehe");
//		hrm.setGrCity("hehe");
//		hrm.setGrAddress("hehe");
//		hrm.setGrZipCode("518000");
//		hrm.setGrEmail("example@example.com");
//		hrm.setGrphoneNumber("13800000000");
//		hrm.setGrFirstName("hehe");
//		hrm.setGrLastName("hehe");
//		hrm.setpName("");
//		hrm.setMd5Key("ROWrDC8P");
//		hrm.setCardNO("4111111111111111");
//		hrm.setExpYear("2018");
//		
//		hrm.setExpMonth("12");
//		hrm.setCvv("212");
//		hr.get(hrm);
		
		
	}
	
	public static void send_email() throws IOException, AddressException, MessagingException{
        String to = "466865609@qq.com";
        String subject = "subject";
        String content = "content";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        Authenticator authenticator = new Email_Authenticator("878701211@qq.com", "");
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress("878701211@qq.com"));
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(subject, "UTF-8");
        mailMessage.setSentDate(new Date());
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
    }

//	public static String buzero(String refundRMBMoney) {
//		String refundRMB = "000000000000";
//		String zero_12 = "000000000000";
//		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
//		if (StringUtils.isNotBlank(refundRMBMoney)
//				&& refundRMBMoney.replace(".", "").matches("\\d+")) {
//			String refundRMBStr = Double.valueOf((decimalFormat.format(Double
//					.valueOf(refundRMBMoney)))) * 100 + "";
//			String refundRMB_0 = zero_12
//					+ refundRMBStr.substring(0, refundRMBStr.indexOf("."));
//			refundRMB = refundRMB_0.substring(refundRMB_0.length() - 12,
//					refundRMB_0.length());
//		}
//		return refundRMB;
//	}
//  public static String getSha256(String strData) {
//		 String output = "";
//	     try {
//	       MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	       byte[] hash = digest.digest(strData.getBytes("UTF-8"));
//	       output = Hex.encodeHexString(hash);
//	       System.out.println(output);
//	      } catch (Exception e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	      }
//	    return output;
//	}
	public static Hashtable getmmValue(HashMap hm,Long tradeId) {
		String maxmindValue;
		String bankName = null;
		String bankCountry = null;
		String bankPhone = null;
		String values="";
		String SECURE_SECRET;
		MaxMindExample exam = new MaxMindExample();
		InternationalBackMaxMind bmm=new InternationalBackMaxMind();
		bmm.setTradeId(tradeId);
		HashMap h = exam.maxMindScore(hm);
		System.out.println(h);
		// 把MaxMind返回的参数打印出来,
		for (Iterator i = h.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			maxmindValue = (String) h.get(key);
			if (key.equals("riskScore")) {
				values = maxmindValue;
				bmm.setRiskScore(maxmindValue);
			}
			if (key.equals("binName")) {
				bankName = maxmindValue;
				bmm.setBinName(maxmindValue);
			}
			if (key.equals("binCountry")) {
				bankCountry = maxmindValue;
				bmm.setBinCountry(maxmindValue);
			}
			if (key.equals("binPhone")) {
				bankPhone = maxmindValue;
				bmm.setBinPhone(maxmindValue);
			}
			if (key.equals("ip_areaCode")) {
				bmm.setIp_areaCode(maxmindValue);
			}
			if (key.equals("ip_postalCode")) {
				bmm.setIp_postalCode(maxmindValue);
			}
			if (key.equals("ip_regionName")) {
				bmm.setIp_regionName(maxmindValue);
			}
			if (key.equals("ip_region")) {
				bmm.setIp_region(maxmindValue);
			}
			if (key.equals("ip_countryName")) {
				bmm.setIp_countryName(maxmindValue);
			}
			if (key.equals("countryCode")) {
				bmm.setCountryCode(maxmindValue);
			}
			if (key.equals("anonymousProxy")) {
				bmm.setAnonymousProxy(maxmindValue);
			}
			if (key.equals("distance")) {
				bmm.setDistance(maxmindValue);
			}
			if (key.equals("proxyScore")) {
				bmm.setProxyScore(maxmindValue);
			}
			if (key.equals("postalMatch")) {
				bmm.setPostalMatch(maxmindValue);
			}
			if (key.equals("custPhoneInBillingLoc")) {
				bmm.setCustPhoneInBillingLoc(maxmindValue);
			}
			if (key.equals("shipCityPostalMatch")) {
				bmm.setShipCityPostalMatch(maxmindValue);
			}
		}
		Hashtable ht = new Hashtable();
		if (values == null) {
			values = "0";
		}
		ht.put("values", values);
		ht.put("bankName", bankName);
		ht.put("bankCountry", bankCountry);
		ht.put("bankPhone", bankPhone);
		
		return ht;
	}
	
}
