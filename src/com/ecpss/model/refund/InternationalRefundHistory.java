package com.ecpss.model.refund;

import java.io.Serializable;
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
@Table(name = "international_refundhistory")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalRefundHistory  implements Serializable {
	private static final long	serialVersionUID	= 6348641281193287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_refundhistory", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "cardNo",nullable = true)
	private String cardNo;     //交易id

	@Column(name = "tradeAmount",nullable = true,length=2)
	private Double tradeAmount;     //退款状态
	
	@Column(name = "refundAmount",nullable = true)
	private Double refundAmount;     //退款金额

	@Column(name = "tradeTime",nullable = true)
	private String tradeTime;     //交易日期 
	
	@Column(name = "terminalNo",nullable = true)
	private String terminalNo;     //终端
	
	@Column(name = "authorizationNo",nullable = true)
	private String authorizationNo;     //授权号
	
	@Column(name = "checkInman",nullable = true)
	private String checkInman;     //登记人
	
	@Column(name = "phone",nullable = true,length=30)
	private String phone;     //联系电话

	@Column(name = "reason",nullable = true,length=20)
	private String reason;     //审核人
	
	@Column(name = "fileName")
	private String fileName;     //文件名

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getAuthorizationNo() {
		return authorizationNo;
	}

	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	public String getCheckInman() {
		return checkInman;
	}

	public void setCheckInman(String checkInman) {
		this.checkInman = checkInman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
