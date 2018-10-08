package vpn;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ecpss.util.MD5;
import com.ecpss.util.ThreeDES;
import com.ecpss.util.YoungPayAESUtil;

public class YoungPayMessage {
			// 接口参数
	private String signKey;
	private String transType;
	private String ip;
	private String cardType;
	private String cardNo;
	private String expireDate;
	private String cvcCode;
	private String merId;
	private String b2mOrder;
	private String b2mBank;
	private String b2mWebsite;
	private String b2mFee;
	private String b2mCur;
	private String b2mReturnUrl;
	private String b2mNotifyUrl;
	private String b2mPhone;
	private String b2mCargoName;
	private String b2mCargoEmail;
	private String b2mCargoCountry;
	private String b2mCargoState;
	private String b2mCargoCity;
	private String b2mCargoAddr;
	private String b2mCargoZip;
	private String b2mHolderName;
	private String b2mHolderEmail;
	private String b2mHolderCountry;
	private String b2mHolderState;
	private String b2mHolderCity;
	private String b2mHolderAddr;
	private String b2mHolderZip;
	private String b2mSignure;
	private String b2mRefundAmount;
	
	//返回参数
	private String res_statusCode;
	private String res_errorCode;
		    


	public static String MD5Encode(String data) throws SecurityException {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			resultString = bintoascii(md.digest(data.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("MD5运算失败");
		}
		return resultString;
	}

	private static String bintoascii(byte[] bySourceByte) {
		int len, i;
		byte tb;
		char high, tmp, low;
		String result = new String();
		len = bySourceByte.length;
		for (i = 0; i < len; i++) {
			tb = bySourceByte[i];
			tmp = (char) ((tb >>> 4) & 0x000f);
			if (tmp >= 10)
				high = (char) ('a' + tmp - 10);
			else
				high = (char) ('0' + tmp);
			result += high;
			tmp = (char) (tb & 0x000f);
			if (tmp >= 10)
				low = (char) ('a' + tmp - 10);
			else
				low = (char) ('0' + tmp);
			result += low;
		}

		return result;
	}
	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		MD5 md5= new MD5();
		return YoungPayAESUtil.AES_Encrypt(md5.getMD5ofStr(getSignKey()).toUpperCase(),cardNo);
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpireDate() {
		MD5 md5= new MD5();
		return YoungPayAESUtil.AES_Encrypt(md5.getMD5ofStr(getSignKey()).toUpperCase(),expireDate);
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getCvcCode() {
		MD5 md5= new MD5();
		return YoungPayAESUtil.AES_Encrypt(md5.getMD5ofStr(getSignKey()).toUpperCase(),cvcCode);
	}

	public void setCvcCode(String cvcCode) {
		this.cvcCode = cvcCode;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getB2mOrder() {
		return b2mOrder;
	}

	public void setB2mOrder(String b2mOrder) {
		this.b2mOrder = b2mOrder;
	}

	public String getB2mBank() {
		return b2mBank;
	}

	public void setB2mBank(String b2mBank) {
		this.b2mBank = b2mBank;
	}

	public String getB2mWebsite() {
		return b2mWebsite;
	}

	public void setB2mWebsite(String b2mWebsite) {
		this.b2mWebsite = b2mWebsite;
	}

	public String getB2mFee() {
		return b2mFee;
	}

	public void setB2mFee(String b2mFee) {
		this.b2mFee = b2mFee;
	}

	public String getB2mCur() {
		return b2mCur;
	}

	public void setB2mCur(String b2mCur) {
		this.b2mCur = b2mCur;
	}

	public String getB2mReturnUrl() {
		return b2mReturnUrl;
	}

	public void setB2mReturnUrl(String b2mReturnUrl) {
		this.b2mReturnUrl = b2mReturnUrl;
	}

	public String getB2mNotifyUrl() {
		return b2mNotifyUrl;
	}

	public void setB2mNotifyUrl(String b2mNotifyUrl) {
		this.b2mNotifyUrl = b2mNotifyUrl;
	}

	public String getB2mPhone() {
		return b2mPhone;
	}

	public void setB2mPhone(String b2mPhone) {
		this.b2mPhone = b2mPhone;
	}

	public String getB2mCargoName() {
		return b2mCargoName;
	}

	public void setB2mCargoName(String b2mCargoName) {
		this.b2mCargoName = b2mCargoName;
	}

	public String getB2mCargoEmail() {
		return b2mCargoEmail;
	}

	public void setB2mCargoEmail(String b2mCargoEmail) {
		this.b2mCargoEmail = b2mCargoEmail;
	}

	public String getB2mCargoCountry() {
		return b2mCargoCountry;
	}

	public void setB2mCargoCountry(String b2mCargoCountry) {
		this.b2mCargoCountry = b2mCargoCountry;
	}

	public String getB2mCargoState() {
		return b2mCargoState;
	}

	public void setB2mCargoState(String b2mCargoState) {
		this.b2mCargoState = b2mCargoState;
	}

	public String getB2mCargoCity() {
		return b2mCargoCity;
	}

	public void setB2mCargoCity(String b2mCargoCity) {
		this.b2mCargoCity = b2mCargoCity;
	}

	public String getB2mCargoAddr() {
		return b2mCargoAddr;
	}

	public void setB2mCargoAddr(String b2mCargoAddr) {
		this.b2mCargoAddr = b2mCargoAddr;
	}

	public String getB2mCargoZip() {
		return b2mCargoZip;
	}

	public void setB2mCargoZip(String b2mCargoZip) {
		this.b2mCargoZip = b2mCargoZip;
	}

	public String getB2mHolderName() {
		return b2mHolderName;
	}

	public void setB2mHolderName(String b2mHolderName) {
		this.b2mHolderName = b2mHolderName;
	}

	public String getB2mHolderEmail() {
		return b2mHolderEmail;
	}

	public void setB2mHolderEmail(String b2mHolderEmail) {
		this.b2mHolderEmail = b2mHolderEmail;
	}

	public String getB2mHolderCountry() {
		return b2mHolderCountry;
	}

	public void setB2mHolderCountry(String b2mHolderCountry) {
		this.b2mHolderCountry = b2mHolderCountry;
	}

	public String getB2mHolderState() {
		return b2mHolderState;
	}

	public void setB2mHolderState(String b2mHolderState) {
		this.b2mHolderState = b2mHolderState;
	}

	public String getB2mHolderCity() {
		return b2mHolderCity;
	}

	public void setB2mHolderCity(String b2mHolderCity) {
		this.b2mHolderCity = b2mHolderCity;
	}

	public String getB2mHolderAddr() {
		return b2mHolderAddr;
	}

	public void setB2mHolderAddr(String b2mHolderAddr) {
		this.b2mHolderAddr = b2mHolderAddr;
	}

	public String getB2mHolderZip() {
		return b2mHolderZip;
	}

	public void setB2mHolderZip(String b2mHolderZip) {
		this.b2mHolderZip = b2mHolderZip;
	}

	public String getB2mSignure() {
		String dataValue=getMerId()+getB2mOrder()+getB2mFee()+getSignKey();
		return MD5Encode(dataValue);
	}

	public void setB2mSignure(String b2mSignure) {
		this.b2mSignure = b2mSignure;
	}

	public String getB2mRefundAmount() {
		return b2mRefundAmount;
	}

	public void setB2mRefundAmount(String b2mRefundAmount) {
		this.b2mRefundAmount = b2mRefundAmount;
	}

	public String getRes_statusCode() {
		return res_statusCode;
	}

	public void setRes_statusCode(String res_statusCode) {
		this.res_statusCode = res_statusCode;
	}

	public String getRes_errorCode() {
		return res_errorCode;
	}

	public void setRes_errorCode(String res_errorCode) {
		this.res_errorCode = res_errorCode;
	}


}
