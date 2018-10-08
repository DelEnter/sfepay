package com.ecpss.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;

public class JsonDataAction extends BaseAction{
	private Object jsonData;
	
	
	/**
	 * 根据持卡人邮箱获取改持卡人交易信息
	 * @return
	 */
	public String getTradeNoByEmail(){
		String email = ((String[])ActionContext.getContext().getParameters().get("email"))[0];
		String tradeno = ((String[])ActionContext.getContext().getParameters().get("tradeno"))[0];
		System.out.println(email);
		System.out.println(tradeno);
		String hql = "select m.merno,ti.orderNo,ti.merchantOrderNo,c.email from InternationalTradeinfo ti," +
				"InternationalCardholdersInfo c," +
				"InternationalMerchant m " +
				"where c.tradeId=ti.id " +
				"and ti.merchantId=m.id " ;
		if(StringUtils.isNotBlank(email)){
			hql+=" and lower(c.email)='"+email.trim().toLowerCase()+"' ";
		}
		if(StringUtils.isNotBlank(tradeno)){
			hql+=" and ti.orderNo='"+tradeno.trim()+"' ";
		}
		List list = this.commonService.list(hql);
		if(list.size()>1){
			jsonData = ""+"|"+" "+"|"+" ";
		}else{
			Object o [] = (Object[]) list.get(0);
			jsonData = o[0].toString()+"|"+o[1].toString()+"|"+o[2].toString()+"|"+o[3].toString();
		}
		return SUCCESS;
	}
	
	
	public Object getJsonData() {
		return jsonData;
	}

	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}
	

	

}
