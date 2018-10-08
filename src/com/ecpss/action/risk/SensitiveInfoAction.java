package com.ecpss.action.risk;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalSensitiveInfo;
import com.ecpss.web.PageInfo;
import com.ecpss.web.tag.Page;

public class SensitiveInfoAction extends BaseAction {
	
	private PageInfo info = new PageInfo();
	private InternationalSensitiveInfo senInfo;
	private String qbillNo;
	private String qemail;
	private String qtradeUrl;
	private String qproducts;
	private String startDate;
	private String endDate;
	private String senId;
	private String ip;
	private String qip;
	public String sensitive(){
		String hql="from InternationalSensitiveInfo where 1=1 ";
		if(StringUtils.isNotBlank(qbillNo)){
			hql=hql+" and billNo like '%"+qbillNo+"%'";
		}
		if(StringUtils.isNotBlank(qemail)){
			hql=hql+" and email like '%"+qemail+"%'";
		}
		if(StringUtils.isNotBlank(qtradeUrl)){
			hql=hql+" and tradeUrl like '%"+qtradeUrl+"%'";
		}
		if(StringUtils.isNotBlank(qproducts)){
			hql=hql+" and products like '%"+qproducts+"%'";
		}
		if(StringUtils.isNotBlank(startDate)){
			hql=hql+" and createTime >=to_date('"+startDate+" 00:00:00"+"','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtils.isNotBlank(endDate)){
			hql=hql+" and createTime <=to_date('"+endDate+" 23:59:59"+"','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtils.isNotBlank(qip)){
			String[] addIp=qip.split("\\.");
			for(int i=0;i<addIp.length;i++){
				if(i==0){
					hql=hql+" and ip1='"+addIp[i].toString()+"'";
				}
				if(i==1){
					hql=hql+" and ip2='"+addIp[i].toString()+"'";
				}
				if(i==2){
					hql=hql+" and ip3='"+addIp[i].toString()+"'";
				}
				if(i==3){
					hql=hql+" and ip4='"+addIp[i].toString()+"'";
				}
			}
		}
		hql=hql+" order by createTime desc ";
		info=commonService.listQueryResultByHql(hql, info);
		return SUCCESS;
	}
	public String addSensitive(){
			senInfo.setCreateTime(new Date());
			senInfo.setLastMan(getUserBean().getUserName());
			if(StringUtils.isNotBlank(ip)){
				String[] addIp=ip.split("\\.");
				for(int i=0;i<addIp.length;i++){
					if(i==0){
						senInfo.setIp1(addIp[i]);
					}
					if(i==1){
						senInfo.setIp2(addIp[i]);
					}
					if(i==2){
						senInfo.setIp3(addIp[i]);
					}
					if(i==3){
						senInfo.setIp4(addIp[i]);
					}
				}
			}
			commonService.save(senInfo);
		return sensitive();
	}
	public String delSensitive(){
		if(StringUtils.isNotBlank(senId)){
			commonService.delete(InternationalSensitiveInfo.class, new Long(senId));
		}
		return sensitive();
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	
	public InternationalSensitiveInfo getSenInfo() {
		return senInfo;
	}
	public void setSenInfo(InternationalSensitiveInfo senInfo) {
		this.senInfo = senInfo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSenId() {
		return senId;
	}
	public void setSenId(String senId) {
		this.senId = senId;
	}
	public String getQbillNo() {
		return qbillNo;
	}
	public void setQbillNo(String qbillNo) {
		this.qbillNo = qbillNo;
	}
	public String getQemail() {
		return qemail;
	}
	public void setQemail(String qemail) {
		this.qemail = qemail;
	}
	public String getQtradeUrl() {
		return qtradeUrl;
	}
	public void setQtradeUrl(String qtradeUrl) {
		this.qtradeUrl = qtradeUrl;
	}
	public String getQproducts() {
		return qproducts;
	}
	public void setQproducts(String qproducts) {
		this.qproducts = qproducts;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getQip() {
		return qip;
	}
	public void setQip(String qip) {
		this.qip = qip;
	}

}
