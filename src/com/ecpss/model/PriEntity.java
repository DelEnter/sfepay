package com.ecpss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class PriEntity {

	//@Column(length = 20)
	@Column(name="lastDate", length=7) 
	private Date lastDate;   //�最后修改时间
	@Column(length = 20)
	private String lastMan; //�最后修改人
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getLastMan() {
		return lastMan;
	}
	public void setLastMan(String lastMan) {
		this.lastMan = lastMan;
	}

}
