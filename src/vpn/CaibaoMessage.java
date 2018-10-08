package vpn;

public class CaibaoMessage {
		// 返回属性
		private String res_merNo = "";
		private String res_gatewayNo = "";
		private String res_tradeNo = "";
		private String res_orderNo = "";
		private String res_orderCurrency = "";
		private String res_orderAmount = "";
		private String res_orderStatus = "";
		private String res_orderInfo = "";
	    private String res_signInfo="";
	    private String res_riskInfo="";
	    private String res_remark="";
	    private String res_billAddress="";
		// 提交的属性
	    private String merNo = "";
	    private String gatewayNo="";
	    private String orderNo = "";
	    private String orderCurrency = "";
	    private String orderAmount = "";
	    private String returnURL = "";
		private String cardNo = "";
		private String cardSecurityCode = "";
		private String cardExpireYear = "";
		private String cardExpireMonth = "";
		private String issuingBank = "";
		private String ip = "";
		private String email = "";
		private String PaymentMethod = "";
		private String phone = "";
		private String country = "";
		private String state = "";
		private String city = "";
		private String address = "";
		private String zip = "";
		private String signInfo = "";
		private String isAuthor = "";
		private String remark = "";
		private String firstName="";
		private String lastName="";
		private String csid;
		private static String[] csidValue={"ZH-CN&@ZH-CN&@MOZILLA&@0&@MICROSOFT INTERNET EXPLORER&@5.0 (COMPATIBLE; MSIE 10.0; WINDOWS NT 6.1; TRIDENT/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; KB974488; QQBROWSER/8.0.3345.400)&@TRUE&@2D1F6CFAC78AF0E8D9A7376B9CA4D74C1427789548624&@TRUE&@WIN32&@MOZILLA/5.0 (COMPATIBLE; MSIE 10.0; WINDOWS NT 6.1; TRIDENT/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; KB974488; QQBROWSER/8.0.3345.400)&@ZH-CN&@TRUE&@1360X768&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@0&@MICROSOFT INTERNET EXPLORER&@4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1; WOW64; TRIDENT/4.0; SLCC2; .NET CLR 2.0.50727;.NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0; .NET4.0C; .NET4.0E)&@TRUE&@6B92F706DDA22A85F5A0A9D8726185C81427789545477&@TRUE&@WIN32&@MOZILLA/4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1; WOW64; TRIDENT/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0; .NET4.0C; .NET4.0E)&@ZH-CN&@TRUE&@1360X768&@32&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@&@NETSCAPE&@5.0 (WINDOWS NT 6.1) APPLEWEBKIT/537.36 (KHTML, LIKE GECKO) CHROME/40.0.2214.94 SAFARI/537.36&@TRUE&@23BDCF1A8C35F63B4F30272D51DDC4691427789675196&@TRUE&@WIN32&@MOZILLA/5.0 (WINDOWS NT 6.1) APPLEWEBKIT/537.36 (KHTML, LIKE GECKO) CHROME/40.0.2214.94 SAFARI/537.36&@&@UNDEFINED&@1366X768&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@&@NETSCAPE&@5.0 (WINDOWS NT 6.3; WOW64) APPLEWEBKIT/537.36 (KHTML, LIKE GECKO) CHROME/38.0.2125.122 SAFARI/537.36&@TRUE&@3929BEFA06BBF2AE37975647E9A828561427426726460OLD&@TRUE&@WIN32&@MOZILLA/5.0 (WINDOWS NT 6.3; WOW64) APPLEWEBKIT/537.36 (KHTML, LIKE GECKO) CHROME/38.0.2125.122 SAFARI/537.36&@&@UNDEFINED&@1920X1080&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@&@NETSCAPE&@5.0 (WINDOWS)&@TRUE&@A76A6B64F868BB99599FCDADEF6D896D1427789372746OLD&@TRUE&@WIN32&@MOZILLA/5.0 (WINDOWS NT 6.1; RV:36.0) GECKO/20100101 FIREFOX/36.0&@&@UNDEFINED&@1360X768&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@&@NETSCAPE&@5.0 (WINDOWS)&@TRUE&@A76A6B64F868BB99599FCDADEF6D896D1427789372746OLD&@TRUE&@WIN32&@MOZILLA/5.0 (WINDOWS NT 6.1; RV:36.0) GECKO/20100101 FIREFOX/36.0&@&@UNDEFINED&@1360X768&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@&@NETSCAPE&@5.0(WINDOWS)&@TRUE&@A76A6B64F868BB99599FCDADEF6D896D1427789372746OLD&@TRUE&@WIN32&@MOZILLA/5.0 (WINDOWS NT 6.1; RV:36.0) GECKO/20100101FIREFOX/36.0&@&@UNDEFINED&@1360X768&@24&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@0&@MICROSOFT INTERNET EXPLORER&@4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1; TRIDENT/4.0; GTB7.5; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0)&@TRUE&@0EBB69A48FBC89F9E7C5C760B6917C0B1427442521601OLD&@TRUE&@WIN32&@MOZILLA/4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1; TRIDENT/4.0; GTB7.5; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0)&@ZH-CN&@TRUE&@1360X768&@32&@8&@www.sfepay.com",
				"ZH-CN&@ZH-CN&@MOZILLA&@0&@MICROSOFT INTERNET EXPLORER&@4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1;TRIDENT/4.0; GTB7.5; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0)&@TRUE&@A74D9A6DC95D7F79B589A185189D75AE1427792192993&@TRUE&@WIN32&@MOZILLA/4.0 (COMPATIBLE; MSIE 8.0; WINDOWS NT 6.1; TRIDENT/4.0; GTB7.5; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MEDIA CENTER PC 6.0)&@ZH-CN&@TRUE&@1360X768&@32&@8&@www.sfepay.com"};
		private String tcsid;
		
		public String getTcsid() {
			return tcsid;
		}
		public void setTcsid(String tcsid) {
			this.tcsid = tcsid;
		}
		public String getRes_merNo() {
			return res_merNo;
		}
		public void setRes_merNo(String res_merNo) {
			this.res_merNo = res_merNo;
		}
		public String getRes_gatewayNo() {
			return res_gatewayNo;
		}
		public void setRes_gatewayNo(String res_gatewayNo) {
			this.res_gatewayNo = res_gatewayNo;
		}
		public String getRes_tradeNo() {
			return res_tradeNo;
		}
		public void setRes_tradeNo(String res_tradeNo) {
			this.res_tradeNo = res_tradeNo;
		}
		public String getRes_orderNo() {
			return res_orderNo;
		}
		public void setRes_orderNo(String res_orderNo) {
			this.res_orderNo = res_orderNo;
		}
		public String getRes_orderCurrency() {
			return res_orderCurrency;
		}
		public void setRes_orderCurrency(String res_orderCurrency) {
			this.res_orderCurrency = res_orderCurrency;
		}
		public String getRes_orderAmount() {
			return res_orderAmount;
		}
		public void setRes_orderAmount(String res_orderAmount) {
			this.res_orderAmount = res_orderAmount;
		}
		public String getRes_orderStatus() {
			return res_orderStatus;
		}
		public void setRes_orderStatus(String res_orderStatus) {
			this.res_orderStatus = res_orderStatus;
		}
		public String getRes_orderInfo() {
			return res_orderInfo;
		}
		public void setRes_orderInfo(String res_orderInfo) {
			this.res_orderInfo = res_orderInfo;
		}
		public String getRes_signInfo() {
			return res_signInfo;
		}
		public void setRes_signInfo(String res_signInfo) {
			this.res_signInfo = res_signInfo;
		}
		public String getRes_riskInfo() {
			return res_riskInfo;
		}
		public void setRes_riskInfo(String res_riskInfo) {
			this.res_riskInfo = res_riskInfo;
		}
		public String getRes_remark() {
			return res_remark;
		}
		public void setRes_remark(String res_remark) {
			this.res_remark = res_remark;
		}
		public String getMerNo() {
			return merNo;
		}
		public void setMerNo(String merNo) {
			this.merNo = merNo;
		}
		public String getGatewayNo() {
			return gatewayNo;
		}
		public void setGatewayNo(String gatewayNo) {
			this.gatewayNo = gatewayNo;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getOrderCurrency() {
			return orderCurrency;
		}
		public void setOrderCurrency(String orderCurrency) {
			this.orderCurrency = orderCurrency;
		}
		public String getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getReturnURL() {
			return returnURL;
		}
		public void setReturnURL(String returnURL) {
			this.returnURL = returnURL;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getCardSecurityCode() {
			return cardSecurityCode;
		}
		public void setCardSecurityCode(String cardSecurityCode) {
			this.cardSecurityCode = cardSecurityCode;
		}
		public String getCardExpireYear() {
			return cardExpireYear;
		}
		public void setCardExpireYear(String cardExpireYear) {
			this.cardExpireYear = cardExpireYear;
		}
		public String getCardExpireMonth() {
			return cardExpireMonth;
		}
		public void setCardExpireMonth(String cardExpireMonth) {
			this.cardExpireMonth = cardExpireMonth;
		}
		public String getIssuingBank() {
			return issuingBank;
		}
		public void setIssuingBank(String issuingBank) {
			this.issuingBank = issuingBank;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPaymentMethod() {
			return PaymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			PaymentMethod = paymentMethod;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getSignInfo() {
			return signInfo;
		}
		public void setSignInfo(String signInfo) {
			this.signInfo = signInfo;
		}
		public String getIsAuthor() {
			return isAuthor;
		}
		public void setIsAuthor(String isAuthor) {
			this.isAuthor = isAuthor;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
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
		public String getCsid() {
			int i= (int)(Math.random()*9)+1;
			this.csid =csidValue[i-1];
			return csid;
		}
		public void setCsid(String csid) {
			this.csid = csid;
		}
		public String getRes_billAddress() {
			return res_billAddress;
		}
		public void setRes_billAddress(String res_billAddress) {
			this.res_billAddress = res_billAddress;
		}


}
