package com.ecpss.vo;

public class ImportChargeBack {
	private String ref;
	private String productNo;
	private String cardNo;
	private String tradeDate;
	private Double tradeAmount;
	private String authorizationNo;
	private String chargeBackRemark;
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getProductNo() {
		return productNo;
	}
	public String getAuthorizationNo() {
		return authorizationNo;
	}
	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getChargeBackRemark() {
		return chargeBackRemark;
	}
	public void setChargeBackRemark(String chargeBackRemark) {
		this.chargeBackRemark = chargeBackRemark;
	}
	
	
}
