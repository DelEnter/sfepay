package com.ecpss.model.cardtype;

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
@Table(name = "international_MerCreditCard")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMerCreditCard extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348643456793287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_mercreditcard", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "merChannelId",nullable = true)
	private Long merChannelId;     //商户通道id
	
	@Column(name = "creditCardId",nullable = true)
	private Long creditCardId;     //卡种id
	
	@Column(name = "onoff",nullable = true,length=2)
	private String onoff;     //是否打开   0:关闭  1:打开

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getMerChannelId() {
		return merChannelId;
	}

	public void setMerChannelId(Long merChannelId) {
		this.merChannelId = merChannelId;
	}
	
}