package com.ecpss.model.shop;

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
@Table(name = "International_TerminalManager")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTerminalManager extends PriEntity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13232332L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_terminalmanager", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "channelId", nullable = true)
	private Long channelId;   //通道ID
	
	@Column(name = "creditCardId", nullable = true)
	private Long creditCardId; //卡种id
	
	@Column(name = "terminalNo", nullable = true, length = 20)
	private String terminalNo; //终端号

	@Column(name = "andterminalNo", nullable = true)
	private String andterminalNo; //关联终端号
	
	@Column(name = "merchantNo", nullable = true, length = 20)
	private String merchantNo; //商户号

	@Column(name = "billingAddress", nullable = true, length = 50)
	private String billingAddress; //该终端下的账单地址
	
	@Column(name = "onoff", nullable = true, length = 20)
	private String onoff; //是否开通  1开通  0关闭

	@Column(name = "isuses", nullable = true, length = 2)
	private String isuses; //是否使用
	
	@Column(name = "tradeTimes")
	private Long tradeTimes; //交易笔数
	
	@Column(name = "isauto", nullable = true, length = 2)
	private String isauto; //是否自动处理交易
	@Column(name = "authcode")
	private String authcode; //授权码
	
	@Column(name = "hashcode", nullable = true, length = 2)
	private String hashcode; //HASH码	
	
	@Column(name = "netstate", nullable = true, length = 2)
	private String netstate; //网关类型
	
	@Column(name = "banktype", nullable = true, length = 2)
	private String banktype; //银行类型
	
	@Column(name = "bankbackremark")
	private String bankBackRemark; //银行返回备注（用于重跑交易控制）
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getIsuses() {
		return isuses;
	}

	public void setIsuses(String isuses) {
		this.isuses = isuses;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Long getTradeTimes() {
		return tradeTimes;
	}

	public void setTradeTimes(Long tradeTimes) {
		this.tradeTimes = tradeTimes;
	}

	public String getAndterminalNo() {
		return andterminalNo;
	}

	public void setAndterminalNo(String andterminalNo) {
		this.andterminalNo = andterminalNo;
	}

	public String getIsauto() {
		return isauto;
	}

	public void setIsauto(String isauto) {
		this.isauto = isauto;
	}

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getNetstate() {
		return netstate;
	}

	public void setNetstate(String netstate) {
		this.netstate = netstate;
	}

	public String getBanktype() {
		return banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}

	public String getBankBackRemark() {
		return bankBackRemark;
	}

	public void setBankBackRemark(String bankBackRemark) {
		this.bankBackRemark = bankBackRemark;
	}


	
}