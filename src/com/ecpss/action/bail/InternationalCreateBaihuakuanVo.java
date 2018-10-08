package com.ecpss.action.bail;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

public class InternationalCreateBaihuakuanVo implements java.io.Serializable{
	private Long id;
	
	private String channelName;
	private String moneykindname;

	private Long batchno;


	private Date ceatetabletime;

	private Date tradestarttime;

	private Date tradeendtime;

	private Long tradenumber;

	private Double trademoney=0d;



	private Double bailexchangerate=0d;

	private Double partbailrefund= 0d;
	
	private Double refundmentBailMoney=0d;
	
	private Double bailmoney=0d;

	private Double balanceexchangerate=0d;

	private String balancemoneykind;

	private Double balancemoney=0d;


	private Date huakuantime;

	private String huakuanbank;
	
	private Date exchTime;//结算汇率设置时间
	
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
	 * @return the tradenumber
	 */
	public Long getTradenumber() {
		return tradenumber;
	}

	/**
	 * @param tradenumber the tradenumber to set
	 */
	public void setTradenumber(Long tradenumber) {
		this.tradenumber = tradenumber;
	}

	/**
	 * @return the trademoney
	 */
	public Double getTrademoney() {
		return trademoney;
	}

	/**
	 * @param trademoney the trademoney to set
	 */
	public void setTrademoney(Double trademoney) {
		this.trademoney = trademoney;
	}


	/**
	 * @return the bailexchangerate
	 */
	public Double getBailexchangerate() {
		return bailexchangerate;
	}

	/**
	 * @param bailexchangerate the bailexchangerate to set
	 */
	public void setBailexchangerate(Double bailexchangerate) {
		this.bailexchangerate = bailexchangerate;
	}

	/**
	 * @return the partbailrefund
	 */
	public Double getPartbailrefund() {
		return partbailrefund*this.getBailexchangerate();
	}

	/**
	 * @param partbailrefund the partbailrefund to set
	 */
	public void setPartbailrefund(Double partbailrefund) {
		this.partbailrefund = partbailrefund;
	}

	/**
	 * @return the refundmentBailMoney
	 */
	public Double getRefundmentBailMoney() {
		return refundmentBailMoney;
	}

	/**
	 * @param refundmentBailMoney the refundmentBailMoney to set
	 */
	public void setRefundmentBailMoney(Double refundmentBailMoney) {
		this.refundmentBailMoney = refundmentBailMoney;
	}

	/**
	 * @return the bailmoney
	 */
	public Double getBailmoney() {
		return this.getTrademoney()*this.getBailexchangerate();
	}

	/**
	 * @param bailmoney the bailmoney to set
	 */
	public void setBailmoney(Double bailmoney) {
		this.bailmoney = bailmoney;
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

	/**
	 * @return the balancemoneykind
	 */
	public String getBalancemoneykind() {
		return balancemoneykind;
	}

	/**
	 * @param balancemoneykind the balancemoneykind to set
	 */
	public void setBalancemoneykind(String balancemoneykind) {
		this.balancemoneykind = balancemoneykind;
	}

	/**
	 * @return the balancemoney
	 */
	public Double getBalancemoney() {
		return   (this.getBailmoney()-this.getPartbailrefund())*this.getBalanceexchangerate();
	}

	/**
	 * @param balancemoney the balancemoney to set
	 */
	public void setBalancemoney(Double balancemoney) {
		this.balancemoney = balancemoney;
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
	 * @return the exchTime
	 */
	public Date getExchTime() {
		return exchTime;
	}

	/**
	 * @param exchTime the exchTime to set
	 */
	public void setExchTime(Date exchTime) {
		this.exchTime = exchTime;
	}

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the moneykindname
	 */
	public String getMoneykindname() {
		return moneykindname;
	}

	/**
	 * @param moneykindname the moneykindname to set
	 */
	public void setMoneykindname(String moneykindname) {
		this.moneykindname = moneykindname;
	}
}
