package com.ecpss.model.shop;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "INTERNATIONAL_EXPRESS")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalExpress implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_merchant", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 19)
	private Long id;
	
	@Column(name = "tradeid", nullable = true, length = 19)
	private Long tradeid;
	
	@Column(name = "expressno", nullable = true, length = 50)
	private String expressno;
	
	@Column(name = "expresstype", nullable = true, length = 20)
	private String expresstype;
	
	@Column(name = "expressstatus", nullable = true, length = 20)
	private String expressstaus;
		
	@Column(name = "expressamout", nullable = true, length = 20)
	private Double expressamout;
	
	@Column(name = "remark", nullable = true, length = 20)
	private String remark;
	
	@Column(name = "operatertime", nullable = true, length = 20)
	private Date operatertime;
	
	@Column(name = "operatorman", nullable = true, length = 20)
	private String operatorman;
	
	@Column(name = "createtime", nullable = true, length = 20)
	private Date createtime;
	
	@Column(name = "discountamout", nullable = true, length = 20)
	private Double discountamout;
	
	@Column(name = "field1", nullable = true, length = 20)
	private String field1;
	
	@Column(name = "field2", nullable = true, length = 20)
	private String field2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTradeid() {
		return tradeid;
	}

	public void setTradeid(Long tradeid) {
		this.tradeid = tradeid;
	}

	public String getExpressno() {
		return expressno;
	}

	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	public String getExpresstype() {
		return expresstype;
	}

	public void setExpresstype(String expresstype) {
		this.expresstype = expresstype;
	}

	public String getExpressstaus() {
		return expressstaus;
	}

	public void setExpressstaus(String expressstaus) {
		this.expressstaus = expressstaus;
	}

	public Double getExpressamout() {
		return expressamout;
	}

	public void setExpressamout(Double expressamout) {
		this.expressamout = expressamout;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOperatertime() {
		return operatertime;
	}

	public void setOperatertime(Date operatertime) {
		this.operatertime = operatertime;
	}

	public String getOperatorman() {
		return operatorman;
	}

	public void setOperatorman(String operatorman) {
		this.operatorman = operatorman;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Double getDiscountamout() {
		return discountamout;
	}

	public void setDiscountamout(Double discountamout) {
		this.discountamout = discountamout;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
}
