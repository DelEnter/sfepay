/*
 * Created on 2006-2-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ecpss.icbc.SSL;

/**
 * @author xiezhichao
 * 
 * this class is used to send and receive message through SSL
 */
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class SSLSendAndRec {

	/**
	 * 
	 */
	public SSLSendAndRec() {
		super();
	}

	public static void main(String[] args) throws Exception {
		Protocol authhttps = new Protocol("https",
		// AuthSSLProtocolSocketFactory参数含义：证书库，证书库，本地端口（建立https连接时使用的本地端口，需要空闲端口）
			new AuthSSLProtocolSocketFactory(
	
			new URL("file:///C:/Sun/jwsdp-2.0/xws-security/bin/icbc18.jks"), "12345678",
			
			new URL("file:///C:/Sun/jwsdp-2.0/xws-security/bin/icbc18.jks"), "12345678"), 8443);

		HttpClient client = new HttpClient();
		// 对方ip或域名，端口(一般是443)，protocol对象
		client.getHostConfiguration().setHost("corporbank.icbc.com.cn", 443, authhttps);
		//client.getHostConfiguration().setHost("corporbank3.dccnet.com.cn", 443, authhttps);
		// client.getHostConfiguration().setHost("83.252.30.98",8890);
		/* 只能使用相对路径 */

		PostMethod httpget = new PostMethod("/servlet/ICBCINBSEBusinessServlet");
		httpget.setParameter("APIName", "EAPI");
		httpget.setParameter("APIVersion", "001.001.002.001");
		// String sengmsg = URLEncoder.encode("<?xml version=\"1.0\"
		// encoding=\"GBK\" standalone=\"no\"
		// ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
		String reqdata="<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?>" +
				"<ICBCAPI>" +
					"<in>" +
						"<orderNum>005</orderNum>" +
						"<tranDate>20110302111753</tranDate>" +
						"<ShopCode>2102EC23720701</ShopCode>" +
						"<ShopAccount>2102102019300750985</ShopAccount>" +
					"</in>" +
				"</ICBCAPI>";
		httpget
				.setParameter(
						"MerReqData",reqdata);
		client.executeMethod(httpget);
		
		String bbb=URLDecoder.decode(httpget.getResponseBodyAsString(),"GBK");
		System.out.println(bbb);
		
		System.out.println("server responding body :"
				+ httpget.getResponseBodyAsString());

		System.out.println("server responding code :"
				+ httpget.getStatusLine().toString());
	}
}
