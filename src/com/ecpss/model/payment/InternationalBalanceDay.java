package com.ecpss.model.payment;

import java.io.Serializable;

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
@Table(name = "international_balanceday")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalBalanceDay extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 634862332233287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_balanceday", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "merchantId",nullable = true,length=20)
	private Long merchantId;     //商户ID
	
	@Column(name = "balanceDay",nullable = true)
	private int balanceDay;     //结算日

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public int getBalanceDay() {
		return balanceDay;
	}

	public void setBalanceDay(int balanceDay) {
		this.balanceDay = balanceDay;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
