package com.ecpss.vo;

import java.io.Serializable;

public class ImportTrackingOrder implements Serializable{
	private static final long serialVersionUID = -1001111120215L;
	
	private String orderNo;
	private String trackingType;
	private String trackingNo;
	
	public String getTrackingType() {
		return trackingType;
	}
	public void setTrackingType(String trackingType) {
		this.trackingType = trackingType;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
