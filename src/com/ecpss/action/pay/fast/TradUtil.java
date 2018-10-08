package com.ecpss.action.pay.fast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import vpn.VpnUtil_Moto;

public class TradUtil {
	Logger logger = Logger.getLogger(TradUtil.class.getName());
	public void get(TradeMessage trade){
		HttpPost httpPost = new HttpPost(
				"https://bill.westterrypay.com/payment/payment/direct.xml");
		httpPost.addHeader("referer", "https://www.sfepay.com");

		
	 NameValuePair cardNo =new BasicNameValuePair("cardNo",trade.getCardNo());
	 NameValuePair cvv  = new BasicNameValuePair("cvv",trade.getCvv());
	 NameValuePair expirationYear  = new BasicNameValuePair("expirationYear",trade.getExpirationYear());
	 NameValuePair expirationMonth  = new BasicNameValuePair("expirationMonth",trade.getExpirationMonth());
	 NameValuePair payNumber  = new BasicNameValuePair("payNumber",trade.getPayNumber());
	 NameValuePair merNo  = new BasicNameValuePair("merNo",trade.getMerNo());
	 NameValuePair shopName  = new BasicNameValuePair("shopName",trade.getShopName());
	 NameValuePair orderNo  = new BasicNameValuePair("orderNo",trade.getOrderNo());
	 NameValuePair amount  = new BasicNameValuePair("amount",trade.getAmount());
	 NameValuePair currency  = new BasicNameValuePair("currency",trade.getCurrency());
	 NameValuePair goodsName  = new BasicNameValuePair("goodsName",trade.getGoodsName());
	 NameValuePair goodsPrice  = new BasicNameValuePair("goodsPrice",trade.getGoodsPrice());
	 NameValuePair goodsNumber  = new BasicNameValuePair("goodsNumber",trade.getGoodsNumber());
	 NameValuePair billFirstName  = new BasicNameValuePair("billFirstName",trade.getBillFirstName());
	 NameValuePair billLastName  = new BasicNameValuePair("billLastName",trade.getBillLastName());
	 NameValuePair billAddress  = new BasicNameValuePair("billAddress",trade.getBillAddress());
	 NameValuePair billCity  = new BasicNameValuePair("billCity",trade.getBillCity());
	 NameValuePair billState  = new BasicNameValuePair("billState",trade.getBillState());
	 NameValuePair billCountry  = new BasicNameValuePair("billCountry",trade.getBillCountry());
	 NameValuePair billZip  = new BasicNameValuePair("billZip",trade.getBillZip());
	 NameValuePair email  = new BasicNameValuePair("email",trade.getEmail());
	 NameValuePair phone  = new BasicNameValuePair("phone",trade.getPhone());
	 NameValuePair shipFirstName  = new BasicNameValuePair("shipFirstName",trade.getShipFirstName());
	 NameValuePair shipLastName  = new BasicNameValuePair("shipLastName",trade.getShipLastName());
	 NameValuePair shipAddress  = new BasicNameValuePair("shipAddress",trade.getShipAddress());
	 NameValuePair shipCity  = new BasicNameValuePair("shipCity",trade.getShipCity());
	 NameValuePair shipState  = new BasicNameValuePair("shipState",trade.getShipState());
	 NameValuePair shipCountry  = new BasicNameValuePair("shipCountry",trade.getShipCountry());
	 NameValuePair shipZip  = new BasicNameValuePair("shipZip",trade.getShipZip());
	 NameValuePair returnURL  = new BasicNameValuePair("returnURL",trade.getReturnURL());
	 NameValuePair language  = new BasicNameValuePair("language",trade.getLanguage());
	 NameValuePair remark  = new BasicNameValuePair("remark",trade.getRemark());
	 NameValuePair md5Info  = new BasicNameValuePair("md5Info",trade.getMd5Info());
	 NameValuePair payIp  = new BasicNameValuePair("payIp",trade.getPayIp());
	 NameValuePair acceptLanguage  = new BasicNameValuePair("acceptLanguage",trade.getAcceptLanguage());
	 NameValuePair userAgent  = new BasicNameValuePair("userAgent",trade.getUserAgent());
	 NameValuePair payMethod  = new BasicNameValuePair("payMethod",trade.getPayMethod());
		NameValuePair submit = new BasicNameValuePair("submit", "Continue");
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(cardNo);
		nvps1.add(cvv);
		nvps1.add(expirationYear);
		nvps1.add(expirationMonth);
		nvps1.add(payNumber);
		nvps1.add(merNo);
		nvps1.add(shopName);
		nvps1.add(orderNo);
		nvps1.add(amount);
		nvps1.add(currency);
		nvps1.add(goodsName);
		nvps1.add(goodsPrice);
		nvps1.add(goodsNumber);
		nvps1.add(billFirstName);
		nvps1.add(billLastName);
		nvps1.add(billAddress);
		nvps1.add(billCity);
		nvps1.add(billState);
		nvps1.add(billCountry);
		nvps1.add(billZip);
		nvps1.add(email);
		nvps1.add(phone);
		nvps1.add(shipFirstName);
		nvps1.add(shipLastName);
		nvps1.add(shipAddress);
		nvps1.add(shipCity);
		nvps1.add(shipState);
		nvps1.add(shipCountry);
		nvps1.add(shipZip);
		nvps1.add(returnURL);
		nvps1.add(language);
		nvps1.add(remark);
		nvps1.add(md5Info);
		nvps1.add(payIp);
		nvps1.add(acceptLanguage);
		nvps1.add(userAgent);
		nvps1.add(payMethod);
		List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
		nvps2.add(payNumber);
		nvps2.add(merNo);
		nvps2.add(shopName);
		nvps2.add(orderNo);
		nvps2.add(amount);
		nvps2.add(currency);
		nvps2.add(goodsName);
		nvps2.add(goodsPrice);
		nvps2.add(goodsNumber);
		nvps2.add(billFirstName);
		nvps2.add(billLastName);
		nvps2.add(billAddress);
		nvps2.add(billCity);
		nvps2.add(billState);
		nvps2.add(billCountry);
		nvps2.add(billZip);
		nvps2.add(email);
		nvps2.add(phone);
		nvps2.add(shipFirstName);
		nvps2.add(shipLastName);
		nvps2.add(shipAddress);
		nvps2.add(shipCity);
		nvps2.add(shipState);
		nvps2.add(shipCountry);
		nvps2.add(shipZip);
		nvps2.add(returnURL);
		nvps2.add(language);
		nvps2.add(remark);
		nvps2.add(md5Info);
		nvps2.add(payIp);
		nvps2.add(acceptLanguage);
		nvps2.add(userAgent);
		nvps2.add(payMethod);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps1, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpClient httpclient = new DefaultHttpClient();
		String result = "";
		try {
			logger.info("v提交数据:"+nvps2.toString());
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = null;
			entity = response.getEntity();
			result=EntityUtils.toString(entity, "UTF-8");
			System.out.println("返回数据："+result+"===================="+nvps1.toString()+"======");
			try {
				Document parseText = DocumentHelper.parseText(result);
				
				Element rootElement = parseText.getRootElement();
				for (Iterator i = rootElement.elementIterator(); i.hasNext();) {
					Element next = (Element) i.next();
					if (next.getName().equals("amount")) {
						trade.setAmountback(next.getText().trim());
					}
					if (next.getName().equals("bankInfo")) {
						trade.setBankInfo(next.getText().trim());
					}					
					if (next.getName().equals("currency")) {
						trade.setCurrencyback(next.getText().trim());
					}
					if (next.getName().equals("errorCode")) {
						trade.setErrorCode(next.getText().trim());
					}
					if (next.getName().equals("errorMsg")) {
						trade.setErrorMsg(next.getText().trim());
					}
					if (next.getName().equals("md5Info")) {
						trade.setMd5Infoback(next.getText().trim());
					}
					if (next.getName().equals("orderNo")) {
						trade.setOrderNoBack(next.getText().trim());
					}
					if (next.getName().equals("succeed")) {
						trade.setSucceed( next.getText().trim());
					}
					if (next.getName().equals("tradeNo")) {
						trade.setTradeNo(next.getText().trim());
					}							
					
				}
				
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			Header[] headers = response.getHeaders("Location");
//			result=response.
//			for (Header header : headers) {
//				if (StringUtils.isNotBlank(header.getValue())) {
//					result = header.getValue();
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("通道请求超时，重新发送。。。");
			try{
				HttpResponse response2 = httpclient.execute(httpPost);
				HttpEntity entity = null;
				entity = response2.getEntity();
				result=EntityUtils.toString(entity, "UTF-8");
				logger.info("返回报文："+result);
				try {
					Document parseText = DocumentHelper.parseText(result);
					
					Element rootElement = parseText.getRootElement();
					for (Iterator i = rootElement.elementIterator(); i.hasNext();) {
						Element next = (Element) i.next();
						if (next.getName().equals("amount")) {
							trade.setAmountback(next.getText().trim());
						}
						if (next.getName().equals("bankInfo")) {
							trade.setBankInfo(next.getText().trim());
						}					
						if (next.getName().equals("currency")) {
							trade.setCurrencyback(next.getText().trim());
						}
						if (next.getName().equals("errorCode")) {
							trade.setErrorCode(next.getText().trim());
						}
						if (next.getName().equals("errorMsg")) {
							trade.setErrorMsg(next.getText().trim());
						}
						if (next.getName().equals("md5Info")) {
							trade.setMd5Infoback(next.getText().trim());
						}
						if (next.getName().equals("orderNo")) {
							trade.setOrderNoBack(next.getText().trim());
						}
						if (next.getName().equals("succeed")) {
							trade.setSucceed( next.getText().trim());
						}
						if (next.getName().equals("tradeNo")) {
							trade.setTradeNo(next.getText().trim());
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
			if(StringUtils.isEmpty(trade.getSucceed())){
				trade.setSucceed("0");
				trade.setErrorCode("sfe01");
			}
		}
	}
	
}
