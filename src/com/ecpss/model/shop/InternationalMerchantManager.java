package com.ecpss.model.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

//商户管理表
@Entity
@Table(name = "International_MerchantManager")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMerchantManager {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_InternationalMerchantManager", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "merchantId")
	private Long merchantId;
	
	@Column(name = "confirmValue")
	private Long confirmValue;   //待确认
	
	@Column(name = "markValue")   //正常
	private Long markValue;
	@Column(name = "merchantAddress")
	private String merchantAddress;
	@Column(name = "penQuota")
	private Long penQuota;
	@Column(name = "penQuotaLower")
	private Long penQuotaLower;		//单笔交易下限
	@Column(name = "dayQuota")
	private Long dayQuota;
	@Column(name = "monthQuota")
	private Long monthQuota;
	@Column(name = "urlCount")
	private Long urlCount;
	@Column(name = "riskMoney")
	private Double riskMoney;
	@Column(name = "channelMoney")
	private Double channelMoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMarkValue() {
		return markValue;
	}

	public void setMarkValue(Long markValue) {
		this.markValue = markValue;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public Long getPenQuota() {
		return penQuota;
	}

	public void setPenQuota(Long penQuota) {
		this.penQuota = penQuota;
	}

	public Long getDayQuota() {
		return dayQuota;
	}

	public void setDayQuota(Long dayQuota) {
		this.dayQuota = dayQuota;
	}

	public Long getMonthQuota() {
		return monthQuota;
	}

	public void setMonthQuota(Long monthQuota) {
		this.monthQuota = monthQuota;
	}

	public Long getPenQuotaLower() {
		return penQuotaLower;
	}

	public void setPenQuotaLower(Long penQuotaLower) {
		this.penQuotaLower = penQuotaLower;
	}

	public Long getConfirmValue() {
		return confirmValue;
	}

	public void setConfirmValue(Long confirmValue) {
		this.confirmValue = confirmValue;
	}

	public Long getUrlCount() {
		return urlCount;
	}

	public void setUrlCount(Long urlCount) {
		this.urlCount = urlCount;
	}

	public Double getRiskMoney() {
		return riskMoney;
	}

	public void setRiskMoney(Double riskMoney) {
		this.riskMoney = riskMoney;
	}

	public Double getChannelMoney() {
		return channelMoney;
	}

	public void setChannelMoney(Double channelMoney) {
		this.channelMoney = channelMoney;
	}


}
