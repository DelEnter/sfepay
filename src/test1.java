import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import net.sf.json.JSONObject;
import oracle.net.aso.n;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.taglibs.standard.tag.common.core.SetSupport;

import com.ecpss.action.TemporarySynThread;
import com.ecpss.util.AES;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;
import com.ecpss.util.StringUtil;
import com.ecpss.util.YoungPayAESUtil;

import sun.misc.BASE64Encoder;
import vpn.GooPayMessage;
import vpn.GooPayUtil;
import vpn.HJPayMessage;
import vpn.HJPayUtil;
import vpn.HJWPayMessage;
import vpn.HJWPayUtil;
import vpn.HarbinPayMessage;
import vpn.HarbinPayUtil;
import vpn.SfeMessage;
import vpn.SfeUtil;
import vpn.YouPayMessage;
import vpn.YouPayUtil;
import vpn.YoungPayMessage;
import vpn.YoungPayUtil;
import vpn.SfeUtil.UTF8PostMethod;


public class test1 {
	public static void main(String[] args) {
	
		HJPayUtil HJ=new HJPayUtil();
		HJPayMessage hm=new HJPayMessage();
		hm.setAcctNo("huajinrong");
		hm.setAgent_AcctNo("huajinrong3");
		hm.setOrderID("10011581618245514856");
		hm.setCurrCode("156");
		hm.setAmount("50903");
		hm.setIpAddress("91.56.12.152");
		hm.setCardType("V");
		hm.setCardPAN("5486225375055725");
		hm.setPname("1 x Nike Detroit Lions 81 Calvin Johnson Black Men's Stitched NFL Elite Camo Fashion Jersey");
		hm.setCname("jiahui");
		hm.setExpDate("1806");
		hm.setCvv2("212");
		hm.setIssCountry("US");
		hm.setBaddress("CEDARCREST");
		hm.setPostCode("200120");
		hm.setIversion("V5.0");
		hm.setTelephone("9785529884");
		hm.setRetURL("www.sfepay.com");
		hm.setEmail("GAMS1950@YAHOO.COM");
		String hash = null;
		String jiamiqian="";
		try {
		MessageDigest getMd5=MessageDigest.getInstance("MD5");
		BASE64Encoder baseE=new BASE64Encoder(); 
		
		
			hash = baseE.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		jiamiqian="dsfhue2568415sfh"+ hm.getAcctNo() + hm.getOrderID() + hm.getAmount() + hm.getCurrCode();
		MessageDigest getMd5;
		BASE64Encoder base64E = new BASE64Encoder();
		String value = null;
		try {
			getMd5 = MessageDigest.getInstance("MD5");
			System.out.println(jiamiqian+"的base64md5====="+getMd5.digest(jiamiqian.getBytes("UTF-8")));
			value =  base64E.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("加密前："+jiamiqian);
		System.out.println("加密后："+value);
		hm.setHashValue(value);
		hm.setTxnID("201508160007222832");
		hm.setTxnType("03");
		HJ.get(hm);
		
		
//		YouPayUtil yu=new YouPayUtil();
//		YouPayMessage ym=new YouPayMessage();
//		ym.setMerNo("2578");
//		ym.setBillNo("100112345678");
//		ym.setAmount("50.1");
//		ym.setCurrency("1");
//		ym.setLanguage("2");
//		ym.setReturnURL("www.sfepay.com");
//		MD5 md5=new MD5();
//		String MD5info=md5.getMD5ofStr(ym.getMerNo()+ym.getBillNo()+ym.getCurrency()+ym.getAmount()+ym.getLanguage()+ym.getReturnURL()+"{P}V{Fpn");
//		ym.setMD5info(MD5info);
//		ym.setIpAddr("127.0.0.1");
//		ym.setRemark("sfepay");
//		ym.setTradeUrl("www.sfepay.com");
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
//		ym.setTradeDate(sdf.format(new Date()));
//		BASE64Encoder base64E = new BASE64Encoder();
//		ym.setCardNum(base64E.encode("4111111111111111".getBytes()));
//		ym.setCvv2(base64E.encode("212".getBytes()));
//		ym.setMonth(base64E.encode("08".getBytes()));
//		ym.setYear(base64E.encode("18".getBytes()));
//		ym.setCardbank("test");
//		ym.setCardType("4");
//		ym.setFirstname("jia");
//		ym.setLastname("hui");
//		ym.setEmail("12345@126.com");
//		ym.setCountry("US");
//		ym.setState("sh");
//		ym.setCity("shanghai");
//		ym.setAddress("shanghai");
//		ym.setPhone("02112345678");
//		ym.setZipcode("200120");
//		ym.setShippingFirstName("jia");
//		ym.setShippingLastName("hui");
//		ym.setShippingEmail("12345@126.com");
//		ym.setShippingPhone("02112345678");
//		ym.setShippingZipcode("200120");
//		ym.setShippingAddress("shanghai");
//		ym.setShippingCity("shanghai");
//		ym.setShippingSstate("shangahi");
//		ym.setShippingCountry("US");
//		ym.setProducts("nike");
//		ym.setProductCategory("xiezi");
//		ym.setAddress2("shanghai");
//		ym.setShippingAddress2("shanghai");
//		yu.paySfe(ym);
//		String ids="123,";
//		String[] id=ids.split(",");
//		System.out.println(id.length);
//		String radStr = "AaBbCcDdEeFfGgHhiJjKkLMmNnOoPpQqRrSsTtUuVvWwXxYyZz~!^()_{}[]";
//		StringBuffer generateRandStr = new StringBuffer();
//		Random rand = new Random();
//		int length = 8;
//		for (int i = 0; i < length; i++) {
//			int randNum = rand.nextInt(60);
//			generateRandStr.append(radStr.substring(randNum, randNum + 1));
//		}
//		System.out.println(generateRandStr);
//		String md5src="4001125411823.582http://fotballdrakterbutikk.com/index.php?route=payment/sfepay/callbackY!S[Vby[";
//		MD5 md5=new MD5();
//		String MD5info ="29780" +"2"
//				+ "25.80" + "88" + "jZ]DwO)m";
//		System.out.println(md5.getMD5ofStr(MD5info));
		//
//		System.out.println(md5.getMD5ofStr(md5src));
//		System.out.println(AES.setCarNo("4695965052237761"));YihubaiyingTradeCo.,Ltd.  messcabuy
//		TemporarySynThread ts=new TemporarySynThread("http://www.maxmaillots.net/index.php?main_page=checkout_payresult","29780", "88","测试");adoredvintage
//		TemporarySynThread ts=new TemporarySynThread("http://www.xingbill.com/synTradeInfo","115617189301856485", "1","adoredvintage");
//		TemporarySynThread ts=new TemporarySynThread("http://www.xingbill.com/synTradeInfo","1145171816312333837", "1","SHXJIETRADINGCO.,LTD.");
		TemporarySynThread ts=new TemporarySynThread("http://www.xingbill.com/synTradeInfo","112017119161529473", "0","1093high risk!");
//////		TemporarySynThread ts=new TemporarySynThread("https://www.xingbill.com/synTradeInfo","1057151278263460584", "1","zhongxin estore"); //M
//		ts.start();
		
//		TemporarySynThread ts=new TemporarySynThread("http://www.win4mall.com/OrderAutomatic","414416121312582484325","0", "1093high risk!Failed");
		ts.start();
////		
//		MessageDigest md5;
//		BASE64Encoder base64en = new BASE64Encoder();
//		try {
//			md5 = MessageDigest.getInstance("MD5");
////		
//		String passwords = base64en.encode(md5.digest("egn123321".getBytes("utf-8")));
//		System.out.println(passwords);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HJWPayUtil hjw=new HJWPayUtil();
//		HJWPayMessage hwm=new HJWPayMessage();
//		hwm.setMerchantId("188001000261324");
//		hwm.setMd5key("CYNCEPKR29VCQ4GX48TU8X7NURHYMKBX");
////		hwm.setAmount("10.01");
////		hwm.setCurrency("100");
////		hwm.setBillNo("10000008");
////		hwm.setCardAsn("4111111111111111");
////		hwm.setValidity("1808");
////		hwm.setCvv("212");
////		hwm.setCardType("1");
////		hwm.setSrcUrl("www.sfepay.com");
////		hwm.setFirstName("hehe");
////		hwm.setLastName("haha");
////		hwm.setAddress("meiguo");
////		hwm.setRemark("shoesbag");
////		hwm.setEmail("hehe@126.com");
////		hwm.setTelephone("021-12345678");
////		String strData=hwm.getMerchantId() +hwm.getBillNo()+hwm.getCardAsn()+hwm.getValidity()+hwm.getCvv()+ hwm.getAmount() + hwm.getCurrency()  + hwm.getRemark() +"CYNCEPKR29VCQ4GX48TU8X7NURHYMKBX";
////		MD5 md5=new MD5();
////		hwm.setMd5Data(md5.getMD5ofStr(strData));
////		hjw.get(hwm);
//		
//		GetBatchNo ut = new GetBatchNo();
//		hwm.setBillNo(ut.getOrderinfo("1102"));
//		hwm.setJcTradeId("100000011714");
//		hwm.setAmount("12.12");
//		hwm.setTradType("2");
//		hjw.get(hwm);
//		GooPayMessage msg=new GooPayMessage();
//		GooPayUtil yu=new GooPayUtil();
//		msg.setMerchantMID("2110");
//		msg.setNewcardtype("4");
//		msg.setTrans_Type("refund");
//		BASE64Encoder baseE=new BASE64Encoder(); 
//		msg.setCardnum(baseE.encode("4337869012381012".getBytes()));
//		msg.setCvv2(baseE.encode("211".getBytes()));
//		msg.setYear(baseE.encode(("2018").getBytes()));
//		msg.setMonth(baseE.encode("08".getBytes()));
//		msg.setCardbank("test");
//		msg.setBillNo("211012345678");
//		msg.setAmount("10.2");
//		msg.setCurrency("3");
//		msg.setLanguage("en");
//		msg.setWebsite("www.sfepay.com");
//		msg.setReturnURL("www.sfepay.com");
//		MD5 md5=new MD5();
//		String md5Hash=msg.getMerchantMID()+msg.getBillNo() +msg.getCurrency() +msg.getAmount() +msg.getLanguage()+msg.getReturnURL()+"Yu^HJXBd";
//		msg.setHASH(md5.getMD5ofStr(md5Hash));
//		msg.setShippingFirstName("ceshi");
//		msg.setShippingLastName("ceshi");
//		msg.setShippingEmail("ceshi@126.com");
//		msg.setShippingPhone("02112345678");
//		msg.setShippingZipcode("123456");
//		msg.setShippingAddress("shanghai");
//		msg.setShippingCity("shanghai");
//		msg.setShippingSstate("shanghai");
//		msg.setShippingCountry("US");
//		msg.setProducts("shoes");
//		msg.setFirstname("ceshi");
//		msg.setLastname("ceshi");
//		msg.setEmail("ceshi@126.com");
//		msg.setPhone("02112345678");
//		msg.setZipcode("123456");
//		msg.setAddress("shanghai");
//		msg.setCity("shanghai");
//		msg.setState("shanghai");
//		msg.setCountry("US");
//		msg.setIpAddr("127.0.0.1");
//		yu.get(msg);
//		System.out.println(msg.getGrn());
//		System.out.println(AES.setCarNo("415969"));
//		Hashtable a=new Hashtable();
//		a.put("fs", "1231");
////		SfeUtil su = new SfeUtil();
////        SfeMessage sm = new SfeMessage();
////        if(card.getMaxmindValue()==null){
////        	card.setMaxmindValue(0.0);
////        }
////        sm.setMaxmindRiskValue(card.getMaxmindValue().toString());
////        sm.setTradetime(new Date());
////        sm.setCardBank("2113");
////        sm.setXingChanel("fdsda");
//        JSONObject jsMaxMind = JSONObject.fromObject(a);
//        System.out.println(jsMaxMind);
//        Map<String, Object> map2 = (Map) jsMaxMind;
//        System.out.println(map2.get("fs"));
//        sm.setMaxMindInfo(jsMaxMind.toString());
//        su.paySfe(sm);
		
//        msg.setResponseCode(Integer.valueOf(resSucceed[1]));
		
		
//		HarbinPayMessage hb=new HarbinPayMessage();
//		HarbinPayUtil hbp=new HarbinPayUtil();
//		hb.setInputCharset("1");
//		hb.setPickupUrl("www.sfepay.com");
//		hb.setReceiveUrl("www.sfepay.com");
//		hb.setVersion("v1.0");
//		hb.setSignType("0");
//		hb.setMerchantId("010704515311001");
//		hb.setOrderNo("10000117");
//		hb.setOrderAmount("1.1");
//		hb.setOrderCurrency("156");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//		hb.setOrderDatetime(formatter.format(new Date()));
//		hb.setPayType("13");
//		hb.setIssuerId("M");
//		hb.setFirstName("sfepay");
//		hb.setLastName("sfepay");
//		hb.setCardCvv2("111");
//		hb.setTradeNature("GOODS");
//		hb.setCardNumber("5186006600001012");
//		hb.setExpiryMonth("25");
//		hb.setExpiryYear("12");
//		hb.setMd5key("1BoHbM2YrhsOeui0VSQvJg==");
//		try {
//			hbp.getHarMessage(hb,"13");
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (KeyManagementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//	HarbinPayMessage hb=new HarbinPayMessage();
//	HarbinPayUtil hbp=new HarbinPayUtil();
//	hb.setVersion("v1.3");
//	hb.setSignType("0");
//	hb.setMerchantId("010704515311001");
//	hb.setOrderNo("10000113");
//	hb.setRefundAmount("100.1");
//	hb.setOrderDatetime("20160802093921");
//	hb.setOriginalOrderNo("10000113");
//	hb.setMd5key("1BoHbM2YrhsOeui0VSQvJg==");
//	try {
//		hbp.getHarMessage(hb,"3");
//	} catch (HttpException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (KeyManagementException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (NoSuchAlgorithmException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		//查询
//		HarbinPayMessage hb=new HarbinPayMessage();
//		HarbinPayUtil hbp=new HarbinPayUtil();
//		hb.setVersion("v1.5");
//		hb.setSignType("0");
//		hb.setMerchantId("010704515311001");
//		hb.setOrderNo("10000029");
//		hb.setOrderDatetime("20160701153921");
//		hb.setQueryDatetime("20160725101942");
//		hb.setMd5key("1BoHbM2YrhsOeui0VSQvJg==");
//		try {
//			hbp.getHarMessage(hb,"1");
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (KeyManagementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		YoungPayMessage yp=new YoungPayMessage();
//		YoungPayUtil ypu=new YoungPayUtil();
//		yp.setMerId("Y44933");
//		yp.setTransType("2P_SALES");
//		yp.setB2mOrder("1000017");
//		yp.setCardNo("4337869012381012");
//		yp.setCardType("VISA");
//		yp.setExpireDate("1711");
//		yp.setCvcCode("123");
//		yp.setB2mBank("test");
//		yp.setB2mFee("10");
//		yp.setB2mCur("CNY");
//		yp.setB2mWebsite("sfepay.com");
//		yp.setB2mReturnUrl("https://www.sfepay.com");
//		yp.setB2mNotifyUrl("https://www.sfepay.com");
//		yp.setIp("42.196.43.6");
//		yp.setB2mCargoCountry("USA");
//		yp.setB2mHolderCountry("USA");
//		yp.setB2mCargoName("Zhitao dsadadadasd");
//		yp.setB2mPhone("30389344");
//		yp.setB2mCargoEmail("eddy_cheng@163.com");
//		yp.setB2mCargoState("Virginia");
//		yp.setB2mCargoCity("Arlington");
////		yp.setB2mCargoAddr("YvelinesversaillesFRAFR5 allée des bievres appt 554");
//		yp.setB2mCargoZip("22202");
//		yp.setB2mHolderName("Zhitao  dsadadadasd");
//		yp.setB2mHolderEmail("eddy_cheng@163.com");
//		yp.setB2mHolderState("Virginia");
//		yp.setB2mHolderCity("Arlington");
//		yp.setB2mHolderAddr("YvelinesversaillesFRAFR5 allée des bievres appt 554");
//		yp.setB2mHolderZip("22202");
//		yp.setSignKey("zW2j9tTmaxYu8tbLjIuXfacmybpuBOZX");
//		try {
//			ypu.getHarMessage(yp, "0");
//			System.out.println(yp.getRes_statusCode());
//			System.out.println(yp.getRes_errorCode());
//		} catch (KeyManagementException | NoSuchAlgorithmException
//				| IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String ip="111";
//		System.out.println(ip.split(",")[0]);
				

    }  

}
