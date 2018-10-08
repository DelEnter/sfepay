package com.ecpss.vo;

import java.util.Date;

public class ImportCheck {
	

	private String cardNo;
	private String amount;
	private Double tradeAmount;
	private String authorizationNo;
	private String terminalNo;
	
	private String orderNo;
	private Date tradeDate;
	
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getAuthorizationNo() {
		return authorizationNo;
	}
	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	
	
}
