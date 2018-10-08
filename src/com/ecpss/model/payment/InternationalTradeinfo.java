package com.ecpss.model.payment;

import java.io.Serializable;
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
@Table(name = "INTERNATIONAL_TRADEINFO")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTradeinfo extends PriEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6348641281692587065L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_tradeinfo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;

	@Column(name = "orderNo", nullable = true, length = 50)
	private String orderNo; // Ecpss流水订单号

	@Column(name = "merchantOrderNo", nullable = true, length = 100)
	private String merchantOrderNo; // 商户流水订单号

	@Column(name = "merchantId", nullable = true)
	private Long merchantId; // 商户id

	@Column(name = "tradeTime")
	private Date tradeTime; // 交易时间

	@Column(name = "tradeAmount", nullable = true)
	private Double tradeAmount; // 交易金额（外币）

	@Column(name = "rmbAmount")
	private Double rmbAmount; // 人民币金额

	/** 名称      占位符     状态
	 * 交易状态	 1	       0 失败  1 成功  2 待处理   3 取消  4 待确认 5未返回 
     * 退款	     2	       0 未退款  1已退款  2部分退款
	 * 拒付	     3	       0 未拒付   1已拒付 
	 * 冻结	     4	       0 未冻结   1 已冻结  2 解冻  3审核解冻
	 * 勾兑	     5	       0 未勾兑  1 已勾兑
	 * 申请	     6	       0未申请   1已申请
	 * 审核	     7	       0 未审核   1已审核 2 问题单
	 * 是否划款	 8	       0 未划款   1已划款
	 * 保证金审核	 9	       0 未审核    1 已审核
	 * 保证金划款	 10	       0 未划款    1 已划款
	 * 勾兑状态   11         0正常      1 金额不对  2 批次号不对 3 授权号不对
	 * 返回值状态  12        0 已返回   1 未返回  
	 * 自动处理交易 13       0未处理   1已处理  
	 * 是否需要自动预授权14   0不需要   1需要   2申请  3审核 4 完成 5 审核不通过 6 过期被取消 7 已经处理过
	 * 待处理状态下是否需要自动处理（预授权）15    3.已经处理过（授权完成）
	 */
	@Column(name = "tradeState")
	private String tradeState; // 交易状态组合字段,比如: 012121

	@Column(name = "tradeChannel", nullable = true)
	private Long tradeChannel; // 商户通道ID

	@Column(name = "tradeRate", nullable = true)
	private Long tradeRate; // 交易汇率	 

	@Column(name = "balanceRate", nullable = true)
	private Long balanceRate; // 结算汇率	
	
	
	
	@Column(name = "isPicture")    
	private String isPicture;//是否上传图片
	@Column(name = "isTrackNo") 	
	private String isTrackNo;//是否上传跟踪单号 
	@Column(name = "remark") 
	private String remark;//备注
	@Column(name = "gouduiTime") 	
	private Date gouduiTime;//勾兑时间
	@Column(name = "moneyType") 	
	private Long moneyType ;//币种类型
	@Column(name = "batchNo") 	
	private String batchNo;//批次号

	@Column(name = "matterDepict") 	
	private String matterDepict;//审核备注
	
	@Column(name = "auditingDate") 	
	private Date auditingDate;//审核时间
	
	@Column(name = "protestTime") 	
	private Date protestTime;//拒付时间
	@Column(name = "protestPerson") 	
	private String protestPerson ;//拒付操作人
	@Column(name = "balancetime") 	
	private Date balancetime;//结算时间
	@Column(name = "backCount") 	
	private  Double backCount;//退款金额
	@Column(name = "applyTime")
	private Date applyTime;   //申请划款时间

	@Column(name = "VIPTerminalNo",length=20)
	private String VIPTerminalNo;   //VIP通道处理终端号
	
	@Column(name = "VIPBatchNo",length=30)
	private String VIPBatchNo;   //VIP通道处理批次号

	@Column(name = "VIPAuthorizationNo",length=20)
	private String VIPAuthorizationNo;   //VIP授权号

	@Column(name = "VIPDisposePorson",length=20)
	private String VIPDisposePorson;   //VIP处理人

	@Column(name = "VIPDisposeDate",length=20)
	private Date VIPDisposeDate;   //VIP处理时间
	
	@Column(name = "DCCTradeCurrency",length=10)
	private String DCCTradeCurrency;
	
	@Column(name = "DCCTradeAmount")
	private Double DCCTradeAmount;
	
	/*@Column(name = "merTradeUrl",length=1000)
	private String merTradeUrl;
*/
	@Column(name = "tradeUrl",length=20)	
    private String tradeUrl;//交易地址
	
	@Column(name = "returnUrl",length=20)    
    private String returnUrl;//返回地址
	
	@Column(name = "ref_No",length=20)    
    private String ref_No;//返回地址	
	
	@Column(name = "pre_money")
	private Double pre_money;//完成外币金额
	
	@Column(name = "beginmoney")
	private Double beginmoney;//原始外币金额	
	
	@Column(name = "pre_money_rmb")
	private Double pre_money_rmb;//原始RMB金额	
	
	@Column(name = "boc_rrn")
	private String boc_rrn;       // 中行TC通道 RRN 码
	
	@Column(name = "boc_invoice")
	private String boc_invoice;       // 中行TC通道 凭证号
	
	@Column(name = "boc_date")
	private String boc_date;       // 中行TC通道 交易日期
	
	@Column(name = "boc_time")
	private String boc_time;       // 中行TC通道 交易时间
	
	@Column(name = "token_id") 	
	private String token_id;//boc token
	
	@Column(name = "channelFee") 	
	private Double channelFee;//通道附加费
	
	@Column(name = "expordersta") 	
	private  String expordersta;//下单状态

	@Column(name = "expordermess") 	
	private  String expordermess;//下单返回信息
	
	@Column(name = "csid") 	
	private  String csid;//csid风控
	
	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getTradeUrl() {
		return tradeUrl;            
	}

	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Double getRmbAmount() {
		return rmbAmount;
	}

	public void setRmbAmount(Double rmbAmount) {
		this.rmbAmount = rmbAmount;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}


	public Long getTradeChannel() {
		return tradeChannel;
	}

	public void setTradeChannel(Long tradeChannel) {
		this.tradeChannel = tradeChannel;
	}

	public Date getGouduiTime() {
		return gouduiTime;
	}

	public void setGouduiTime(Date gouduiTime) {
		this.gouduiTime = gouduiTime;
	}

	public Long getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Long moneyType) {
		this.moneyType = moneyType;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getMatterDepict() {
		return matterDepict;
	}

	public void setMatterDepict(String matterDepict) {
		this.matterDepict = matterDepict;
	}

	public Date getProtestTime() {
		return protestTime;
	}

	public void setProtestTime(Date protestTime) {
		this.protestTime = protestTime;
	}

	public String getProtestPerson() {
		return protestPerson;
	}

	public void setProtestPerson(String protestPerson) {
		this.protestPerson = protestPerson;
	}

	public Date getBalancetime() {
		return balancetime;
	}

	public void setBalancetime(Date balancetime) {
		this.balancetime = balancetime;
	}

	public Double getBackCount() {
		return backCount;
	}

	public void setBackCount(Double backCount) {
		this.backCount = backCount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getTradeRate() {
		return tradeRate;
	}

	public void setTradeRate(Long tradeRate) {
		this.tradeRate = tradeRate;
	}

	public String getIsPicture() {
		return isPicture;
	}

	public void setIsPicture(String isPicture) {
		this.isPicture = isPicture;
	}

	public String getIsTrackNo() {
		return isTrackNo;
	}

	public void setIsTrackNo(String isTrackNo) {
		this.isTrackNo = isTrackNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Long getBalanceRate() {
		return balanceRate;
	}

	public void setBalanceRate(Long balanceRate) {
		this.balanceRate = balanceRate;
	}

	public String getVIPBatchNo() {
		return VIPBatchNo;
	}

	public void setVIPBatchNo(String batchNo) {
		VIPBatchNo = batchNo;
	}

	public String getVIPAuthorizationNo() {
		return VIPAuthorizationNo;
	}

	public void setVIPAuthorizationNo(String authorizationNo) {
		VIPAuthorizationNo = authorizationNo;
	}


	public String getVIPDisposePorson() {
		return VIPDisposePorson;
	}

	public void setVIPDisposePorson(String disposePorson) {
		VIPDisposePorson = disposePorson;
	}

	public Date getVIPDisposeDate() {
		return VIPDisposeDate;
	}

	public void setVIPDisposeDate(Date disposeDate) {
		VIPDisposeDate = disposeDate;
	}

	/**
	 * @return the vIPTerminalNo
	 */
	public String getVIPTerminalNo() {
		return VIPTerminalNo;
	}

	/**
	 * @param terminalNo the vIPTerminalNo to set
	 */
	public void setVIPTerminalNo(String terminalNo) {
		VIPTerminalNo = terminalNo;
	}

	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}

	public String getDCCTradeCurrency() {
		return DCCTradeCurrency;
	}

	public void setDCCTradeCurrency(String tradeCurrency) {
		DCCTradeCurrency = tradeCurrency;
	}

	public Double getDCCTradeAmount() {
		return DCCTradeAmount;
	}

	public void setDCCTradeAmount(Double tradeAmount) {
		DCCTradeAmount = tradeAmount;
	}

	public String getRef_No() {
		return ref_No;
	}

	public void setRef_No(String ref_No) {
		this.ref_No = ref_No;
	}

	public Double getPre_money() {
		return pre_money;
	}

	public void setPre_money(Double pre_money) {
		this.pre_money = pre_money;
	}

	public Double getBeginmoney() {
		return beginmoney;
	}

	public void setBeginmoney(Double beginmoney) {
		this.beginmoney = beginmoney;
	}

	public Double getPre_money_rmb() {
		return pre_money_rmb;
	}

	public void setPre_money_rmb(Double pre_money_rmb) {
		this.pre_money_rmb = pre_money_rmb;
	}

	public String getBoc_rrn() {
		return boc_rrn;
	}

	public void setBoc_rrn(String boc_rrn) {
		this.boc_rrn = boc_rrn;
	}

	public String getBoc_invoice() {
		return boc_invoice;
	}

	public void setBoc_invoice(String boc_invoice) {
		this.boc_invoice = boc_invoice;
	}

	public String getBoc_date() {
		return boc_date;
	}

	public void setBoc_date(String boc_date) {
		this.boc_date = boc_date;
	}

	public String getBoc_time() {
		return boc_time;
	}

	public void setBoc_time(String boc_time) {
		this.boc_time = boc_time;
	}

	public Double getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(Double channelFee) {
		this.channelFee = channelFee;
	}

	public String getExpordersta() {
		return expordersta;
	}

	public void setExpordersta(String expordersta) {
		this.expordersta = expordersta;
	}

	public String getExpordermess() {
		return expordermess;
	}

	public void setExpordermess(String expordermess) {
		this.expordermess = expordermess;
	}




	/**
	 * @return the merTradeUrl
	 */
	/*public String getMerTradeUrl() {
		return merTradeUrl;
	}

	*//**
	 * @param merTradeUrl the merTradeUrl to set
	 *//*
	public void setMerTradeUrl(String merTradeUrl) {
		this.merTradeUrl = merTradeUrl;
	}
*/

}
