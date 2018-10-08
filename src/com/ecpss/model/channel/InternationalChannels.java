package com.ecpss.model.channel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import com.ecpss.model.PriEntity;

/**
 * @银行通道管理
 * @author yepeng
 * 
 */
@Entity
@Table(name = "international_channels")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalChannels extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348641281693287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_channel", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "channelName",nullable = true,length=50)
	private String channelName;     //通道名称
	
	@Column(name = "bankName",nullable = true,length=50)
	private String bankName;     //银行名称
	
	@Column(name = "bankMerchantId",nullable = true,length=50)
	private String bankMerchantId;     //在银行商户号
	
	@Column(name = "accessCode",nullable = true,length=50)
	private String accessCode;     //银行接入码
	
	@Column(name = "bankUrl",nullable = true,length=50)
	private String bankUrl;     //银行url地址
	
	@Column(name = "checkUrl",nullable = true,length=50)
	private String checkUrl;     //银行对账url地址
	
	@Column(name = "checkUserName",nullable = true,length=50)
	private String checkUserName;     //银行对账用户名
	
	@Column(name = "checkUserPwd",nullable = true,length=50)
	private String checkUserPwd;     //银行对账密码
	
	@Column(name = "md5",nullable = true,length=50)
	private String md5;     //加密md5
	
	@Column(name = "onoff",nullable = true,length=50)
	private String onoff;     //使用状态   0: 关闭  1:打开
	@Column(name = "executeTime")
	private Date executeTime;     //执行时间
	
	@Column(name = "operator",nullable = true,length=50)
	private String operator;     //操作人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankMerchantId() {
		return bankMerchantId;
	}

	public void setBankMerchantId(String bankMerchantId) {
		this.bankMerchantId = bankMerchantId;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getBankUrl() {
		return bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getCheckUserPwd() {
		return checkUserPwd;
	}

	public void setCheckUserPwd(String checkUserPwd) {
		this.checkUserPwd = checkUserPwd;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}


	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}


















