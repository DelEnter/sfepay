package com.ecpss.model.risk;

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

//ºÚ¿¨¿â
@Entity
@Table(name = "International_SensitiveInfo")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalSensitiveInfo extends PriEntity implements java.io.Serializable {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_SensitiveInfo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "email")
	private String email;
	@Column(name = "billNo")
	private String billNo;
	@Column(name = "tradeUrl")
	private String tradeUrl;
	@Column(name = "products")
	private String products;
	@Column(name = "ip1")
	private String ip1;
	@Column(name = "ip2")
	private String ip2;
	@Column(name = "ip3")
	private String ip3;
	@Column(name = "ip4")
	private String ip4;
	@Column(name = "createTime")
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getTradeUrl() {
		return tradeUrl;
	}
	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIp1() {
		return ip1;
	}
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	public String getIp2() {
		return ip2;
	}
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	public String getIp3() {
		return ip3;
	}
	public void setIp3(String ip3) {
		this.ip3 = ip3;
	}
	public String getIp4() {
		return ip4;
	}
	public void setIp4(String ip4) {
		this.ip4 = ip4;
	}
	
}
