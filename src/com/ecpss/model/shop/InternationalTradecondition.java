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
@Table(name = "InternationalTradecondition")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTradecondition {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_InternationalTradecondition", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "merchantId")
	private Long merchantId;
	@Column(name = "itemno")
	private Long itemno;
	@Column(name = "tradenumber")
	private Long tradenumber;
	@Column(name = "cycle")
	private Long cycle;
	@Column(name = "remark")
	private String remark;
	@Column(name = "itemName")
	private String itemName;

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

	public Long getItemno() {
		return itemno;
	}

	public void setItemno(Long itemno) {
		this.itemno = itemno;
	}

	public Long getTradenumber() {
		return tradenumber;
	}

	public void setTradenumber(Long tradenumber) {
		this.tradenumber = tradenumber;
	}

	public Long getCycle() {
		return cycle;
	}

	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
