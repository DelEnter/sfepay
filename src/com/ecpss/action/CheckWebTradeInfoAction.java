package com.ecpss.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ecpss.model.risk.InternationalRiskItems;
import com.ecpss.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author 获取网站交易具体信息
 *
 */
public class CheckWebTradeInfoAction extends BaseAction {
	private String jsonData="";
	//ajaxs 网址交易基础信息
	public void getWebTradeDetail(){
		PrintWriter pw = null;
		Map<String, String> m = new HashMap<String, String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try{
		pw = response.getWriter();
		String ajaxTradeUrl = ((String[])ActionContext.getContext().getParameters().get("ajaxTradeUrl"))[0];
		String ajaxTradetime = ((String[])ActionContext.getContext().getParameters().get("ajaxTradetime"))[0];
		if(StringUtils.isNotBlank(ajaxTradeUrl)){
			String hql="select w.executetime,w.webSiteType from InternationalWebchannels w where w.tradeWebsite like '%"+ajaxTradeUrl.trim()+"%' order by w.executetime desc ";
			List list = this.commonService.list(hql);
			String hql2=" from InternationalRiskItems t where t.itemType='3'";
			List<InternationalRiskItems> list2=commonService.list(hql2);
			Long tradSum = (Long) commonService
				.uniqueResult("select count(*) from InternationalTradeinfo t where t.tradeUrl like '%"+ajaxTradeUrl.trim()+"%' and t.tradeTime<=to_date('20"+ajaxTradetime+"','yyyy-MM-dd hh24:mi:ss') ");
			if(list!=null&&list.size()>0){
				Object[] obj=(Object[]) list.get(0);
				for(InternationalRiskItems web:list2){
					if(StringUtils.isNotBlank(web.getItemName())&&obj[1]!=null){
						if(obj[1].toString().toLowerCase().indexOf(web.getItemName().toLowerCase())>=0){
							jsonData="1,";
							break;
						}
					}else{
						jsonData="0,";
					}
				}
				if(StringUtils.isBlank(jsonData)){
					jsonData="0,";
				}
				jsonData=jsonData+obj[0].toString()+","+obj[1]+",";
			}else{
				jsonData="0,0,0,";
			}
			jsonData=jsonData.toString()+tradSum;
		}else{
			jsonData="数据异常！";
		}
		System.out.println("json:"+jsonData);
		m.put("jsonData", jsonData);
		pw.print(JsonUtils.toJSONObject(m).toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			pw.close();
			pw = null;
		}
		}
	public void getTradeChart(){
		PrintWriter pw = null;
		Map<String, Object> m = new HashMap<String, Object>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try{
			Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
			calendar.add(Calendar.DATE,-5); // 得到前5天
			Calendar calendar1 = Calendar.getInstance();// 此时打印它获取的是系统当前时间
			calendar1.add(Calendar.DATE,-10); // 得到前10天
			String beginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(calendar.getTime());
			String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(calendar1.getTime());
			String startDate = ((String[])ActionContext.getContext().getParameters().get("startDate"))[0];
			String endDate = ((String[])ActionContext.getContext().getParameters().get("endDate"))[0];
			String startDate2 = ((String[])ActionContext.getContext().getParameters().get("startDate2"))[0];
			String endDate2 = ((String[])ActionContext.getContext().getParameters().get("endDate2"))[0];
			String merchantNo = ((String[])ActionContext.getContext().getParameters().get("merchantNo"))[0];
			String sql="";
			if(StringUtils.isNotBlank(startDate)&&StringUtils.isNotBlank(endDate)&&StringUtils.isNotBlank(startDate2)&&StringUtils.isNotBlank(endDate2)){
				sql=sql+"select *  from (select m.merno,sum(case when ti.tradeTime between to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+endDate+"','yyyy-MM-dd hh24:mi:ss') then '1' else '0' end) beginTrade,"
						+ " sum(case when ti.tradeTime between to_date('"+startDate2+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+endDate2+"','yyyy-MM-dd hh24:mi:ss') then '1' else '0' end)  endTrade "
						+ " from International_Tradeinfo ti,International_Merchant m where ti.merchantId=m.id ";
			}else{
				sql=sql+"select *  from (select m.merno,sum(case when ti.tradeTime>=to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss') then '1' else '0' end) beginTrade,"
					+ " sum(case when ti.tradeTime between to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+beginTime+"','yyyy-MM-dd hh24:mi:ss') then '1' else '0' end)  endTrade "
					+ " from International_Tradeinfo ti,International_Merchant m where ti.merchantId=m.id ";
			}
			if(StringUtils.isNotBlank(merchantNo)){
				sql=sql+" and m.merno='"+merchantNo+"'";
			}
			sql=sql+" group by m.merno order by m.merno desc ) where beginTrade!='0' or endTrade!='0'";
			List list = this.commonService.getByList(sql);
			String[] merNos = null;
			String[] firstData = null;
			String[] lastData = null;
			if(list!=null&&list.size()>0){
				merNos=new String[list.size()];
				firstData=new String[list.size()];
				lastData=new String[list.size()];
				for(int i=0;i<list.size();i++){
					Object[] obj=(Object[]) list.get(i);
					merNos[i]=obj[0]+"";
					firstData[i]=obj[1]+"";
					lastData[i]=obj[2]+"";
				}
			}
			pw = response.getWriter();
			m.put("merNos", merNos);
			m.put("firstData", firstData);
			m.put("lastData", lastData);
			pw.print(JsonUtils.toJSONObject(m).toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			pw.close();
			pw = null;
		}
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
