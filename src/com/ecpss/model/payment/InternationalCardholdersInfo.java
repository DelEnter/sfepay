package com.ecpss.model.payment;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

import com.ecpss.model.PriEntity;


@Entity
@Table(name = "international_cardholdersinfo")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalCardholdersInfo extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348641281692587064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_cardholdersinfo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "tradeId",nullable = true,length=50)
	private Long tradeId;     //Ecpss订单id号

	@Column(name = "firstName",nullable = true,length=100)
	private String firstName;     //持卡人名字
	
	@Column(name = "lastName",nullable = true)
	private String lastName;     //持卡人姓
	
	@Column(name = "cardNo")
	private String cardNo;     //卡号
	
	@Column(name = "cardNo6")	//卡号前6
	private String cardNo6;
	
	@Column(name = "cardNo9")	//卡号前9
	private String cardNo9;
	
	@Column(name = "cardNo12")	//卡号前12
	private String cardNo12;
	
	@Column(name = "cardNo4")	//卡号后4
	private String cardNo4;
	
	@Column(name = "expiryDate",nullable = true)
	private String expiryDate;     //信用卡有效期
	
	@Column(name = "cvv2",length=3,nullable = true)
	private String cvv2;    //cvv2
	
	@Column(name = "cookie",length=3,nullable = true)
	private String cookie;    //持卡人支付电脑保存cookie
	
	@Column(name = "ip",length=30,nullable = true)
	private String ip;    //持卡人交易电脑IP
	
	@Column(name = "address",length=150)
	private String address;
	@Column(name = "city",length=30)
	private String city;
	@Column(name = "phone",length=30)
	private String phone;       
	@Column(name = "state",length=90)
	private String state;
	@Column(name = "zipcode",length=30)
	private String zipcode;
	@Column(name = "country",length=30)
	private String country;
	@Column(name = "email",length=100,nullable = true)
	private String email;    //持卡人邮箱
	
	@Column(name = "shippingFullName",nullable = true,length=100)
	private String shippingFullName;     //持卡人名字
	
	@Column(name = "shippingAddress",length=100,nullable = true)
	private String shippingAddress;     //发货地址
	
	@Column(name = "shippingCity",length=100,nullable = true)
	private String shippingCity;     //收获城市
	
	@Column(name = "shippingState",length=100,nullable = true)
	private String shippingState;     //收货
	
	@Column(name = "shippingZip",length=20,nullable = true)
	private String shippingZip;     //
	
	@Column(name = "shippingCountry",length=70,nullable = true)
	private String shippingCountry;     //
	
	@Column(name = "shippingEmail",length=100,nullable = true)
	private String shippingEmail;     //
	
	@Column(name = "shippingPhone",length=20,nullable = true)
	private String shippingPhone;     //
	
	@Column(name = "productInfo",length=500,nullable = true)
	private String productInfo;   //物品信息
	
	@Column(name = "product",length=500,nullable = true)
	private String product;   //修改后的物品信息
	
	@Column(name = "userBank",nullable = true)
	private String userBank;
	
	@Column(name = "maxmindValue",nullable = true)
	private Double maxmindValue;

	@Column(name = "bankcountry",length=100,nullable = true)
	private String bankcountry;
	
	@Column(name = "bankname",length=100,nullable = true)
	private String bankname;
	
	@Column(name = "bankphone",length=100,nullable = true)
	private String bankphone;
			
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getShippingFullName() {
		return shippingFullName;
	}
	public void setShippingFullName(String shippingFullName) {
		this.shippingFullName = shippingFullName;
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
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public String getShippingZip() {
		return shippingZip;
	}
	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
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
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public Double getMaxmindValue() {
		return maxmindValue;
	}
	public void setMaxmindValue(Double maxmindValue) {
		this.maxmindValue = maxmindValue;
	}
	public String getBankcountry() {
		return bankcountry;
	}
	public void setBankcountry(String bankcountry) {
		this.bankcountry = bankcountry;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankphone() {
		return bankphone;
	}
	public void setBankphone(String bankphone) {
		this.bankphone = bankphone;
	}
	public String getCardNo6() {
		return cardNo6;
	}
	public void setCardNo6(String cardNo6) {
		this.cardNo6 = cardNo6;
	}
	public String getCardNo4() {
		return cardNo4;
	}
	public void setCardNo4(String cardNo4) {
		this.cardNo4 = cardNo4;
	}
	public String getCardNo9() {
		return cardNo9;
	}
	public void setCardNo9(String cardNo9) {
		this.cardNo9 = cardNo9;
	}
	public String getCardNo12() {
		return cardNo12;
	}
	public void setCardNo12(String cardNo12) {
		this.cardNo12 = cardNo12;
	}
	public String getUserBank() {
		return userBank;
	}
	public void setUserBank(String userBank) {
		this.userBank = userBank;
	}
	
}




















