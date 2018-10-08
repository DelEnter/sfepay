package com.ecpss.model.shop;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "INTERNATIONAL_WEBCHANNELS")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalWebchannels implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_webchannels", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long Id;
	
	@Column(name = "merchanid", nullable = true, length = 100)
	private Long merchanid;
	
	@Column(name = "website", nullable = true, length = 50)
	private String website;
	
	@Column(name = "tradeWebsite", nullable = true, length = 50)
	private String tradeWebsite;
	
	@Column(name = "channels", nullable = true, length = 20)
	private String channels;
	
	@Column(name = "executetime", nullable = true)
	private Date executetime;
	
	@Column(name = "operator", nullable = true, length = 20)
	private String operator;
	
	@Column(name = "remark", nullable = true, length = 50)
	private String remark;

	@Column(name = "isblack")
	private String isblack;
	
	@Column(name = "webSiteType")
	private String webSiteType;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTradeWebsite() {
		return tradeWebsite;
	}

	public void setTradeWebsite(String tradeWebsite) {
		this.tradeWebsite = tradeWebsite;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getMerchanid() {
		return merchanid;
	}

	public void setMerchanid(Long merchanid) {
		this.merchanid = merchanid;
	}

	public String getIsblack() {
		return isblack;
	}

	public void setIsblack(String isblack) {
		this.isblack = isblack;
	}

	public String getWebSiteType() {
		return webSiteType;
	}

	public void setWebSiteType(String webSiteType) {
		this.webSiteType = webSiteType;
	}

	

	
}
