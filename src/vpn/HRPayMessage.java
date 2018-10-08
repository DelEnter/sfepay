package vpn;

import java.security.MessageDigest;

public class HRPayMessage {
		// 返回属性
		private String merNo = "";
		private String transType = "";
		private String amount = "";
		private String currencyCode = "";
		private String orderNo  = "";
		private String siteUrl = "";
		private String webInfo = "";
		private String language = "";
	    private String cardCountry ="";
	    private String cardState ="";
	    private String cardCity ="";
	    private String cardAddress = "";
	    private String cardZipCode ="";
	    private String payIP ="";
	    private String cardFirstName = "";
	    private String cardLastName  = "";
	    private String cardFullPhone = "";
	    private String grCountry = "";
		private String grState = "";
		private String grCity = "";
		private String grAddress = "";
		private String grZipCode = "";
		private String grEmail = "";
		private String grphoneNumber = "";
		private String grFirstName = "";
		private String grLastName = "";
		private String pName = "";
		private String hashCode = "";
		private String cardNO = "";
		private String expYear = "";
		private String expMonth = "";
		private String cvv = "";
		private String md5Key="";
		
		private String res_orderNo ="";
		private String res_tradeNo ="";
		private String res_amount ="";
		private String res_respCode ="";
		private String res_respMsg ="";
		private String res_bankTime ="";
		private String res_hashcode ="";
		private String res_acquirer ="";
		public String getMerNo() {
			return merNo;
		}
		public void setMerNo(String merNo) {
			this.merNo = merNo;
		}
		public String getTransType() {
			return transType;
		}
		public void setTransType(String transType) {
			this.transType = transType;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getCurrencyCode() {
			return currencyCode;
		}
		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getSiteUrl() {
			return siteUrl;
		}
		public void setSiteUrl(String siteUrl) {
			this.siteUrl = siteUrl;
		}
		public String getWebInfo() {
			return webInfo;
		}
		public void setWebInfo(String webInfo) {
			this.webInfo = webInfo;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getCardCountry() {
			return cardCountry;
		}
		public void setCardCountry(String cardCountry) {
			this.cardCountry = cardCountry;
		}
		public String getCardState() {
			return cardState;
		}
		public void setCardState(String cardState) {
			this.cardState = cardState;
		}
		public String getCardCity() {
			return cardCity;
		}
		public void setCardCity(String cardCity) {
			this.cardCity = cardCity;
		}
		public String getCardAddress() {
			return cardAddress;
		}
		public void setCardAddress(String cardAddress) {
			this.cardAddress = cardAddress;
		}
		public String getCardZipCode() {
			return cardZipCode;
		}
		public void setCardZipCode(String cardZipCode) {
			this.cardZipCode = cardZipCode;
		}
		public String getPayIP() {
			return payIP;
		}
		public void setPayIP(String payIP) {
			this.payIP = payIP;
		}
		public String getCardFirstName() {
			return cardFirstName;
		}
		public void setCardFirstName(String cardFirstName) {
			this.cardFirstName = cardFirstName;
		}
		public String getCardLastName() {
			return cardLastName;
		}
		public void setCardLastName(String cardLastName) {
			this.cardLastName = cardLastName;
		}
		public String getCardFullPhone() {
			return cardFullPhone;
		}
		public void setCardFullPhone(String cardFullPhone) {
			this.cardFullPhone = cardFullPhone;
		}
		public String getGrCountry() {
			return grCountry;
		}
		public void setGrCountry(String grCountry) {
			this.grCountry = grCountry;
		}
		public String getGrState() {
			return grState;
		}
		public void setGrState(String grState) {
			this.grState = grState;
		}
		public String getGrCity() {
			return grCity;
		}
		public void setGrCity(String grCity) {
			this.grCity = grCity;
		}
		public String getGrAddress() {
			return grAddress;
		}
		public void setGrAddress(String grAddress) {
			this.grAddress = grAddress;
		}
		public String getGrZipCode() {
			return grZipCode;
		}
		public void setGrZipCode(String grZipCode) {
			this.grZipCode = grZipCode;
		}
		public String getGrEmail() {
			return grEmail;
		}
		public void setGrEmail(String grEmail) {
			this.grEmail = grEmail;
		}
		public String getGrphoneNumber() {
			return grphoneNumber;
		}
		public void setGrphoneNumber(String grphoneNumber) {
			this.grphoneNumber = grphoneNumber;
		}
		public String getGrFirstName() {
			return grFirstName;
		}
		public void setGrFirstName(String grFirstName) {
			this.grFirstName = grFirstName;
		}
		public String getGrLastName() {
			return grLastName;
		}
		public void setGrLastName(String grLastName) {
			this.grLastName = grLastName;
		}
		public String getpName() {
			return pName;
		}
		public void setpName(String pName) {
			this.pName = pName;
		}
		public String getHashCode() {
			String dateValue="merNo="+getMerNo()+"&orderNo="+getOrderNo()+"&currencyCode="+getCurrencyCode()+"&amount="+getAmount()+"&payIP="+getPayIP()+"&transType="+getTransType()+"&"+getMd5Key();
			System.out.println(dateValue);
			return MD5Encode(dateValue);
		}
		public void setHashCode(String hashCode) {
			this.hashCode = hashCode;
		}
		public String getCardNO() {
			return cardNO;
		}
		public void setCardNO(String cardNO) {
			this.cardNO = cardNO;
		}
		public String getExpYear() {
			return expYear;
		}
		public void setExpYear(String expYear) {
			this.expYear = expYear;
		}
		public String getExpMonth() {
			return expMonth;
		}
		public void setExpMonth(String expMonth) {
			this.expMonth = expMonth;
		}
		public String getCvv() {
			return cvv;
		}
		public void setCvv(String cvv) {
			this.cvv = cvv;
		}
		public String getRes_orderNo() {
			return res_orderNo;
		}
		public void setRes_orderNo(String res_orderNo) {
			this.res_orderNo = res_orderNo;
		}
		public String getRes_tradeNo() {
			return res_tradeNo;
		}
		public void setRes_tradeNo(String res_tradeNo) {
			this.res_tradeNo = res_tradeNo;
		}
		public String getRes_amount() {
			return res_amount;
		}
		public void setRes_amount(String res_amount) {
			this.res_amount = res_amount;
		}
		public String getRes_respCode() {
			return res_respCode;
		}
		public void setRes_respCode(String res_respCode) {
			this.res_respCode = res_respCode;
		}
		public String getRes_respMsg() {
			return res_respMsg;
		}
		public void setRes_respMsg(String res_respMsg) {
			this.res_respMsg = res_respMsg;
		}
		public String getRes_bankTime() {
			return res_bankTime;
		}
		public void setRes_bankTime(String res_bankTime) {
			this.res_bankTime = res_bankTime;
		}
		public String getRes_hashcode() {
			return res_hashcode;
		}
		public void setRes_hashcode(String res_hashcode) {
			this.res_hashcode = res_hashcode;
		}
		public String getRes_acquirer() {
			return res_acquirer;
		}
		public void setRes_acquirer(String res_acquirer) {
			this.res_acquirer = res_acquirer;
		}
		public String getMd5Key() {
			return md5Key;
		}
		public void setMd5Key(String md5Key) {
			this.md5Key = md5Key;
		}
		public static String MD5Encode(String aData) throws SecurityException {
			String resultString = null;
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				resultString = bytes2HexString(md.digest(aData.getBytes("UTF-8")));
			} catch (Exception e) {
				e.printStackTrace();
				throw new SecurityException("MD5运算失败");
			}
			return resultString;
		}
		public static String bytes2HexString(byte[] b) {
			String ret = "";
			for (int i = 0; i < b.length; i++) {
				String hex = Integer.toHexString(b[i] & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
				ret += hex.toUpperCase();
			}
			return ret;
		}
		

}
