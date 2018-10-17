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
@Table(name = "INTERNATIONAL_MERCHANT")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalMerchant implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_merchant", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "merno", nullable = true, length = 6)
	private Long merno;//商户号
	
	@Column(name = "username", nullable = true, length = 20)
	private String username;//登录名
	
	@Column(name = "password", nullable = true, length = 50)
	private String password;//密码
	
	@Column(name = "md5key", nullable = true, length = 20)
	private String md5key;//md5k
	
	@Column(name = "mername", nullable = true, length = 20)
	private String mername;//商户名
	
	@Column(name = "certificateno", nullable = true, length = 20)
	private String certificateno;//身份证号
	
	@Column(name = "meradress", nullable = true, length = 100)
	private String meradress;//商户地址
	
	@Column(name = "merphone", nullable = true, length = 20)
	private String merphone;//商户电话
	
	@Column(name = "merfax", nullable = true, length = 20)
	private String merfax;//商户传真
	
	@Column(name = "mermobile", nullable = true, length = 15)
	private String mermobile;//商户
	
	@Column(name = "meremail", nullable = true, length = 50)
	private String meremail;//商户邮箱
	
	@Column(name = "merqq", nullable = true, length = 15)
	private String merqq;//商户QQ
	
	@Column(name = "mermsn", nullable = true, length = 20)
	private String mermsn;//商户msn
	
	@Column(name = "website", nullable = true, length = 100)
	private String website;	//商户网址
	
	@Column(name = "mertype", nullable = true, length = 2)
	private Long mertype;//账户类型
	
	@Column(name = "bank", nullable = true, length = 20)
	private String bank;//开户银行
	
	@Column(name = "accountname", nullable = true, length = 20)
	private String accountname;//开户名
	
	@Column(name = "cardno", nullable = true, length = 20)
	private String cardno;//开户账号
	
	@Column(name = "businesstype", nullable = true, length = 2)
	private Long businesstype;//账户类型
	
	@Column(name = "businessyears", nullable = true, length = 3)
	private Long businessyears;//行业年限
	
	@Column(name = "gatheringway", nullable = true, length = 100)
	private String gatheringway;//以前的收款方式

	@Column(name = "linkman", nullable = true, length = 20)
	private String linkman;//联系人
	
	@Column(name = "linkmanmobile", nullable = true, length = 15)
	private String linkmanmobile;//联系手机
	
	@Column(name = "linkmanadress", nullable = true, length = 100)
	private String linkmanadress;//联系地址
	
	@Column(name = "linkmanphone", nullable = true, length = 15)
	private String linkmanphone;//联系人手机号
	
	@Column(name = "linkmanfax", nullable = true, length = 15)
	private String linkmanfax;//联系人传真
	
	@Column(name = "linkmanemail", nullable = true, length = 20)
	private String linkmanemail;//联系人邮箱
	
	@Column(name = "linkmanqq", nullable = true, length = 20)
	private String linkmanqq;//联系人QQ
	
	@Column(name = "linkmanmsn", nullable = true, length = 20)
	private String linkmanmsn;//联系人msn
	
	@Column(name = "linkmancertificateno", nullable = true, length = 20)
	private String linkmancertificateno;//
	
	@Column(name = "salesman", nullable = true, length = 20)
	private String salesman;//业务员
	
	@Column(name = "mertypename", nullable = true, length = 20)
	private String mertypename;//商户类型
	
	@Column(name = "businesstypename", nullable = true, length = 20)
	private String businesstypename;//行业类型
	
	//0 未开通，1开通
	@Column(name = "isopen", nullable = true, length = 20)
	private String isopen;	
	/* 占位                 状态                                     意义
	 * 1                 是否发送跟踪单号邮件                   0 发送邮件，1 不发送邮件
	 * 2                是否发送汇贾广告                        0不发送  1 发送
	 * 3                发送客服联系方式                        0发送    1 不发送
	 * 4              支付时是否显示汇率                        0显示    1不显示
	 * 5              成功后是否发生邮件给商户                   0 发送，1 不发送
	 * 6             拒付发生后是否发送邮件给商户                0发送  1不发送
	 * 7              实时通道是否待处理                        0 默认  1 直接失败
	 */
	@Column(name = "statutes", nullable = true, length = 20)
	private String statutes;		
	
	@Column(name = "errorCount")
	private Long errorCount;
	
	@Column(name = "loginTime")
	private Date loginTime;    //上次登陆时间
	
	@Column(name = "modifyPwdTime")
	private Date modifyPwdTime;    //最近一次修改密码时间   3个月不修改密码提醒修改密码
	
	@Column(name = "monthTradeMoney")
	private Double monthTradeMoney;       //商户月交易限额
	
	@Column(name = "processModifyDate")
	private Date processModifyDate;       //商户最近一次修改待处理状态日期

	@Column(name = "processModifyCount")
	private Long processModifyCount;       //商户最近一次修改待处理状态次数
	
	//新加字段
	
	@Column(name = "openTime")			//商户开通时间
	private Date openTime;
	
	@Column(name = "annualFee")			//商户年费
	private Double annualFee;
	
	@Column(name = "remark")			//备注
	private String remark;
		
	@Column(name = "expmerchantno", nullable = true, length = 10)
	private Long expmerchantno;								//物流商户号
	
	@Column(name = "expuername", nullable = true, length = 20)
	private String expuername;								    //用户名
	
	@Column(name = "exppassword", nullable = true, length = 500)
	private String exppassword;									//密码
	
	@Column(name = "expopenstatus", nullable = true, length = 5)
	private String expopenstatus;                               //是否开通合作物流
	
	@Column(name = "discountfee", nullable = true, length = 10)
	private String discountfee;                                 //折扣率
	
	@Column(name = "exppay", nullable = true, length = 10)
	private String exppay;										//是否申请物流担保
	
	@Column(name = "exppaystatus", nullable = true, length = 10)
	private String exppaystatus;									////是否开通物流担保
	
	@Column(name = "assuretime", nullable = true, length = 10)
	private Long assuretime;									//担保期
	
	@Column(name = "uploadFileName", nullable = true, length = 500)
	private String uploadFileName;	//身份证照片路径
	
	@Column(name = "Dishonor")//拒付手续费
	private Double Dishonor;
	
	public Double getDishonor() {
		return Dishonor;
	}
	public void setDishonor(Double dishonor) {
		Dishonor = dishonor;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public Long getExpmerchantno() {
		return expmerchantno;
	}
	public void setExpmerchantno(Long expmerchantno) {
		this.expmerchantno = expmerchantno;
	}
	public String getExpuername() {
		return expuername;
	}
	public void setExpuername(String expuername) {
		this.expuername = expuername;
	}
	public String getExppassword() {
		return exppassword;
	}
	public void setExppassword(String exppassword) {
		this.exppassword = exppassword;
	}
	public String getExpopenstatus() {
		return expopenstatus;
	}
	public void setExpopenstatus(String expopenstatus) {
		this.expopenstatus = expopenstatus;
	}
	public String getDiscountfee() {
		return discountfee;
	}
	public void setDiscountfee(String discountfee) {
		this.discountfee = discountfee;
	}
	public Long getMerno() {
		return merno;
	}
	public void setMerno(Long merno) {
		this.merno = merno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMername() {
		return mername;
	}
	public void setMername(String mername) {
		this.mername = mername;
	}
	public String getCertificateno() {
		return certificateno;
	}
	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno;
	}
	public String getMeradress() {
		return meradress;
	}
	public void setMeradress(String meradress) {
		this.meradress = meradress;
	}
	public String getMerphone() {
		return merphone;
	}
	public void setMerphone(String merphone) {
		this.merphone = merphone;
	}
	public String getMerfax() {
		return merfax;
	}
	public void setMerfax(String merfax) {
		this.merfax = merfax;
	}
	public String getMermobile() {
		return mermobile;
	}
	public void setMermobile(String mermobile) {
		this.mermobile = mermobile;
	}
	public String getMeremail() {
		return meremail;
	}
	public void setMeremail(String meremail) {
		this.meremail = meremail;
	}
	public String getMerqq() {
		return merqq;
	}
	public void setMerqq(String merqq) {
		this.merqq = merqq;
	}
	public String getMermsn() {
		return mermsn;
	}
	public void setMermsn(String mermsn) {
		this.mermsn = mermsn;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkmanmobile() {
		return linkmanmobile;
	}
	public void setLinkmanmobile(String linkmanmobile) {
		this.linkmanmobile = linkmanmobile;
	}
	public Long getMertype() {
		return mertype;
	}
	public void setMertype(Long mertype) {
		this.mertype = mertype;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public Long getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(Long businesstype) {
		this.businesstype = businesstype;
	}
	public Long getBusinessyears() {
		return businessyears;
	}
	public void setBusinessyears(Long businessyears) {
		this.businessyears = businessyears;
	}
	public String getGatheringway() {
		return gatheringway;
	}
	public void setGatheringway(String gatheringway) {
		this.gatheringway = gatheringway;
	}
	public String getMd5key() {
		return md5key;
	}
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
	public String getLinkmanadress() {
		return linkmanadress;
	}
	public void setLinkmanadress(String linkmanadress) {
		this.linkmanadress = linkmanadress;
	}
	public String getLinkmanphone() {
		return linkmanphone;
	}
	public void setLinkmanphone(String linkmanphone) {
		this.linkmanphone = linkmanphone;
	}
	public String getLinkmanfax() {
		return linkmanfax;
	}
	public void setLinkmanfax(String linkmanfax) {
		this.linkmanfax = linkmanfax;
	}
	public String getLinkmanemail() {
		return linkmanemail;
	}
	public void setLinkmanemail(String linkmanemail) {
		this.linkmanemail = linkmanemail;
	}
	public String getLinkmanqq() {
		return linkmanqq;
	}
	public void setLinkmanqq(String linkmanqq) {
		this.linkmanqq = linkmanqq;
	}
	public String getLinkmanmsn() {
		return linkmanmsn;
	}
	public void setLinkmanmsn(String linkmanmsn) {
		this.linkmanmsn = linkmanmsn;
	}
	public String getLinkmancertificateno() {
		return linkmancertificateno;
	}
	public void setLinkmancertificateno(String linkmancertificateno) {
		this.linkmancertificateno = linkmancertificateno;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getMertypename() {
		return mertypename;
	}
	public void setMertypename(String mertypename) {
		this.mertypename = mertypename;
	}
	public String getBusinesstypename() {
		return businesstypename;
	}
	public void setBusinesstypename(String businesstypename) {
		this.businesstypename = businesstypename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
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
	public String getStatutes() {
		return statutes;
	}
	public void setStatutes(String statutes) {
		this.statutes = statutes;
	}
	public Double getMonthTradeMoney() {
		return monthTradeMoney;
	}
	public void setMonthTradeMoney(Double monthTradeMoney) {
		this.monthTradeMoney = monthTradeMoney;
	}
	public Date getProcessModifyDate() {
		return processModifyDate;
	}
	public void setProcessModifyDate(Date processModifyDate) {
		this.processModifyDate = processModifyDate;
	}
	public Long getProcessModifyCount() {
		return processModifyCount;
	}
	public void setProcessModifyCount(Long processModifyCount) {
		this.processModifyCount = processModifyCount;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Double getAnnualFee() {
		return annualFee;
	}
	public void setAnnualFee(Double annualFee) {
		this.annualFee = annualFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExppay() {
		return exppay;
	}
	public void setExppay(String exppay) {
		this.exppay = exppay;
	}
	public String getExppaystatus() {
		return exppaystatus;
	}
	public void setExppaystatus(String exppaystatus) {
		this.exppaystatus = exppaystatus;
	}
	public Long getAssuretime() {
		return assuretime;
	}
	public void setAssuretime(Long assuretime) {
		this.assuretime = assuretime;
	}
	
}
