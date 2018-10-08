package com.ecpss.vo;


public class ImportRefundHistory  {
	private String cardNo;     //交易id

	private String tradeAmount;     //退款状态
	
	private String refundAmount;     //退款金额

	private String tradeTime;     //交易日期 
	
	private String terminalNo;     //终端
	
	private String authorizationNo;     //授权号
	
	private String checkInman;     //登记人
	
	private String phone;     //联系电话

	private String reason;     //审核人

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getAuthorizationNo() {
		return authorizationNo;
	}

	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	public String getCheckInman() {
		return checkInman;
	}

	public void setCheckInman(String checkInman) {
		this.checkInman = checkInman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
}
