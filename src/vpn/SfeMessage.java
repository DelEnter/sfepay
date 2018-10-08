package vpn;

import java.util.Date;
import java.util.Hashtable;

public class SfeMessage {
	private String merNo;
	private String Amount;
	private String Currency;
	private String tradeAdd;
	private Date tradetime;
	private String ReturnURL;
	private String cardNo;
	private String cvv2;
	private String month;
	private String year;
	private String merchantOrderNo;
	private int responseCode;
	private String remark;
	private String MD5key;
	private String ip;
	private String cartype;
	private String country;
	private String cookie;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String state;
	private String city;
	private String address;
	private String zipcode;
	private String products;
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingAddress;
	private String shippingCity;
	private String shippingSstate;
	private String shippingCountry;
	private String shippingZipcode;
	private String shippingEmail;
	private String shippingPhone;
	private String userbrowser;
	private String billaddress;
	private String cardBank;
	private String xingChanel;
	private String maxmindRiskValue;
	private String maxMindInfo;
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
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
	public String getTradeAdd() {
		return tradeAdd;
	}
	public void setTradeAdd(String tradeAdd) {
		this.tradeAdd = tradeAdd;
	}
	public String getReturnURL() {
		return ReturnURL;
	}
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMD5key() {
		return MD5key;
	}
	public void setMD5key(String mD5key) {
		MD5key = mD5key;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
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
	public String getShippingZipcode() {
		return shippingZipcode;
	}
	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
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
	public String getUserbrowser() {
		return userbrowser;
	}
	public void setUserbrowser(String userbrowser) {
		this.userbrowser = userbrowser;
	}
	public Date getTradetime() {
		return tradetime;
	}
	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}
	public String getBilladdress() {
		return billaddress;
	}
	public void setBilladdress(String billaddress) {
		this.billaddress = billaddress;
	}
	public String getCardBank() {
		return cardBank;
	}
	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}
	public String getXingChanel() {
		return xingChanel;
	}
	public void setXingChanel(String xingChanel) {
		this.xingChanel = xingChanel;
	}
	public String getMaxmindRiskValue() {
		return maxmindRiskValue;
	}
	public void setMaxmindRiskValue(String maxmindRiskValue) {
		this.maxmindRiskValue = maxmindRiskValue;
	}
	public String getMaxMindInfo() {
		return maxMindInfo;
	}
	public void setMaxMindInfo(String maxMindInfo) {
		this.maxMindInfo = maxMindInfo;
	}

}
