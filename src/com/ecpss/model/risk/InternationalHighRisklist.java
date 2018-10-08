package com.ecpss.model.risk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

//ºÚ¿¨¿â
@Entity
@Table(name = "InternationalHighRisklist")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalHighRisklist implements java.io.Serializable {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_InternationalHighBacklist", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "cardno")
	private String cardno;
	@Column(name = "ip")
	private String ip;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@Column(name = "zipCode")
	private String zipCode;
	@Column(name = "cookie")
	private String cookie;
	@Column(name = "merId")
	private Long merId;
	@Column(name = "remark")
	private String remark;
	@Column(name = "operator")
	private String operator;
	@Column(name = "createDate")
	private Date createDate;
	@Column(name = "tradeUrl")
	private String tradeUrl;
	@Column(name = "errorColumn")
	private String errorColumn;
	@Column(name = "isWeb")
	private String isWeb;
	@Column(name = "isQWeb")
	private String isQWeb;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
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

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTradeUrl() {
		return tradeUrl;
	}

	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}

	public String getErrorColumn() {
		return errorColumn;
	}

	public void setErrorColumn(String errorColumn) {
		this.errorColumn = errorColumn;
	}

	public String getIsWeb() {
		return isWeb;
	}

	public void setIsWeb(String isWeb) {
		this.isWeb = isWeb;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getIsQWeb() {
		return isQWeb;
	}

	public void setIsQWeb(String isQWeb) {
		this.isQWeb = isQWeb;
	}

}