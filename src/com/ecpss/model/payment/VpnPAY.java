package com.ecpss.model.payment;

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
@Table(name = "international_VPNPAY")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class VpnPAY {

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_international_VPNPAY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	// 返回码
	@Column(name = "refNo")
	private String refNo;

	// INVOICE 码
	@Column(name = "Invoice")
	private String Invoice;

	// 交易ID
	@Column(name = "tradeId")
	private Long tradeId;

	// 操作类型
	/*
	 * 1 退款， 2 撤销 ,3 ,预授权，4.EDC 交易 5.DCC 交易 6.查询，7 DCC完成，8 EDC完成 9 系统错误
	 * 
	 */
	@Column(name = "operatorType")
	private String operatorType;

	// 操作状态
	/*
	 * 返回码
	 * 
	 */
	@Column(name = "operatorStatus")
	private String operatorStatus;
	// 操作人
	@Column(name = "operatorMan")
	private String operatorMan;
	// 操作时间
	@Column(name = "operaterTime")
	private Date operaterTime;
	// 申请时间
	@Column(name = "applyTime")
	private Date applyTime;
	// 审核时间
	@Column(name = "auditTime")
	private Date auditTime;
	// 备注
	@Column(name = "remark")
	private String remark;

	@Column(name = "refundAmount", nullable = true)
	private Double refundAmount; // 交易金额（RMB）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getInvoice() {
		return Invoice;
	}

	public void setInvoice(String invoice) {
		Invoice = invoice;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getOperatorStatus() {
		return operatorStatus;
	}

	public void setOperatorStatus(String operatorStatus) {
		this.operatorStatus = operatorStatus;
	}

	public String getOperatorMan() {
		return operatorMan;
	}

	public void setOperatorMan(String operatorMan) {
		this.operatorMan = operatorMan;
	}

	public Date getOperaterTime() {
		return operaterTime;
	}

	public void setOperaterTime(Date operaterTime) {
		this.operaterTime = operaterTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

}
