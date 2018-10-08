import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.httpclient.HttpException;

import sun.misc.BASE64Decoder;
import vpn.YoungPayMessage;
import vpn.YoungPayUtil;


public class test4 {
	public static void main(String[] args) {
		//支付
	/*	HarbinPayMessage hb=new HarbinPayMessage();
		HarbinPayUtil hbp=new HarbinPayUtil();
		hb.setInputCharset("1");
		hb.setPickupUrl("www.sfepay.com");
		hb.setReceiveUrl("www.sfepay.com");
		hb.setVersion("v1.0");
		hb.setSignType("0");
		hb.setMerchantId("010704515311001");
		hb.setOrderNo("10000029");
		hb.setOrderAmount("100.1");
		hb.setOrderCurrency("156");
		hb.setOrderDatetime("20160701153921");
		hb.setPayType("13");
		hb.setIssuerId("M");
//		hb.setFirstName("sfepay");
//		hb.setLastName("sfepay");
		hb.setCardCvv2("111");
		hb.setCardNumber("5186006600001012");
		hb.setExpiryMonth("25");
		hb.setExpiryYear("12");
		hb.setMd5key("1BoHbM2YrhsOeui0VSQvJg==");
		try {
			hbp.getHarMessage(hb,"13");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		//退款
//		HarbinPayMessage hb=new HarbinPayMessage();
//		HarbinPayUtil hbp=new HarbinPayUtil();
//		hb.setVersion("v1.3");
//		hb.setSignType("0");
//		hb.setMerchantId("010704515311001");
//		hb.setOrderNo("100000005");
//		hb.setRefundAmount("100.1");
//		hb.setOrderDatetime("20160701153921");
//		hb.setOriginalOrderNo("10000023");
//		hb.setMd5key("1BoHbM2YrhsOeui0VSQvJg==");
//		try {
//			hbp.getHarMessage(hb,"3");
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
		//查询
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
//	
		System.out.println(new Date());
		YoungPayMessage yp=new YoungPayMessage();
		YoungPayUtil ypu=new YoungPayUtil();
		yp.setMerId("Y44933");
		yp.setTransType("2P_SALES");
		yp.setB2mOrder("1000017");
		yp.setCardNo("5337869012381012");//5165010031193575
		yp.setCardType("MASTER");
		yp.setExpireDate("1711");
		yp.setCvcCode("123");
		yp.setB2mBank("test");
		yp.setB2mFee("10");
		yp.setB2mCur("CNY");
		yp.setB2mWebsite("sfepay.com");
		yp.setB2mReturnUrl("https://www.sfepay.com");
		yp.setB2mNotifyUrl("https://www.sfepay.com");
		yp.setIp("42.196.43.6");
		yp.setB2mCargoCountry("USA");
		yp.setB2mHolderCountry("USA");
		yp.setB2mCargoName("Zhitao dsadadadasd");
		yp.setB2mPhone("30389344");
		yp.setB2mCargoEmail("eddy_cheng@163.com");
		yp.setB2mCargoState("Virginia");
		yp.setB2mCargoCity("Arlington");
		yp.setB2mCargoAddr("YvelinesversaillesFRAFR5 allée des bievres appt 554");
		yp.setB2mCargoZip("22202");
		yp.setB2mHolderName("Zhitao  dsadadadasd");
		yp.setB2mHolderEmail("eddy_cheng@163.com");
		yp.setB2mHolderState("Virginia");
		yp.setB2mHolderCity("Arlington");
		yp.setB2mHolderAddr("YvelinesversaillesFRAFR5 allée des bievres appt 554");
		yp.setB2mHolderZip("22202");
		yp.setSignKey("zW2j9tTmaxYu8tbLjIuXfacmybpuBOZX");
		try {
			ypu.getHarMessage(yp, "0");
			System.out.println(yp.getRes_statusCode());
			System.out.println(new Date());
			System.out.println(yp.getRes_errorCode());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
