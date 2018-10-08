package com.ecpss.action.websitemanager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalWebchannels;
import com.opensymphony.xwork2.ActionContext;

public class WebsiteManagerAction extends BaseAction{

	private Long merno;
	private String tradeurl;
	private String returnurl;
	private String isblack;
	private Long [] ids;
	private String[] tradeurls;
	private String[] returnurls;
	
	private String newtradeWebsite;
	private String newWebsite;
	private Long urlcount=0L;
	
	private List websiteList;
	
	private String[] isblacks;
	private List webTypeList;
	private String webType;
	
	
	/**
	 * 我们后台商户网址管理
	 * @return
	 */
	public String towebsitemanager(){
		String webhql="select distinct(t.webSiteType) from InternationalWebchannels t where t.webSiteType is not null order by t.webSiteType asc";
		webTypeList=commonService.list(webhql);
		if(merno!=null || StringUtils.isNotBlank(tradeurl) || StringUtils.isNotBlank(returnurl))
		{
		 	String hql = "select wc,m.merno from InternationalWebchannels wc,InternationalMerchant m " +
					"where m.id=wc.merchanid";
			if(merno!=null){
				hql+=" and m.merno="+merno;
			}
			if(StringUtils.isNotBlank(tradeurl)){
				hql+=" and wc.tradeWebsite like '%"+tradeurl.trim()+"%'";
			}
			if(StringUtils.isNotBlank(returnurl)){
				hql+=" and wc.website like '%"+returnurl.trim()+"%'";
			}
			if(StringUtils.isNotBlank(isblack)){
				hql+=" and wc.isblack="+isblack.trim();
			}
			if(StringUtils.isNotBlank(webType)){
				hql+=" and wc.webSiteType like '%"+webType.trim()+"%'";
			}
			//商户后台交易网址个数统计
			hql+=" order by wc.executetime ";
			websiteList = this.commonService.list(hql);
			ActionContext.getContext().getSession().put("merchantno", merno);
			//统计正常和禁止交易的网址
			String hql1="select count(*) from InternationalWebchannels wc,InternationalMerchant m "+
			    "where m.id=wc.merchanid ";
			if(merno!=null){
				hql1+=" and m.merno="+merno;
			}
			if(StringUtils.isNotBlank(tradeurl)){
				hql1+=" and wc.tradeWebsite like '%"+tradeurl.trim()+"%'";
			}
			if(StringUtils.isNotBlank(returnurl)){
				hql1+=" and wc.website like '%"+returnurl.trim()+"%'";
			}
			if(StringUtils.isNotBlank(isblack)){
				hql1+=" and wc.isblack="+isblack.trim();
			}
			List list = this.commonService.list(hql1);
			if(list.size()>0){
				urlcount = (Long) this.commonService.list(hql1).get(0);
			}
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * 对网址进行操作
	 * @return
	 */
	//交易网址管理添加交易网址
	public String modifywebsite(){
		merno = (Long) ActionContext.getContext().getSession().get("merchantno");
		if((StringUtils.isNotBlank(newtradeWebsite) || StringUtils.isNotBlank(newWebsite)) && merno!=null){
			InternationalMerchant merchant = (InternationalMerchant) this.commonService.uniqueResult("select m from InternationalMerchant m where m.merno="+merno);
			InternationalWebchannels website = new InternationalWebchannels();
			website.setExecutetime(new Date());
			website.setOperator(getUserBean().getUserName());
			website.setTradeWebsite(newtradeWebsite);
			website.setWebsite(newWebsite);
			website.setIsblack("0");
			website.setMerchanid(merchant.getId());
			this.commonService.save(website);
		}
		if(ids!=null){
			for (int i=0;i<ids.length;i++) {
				InternationalWebchannels web = (InternationalWebchannels) this.commonService.load(InternationalWebchannels.class, ids[i]);
				if(StringUtils.isNotBlank(tradeurls[i]) || StringUtils.isNotBlank(returnurls[i])){
					if(!web.getWebsite().equals(returnurls[i])){
						web.setOperator(getUserBean().getUserName());
						web.setExecutetime(new Date());
					}
					web.setTradeWebsite(tradeurls[i]);
					web.setWebsite(returnurls[i]);
					//web.setIsblack(isblacks[i]);//暂时取消，获取不到数据error
					this.commonService.update(web);
				}else{
					this.commonService.delete(web);
				}
			}
		}
		return SUCCESS;
	}
	
	
	public Long getMerno() {
		return merno;
	}
	public void setMerno(Long merno) {
		this.merno = merno;
	}
	public String getTradeurl() {
		return tradeurl;
	}
	public void setTradeurl(String tradeurl) {
		this.tradeurl = tradeurl;
	}
	public String getReturnurl() {
		return returnurl;
	}
	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String[] getTradeurls() {
		return tradeurls;
	}
	public void setTradeurls(String[] tradeurls) {
		this.tradeurls = tradeurls;
	}
	public String[] getReturnurls() {
		return returnurls;
	}
	public void setReturnurls(String[] returnurls) {
		this.returnurls = returnurls;
	}
	public List getWebsiteList() {
		return websiteList;
	}
	public void setWebsiteList(List websiteList) {
		this.websiteList = websiteList;
	}


	public String getNewtradeWebsite() {
		return newtradeWebsite;
	}


	public void setNewtradeWebsite(String newtradeWebsite) {
		this.newtradeWebsite = newtradeWebsite;
	}


	public String getNewWebsite() {
		return newWebsite;
	}


	public void setNewWebsite(String newWebsite) {
		this.newWebsite = newWebsite;
	}


	public Long getUrlcount() {
		return urlcount;
	}


	public void setUrlcount(Long urlcount) {
		this.urlcount = urlcount;
	}


	public String[] getIsblacks() {
		return isblacks;
	}


	public void setIsblacks(String[] isblacks) {
		this.isblacks = isblacks;
	}
	public String getIsblack() {
		return isblack;
	}


	public void setIsblack(String isblack) {
		this.isblack = isblack;
	}


	public List getWebTypeList() {
		return webTypeList;
	}


	public void setWebTypeList(List webTypeList) {
		this.webTypeList = webTypeList;
	}


	public String getWebType() {
		return webType;
	}


	public void setWebType(String webType) {
		this.webType = webType;
	}
	
	
}
