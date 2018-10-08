package com.ecpss.model.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "SettlementCycleTime")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class SettlementCycleTime {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_settlementCycleTime", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "cycleTime")	
	public Long cycleTime;
	
	@Column(name = "merchant")	
	public Long merchant;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCycleTime() {
		return cycleTime;
	}


	public void setCycleTime(Long cycleTime) {
		this.cycleTime = cycleTime;
	}


	public Long getMerchant() {
		return merchant;
	}


	public void setMerchant(Long merchant) {
		this.merchant = merchant;
	}
	
	
	
}
