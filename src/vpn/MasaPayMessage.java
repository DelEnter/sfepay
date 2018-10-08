package vpn;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class MasaPayMessage {
		// 提交的属性
		private String version = "";
		private String merchantId = "";
		private String charset = "";
		private String language = "";
		private String signType = "";
		private String merchantOrderNo = "";
		private String goodsName = "";
		private String goodsDesc = "";
	    private String orderExchange="";
	    private String currencyCode="";
	    private String orderAmount="";
	    private String submitTime="";
	    private String expiryTime = "";
	    private String bgUrl="";
	    private String payMode = "";
	    private String orgCode = "";
	    private String cardNumber = "";
	    private String cardHolderFirstName = "";
		private String cardHolderLastName = "";
		private String cardExpirationMonth = "";
		private String cardExpirationYear = "";
		private String securityCode = "";
		private String cardHolderEmail = "";
		private String cardHolderPhoneNumber = "";
		private String billName = "";
		private String billAddress = "";
		private String billPostalCode = "";
		private String billCountry = "";
		private String billState = "";
		private String billCity = "";
		private String billEmail = "";
		private String billPhoneNumber = "";
		private String shippingName = "";
		private String shippingAddress = "";
		private String shippingPostalCode = "";
		private String shippingCountry="";
		private String shippingState;
		private String shippingCity = "";
		private String shippingEmail = "";
		private String shippingPhoneNumber = "";
		private String deviceFingerprintID = "";
		private String registerUserEmail = "";
		private String registerTime = "";
		private String registerIp = "";
		private String registerTerminal="";
		private String orderIp;
		private String orderTerminal = "";
		private String signMsg = "";
		private String md5key="";
		//返回结果
		private String res_errCode;
		private String res_errMsg;
		private String res_masapayOrderNo;
		private String res_resultCode;
		private String res_paidAmount;
		
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getSignType() {
			return signType;
		}
		public void setSignType(String signType) {
			this.signType = signType;
		}
		public String getMerchantOrderNo() {
			return merchantOrderNo;
		}
		public void setMerchantOrderNo(String merchantOrderNo) {
			this.merchantOrderNo = merchantOrderNo;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsDesc() {
			return goodsDesc;
		}
		public void setGoodsDesc(String goodsDesc) {
			this.goodsDesc = goodsDesc;
		}
		public String getOrderExchange() {
			return orderExchange;
		}
		public void setOrderExchange(String orderExchange) {
			this.orderExchange = orderExchange;
		}
		public String getCurrencyCode() {
			return currencyCode;
		}
		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}
		public String getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getSubmitTime() {
			return submitTime;
		}
		public void setSubmitTime(String submitTime) {
			this.submitTime = submitTime;
		}
		public String getExpiryTime() {
			return expiryTime;
		}
		public void setExpiryTime(String expiryTime) {
			this.expiryTime = expiryTime;
		}
		public String getBgUrl() {
			return bgUrl;
		}
		public void setBgUrl(String bgUrl) {
			this.bgUrl = bgUrl;
		}
		public String getPayMode() {
			return payMode;
		}
		public void setPayMode(String payMode) {
			this.payMode = payMode;
		}
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public String getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}
		public String getCardHolderFirstName() {
			return cardHolderFirstName;
		}
		public void setCardHolderFirstName(String cardHolderFirstName) {
			this.cardHolderFirstName = cardHolderFirstName;
		}
		public String getCardHolderLastName() {
			return cardHolderLastName;
		}
		public void setCardHolderLastName(String cardHolderLastName) {
			this.cardHolderLastName = cardHolderLastName;
		}
		public String getCardExpirationMonth() {
			return cardExpirationMonth;
		}
		public void setCardExpirationMonth(String cardExpirationMonth) {
			this.cardExpirationMonth = cardExpirationMonth;
		}
		public String getCardExpirationYear() {
			return cardExpirationYear;
		}
		public void setCardExpirationYear(String cardExpirationYear) {
			this.cardExpirationYear = cardExpirationYear;
		}
		public String getSecurityCode() {
			return securityCode;
		}
		public void setSecurityCode(String securityCode) {
			this.securityCode = securityCode;
		}
		public String getCardHolderEmail() {
			return cardHolderEmail;
		}
		public void setCardHolderEmail(String cardHolderEmail) {
			this.cardHolderEmail = cardHolderEmail;
		}
		public String getCardHolderPhoneNumber() {
			return cardHolderPhoneNumber;
		}
		public void setCardHolderPhoneNumber(String cardHolderPhoneNumber) {
			this.cardHolderPhoneNumber = cardHolderPhoneNumber;
		}
		public String getBillName() {
			return billName;
		}
		public void setBillName(String billName) {
			this.billName = billName;
		}
		public String getBillAddress() {
			return billAddress;
		}
		public void setBillAddress(String billAddress) {
			this.billAddress = billAddress;
		}
		public String getBillPostalCode() {
			return billPostalCode;
		}
		public void setBillPostalCode(String billPostalCode) {
			this.billPostalCode = billPostalCode;
		}
		public String getBillCountry() {
			return billCountry;
		}
		public void setBillCountry(String billCountry) {
			this.billCountry = billCountry;
		}
		public String getBillState() {
			return billState;
		}
		public void setBillState(String billState) {
			Map<String,String> USStates = new HashMap<String,String>();
			USStates.put("AL", "Alabama");
            USStates.put("AK", "Alaska");
            USStates.put("AS", "American Samoa");
            USStates.put("AZ", "Arizona");
            USStates.put("AR", "Arkansas");
            USStates.put("AF", "Armed Forces Africa");
            USStates.put("AA", "Armed Forces Americas");
            USStates.put("AC", "Armed Forces Canada");
            USStates.put("AE", "Armed Forces Europe");
            USStates.put("AM", "Armed Forces Middle East");
            USStates.put("AP", "Armed Forces Pacific");
            USStates.put("CA", "California");
            USStates.put("CO", "Colorado");
            USStates.put("CT", "Connecticut");
            USStates.put("DE", "Delaware");
            USStates.put("DC", "District of Columbia");
            USStates.put("FM", "Federated States of Micronesia");
            USStates.put("FL", "Florida");
            USStates.put("GA", "Georgia");
            USStates.put("GU", "Guam");
            USStates.put("HI", "Hawaii");
            USStates.put("ID", "Idaho");
            USStates.put("IL", "Illinois");
            USStates.put("IN", "Indiana");
            USStates.put("IA", "Iowa");
            USStates.put("KS", "Kansas");
            USStates.put("KY", "Kentucky");
            USStates.put("LA", "Louisiana");
            USStates.put("ME", "Maine");
            USStates.put("MH", "Marshall Islands");
            USStates.put("MD", "Maryland");
            USStates.put("MA", "Massachusetts");
            USStates.put("MI", "Michigan");
            USStates.put("MN", "Minnesota");
            USStates.put("MS", "Mississippi");
            USStates.put("MO", "Missouri");
            USStates.put("MT", "Montana");
            USStates.put("NE", "Nebraska");
            USStates.put("NV", "Nevada");
            USStates.put("NH", "New Hampshire");
            USStates.put("NJ", "New Jersey");
            USStates.put("NM", "New Mexico");
            USStates.put("NY", "New York");
            USStates.put("NC", "North Carolina");
            USStates.put("ND", "North Dakota");
            USStates.put("MP", "Northern Mariana Islands");
            USStates.put("OH", "Ohio");
            USStates.put("OK", "Oklahoma");
            USStates.put("OR", "Oregon");
            USStates.put("PW", "Palau");
            USStates.put("PA", "Pennsylvania");
            USStates.put("PR", "Puerto Rico");
            USStates.put("RI", "Rhode Island");
            USStates.put("SC", "South Carolina");
            USStates.put("SD", "South Dakota");
            USStates.put("TN", "Tennessee");
            USStates.put("TX", "Texas");
            USStates.put("UT", "Utah");
            USStates.put("VT", "Vermont");
            USStates.put("VI", "Virgin Islands");
            USStates.put("VA", "Virginia");
            USStates.put("WA", "Washington");
            USStates.put("WV", "West Virginia");
            USStates.put("WI", "Wisconsin");
            USStates.put("WY", "Wyoming");
            USStates.put("AB", "Alberta");
            USStates.put("BC", "British Columbia");
            USStates.put("MB", "Manitoba");
            USStates.put("NB", "New Brunswick");
            USStates.put("NL", "Newfoundland and Labrador");
            USStates.put("NS", "Nova Scotia");
            USStates.put("ON", "Ontario");
            USStates.put("PE", "Prince Edward Island");
            USStates.put("QC", "Quebec");
            USStates.put("SK", "Saskatchewan");
            USStates.put("NT", "Northwest Territories");
            USStates.put("NU", "Nunavut");
            USStates.put("YT", "Yukon Territory");
            for(String key : USStates.keySet()){ 
                String value = USStates.get(key); 
                if(billState.equals(value)){
                	this.billState = key;
                	break;
                }else{
                	this.billState = billState;
                }
            }  					
			//this.billState = billState;
		}
		public String getBillCity() {
			return billCity;
		}
		public void setBillCity(String billCity) {
			this.billCity = billCity;
		}
		public String getBillEmail() {
			return billEmail;
		}
		public void setBillEmail(String billEmail) {
			this.billEmail = billEmail;
		}
		public String getBillPhoneNumber() {
			return billPhoneNumber;
		}
		public void setBillPhoneNumber(String billPhoneNumber) {
			this.billPhoneNumber = billPhoneNumber;
		}
		public String getShippingName() {
			return shippingName;
		}
		public void setShippingName(String shippingName) {
			this.shippingName = shippingName;
		}
		public String getShippingAddress() {
			return shippingAddress;
		}
		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
		public String getShippingPostalCode() {
			return shippingPostalCode;
		}
		public void setShippingPostalCode(String shippingPostalCode) {
			this.shippingPostalCode = shippingPostalCode;
		}
		public String getShippingCountry() {
			return shippingCountry;
		}
		public void setShippingCountry(String shippingCountry) {
			this.shippingCountry = shippingCountry;
		}
		public String getShippingState() {
			return shippingState;
		}
		public void setShippingState(String shippingState) {
			Map<String,String> USStates = new HashMap<String,String>();
			USStates.put("AL", "Alabama");
            USStates.put("AK", "Alaska");
            USStates.put("AS", "American Samoa");
            USStates.put("AZ", "Arizona");
            USStates.put("AR", "Arkansas");
            USStates.put("AF", "Armed Forces Africa");
            USStates.put("AA", "Armed Forces Americas");
            USStates.put("AC", "Armed Forces Canada");
            USStates.put("AE", "Armed Forces Europe");
            USStates.put("AM", "Armed Forces Middle East");
            USStates.put("AP", "Armed Forces Pacific");
            USStates.put("CA", "California");
            USStates.put("CO", "Colorado");
            USStates.put("CT", "Connecticut");
            USStates.put("DE", "Delaware");
            USStates.put("DC", "District of Columbia");
            USStates.put("FM", "Federated States of Micronesia");
            USStates.put("FL", "Florida");
            USStates.put("GA", "Georgia");
            USStates.put("GU", "Guam");
            USStates.put("HI", "Hawaii");
            USStates.put("ID", "Idaho");
            USStates.put("IL", "Illinois");
            USStates.put("IN", "Indiana");
            USStates.put("IA", "Iowa");
            USStates.put("KS", "Kansas");
            USStates.put("KY", "Kentucky");
            USStates.put("LA", "Louisiana");
            USStates.put("ME", "Maine");
            USStates.put("MH", "Marshall Islands");
            USStates.put("MD", "Maryland");
            USStates.put("MA", "Massachusetts");
            USStates.put("MI", "Michigan");
            USStates.put("MN", "Minnesota");
            USStates.put("MS", "Mississippi");
            USStates.put("MO", "Missouri");
            USStates.put("MT", "Montana");
            USStates.put("NE", "Nebraska");
            USStates.put("NV", "Nevada");
            USStates.put("NH", "New Hampshire");
            USStates.put("NJ", "New Jersey");
            USStates.put("NM", "New Mexico");
            USStates.put("NY", "New York");
            USStates.put("NC", "North Carolina");
            USStates.put("ND", "North Dakota");
            USStates.put("MP", "Northern Mariana Islands");
            USStates.put("OH", "Ohio");
            USStates.put("OK", "Oklahoma");
            USStates.put("OR", "Oregon");
            USStates.put("PW", "Palau");
            USStates.put("PA", "Pennsylvania");
            USStates.put("PR", "Puerto Rico");
            USStates.put("RI", "Rhode Island");
            USStates.put("SC", "South Carolina");
            USStates.put("SD", "South Dakota");
            USStates.put("TN", "Tennessee");
            USStates.put("TX", "Texas");
            USStates.put("UT", "Utah");
            USStates.put("VT", "Vermont");
            USStates.put("VI", "Virgin Islands");
            USStates.put("VA", "Virginia");
            USStates.put("WA", "Washington");
            USStates.put("WV", "West Virginia");
            USStates.put("WI", "Wisconsin");
            USStates.put("WY", "Wyoming");
            USStates.put("AB", "Alberta");
            USStates.put("BC", "British Columbia");
            USStates.put("MB", "Manitoba");
            USStates.put("NB", "New Brunswick");
            USStates.put("NL", "Newfoundland and Labrador");
            USStates.put("NS", "Nova Scotia");
            USStates.put("ON", "Ontario");
            USStates.put("PE", "Prince Edward Island");
            USStates.put("QC", "Quebec");
            USStates.put("SK", "Saskatchewan");
            USStates.put("NT", "Northwest Territories");
            USStates.put("NU", "Nunavut");
            USStates.put("YT", "Yukon Territory");
            for(String key : USStates.keySet()){ 
                String value = USStates.get(key); 
                if(shippingState.equals(value)){
                	this.shippingState = key;
                	break;
                }else{
                	this.shippingState = shippingState;
                }
            }  		
			//this.shippingState = shippingState;
		}
		public String getShippingCity() {
			return shippingCity;
		}
		public void setShippingCity(String shippingCity) {
			this.shippingCity = shippingCity;
		}
		public String getShippingEmail() {
			return shippingEmail;
		}
		public void setShippingEmail(String shippingEmail) {
			this.shippingEmail = shippingEmail;
		}
		public String getShippingPhoneNumber() {
			return shippingPhoneNumber;
		}
		public void setShippingPhoneNumber(String shippingPhoneNumber) {
			this.shippingPhoneNumber = shippingPhoneNumber;
		}
		public String getDeviceFingerprintID() {
			return deviceFingerprintID;
		}
		public void setDeviceFingerprintID(String deviceFingerprintID) {
			this.deviceFingerprintID = deviceFingerprintID;
		}
		public String getRegisterUserEmail() {
			return registerUserEmail;
		}
		public void setRegisterUserEmail(String registerUserEmail) {
			this.registerUserEmail = registerUserEmail;
		}
		public String getRegisterTime() {
			return registerTime;
		}
		public void setRegisterTime(String registerTime) {
			this.registerTime = registerTime;
		}
		public String getRegisterIp() {
			return registerIp;
		}
		public void setRegisterIp(String registerIp) {
			this.registerIp = registerIp;
		}
		public String getRegisterTerminal() {
			return registerTerminal;
		}
		public void setRegisterTerminal(String registerTerminal) {
			this.registerTerminal = registerTerminal;
		}
		public String getOrderIp() {
			return orderIp;
		}
		public void setOrderIp(String orderIp) {
			this.orderIp = orderIp;
		}
		public String getOrderTerminal() {
			return orderTerminal;
		}
		public void setOrderTerminal(String orderTerminal) {
			this.orderTerminal = orderTerminal;
		}
		public String getSignMsg() {
			String signValue="version="+getVersion()+"&merchantId="+getMerchantId()+"&signType="+getSignType()+"&merchantOrderNo="+getMerchantOrderNo()+"&currencyCode="+getCurrencyCode()+"&orderAmount="+getOrderAmount()+
					"&submitTime="+getSubmitTime()+"&cardNumber="+getCardNumber()+"&cardExpirationMonth="+getCardExpirationMonth()+"&cardExpirationYear="+getCardExpirationYear()+
					"&securityCode="+getSecurityCode()+"&key="+getMd5key();
			return getSha256(signValue);
		}
		public String getMd5key() {
			return md5key;
		}
		public void setMd5key(String md5key) {
			this.md5key = md5key;
		}
		public void setSignMsg(String signMsg) {
			this.signMsg = signMsg;
		}
		public String getRes_errCode() {
			return res_errCode;
		}
		public void setRes_errCode(String res_errCode) {
			this.res_errCode = res_errCode;
		}
		public String getRes_errMsg() {
			return res_errMsg;
		}
		public void setRes_errMsg(String res_errMsg) {
			this.res_errMsg = res_errMsg;
		}
		public String getRes_masapayOrderNo() {
			return res_masapayOrderNo;
		}
		public void setRes_masapayOrderNo(String res_masapayOrderNo) {
			this.res_masapayOrderNo = res_masapayOrderNo;
		}
		public String getRes_resultCode() {
			return res_resultCode;
		}
		public void setRes_resultCode(String res_resultCode) {
			this.res_resultCode = res_resultCode;
		}
		public String getRes_paidAmount() {
			return res_paidAmount;
		}
		public void setRes_paidAmount(String res_paidAmount) {
			this.res_paidAmount = res_paidAmount;
		}
		public static String getSha256(String strData) {
			 String output = "";
		     try {
		       MessageDigest digest = MessageDigest.getInstance("SHA-256");
		       byte[] hash = digest.digest(strData.getBytes("UTF-8"));
		       output = Hex.encodeHexString(hash);
		       System.out.println(output);
		      } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		    return output;
		}
	
}
