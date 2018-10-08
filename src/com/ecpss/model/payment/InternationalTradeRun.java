package com.ecpss.model.payment;
import java.io.Serializable;
import java.util.Date;

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
@Table(name = "international_traderun")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTradeRun extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348641281692587064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_Internationaltraderun", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "orderNo", nullable = true, length = 50)
	private String orderNo; // 交易流水订单号
	@Column(name = "expirationYear")
	private String expirationYear;
	@Column(name = "expirationMonth")
	private String expirationMonth;
	@Column(name = "cvv2")
	private String cvv2;
	@Column(name = "md5key")
	private String md5key;
	@Column(name = "payIp")
	private String payIp;
	@Column(name = "country")
	private String country;
	@Column(name = "UserAgent")
	private String userAgent;
	@Column(name = "createTime")
	private Date createTime; // 交易时间
	@Column(name = "status")
	private String status;
	@Column(name = "remark")
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public String getMd5key() {
		return md5key;
	}
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
	public String getPayIp() {
		return payIp;
	}
	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}




















