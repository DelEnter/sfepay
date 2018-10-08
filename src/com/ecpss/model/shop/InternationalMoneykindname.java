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
@Table(name = "INTERNATIONAL_MONEYKINDNAME")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMoneykindname extends PriEntity implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_moneykindname", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long Id;
	
	@Column(name = "moneykindno", nullable = true, length = 2)
	private Long moneykindno;
	
	@Column(name = "moneykindname", nullable = true, length = 100)
	private String moneykindname;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getMoneykindno() {
		return moneykindno;
	}

	public void setMoneykindno(Long moneykindno) {
		this.moneykindno = moneykindno;
	}

	public String getMoneykindname() {
		return moneykindname;
	}

	public void setMoneykindname(String moneykindname) {
		this.moneykindname = moneykindname;
	}
}
