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
@Table(name = "International_Terminalmerchant")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMerchantTerminal extends PriEntity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13233323232332L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_terminalmerchant", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "terminalId", nullable = true, length = 20)
	private Long terminalId; //终端id
	
	@Column(name = "merchantId", nullable = true, length = 20)
	private Long merchantId; //商户id
	
	@Column(name = "channelId", nullable = true, length = 20)
	private Long channelId; //通道id
	
	@Column(name = "isopen", nullable = true, length = 2)
	private String isopen; //是否开通

	public String getIsopen() {
		return isopen;
	}


	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getTerminalId() {
		return terminalId;
	}


	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}


	public Long getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}


	public Long getChannelId() {
		return channelId;
	}


	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	
		
}