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
@Table(name = "International_TemSettlement")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTemSettlement extends PriEntity implements
		Serializable {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_TemSettlement", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "batchNo")
	private String batchNo;
	@Column(name = "salestate")
	private String salestate;
	@Column(name = "settlementstate")
	private String settlementstate;
	@Column(name = "tradeid")
	private Long tradeid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSalestate() {
		return salestate;
	}

	public void setSalestate(String salestate) {
		this.salestate = salestate;
	}

	public String getSettlementstate() {
		return settlementstate;
	}

	public void setSettlementstate(String settlementstate) {
		this.settlementstate = settlementstate;
	}

	public Long getTradeid() {
		return tradeid;
	}

	public void setTradeid(Long tradeid) {
		this.tradeid = tradeid;
	}

}
