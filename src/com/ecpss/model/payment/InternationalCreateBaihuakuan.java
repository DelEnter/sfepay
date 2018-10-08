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
@Table(name = "international_createbaihuakuan")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalCreateBaihuakuan implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_createbailhuakuan", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	
	private String channelName;
	private String moneykindname;

	@Column(name = "batchno",nullable = true)
	private Long batchno;


	@Column(name = "ceatetabletime",nullable = true)
	private Date ceatetabletime;

	@Column(name = "tradestarttime",nullable = true)
	private Date tradestarttime;

	@Column(name = "tradeendtime",nullable = true)
	private Date tradeendtime;

	@Column(name = "tradenumber",nullable = true)
	private Long tradenumber;

	@Column(name = "trademoney",nullable = true)
	private Double trademoney=0d;



	@Column(name = "bailexchangerate",nullable = true)
	private Double bailexchangerate=0d;

	@Column(name = "partbailrefund",nullable = true)
	private Double partbailrefund= 0d;
	
	@Column(name = "refundmentBailMoney",nullable = true)
	private Double refundmentBailMoney=0d;
	
	@Column(name = "bailmoney",nullable = true)
	private Double bailmoney=0d;

	@Column(name = "balanceexchangerate",nullable = true)
	private Double balanceexchangerate=0d;

	@Column(name = "balancemoneykind",nullable = true)
	private String balancemoneykind;

	@Column(name = "balancemoney",nullable = true)
	private Double balancemoney=0d;


	@Column(name = "huakuantime",nullable = true)
	private Date huakuantime;

	@Column(name = "huakuanbank",nullable = true)
	private String huakuanbank;
	
	@Column(name = "exchTime",nullable = true)
	private Date exchTime;//结算汇率设置时间
	
	private String showRate;//显示汇率
	
	public String getShowRate() {
		
		return this.getBalanceexchangerate().toString();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMoneykindname() {
		return moneykindname;
	}

	public void setMoneykindname(String moneykindname) {
		this.moneykindname = moneykindname;
	}

	public Long getBatchno() {
		return batchno;
	}

	public void setBatchno(Long batchno) {
		this.batchno = batchno;
	}

	public Date getCeatetabletime() {
		return ceatetabletime;
	}

	public void setCeatetabletime(Date ceatetabletime) {
		this.ceatetabletime = ceatetabletime;
	}

	public Date getTradestarttime() {
		return tradestarttime;
	}

	public void setTradestarttime(Date tradestarttime) {
		this.tradestarttime = tradestarttime;
	}

	public Date getTradeendtime() {
		return tradeendtime;
	}

	public void setTradeendtime(Date tradeendtime) {
		this.tradeendtime = tradeendtime;
	}

	public Long getTradenumber() {
		return tradenumber;
	}

	public void setTradenumber(Long tradenumber) {
		this.tradenumber = tradenumber;
	}

	public Double getTrademoney() {
		return trademoney;
	}

	public void setTrademoney(Double trademoney) {
		this.trademoney = trademoney;
	}

	public Double getBailexchangerate() {
		return bailexchangerate;
	}

	public void setBailexchangerate(Double bailexchangerate) {
		this.bailexchangerate = bailexchangerate;
	}

	public Double getPartbailrefund() {
		return partbailrefund;
	}

	public void setPartbailrefund(Double partbailrefund) {
		this.partbailrefund = partbailrefund;
	}

	public Double getRefundmentBailMoney() {
		return refundmentBailMoney;
	}

	public void setRefundmentBailMoney(Double refundmentBailMoney) {
		this.refundmentBailMoney = refundmentBailMoney;
	}

	public Double getBailmoney() {
		return bailmoney;
	}

	public void setBailmoney(Double bailmoney) {
		this.bailmoney = bailmoney;
	}

	public Double getBalanceexchangerate() {
		return balanceexchangerate;
	}

	public void setBalanceexchangerate(Double balanceexchangerate) {
		this.balanceexchangerate = balanceexchangerate;
	}

	public String getBalancemoneykind() {
		return balancemoneykind;
	}

	public void setBalancemoneykind(String balancemoneykind) {
		this.balancemoneykind = balancemoneykind;
	}

	public Double getBalancemoney() {
		return balancemoney;
	}

	public void setBalancemoney(Double balancemoney) {
		this.balancemoney = balancemoney;
	}

	public Date getHuakuantime() {
		return huakuantime;
	}

	public void setHuakuantime(Date huakuantime) {
		this.huakuantime = huakuantime;
	}

	public String getHuakuanbank() {
		return huakuanbank;
	}

	public void setHuakuanbank(String huakuanbank) {
		this.huakuanbank = huakuanbank;
	}

	public Date getExchTime() {
		return exchTime;
	}

	public void setExchTime(Date exchTime) {
		this.exchTime = exchTime;
	}
	

}
