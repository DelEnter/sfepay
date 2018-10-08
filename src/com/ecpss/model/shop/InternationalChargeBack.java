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

import com.ecpss.model.PriEntity;

@Entity
@Table(name = "international_chargeback")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalChargeBack extends PriEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_chargeback", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	
	@Column(name = "tradeId")
	private Long tradeId;      //交易ID
	
	@Column(name = "ref", nullable=true,length=30)
	private String ref;         //档号
	
	@Column(name = "productNo", nullable = true, length = 50)
	private String productNo;   //商品编号
	
	@Column(name = "cardNoBy", length = 20)
	private String cardNoBy;           //上传的卡号
	
	@Column(name = "tradeDateBy", length = 20)
	private String tradeDateBy;        //上传的交易日期
	
	@Column(name = "tradeAmountBy", length = 20)
	private Double tradeAmountBy;      //上传的交易金额
	
	@Column(name = "authorizationNoBy", length = 20)
	private String authorizationNoBy;     //上传的授权号

	@Column(name = "remark", length = 20)
	private String remark;     //拒付理由

	@Column(name = "complaintsFileName", length = 100)
	private String complaintsFileName;     //上传拒付申诉文件名

	@Column(name = "complaintsFileroute", length = 200)
	private String complaintsFileroute;     //上传拒付申诉文件路径
	
	@Column(name = "complaintsFilesize")
	private Long complaintsFilesize;     //上传拒付申诉文件大小
	
	@Column(name = "complaintsFiledate", length = 20)
	private Date complaintsFiledate;     //上传拒付申诉文件时间
	
	@Column(name = "importDate")
	private Date importDate;           //导入日期
	
	@Column(name="isChargeBack")
	private String isChargeBack;       //商户是否拒付     0 可以申诉   1 已申诉  2 已放弃申诉   4处理中
	
	@Column(name="upbatchno",length=50)
	private String upbatchno;  //上传批次号
	
	public String getIsChargeBack() {
		return isChargeBack;
	}

	public void setIsChargeBack(String isChargeBack) {
		this.isChargeBack = isChargeBack;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCardNoBy() {
		return cardNoBy;
	}

	public void setCardNoBy(String cardNoBy) {
		this.cardNoBy = cardNoBy;
	}

	public String getTradeDateBy() {
		return tradeDateBy;
	}

	public void setTradeDateBy(String tradeDateBy) {
		this.tradeDateBy = tradeDateBy;
	}


	public Double getTradeAmountBy() {
		return tradeAmountBy;
	}

	public void setTradeAmountBy(Double tradeAmountBy) {
		this.tradeAmountBy = tradeAmountBy;
	}

	public String getComplaintsFileName() {
		return complaintsFileName;
	}

	public void setComplaintsFileName(String complaintsFileName) {
		this.complaintsFileName = complaintsFileName;
	}

	public String getComplaintsFileroute() {
		return complaintsFileroute;
	}

	public void setComplaintsFileroute(String complaintsFileroute) {
		this.complaintsFileroute = complaintsFileroute;
	}

	public Long getComplaintsFilesize() {
		return complaintsFilesize;
	}

	public void setComplaintsFilesize(Long complaintsFilesize) {
		this.complaintsFilesize = complaintsFilesize;
	}

	public Date getComplaintsFiledate() {
		return complaintsFiledate;
	}

	public void setComplaintsFiledate(Date complaintsFiledate) {
		this.complaintsFiledate = complaintsFiledate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAuthorizationNoBy() {
		return authorizationNoBy;
	}

	public void setAuthorizationNoBy(String authorizationNoBy) {
		this.authorizationNoBy = authorizationNoBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpbatchno() {
		return upbatchno;
	}

	public void setUpbatchno(String upbatchno) {
		this.upbatchno = upbatchno;
	}
	
	
	
}