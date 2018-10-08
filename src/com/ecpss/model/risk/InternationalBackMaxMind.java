package com.ecpss.model.risk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;
//maxMind∑µªÿ÷µ±Ì
@Entity
@Table(name = "InternationalBackMaxMind")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalBackMaxMind implements java.io.Serializable  {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_InternationalBackMaxMind", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "tradeId",nullable = true,length=50)
	private Long tradeId;
	@Column(name = "riskScore")
	private String riskScore;
	@Column(name = "binName")
	private String binName;
	@Column(name = "binCountry")
	private String binCountry;
	@Column(name = "binPhone")
	private String binPhone;
	@Column(name = "ip_areaCode")
	private String ip_areaCode;
	@Column(name = "ip_postalCode")
	private String ip_postalCode;
	@Column(name = "ip_regionName")
	private String ip_regionName;
	@Column(name = "ip_region")
	private String ip_region;
	@Column(name = "ip_countryName")
	private String ip_countryName;
	@Column(name = "countryCode")
	private String countryCode;
	@Column(name = "anonymousProxy")
	private String anonymousProxy;
	@Column(name = "distance")
	private String distance;
	@Column(name = "proxyScore")
	private String proxyScore;
	@Column(name = "postalMatch")
	private String postalMatch;
	@Column(name = "custPhoneInBillingLoc")
	private String custPhoneInBillingLoc;
	@Column(name = "shipCityPostalMatch")
	private String shipCityPostalMatch;
	@Column(name = "ip_city")
	private String ip_city;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRiskScore() {
		return riskScore;
	}
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public String getBinCountry() {
		return binCountry;
	}
	public void setBinCountry(String binCountry) {
		this.binCountry = binCountry;
	}
	public String getBinPhone() {
		return binPhone;
	}
	public void setBinPhone(String binPhone) {
		this.binPhone = binPhone;
	}
	public String getIp_areaCode() {
		return ip_areaCode;
	}
	public void setIp_areaCode(String ip_areaCode) {
		this.ip_areaCode = ip_areaCode;
	}
	public String getIp_postalCode() {
		return ip_postalCode;
	}
	public void setIp_postalCode(String ip_postalCode) {
		this.ip_postalCode = ip_postalCode;
	}
	public String getIp_regionName() {
		return ip_regionName;
	}
	public void setIp_regionName(String ip_regionName) {
		this.ip_regionName = ip_regionName;
	}
	public String getIp_region() {
		return ip_region;
	}
	public void setIp_region(String ip_region) {
		this.ip_region = ip_region;
	}
	public String getIp_countryName() {
		return ip_countryName;
	}
	public void setIp_countryName(String ip_countryName) {
		this.ip_countryName = ip_countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getAnonymousProxy() {
		return anonymousProxy;
	}
	public void setAnonymousProxy(String anonymousProxy) {
		this.anonymousProxy = anonymousProxy;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getProxyScore() {
		return proxyScore;
	}
	public void setProxyScore(String proxyScore) {
		this.proxyScore = proxyScore;
	}
	public String getPostalMatch() {
		return postalMatch;
	}
	public void setPostalMatch(String postalMatch) {
		this.postalMatch = postalMatch;
	}
	public String getCustPhoneInBillingLoc() {
		return custPhoneInBillingLoc;
	}
	public void setCustPhoneInBillingLoc(String custPhoneInBillingLoc) {
		this.custPhoneInBillingLoc = custPhoneInBillingLoc;
	}
	public String getShipCityPostalMatch() {
		return shipCityPostalMatch;
	}
	public void setShipCityPostalMatch(String shipCityPostalMatch) {
		this.shipCityPostalMatch = shipCityPostalMatch;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public String getIp_city() {
		return ip_city;
	}
	public void setIp_city(String ip_city) {
		this.ip_city = ip_city;
	}
	

}
