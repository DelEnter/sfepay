package com.ecpss.action.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ecpss.action.BaseAction;
import com.ecpss.action.risk.WhiteListAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.payment.InternationalURLChannel;
import com.ecpss.model.risk.InternationalWhitelist;
import com.ecpss.web.PageInfo;
import com.opensymphony.xwork2.ActionContext;

public class WebUrlChannelAction extends BaseAction {
	Logger logger = Logger.getLogger(WebUrlChannelAction.class.getName());
	private PageInfo info = new PageInfo();
	private List list = new ArrayList();
	private List channelList;
	private InternationalURLChannel urlChannel;
	private String msg;
	private String riskIds;
	public String getUrlChannelList(){
		String hql = "from InternationalMerchant m  where m.merno>'3600' order by m.merno asc";
		list = commonService.list(hql);
		String hql2 = "select channelName from InternationalChannels";
		channelList= this.commonService.list(hql2);
		StringBuffer hql3=new StringBuffer("select t.id,m.merno,t.webUrl,t.channelName,t.remark,t.lastDate,t.lastMan from InternationalURLChannel t,InternationalMerchant m where t.merchantId=m.id");
		hql3.append(" order by t.lastDate desc");
		info=commonService.listQueryResultByHql(hql3.toString(), info);
				
		return SUCCESS;
	}
	public String addUrlChannel(){
		if(StringUtils.isNotBlank(urlChannel.getWebUrl())){
			String hql="from InternationalURLChannel t where t.webUrl like '%"+urlChannel.getWebUrl().replace("www.","")+"'";
			List<InternationalURLChannel> li=commonService.list(hql);
			if(li.size()>0){
				for(int i=0;i<li.size();i++){
					InternationalURLChannel uc=li.get(i);
					if(uc.getChannelName().equals(urlChannel.getChannelName())){
						msg="网址已存在!";
						return getUrlChannelList();
					}
				}
			}
			urlChannel.setLastDate(new Date());
			urlChannel.setLastMan(getUserBean().getUserName());
			commonService.save(urlChannel);
		}
		return getUrlChannelList();
	}
	public String delUrlChannel(){
		if(StringUtils.isNotBlank(riskIds)){
			String[] riskId=riskIds.split(",");
			for(int i=0;i<riskId.length;i++){
				InternationalURLChannel uc=(InternationalURLChannel) commonService.load(InternationalURLChannel.class, new Long(riskId[i]));
				logger.info(getUserBean().getUserName()+"对"+uc.getWebUrl()+"进行删除操作！");
				commonService.delete(uc);
			}
		}
		return getUrlChannelList();
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public void setUrlChannel(InternationalURLChannel urlChannel) {
		this.urlChannel = urlChannel;
	}
	public InternationalURLChannel getUrlChannel() {
		return urlChannel;
	}
	public List getChannelList() {
		return channelList;
	}

	public void setChannelList(List channelList) {
		this.channelList = channelList;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public String getRiskIds() {
		return riskIds;
	}
	public void setRiskIds(String riskIds) {
		this.riskIds = riskIds;
	}

}
