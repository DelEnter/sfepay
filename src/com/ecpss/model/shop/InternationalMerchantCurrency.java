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
@Table(name = "INTERNATIONAL_MERCHANTCURRENCY")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMerchantCurrency implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_merchantcurrency", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long Id;
	@Column(name = "moneyKindNo", nullable = true, length = 2)
	private Long moneyKindNo;
	@Column(name = "merchanId", nullable = true, length = 10)
	private Long merchanId;
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}
	/**
	 * @return the moneyKindNo
	 */
	public Long getMoneyKindNo() {
		return moneyKindNo;
	}
	/**
	 * @param moneyKindNo the moneyKindNo to set
	 */
	public void setMoneyKindNo(Long moneyKindNo) {
		this.moneyKindNo = moneyKindNo;
	}
	/**
	 * @return the merchanId
	 */
	public Long getMerchanId() {
		return merchanId;
	}
	/**
	 * @param merchanId the merchanId to set
	 */
	public void setMerchanId(Long merchanId) {
		this.merchanId = merchanId;
	}
}
