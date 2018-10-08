package com.ecpss.action.tradestatistics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;

public class InfoNumber extends BaseAction {
	private String ordNo;
	private List foreList;
	private String  hql;
	private InternationalTradeinfo interr;
	public String selectNumber(){
		return "success";
	}
	public String infoNumber(){
		if(StringUtils.isNotBlank(ordNo)){
			hql="from InternationalTradeinfo t where t.orderNo='"+ordNo.trim()+"'";
			interr=(InternationalTradeinfo) commonService.uniqueResult(hql);
		}
		return "success";
	}
	public String updateinfoNumber(){
		InternationalTradeinfo tm=(InternationalTradeinfo)commonService.load(InternationalTradeinfo.class,interr.getId());
		tm.setVIPAuthorizationNo(interr.getVIPAuthorizationNo());
		tm.setBatchNo(interr.getBatchNo());
		tm.setTradeState(interr.getTradeState());
		tm.setIsTrackNo(interr.getIsTrackNo());
		tm.setTradeAmount(interr.getTradeAmount());
		tm.setRmbAmount(interr.getRmbAmount());
		tm.setRemark(interr.getRemark());
		this.commonService.update(tm);
		return "success";
	}
	
	
	public List getForeList() {
		return foreList;
	}
	public void setForeList(List foreList) {
		this.foreList = foreList;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public InternationalTradeinfo getInterr() {
		return interr;
	}
	public void setInterr(InternationalTradeinfo interr) {
		this.interr = interr;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	

}
