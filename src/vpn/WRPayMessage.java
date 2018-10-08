package vpn;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class WRPayMessage {
		// 返回属性
		private String transType = "";
		private String apiType = "";
		private String transModel = "";
		private String EncryptionMode = "";
		private String CharacterSet  = "";
		private String merNo = "";
		private String terNo = "";
		private String amount = "";
	    private String currencyCode ="";
	    private String orderNo ="";
	    private String goodsString ="";
	    private String cardCountry = "";
	    private String cardState ="";
	    private String cardCity ="";
	    private String cardAddress = "";
	    private String cardZipCode  = "";
	    private String cardFullName = "";
	    private String cardFullPhone = "";
	    private String cardEmail = "";
		private String grCountry = "";
		private String grState = "";
		private String grCity = "";
		private String grAddress = "";
		private String grZipCode = "";
		private String grphoneNumber = "";
		private String grPerName = "";
		private String grEmail = "";
		private String cardNO = "";
		private String expYear = "";
		private String expMonth = "";
		private String cvv = "";
		private String payIP = "";
		private String merMgrURL = "";
		private String returnURL="";
		private String notifyURL="";
		private String webInfo="";
		private String language="";
		private String hashcode="";
		private String merremark="";
			
		
		private String tradeNo ="";				
		private String respCode ="";
		private String respMsg ="";		
		private String acquirer ="";
				
		public String getTransType() {
			return transType;
		}
		public void setTransType(String transType) {
			this.transType = transType;
		}
		public String getApiType() {
			return apiType;
		}
		public void setApiType(String apiType) {
			this.apiType = apiType;
		}
		public String getTransModel() {
			return transModel;
		}
		public void setTransModel(String transModel) {
			this.transModel = transModel;
		}
		public String getEncryptionMode() {
			return EncryptionMode;
		}
		public void setEncryptionMode(String encryptionMode) {
			EncryptionMode = encryptionMode;
		}
		public String getCharacterSet() {
			return CharacterSet;
		}
		public void setCharacterSet(String characterSet) {
			CharacterSet = characterSet;
		}
		public String getMerNo() {
			return merNo;
		}
		public void setMerNo(String merNo) {
			this.merNo = merNo;
		}
		public String getTerNo() {
			return terNo;
		}
		public void setTerNo(String terNo) {
			this.terNo = terNo;
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
		public String getGoodsString() {
			return goodsString;
		}
		public void setGoodsString(String goodsString) {
			this.goodsString = goodsString;
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
		public String getCardFullName() {
			return cardFullName;
		}
		public void setCardFullName(String cardFullName) {
			this.cardFullName = cardFullName;
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
		public String getGrphoneNumber() {
			return grphoneNumber;
		}
		public void setGrphoneNumber(String grphoneNumber) {
			this.grphoneNumber = grphoneNumber;
		}
		public String getGrPerName() {
			return grPerName;
		}
		public void setGrPerName(String grPerName) {
			this.grPerName = grPerName;
		}
		public String getGrEmail() {
			return grEmail;
		}
		public void setGrEmail(String grEmail) {
			this.grEmail = grEmail;
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
		public String getPayIP() {
			return payIP;
		}
		public void setPayIP(String payIP) {
			this.payIP = payIP;
		}
		public String getMerMgrURL() {
			return merMgrURL;
		}
		public void setMerMgrURL(String merMgrURL) {
			this.merMgrURL = merMgrURL;
		}
		public String getReturnURL() {
			return returnURL;
		}
		public void setReturnURL(String returnURL) {
			this.returnURL = returnURL;
		}
		public String getNotifyURL() {
			return notifyURL;
		}
		public void setNotifyURL(String notifyURL) {
			this.notifyURL = notifyURL;
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
		public String getHashcode() {
			return hashcode;
		}
		public void setHashcode(String hashcode) {
			this.hashcode = hashcode;
		}
		public String getMerremark() {
			return merremark;
		}
		public void setMerremark(String merremark) {
			this.merremark = merremark;
		}
		public String getTradeNo() {
			return tradeNo;
		}
		public void setTradeNo(String tradeNo) {
			this.tradeNo = tradeNo;
		}
		public String getRespCode() {
			return respCode;
		}
		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}
		public String getRespMsg() {
			return respMsg;
		}
		public void setRespMsg(String respMsg) {
			this.respMsg = respMsg;
		}
		public String getAcquirer() {
			return acquirer;
		}
		public void setAcquirer(String acquirer) {
			this.acquirer = acquirer;
		}
		public String getCardEmail() {
			return cardEmail;
		}
		public void setCardEmail(String cardEmail) {
			this.cardEmail = cardEmail;
		}
		public String getHashCode() {
			String dateValue="EncryptionMode="+getEncryptionMode()+"&CharacterSet="+getCharacterSet()+"&merNo="+getMerNo()+"&terNo="+getTerNo()+"&orderNo="+getOrderNo()+"&currencyCode="+getCurrencyCode()+"&amount="+getAmount()+"&payIP="+getPayIP()+"&transType="+getTransType()+"&transModel="+getTransModel()+"&0d58b7af3f1e40c0a4fcf09fb10533a8";
			System.out.println(dateValue);
			return getSha256(dateValue);
		}
		public void setHashCode(String hashcode) {
			this.hashcode = hashcode;
		}
		public static String getSha256(String aData) {
			 String resultString = "";
		     try {
		       MessageDigest digest = MessageDigest.getInstance("SHA-256");
		       byte[] hash = digest.digest(aData.getBytes("UTF-8"));
		       resultString = Hex.encodeHexString(hash);
		       System.out.println(resultString);
		      } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
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
