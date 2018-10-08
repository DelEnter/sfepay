package com.ecpss.vo;

import java.util.Date;

public class ImportRefund {
	
	private String cardNo;         //¿¨ºÅ 
	private Double tradeAmountRMB;       //½»Ò×½ð¶î
	private Double refundAmountRMB;  //ÍË¿î½ð¶î 
	private String terminalNo;       //ÖÕ¶ËºÅ
	private String authNO;        //ÊÚÈ¨ºÅ
	
	
	private String orderNo;
	private String trackingNo;
	private String tradeState;
	private Date tradeDate;

	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Double getTradeAmountRMB() {
		return tradeAmountRMB;
	}
	public void setTradeAmountRMB(Double tradeAmountRMB) {
		this.tradeAmountRMB = tradeAmountRMB;
	}
	public Double getRefundAmountRMB() {
		return refundAmountRMB;
	}
	public void setRefundAmountRMB(Double refundAmountRMB) {
		this.refundAmountRMB = refundAmountRMB;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getAuthNO() {
		return authNO;
	}
	public void setAuthNO(String authNO) {
		this.authNO = authNO;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

}
