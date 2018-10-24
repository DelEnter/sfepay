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
/*
 * jiahui add 2014 -04-16
 */
@Entity
@Table(name = "INTERNATIONAL_ISAUDITWEB")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalIsAuditWeb implements java.io.Serializable {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_isauditweb", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private long id;
	
	@Column(name = "merchanid", nullable = true, length = 100)
	private Long merchanid;//商户ID
	
	@Column(name = "website", nullable = true, length = 50)
	private String website;//交易网址
	
	@Column(name = "tradeWebsite", nullable = true, length = 50)
	private String tradeWebsite;//返回网址
	
	@Column(name = "createtime", nullable = true)
	private Date createtime;//创建时间
	
	@Column(name = "modifytime", nullable = true)
	private Date modifytime;//修改时间
	
	@Column(name = "operator", nullable = true, length = 20)
	private String operator;//操作员
	
	@Column(name = "remark", nullable = true, length = 100)
	private String remark;//网址类别
	
	@Column(name = "isAudit")
	private String isAudit;//是否通过 {0 ：不通过  1：通过}
	
	@Column(name="webcannelId")
	private Long webcannelId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getMerchanid() {
		return merchanid;
	}

	public void setMerchanid(Long merchanid) {
		this.merchanid = merchanid;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
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

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public Long getWebcannelId() {
		return webcannelId;
	}

	public void setWebcannelId(Long webcannelId) {
		this.webcannelId = webcannelId;
	}

}
