package vpn;

import java.util.Date;

public class GooPayMessage {
	private String merchantMID;
	private String newcardtype;
	private String cardnum;
	private String cvv2;
	private String month;
	private String year;
	private String cardbank;
	private String BillNo;
	private String Amount;
	private String Currency;
	private String Language;
	private String HASH;
	private String ReturnURL;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String zipcode;
	private String address;
	private String city;
	private String state;
	private String country;
	private String ipAddr;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingEmail;
	private String shippingPhone;
	private String shippingZipcode;
	private String shippingAddress;
	private String shippingCity;
	private String shippingSstate;
	private String shippingCountry;
	private String products;
	private String website;
	private String result;
	private String succeed;
	private String remark;
	private String grn;
	private String billingAddress;
	private String trans_Type;
	private String oriAmount;
	private String refundAmount;
	
	public String getMerchantMID() {
		return merchantMID;
	}
	public void setMerchantMID(String merchantMID) {
		this.merchantMID = merchantMID;
	}
	public String getNewcardtype() {
		return newcardtype;
	}
	public void setNewcardtype(String newcardtype) {
		this.newcardtype = newcardtype;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getHASH() {
		return HASH;
	}
	public void setHASH(String hASH) {
		HASH = hASH;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCardbank() {
		return cardbank;
	}
	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
	}
	public String getBillNo() {
		return BillNo;
	}
	public void setBillNo(String billNo) {
		BillNo = billNo;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getReturnURL() {
		return ReturnURL;
	}
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getShippingFirstName() {
		return shippingFirstName;
	}
	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}
	public String getShippingLastName() {
		return shippingLastName;
	}
	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}
	public String getShippingEmail() {
		return shippingEmail;
	}
	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	public String getShippingZipcode() {
		return shippingZipcode;
	}
	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingCity() {
		return shippingCity;
	}
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}
	public String getShippingSstate() {
		return shippingSstate;
	}
	public void setShippingSstate(String shippingSstate) {
		this.shippingSstate = shippingSstate;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getSucceed() {
		return succeed;
	}
	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGrn() {
		return grn;
	}
	public void setGrn(String grn) {
		this.grn = grn;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getTrans_Type() {
		return trans_Type;
	}
	public void setTrans_Type(String trans_Type) {
		this.trans_Type = trans_Type;
	}
	public String getOriAmount() {
		return oriAmount;
	}
	public void setOriAmount(String oriAmount) {
		this.oriAmount = oriAmount;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	

}
