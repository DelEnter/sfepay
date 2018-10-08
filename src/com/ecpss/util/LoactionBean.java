package com.ecpss.util;


public class LoactionBean {
	private Boolean reload = Boolean.TRUE;
	private String reloadFun="";
	private String reloadFunParameter="";
	private String location = "";
	private Boolean back=Boolean.FALSE;

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getReload() {
		return reload;
	}
	public void setReload(Boolean reload) {
		this.reload = reload;
	}
	public Boolean getBack() {
		return back;
	}
	public void setBack(Boolean back) {
		this.back = back;
	}
	public String getReloadFun() {
		return reloadFun;
	}
	public void setReloadFun(String reloadFun) {
		this.reloadFun = reloadFun;
	}
	public String getReloadFunParameter() {
		return reloadFunParameter;
	}
	public void setReloadFunParameter(String reloadFunParameter) {
		this.reloadFunParameter = reloadFunParameter;
	}



}
