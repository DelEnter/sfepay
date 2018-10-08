package com.ecpss.model.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "merchantoperaresource")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class OperaResource {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_merchantoperaresource", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "resources")
	private Long resources;
	@Column(name = "shopOpera")
	private Long shopOpera;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getResources() {
		return resources;
	}
	public void setResources(Long resources) {
		this.resources = resources;
	}
	public Long getShopOpera() {
		return shopOpera;
	}
	public void setShopOpera(Long shopOpera) {
		this.shopOpera = shopOpera;
	}

}
