package com.ecpss.action.risk;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalWhitelist;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.web.PageInfo;

public class WhiteListAction extends BaseAction {
	Logger logger = Logger.getLogger(WhiteListAction.class.getName());
	private PageInfo info = new PageInfo();
	private InternationalWhitelist whitelist;
	private String qwhiteName;
	private String qwhiteType;
	private String qriskType;
	private String qremark;
	private String riskIds;
	public String getWhiteList(){
		StringBuffer hql=new StringBuffer(" from InternationalWhitelist t where 1=1");
		if(StringUtils.isNotBlank(qwhiteName)){
			hql.append(" and t.whitename like '%"+qwhiteName+"%'");
		}
		if(StringUtils.isNotBlank(qwhiteType)){
			hql.append(" and t.whitetype='"+qwhiteType+"'");
		}
		if(StringUtils.isNotBlank(qriskType)){
			hql.append(" and t.risktype='"+qriskType+"'");
		}
		if(StringUtils.isNotBlank(qremark)){
			hql.append(" and t.remark like '%"+qremark+"%'");
		}
		hql.append(" order by t.lastDate desc");
		info=commonService.listQueryResultByHql(hql.toString(), info);
		return SUCCESS;
	}
	public String addWhiteList(){
		whitelist.setLastDate(new Date());
		whitelist.setLastMan(getUserBean().getUserName());
		commonService.save(whitelist);
		return getWhiteList();
	}
	public String delWhiteList(){
		if(StringUtils.isNotBlank(riskIds)){
			String[] riskId=riskIds.split(",");
			for(int i=0;i<riskId.length;i++){
				InternationalWhitelist ri=(InternationalWhitelist) commonService.load(InternationalWhitelist.class, new Long(riskId[i]));
				logger.info(getUserBean().getUserName()+"对"+ri.getWhitename()+"进行删除操作！");
				commonService.delete(ri);
			}
		}
		return getWhiteList();
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public InternationalWhitelist getWhitelist() {
		return whitelist;
	}
	public void setWhitelist(InternationalWhitelist whitelist) {
		this.whitelist = whitelist;
	}
	public String getQwhiteName() {
		return qwhiteName;
	}
	public void setQwhiteName(String qwhiteName) {
		this.qwhiteName = qwhiteName;
	}
	public String getQwhiteType() {
		return qwhiteType;
	}
	public void setQwhiteType(String qwhiteType) {
		this.qwhiteType = qwhiteType;
	}
	public String getQremark() {
		return qremark;
	}
	public void setQremark(String qremark) {
		this.qremark = qremark;
	}
	public String getRiskIds() {
		return riskIds;
	}
	public void setRiskIds(String riskIds) {
		this.riskIds = riskIds;
	}
	public String getQriskType() {
		return qriskType;
	}
	public void setQriskType(String qriskType) {
		this.qriskType = qriskType;
	}

}
