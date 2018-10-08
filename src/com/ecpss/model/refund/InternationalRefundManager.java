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

import com.ecpss.model.PriEntity;

@Entity
@Table(name = "international_refundmanager")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalRefundManager extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348641281193287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_refundmanager", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "tradeId",nullable = true)
	private Long tradeId;     //交易id
	
	@Column(name = "refundNo", nullable = true, length = 50)
	private String refundNo; // 退款流水订单号
	/**
	 * 退款状态
	 * 0   未申请
	 * 1   已申请
	 * 2   已提交
	 * 3   已审核
	 * 6   生成过批量退款
	 * 4   全额退款
	 * 5   部分退款
	 */
	@Column(name = "refundState",nullable = true,length=2)
	private String refundState;     //退款状态
	
	@Column(name = "refundAmount",nullable = true)
	private Double refundAmount;     //退款金额

	@Column(name = "refundRMBAmount",nullable = true)
	private Double refundRMBAmount;     //退款金额
	
	@Column(name = "applyDate",nullable = true)
	private Date applyDate;     //申请时间
	
	@Column(name = "auditingDate",nullable = true)
	private Date auditingDate;     //审核时间
	
	@Column(name = "refundDate",nullable = true)
	private Date refundDate;     //退款时间
	
	@Column(name = "batchNo",nullable = true,length=30)
	private String batchNo;     //退款批次号

	@Column(name = "auditingMan",nullable = true,length=20)
	private String auditingMan;     //审核人
	
	@Column(name = "remark",nullable = true)
	private String remark;     //备注

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

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getAuditingMan() {
		return auditingMan;
	}

	public void setAuditingMan(String auditingMan) {
		this.auditingMan = auditingMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Double getRefundRMBAmount() {
		return refundRMBAmount;
	}

	public void setRefundRMBAmount(Double refundRMBAmount) {
		this.refundRMBAmount = refundRMBAmount;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	
	
}
