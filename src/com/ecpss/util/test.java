package com.ecpss.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import com.ecpss.model.shop.InternationalMerchant;

public class test {
	public static void main(String[] args) {
		aaaa();
		
	}
	
	public static String aaaa(){
//		EmailInfo e =new EmailInfo();
//		String b = e.getPaymentResultEmail("89610614@qq.com", 100d, "1", "www.e.com", new Date(), "www.ecpss.com",
//				"E238473", "15942454545",merchant);
		CCSendMail.setSendMail("89610614@qq.com", "test01", "sfepay@sfepay.com");
		return "";
	}
	
	
	
	
}
