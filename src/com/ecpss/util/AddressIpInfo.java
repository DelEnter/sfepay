package com.ecpss.util;

public class AddressIpInfo {
	private String city;  //城市
	private String country; //国家
	private String countryCode;  //国家简码
	private String lonlat; //经纬度
	private String regionName;  //ip所属城市
	private String zip;	//  邮编
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getLonlat() {
		return lonlat;
	}
	public void setLonlat(String lonlat) {
		this.lonlat = lonlat;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	

}
