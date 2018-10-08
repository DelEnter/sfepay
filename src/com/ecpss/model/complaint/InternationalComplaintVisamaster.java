package com.ecpss.model.complaint;

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

/**
 * VisaMasterPay投诉管理
 * @author Administrator
 *
 */
@Entity
@Table(name = "INTERNATIONAL_VISAMASTER")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalComplaintVisamaster extends PriEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 100000000032233305L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_VISAMASTER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	
	@Column(name = "orderNo",nullable = true)
	private String orderNo;               //交易流水号
	@Column(name = "merchantOrderNo",nullable = true)
	private String merchantOrderNo;               //商户交易号
	@Column(name = "cmEmail",nullable = true)
	private String cmEmail;               //客人投诉使用邮件
	@Column(name = "content",nullable = true,length=5000)
	private String content;               //内容
	@Column(name = "processingResults",nullable = true)
	private String processingResults;     //处理结果:0,未处理,1,已处理
	@Column(name = "dates",nullable = true)
	private String dates;     //处理结果:0,未处理,1,已处理
	@Column(name = "amount",nullable = true)
	private String amount;     //处理结果:0,未处理,1,已处理
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
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public String getCmEmail() {
		return cmEmail;
	}
	public void setCmEmail(String cmEmail) {
		this.cmEmail = cmEmail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProcessingResults() {
		return processingResults;
	}
	public void setProcessingResults(String processingResults) {
		this.processingResults = processingResults;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
		
}