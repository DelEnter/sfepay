package com.ecpss.action.pay.tc;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class XMLSetMessage {
	private XMLGetMessage xmlget = new XMLGetMessage();
	
	
	public void reciveRes(String xmlstr){
		Dom4jForXMLUtils xml = new Dom4jForXMLUtils();
		HashMap<String,String> resp = xml.parse(xmlstr);
		if(StringUtils.isNotBlank(resp.get("txn_id"))){
			xmlget.setTxn_id(resp.get("txn_id"));
		}
		if(StringUtils.isNotBlank(resp.get("systrace"))){
			xmlget.setSystrace(resp.get("systrace"));
		}
		if(StringUtils.isNotBlank(resp.get("txn_date"))){
			xmlget.setTxn_date(resp.get("txn_date"));
		}
		if(StringUtils.isNotBlank(resp.get("txn_time"))){
			xmlget.setTxn_time(resp.get("txn_time"));
		}
		if(StringUtils.isNotBlank(resp.get("rrn"))){
			xmlget.setRrn(resp.get("rrn"));
		}
		if(StringUtils.isNotBlank(resp.get("invoice"))){
			xmlget.setInvoice(resp.get("invoice"));
		}
		if(StringUtils.isNotBlank(resp.get("pan"))){
			xmlget.setPan(resp.get("pan"));
		}
		if(StringUtils.isNotBlank(resp.get("txn_amt"))){
			xmlget.setTxn_amt(resp.get("txn_amt"));
		}
		if(StringUtils.isNotBlank(resp.get("respcode"))){
			xmlget.setRespcode(resp.get("respcode"));
		}
		if(StringUtils.isNotBlank(resp.get("auth_no"))){
			xmlget.setAuth_no(resp.get("auth_no"));
		}
		if(StringUtils.isNotBlank(resp.get("org_txnid"))){
			xmlget.setOrg_txnid(resp.get("org_txnid"));
		}
	}


	public XMLGetMessage getXmlget() {
		return xmlget;
	}


	public void setXmlget(XMLGetMessage xmlget) {
		this.xmlget = xmlget;
	}
}
