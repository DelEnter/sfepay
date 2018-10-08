package com.ecpss.action.risk;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalHighRisklist;
import com.ecpss.model.risk.InternationalSensitiveInfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.web.PageInfo;
import com.ecpss.web.tag.Page;

public class VisaHighRiskAction extends BaseAction {
	Logger logger = Logger.getLogger(VisaHighRiskAction.class.getName());
	private PageInfo info = new PageInfo();
	private InternationalHighRisklist highRiskInfo;
	private String email;
	private String ip;
	private String cardNo;
	private String merNo;
	private String phone;
	private String zipCode;
	private String riskIds;
	private String startDate;
	private String endDate;
	private String delType;
	private String tradUrl;
	private String isWeb;
	private String isQweb;
	private String isFail;
	private String riskOrderId;
	private String userName;
	public String visaRisk(){
		String hql="select t,rownum from InternationalHighRisklist t where 1=1  ";
		if(StringUtils.isNotBlank(isFail)){
			hql=hql+" and t.isWeb ='"+isFail+"'";
		}
		if(StringUtils.isNotBlank(email)){
			hql=hql+" and t.email like '%"+email.trim()+"%'";
		}
		if(StringUtils.isNotBlank(ip)){
			hql=hql+" and t.ip like '%"+ip.trim()+"%'";
		}
		if(StringUtils.isNotBlank(cardNo)){
			hql=hql+" and t.cardno like '%"+cardNo.trim()+"%'";
		}
		if(StringUtils.isNotBlank(merNo)){
			hql=hql+" and t.merId like '%"+merNo.trim()+"%'";
		}
		if(StringUtils.isNotBlank(tradUrl)){
			hql=hql+" and t.tradeUrl like '%"+tradUrl.trim()+"%'";
		}
		if(StringUtils.isNotBlank(phone)){
			hql=hql+" and t.phone like '%"+phone.trim()+"%'";
		}
		if(StringUtils.isNotBlank(zipCode)){
			hql=hql+" and t.zipCode like '%"+zipCode.trim()+"%'";
		}
		if(StringUtils.isNotBlank(userName)){
			hql=hql+" and t.operator like '%"+userName.trim()+"%'";
		}
		if(StringUtils.isNotBlank(startDate)){
			hql=hql+" and t.createDate >=to_date('"+startDate+" 00:00:00"+"','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtils.isNotBlank(endDate)){
			hql=hql+" and t.createDate <=to_date('"+endDate+" 23:59:59"+"','yyyy-MM-dd hh24:mi:ss')";
		}
		hql=hql+" order by t.createDate desc,rownum asc ";
		info=commonService.listQueryResultByHql(hql, info);
		return SUCCESS;
	}
	public String addVisaRisk(){
		highRiskInfo.setOperator(getUserBean().getUserName());
		highRiskInfo.setCreateDate(new Date());
		highRiskInfo.setIsWeb(isWeb);
		//isweb为1时，交易直接取消
		if("1".equals(isWeb)){
			//isQweb为1时，交易不关联网站
			highRiskInfo.setIsQWeb("1");
		}else{
			highRiskInfo.setIsQWeb(isQweb);
		}
		commonService.save(highRiskInfo);
		return visaRisk();
	}
	public String updateVisaRisk(){
		InternationalHighRisklist rl=(InternationalHighRisklist) commonService.load(InternationalHighRisklist.class, new Long(riskOrderId));
		rl.setIsWeb("1");
		rl.setIsQWeb("1");
		commonService.update(rl);
		return visaRisk();
	}
	public String delVisaRisk(){
		if(StringUtils.isNotBlank(riskIds)){
			String[] riskId=riskIds.split(",");
			for(int i=0;i<riskId.length;i++){
				if("0".equals(delType)){
					InternationalHighRisklist rl=(InternationalHighRisklist) commonService.load(InternationalHighRisklist.class, new Long(riskId[i]));
					String logUrl=rl.getTradeUrl();
					rl.setTradeUrl("");
					commonService.update(rl);
					logger.info(getUserBean().getUserName()+"对网址"+logUrl+"进行删除操作！");
				}else{
				InternationalHighRisklist rl=(InternationalHighRisklist) commonService.load(InternationalHighRisklist.class, new Long(riskId[i]));
				logger.info(getUserBean().getUserName()+"对整行"+rl.getCardno()+"进行删除操作！");
				commonService.delete(InternationalHighRisklist.class, new Long(riskId[i]));
				}
			}
		}
		return visaRisk();
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	
	public InternationalHighRisklist getHighRiskInfo() {
		return highRiskInfo;
	}
	public void setHighRiskInfo(InternationalHighRisklist highRiskInfo) {
		this.highRiskInfo = highRiskInfo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getRiskIds() {
		return riskIds;
	}
	public void setRiskIds(String riskIds) {
		this.riskIds = riskIds;
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
	public String getDelType() {
		return delType;
	}
	public void setDelType(String delType) {
		this.delType = delType;
	}
	public String getTradUrl() {
		return tradUrl;
	}
	public void setTradUrl(String tradUrl) {
		this.tradUrl = tradUrl;
	}
	public String getIsWeb() {
		return isWeb;
	}
	public void setIsWeb(String isWeb) {
		this.isWeb = isWeb;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getRiskOrderId() {
		return riskOrderId;
	}
	public void setRiskOrderId(String riskOrderId) {
		this.riskOrderId = riskOrderId;
	}
	public String getIsQweb() {
		return isQweb;
	}
	public void setIsQweb(String isQweb) {
		this.isQweb = isQweb;
	}
	public String getIsFail() {
		return isFail;
	}
	public void setIsFail(String isFail) {
		this.isFail = isFail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
