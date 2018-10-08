package com.ecpss.action.risk;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.risk.InternationalBacklist;
import com.ecpss.model.risk.InternationalHighRisklist;
import com.ecpss.model.risk.InternationalSensitiveInfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.web.PageInfo;
import com.ecpss.web.tag.Page;

public class RiskTradeMonitorAction extends BaseAction {
	
	private PageInfo info = new PageInfo();
	private String startDate;
	private String endDate;
	private String queryType="1";
	private String failNum="2";
	private String cardNo;
	private String detailType;
	private String msg;
	private String noMerchant;
	private String webtype;
	private List webTypeList;
	//进入分析
	public String tradeMonitor(){		
		webTypeList = getWebSiteTypesList();
		String hql="";
		String totleCount="select count(*) from (";
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -7); // 得到前一周
		String lastWeek = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		String termHql="";
		if(StringUtils.isNotBlank(startDate)){
			termHql=termHql+" and ti.tradeTime>=to_date('"+startDate+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(StringUtils.isNotBlank(endDate)){
			termHql=termHql+" and ti.tradeTime<=to_date('"+endDate+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(StringUtils.isNotBlank(webtype)){
			termHql=termHql+" and web.remark='"+webtype+"'";			
		}
		if(StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
			termHql=termHql+" and ti.tradeTime>=to_date('"+lastWeek+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		}
		if("1".equals(queryType)){
			hql="select ci.cardno,count(ci.cardno),count(CASE WHEN substr(ti.tradeState,1,1) in('0','3') then 1 ELSE NULL END ),(CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END ) dif,max(ti.tradeTime) tradeTime "
					+ " from International_CardholdersInfo ci,International_Tradeinfo ti,(select distinct web.tradewebsite "
                    + " from International_IsAuditWeb web) newweb where ci.tradeId=ti.id and newweb.tradeWebsite=ti.tradeUrl"+termHql;
			hql=hql+ "group by ci.cardno having count(CASE WHEN substr(ti.tradeState,1,1) in('0') then 1 ELSE NULL END )>"+failNum;
		}else if("2".equals(queryType)){
			hql="select ci.CARDNO6,count(ci.CARDNO6),count(CASE WHEN substr(ti.tradeState,1,1) in('0','3') then 1 ELSE NULL END ),(CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END ) dif,max(ti.tradeTime) tradeTime "
					+ " from International_CardholdersInfo ci,International_Tradeinfo ti,(select distinct web.tradewebsite "
                    + " from International_IsAuditWeb web) newweb where ci.tradeId=ti.id and newweb.tradeWebsite=ti.tradeUrl"+termHql;
			hql=hql+ "group by ci.CARDNO6 having count(CASE WHEN substr(ti.tradeState,1,1) in('0') then 1 ELSE NULL END )>"+failNum;
		}else if("3".equals(queryType)){
			hql="select ci.CARDNO9,count(ci.CARDNO9),count(CASE WHEN substr(ti.tradeState,1,1) in('0','3') then 1 ELSE NULL END ),(CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END ) dif,max(ti.tradeTime) tradeTime "
					+ " from International_CardholdersInfo ci,International_Tradeinfo ti,(select distinct web.tradewebsite "
                    + " from International_IsAuditWeb web) newweb where ci.tradeId=ti.id and newweb.tradeWebsite=ti.tradeUrl"+termHql;
			hql=hql+ "group by ci.CARDNO9 having count(CASE WHEN substr(ti.tradeState,1,1) in('0') then 1 ELSE NULL END )>"+failNum;
		}else if("4".equals(queryType)){
			hql="select ci.email,count(ci.email),count(CASE WHEN substr(ti.tradeState,1,1) in('0','3') then 1 ELSE NULL END ),(CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END ) dif,max(ti.tradeTime) tradeTime "
					+ " from International_CardholdersInfo ci,International_Tradeinfo ti,(select distinct web.tradewebsite "
                    + " from International_IsAuditWeb web) newweb where ci.tradeId=ti.id and newweb.tradeWebsite=ti.tradeUrl"+termHql;
			hql=hql+ "group by ci.email having count(CASE WHEN substr(ti.tradeState,1,1) in('0') then 1 ELSE NULL END )>"+failNum;
		}else if("5".equals(queryType)){
			hql="select ci.ip,count(ci.ip),count(CASE WHEN substr(ti.tradeState,1,1) in('0','3') then 1 ELSE NULL END ),(CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END ) dif,max(ti.tradeTime) tradeTime "
					+ " from International_CardholdersInfo ci,International_Tradeinfo ti,(select distinct web.tradewebsite "
                    + " from International_IsAuditWeb web) newweb where ci.tradeId=ti.id and newweb.tradeWebsite=ti.tradeUrl"+termHql;
			hql=hql+ "group by ci.ip having count(CASE WHEN substr(ti.tradeState,1,1) in('0') then 1 ELSE NULL END )>"+failNum;
		}
		if("1".equals(noMerchant)){
			hql=hql+" and (CASE WHEN count(distinct(ti.merchantid))>1 then 1 ELSE NULL END )='1' ";
		}
		hql=hql+" order by tradeTime desc ";
		String listinfo=") t1 where ROWNUM<="+(info.getCurPage())*info.getPageSize()+") t2 where t2.rn>"+(info.getCurPage()-1)*info.getPageSize();
		List list=commonService.getByList("select * from(select t1.*,rownum rn from("+hql+listinfo);
		List listCount=this.commonService.getByList(totleCount+hql+")");
		int totle=0;
		if(listCount!=null&&listCount.size()>0){
			totle=Integer.parseInt(listCount.get(0).toString());
   	 		}
		info.setTotalCount(totle);
	    info.setResult(list);
		return SUCCESS;
	}
	//查看明细
	public String viewDetails(){
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -7); // 得到前一周
		String lastWeek = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		String hql="select ci,ti from InternationalCardholdersInfo ci,InternationalTradeinfo ti where ci.tradeId=ti.id ";
		if("1".equals(detailType)){
			hql=hql+ " and ci.cardNo='"+cardNo+"'";
		}
		if("2".equals(detailType)){
			hql=hql+ " and ci.cardNo6='"+cardNo+"'";
		}
		if("3".equals(detailType)){
			hql=hql+ " and ci.cardNo9='"+cardNo+"'";
		}
		if("4".equals(detailType)){
			hql=hql+ " and ci.email='"+cardNo+"'";
		}
		if("5".equals(detailType)){
			hql=hql+ " and ci.ip='"+cardNo+"'";
		}
		if(StringUtils.isNotBlank(startDate)){
			hql=hql+" and ti.tradeTime>=to_date('"+startDate+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(StringUtils.isNotBlank(endDate)){
			hql=hql+" and ti.tradeTime<=to_date('"+endDate+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
			hql=hql+" and ti.tradeTime>=to_date('"+lastWeek+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		}
		hql=hql+"order by ti.tradeTime desc";
		info=commonService.listQueryResultByHql(hql, info);
		return SUCCESS;
	}
	public String addBackList(){
		InternationalBacklist bl=new InternationalBacklist();
		if("1".equals(detailType)||"2".equals(detailType)||"3".equals(detailType)){
			List<InternationalBacklist> list = this.commonService.list("from InternationalBacklist t where t.cardno='"+ cardNo + "' ");
			if(list!=null&&list.size()>0){
				msg="添加项已存在！";
				return tradeMonitor();
			}else{
				bl.setCardno(cardNo);
				commonService.save(bl);
			}
		}
		if("4".equals(detailType)){
			List<InternationalBacklist> list = this.commonService.list("from InternationalBacklist t where lower(t.email)='"
						+ cardNo.trim().toLowerCase() + "' ");
			if(list!=null&&list.size()>0){
				msg="添加项已存在！";
				return tradeMonitor();
			}else{
				bl.setEmail(cardNo);
				commonService.save(bl);
			}
		}
		if("5".equals(detailType)){
			List<InternationalBacklist> list = this.commonService.list("from InternationalBacklist t where lower(t.ip)='"
						+cardNo+ "' ");
			if(list!=null&&list.size()>0){
				msg="添加项已存在！";
				return tradeMonitor();
			}else{
				bl.setIp(cardNo);
				commonService.save(bl);
			}
		}
		msg="添加成功！";
		return tradeMonitor();
	}	
	
	public List getWebSiteTypesList(){
		String hql="select distinct(t.webSiteType) from InternationalWebchannels t where t.webSiteType is not null order by t.webSiteType asc";
		List list=commonService.list(hql);
		return list;
	}
	
	public String getWebtype() {
		return webtype;
	}
	public void setWebtype(String webtype) {
		this.webtype = webtype;
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
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
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getFailNum() {
		return failNum;
	}
	public void setFailNum(String failNum) {
		this.failNum = failNum;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNoMerchant() {
		return noMerchant;
	}
	public void setNoMerchant(String noMerchant) {
		this.noMerchant = noMerchant;
	}
	public List getWebTypeList() {
		return webTypeList;
	}
	public void setWebTypeList(List webTypeList) {
		this.webTypeList = webTypeList;
	}
	
}
