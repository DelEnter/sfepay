package com.ecpss.model.payment;

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
@Table(name = "international_bailhuakuan")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalBailhuakuan implements Serializable{

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_bailhuakuan", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	
	@Column(name = "merchantId",nullable = true)
	private Long merchantId;

	@Column(name = "batchno",nullable = true)
	private Long batchno;

	@Column(name = "bailmoney",nullable = true)
	private Double bailmoney;

	@Column(name = "balancemoney",nullable = true)
	private Double balancemoney;

	@Column(name = "ceatetabletime",nullable = true)
	private Date ceatetabletime;

	@Column(name = "tradestarttime",nullable = true)
	private Date tradestarttime;

	@Column(name = "tradeendtime",nullable = true)
	private Date tradeendtime;

	@Column(name = "huakuantime",nullable = true)
	private Date huakuantime;

	@Column(name = "huakuanbank",nullable = true)
	private String huakuanbank;

	@Column(name = "balanceexchangerate",nullable = true)
	private Double balanceexchangerate;
	
	@Column(name = "remark",nullable = true)
	private String remark;
	
	@Column(name = "disposeman")
	private String disposeman;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the merchantId
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the batchno
	 */
	public Long getBatchno() {
		return batchno;
	}

	/**
	 * @param batchno the batchno to set
	 */
	public void setBatchno(Long batchno) {
		this.batchno = batchno;
	}

	/**
	 * @return the bailmoney
	 */
	public Double getBailmoney() {
		return bailmoney;
	}

	/**
	 * @param bailmoney the bailmoney to set
	 */
	public void setBailmoney(Double bailmoney) {
		this.bailmoney = bailmoney;
	}

	/**
	 * @return the balancemoney
	 */
	public Double getBalancemoney() {
		return balancemoney;
	}

	/**
	 * @param balancemoney the balancemoney to set
	 */
	public void setBalancemoney(Double balancemoney) {
		this.balancemoney = balancemoney;
	}

	/**
	 * @return the ceatetabletime
	 */
	public Date getCeatetabletime() {
		return ceatetabletime;
	}

	/**
	 * @param ceatetabletime the ceatetabletime to set
	 */
	public void setCeatetabletime(Date ceatetabletime) {
		this.ceatetabletime = ceatetabletime;
	}

	/**
	 * @return the tradestarttime
	 */
	public Date getTradestarttime() {
		return tradestarttime;
	}

	/**
	 * @param tradestarttime the tradestarttime to set
	 */
	public void setTradestarttime(Date tradestarttime) {
		this.tradestarttime = tradestarttime;
	}

	/**
	 * @return the tradeendtime
	 */
	public Date getTradeendtime() {
		return tradeendtime;
	}

	/**
	 * @param tradeendtime the tradeendtime to set
	 */
	public void setTradeendtime(Date tradeendtime) {
		this.tradeendtime = tradeendtime;
	}

	/**
	 * @return the huakuantime
	 */
	public Date getHuakuantime() {
		return huakuantime;
	}

	/**
	 * @param huakuantime the huakuantime to set
	 */
	public void setHuakuantime(Date huakuantime) {
		this.huakuantime = huakuantime;
	}

	/**
	 * @return the huakuanbank
	 */
	public String getHuakuanbank() {
		return huakuanbank;
	}

	/**
	 * @param huakuanbank the huakuanbank to set
	 */
	public void setHuakuanbank(String huakuanbank) {
		this.huakuanbank = huakuanbank;
	}

	/**
	 * @return the balanceexchangerate
	 */
	public Double getBalanceexchangerate() {
		return balanceexchangerate;
	}

	/**
	 * @param balanceexchangerate the balanceexchangerate to set
	 */
	public void setBalanceexchangerate(Double balanceexchangerate) {
		this.balanceexchangerate = balanceexchangerate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDisposeman() {
		return disposeman;
	}

	public void setDisposeman(String disposeman) {
		this.disposeman = disposeman;
	}
}
