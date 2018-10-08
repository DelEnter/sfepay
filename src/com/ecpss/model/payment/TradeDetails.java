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

//交易明细
@Entity
@Table(name = "international_TradeDetails")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class TradeDetails {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_TradeDetails", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "batchno")
	private Long batchno;// 批次号
	@Column(name = "rorderno")
	private String rorderno;// 订单流水号
	@Column(name = "merchantorderno")
	private String merchantorderno;// 商户订单号
	@Column(name = "merchantno")
	private Long merchantno;// 商户号
	@Column(name = "tradetime")
	private Date tradetime;// 交易日期
	@Column(name = "ordercount")
	private Double ordercount;// 美元
	@Column(name = "rmbmoney")
	private Double rmbmoney;// 人民币
	@Column(name = "moneytype")
	private Long moneytype;// 交易币种
	@Column(name = "detail")
	private String detail;// 详情
	@Column(name = "remark")
	private String remark;// 备注
	@Column(name = "channels")
	private String channels;// 通道
	@Column(name = "ispicture")
	private String ispicture;// 是否传图
	@Column(name = "istrackno")
	private String istrackno;// 是否传号
	@Column(name = "tradeState")	
	private String tradeState;//状态
	@Column(name = "backAcount")	
	private Double backAcount;//退款金额	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBatchno() {
		return batchno;
	}

	public void setBatchno(Long batchno) {
		this.batchno = batchno;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public String getMerchantorderno() {
		return merchantorderno;
	}

	public void setMerchantorderno(String merchantorderno) {
		this.merchantorderno = merchantorderno;
	}

	public Long getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(Long merchantno) {
		this.merchantno = merchantno;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public Double getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Double ordercount) {
		this.ordercount = ordercount;
	}

	public Double getRmbmoney() {
		return rmbmoney;
	}

	public void setRmbmoney(Double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}

	public Long getMoneytype() {
		return moneytype;
	}

	public void setMoneytype(Long moneytype) {
		this.moneytype = moneytype;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getIspicture() {
		return ispicture;
	}

	public void setIspicture(String ispicture) {
		this.ispicture = ispicture;
	}

	public String getIstrackno() {
		return istrackno;
	}

	public void setIstrackno(String istrackno) {
		this.istrackno = istrackno;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public Double getBackAcount() {
		return backAcount;
	}

	public void setBackAcount(Double backAcount) {
		this.backAcount = backAcount;
	}


}
