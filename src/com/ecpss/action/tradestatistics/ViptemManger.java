package com.ecpss.action.tradestatistics;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.util.CalcuLate;

public class ViptemManger extends BaseAction {
	private List temManger;
	private String temNumber;
	private String vipterminalno;
	private String startDate;
	private String endDate;
	private CalcuLate caclulate = new CalcuLate();
	private String cbtype;
	private Long channelId;
	private String query="";
	private List<InternationalChannels> chennelList;
	public String viptemManger(){
		String chennelsql = "select c from InternationalChannels c";
		chennelList = this.commonService.list(chennelsql);
		
		
		String tmp = "";
		if(StringUtils.isNotBlank(vipterminalno)){
			tmp= "and t.vipterminalno like '"+vipterminalno.trim()+"%'";
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp += " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate+" 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(channelId!=null){
			tmp += " and c.id="+channelId+" ";
		}
		String tmp1 = "";
		if(StringUtils.isNotBlank(vipterminalno)){
			tmp1= "and t.vipterminalno like '"+vipterminalno.trim()+"%'";
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp1 += " and cb.importDate>=to_date('" + startDate
			+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp1 += " and cb.importDate<=to_date('" + endDate+" 23:59:59"
			+ "','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(channelId!=null){
			tmp1 += " and c.id="+channelId+" ";
		}
		StringBuffer sb = new StringBuffer();
		sb
		            .append("select aa.cc,nn.xx,aa.qq , aa.tt,nn.ee from( "
		            		+ "select count(1) cc ,sum(t.rmbamount) tt,t.vipterminalno qq " +
		            				"from international_tradeinfo t,international_merchantChannels mc,international_channels c  "
		            		+ "where t.tradeChannel=mc.id and mc.channelId=c.id and  substr(t.tradestate,1,1)=1  " 
		            		+ tmp);
		 sb.append(" group by t.vipterminalno)aa left join (  "
		            		+ "select count(1) xx,sum(t.rmbamount) ee,t.vipterminalno pp " +
		            				"from international_tradeinfo t,international_chargeback cb,international_merchantChannels mc,international_channels c   "
		            		+ "where t.tradeChannel=mc.id and mc.channelId=c.id and  substr(t.tradestate,3,1)=1  " +
		            				" and cb.tradeId=t.id ");
		if(StringUtils.isNotBlank(cbtype)){
			if(cbtype.equals("0")){
				sb.append(tmp);
			}else{
				sb.append(tmp1);
			}
		}else{
			sb.append(tmp1);
		}
		 
		 sb.append(" group by t.vipterminalno ) nn " +
		            				"on aa.qq=nn.pp ");
		 if(StringUtils.isNotBlank(this.query) && this.query.equals("0")){
				query = "1";
			}else{
				temManger=commonService.getByList(sb.toString()); 
				
			}
		return "success";
	}
	
	
	
	
	public List getTemManger() {
		return temManger;
	}
	public void setTemManger(List temManger) {
		this.temManger = temManger;
	}
	public String getTemNumber() {
		return temNumber;
	}
	public void setTemNumber(String temNumber) {
		this.temNumber = temNumber;
	}
	public String getVipterminalno() {
		return vipterminalno;
	}
	public void setVipterminalno(String vipterminalno) {
		this.vipterminalno = vipterminalno;
	}
	public CalcuLate getCaclulate() {
		return caclulate;
	}
	public void setCaclulate(CalcuLate caclulate) {
		this.caclulate = caclulate;
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
	public String getCbtype() {
		return cbtype;
	}
	public void setCbtype(String cbtype) {
		this.cbtype = cbtype;
	}




	public String getQuery() {
		return query;
	}




	public void setQuery(String query) {
		this.query = query;
	}




	public List<InternationalChannels> getChennelList() {
		return chennelList;
	}




	public void setChennelList(List<InternationalChannels> chennelList) {
		this.chennelList = chennelList;
	}




	public Long getChannelId() {
		return channelId;
	}




	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	
   
}
