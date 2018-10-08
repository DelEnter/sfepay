package vpn;

import java.util.HashMap;
import java.util.Map;

public class ZMTPayMessage {
		// 返回属性
		private String merNo;
		private String billNo;
		private String payCurrency;
		private String amount;
		private String currency;
		private String website;
		private String ie_language;
		private String drawee;
		private String ET_GOODS;
		private String remark;
		private String cardNo;
		private String cardExpireMonth;
		private String cardExpireYear;
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String ip;
		private String cvv2;
		private String zip;
		private String country;
		private String email;
		private String phone;
		private String issuingBank;
		private String SHA256info;
		private String Billingstate;		
		
		//private String billNo;
		private String tradeNo;
		//private String amount;
		private String respCode;
		private String bankInfo;//银行辅助信息
	
		public String getMerNo() {
			return merNo;
		}
		public void setMerNo(String merNo) {
			this.merNo = merNo;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getPayCurrency() {
			return payCurrency;
		}
		public void setPayCurrency(String payCurrency) {
			this.payCurrency = payCurrency;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getIe_language() {
			return ie_language;
		}
		public void setIe_language(String ie_language) {
			this.ie_language = ie_language;
		}
		public String getDrawee() {
			return drawee;
		}
		public void setDrawee(String drawee) {
			this.drawee = drawee;
		}
		public String getET_GOODS() {
			return ET_GOODS;
		}
		public void setET_GOODS(String eT_GOODS) {
			ET_GOODS = eT_GOODS;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getCardExpireMonth() {
			return cardExpireMonth;
		}
		public void setCardExpireMonth(String cardExpireMonth) {
			this.cardExpireMonth = cardExpireMonth;
		}
		public String getCardExpireYear() {
			return cardExpireYear;
		}
		public void setCardExpireYear(String cardExpireYear) {
			this.cardExpireYear = cardExpireYear;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getCvv2() {
			return cvv2;
		}
		public void setCvv2(String cvv2) {
			this.cvv2 = cvv2;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getIssuingBank() {
			return issuingBank;
		}
		public void setIssuingBank(String issuingBank) {
			this.issuingBank = issuingBank;
		}
		public String getSHA256info() {
			return SHA256info;
		}
		public void setSHA256info(String sHA256info) {
			SHA256info = sHA256info;
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
		public String getBankInfo() {
			return bankInfo;
		}
		public void setBankInfo(String bankInfo) {
			this.bankInfo = bankInfo;
		}		
		
		public String getBillingstate() {
			return Billingstate;
		}
	
		public void setBillingstate(String billingstate) {
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
                if(billingstate.equals(value)){
                	Billingstate = key;
                	break;
                }else{
                	Billingstate = billingstate;
                }
            }           
		}
}
