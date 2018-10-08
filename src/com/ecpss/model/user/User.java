package com.ecpss.model.user;

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
@Table(name = "employee")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class User {
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobilePhone")
	private String mobilePhone;

	@Column(name = "errorCount")
	private Long errorCount;
	
	@Column(name = "loginTime")
	private Date loginTime;    //登陆时间
	
	@Column(name = "modifyPwdTime")
	private Date modifyPwdTime;    //最近一次修改密码时间   3个月不修改密码提醒修改密码
	@Column(name = "passid")
	private String passid;
	
	public String getPassid() {
		return passid;
	}
	public void setPassid(String passid) {
		this.passid = passid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Long getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getModifyPwdTime() {
		return modifyPwdTime;
	}
	public void setModifyPwdTime(Date modifyPwdTime) {
		this.modifyPwdTime = modifyPwdTime;
	}
}
