package com.ecpss.icbc;

import java.util.HashMap;

import com.ecpss.action.BaseAction;

public class ICBCPayResponseAction extends BaseAction{
	
	
	private String merVAR;
	private String notifyData;
	private String signMsg;
	private HashMap<String,String> payResultHashMap = new HashMap<String,String>();
	/**
	 * 接受返回代码
	 * @return
	 */
	public String payResponse(){
		IcbcUtils icbcUtils = new IcbcUtils();
		Dom4jForXMLUtils dom4j = new Dom4jForXMLUtils();

		String xmlnotifyData = icbcUtils.signdec(notifyData);
		payResultHashMap = dom4j.parse(xmlnotifyData);
		return SUCCESS;
	}

	public String getMerVAR() {
		return merVAR;
	}

	public void setMerVAR(String merVAR) {
		this.merVAR = merVAR;
	}

	public String getNotifyData() {
		return notifyData;
	}

	public void setNotifyData(String notifyData) {
		this.notifyData = notifyData;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public HashMap<String, String> getPayResultHashMap() {
		return payResultHashMap;
	}

	public void setPayResultHashMap(HashMap<String, String> payResultHashMap) {
		this.payResultHashMap = payResultHashMap;
	}
}
