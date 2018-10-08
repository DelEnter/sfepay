package com.ecpss.action.risk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;

public class RiskWebAction extends BaseAction {
	private List difList=new ArrayList();
	private String startDate;
	public String toQueryDifWeb(){
		return SUCCESS;
	}
	
	public String queryDifWeb(){
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.MONTH, -3); // 得到前3天
		String sanshiDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(calendar.getTime());
		String hql="select ti.merchantId,ti.tradeUrl from InternationalTradeinfo ti ";
		if(StringUtils.isNotBlank(startDate)){
			hql=hql+" where ti.tradeTime>=to_date('"+startDate+" 00:00:00','yyyy-MM-dd hh24:mi:ss') group by ti.merchantId,ti.tradeUrl";
		}else{
		hql=hql+" where ti.tradeTime>=to_date('"+sanshiDate+"','yyyy-MM-dd hh24:mi:ss') group by ti.merchantId,ti.tradeUrl";
		}
		List<Object[]> list=commonService.list(hql);
		for(Object[] obj:list){
			if(obj[0]!=null&&obj[1]!=null){
				String hql2="select ti.orderNo,ti.tradeUrl,ti.merchantOrderNo,ti.tradeTime from InternationalTradeinfo ti where ti.merchantId='"+obj[0].toString()+"' and ti.tradeUrl='"+obj[1].toString()+"'";
				if(StringUtils.isNotBlank(startDate)){
					hql2=hql2+" and ti.tradeTime>=to_date('"+startDate+" 00:00:00','yyyy-MM-dd hh24:mi:ss') order by ti.tradeTime desc";
				}else{
					hql2=hql2+" and ti.tradeTime>=to_date('"+sanshiDate+"','yyyy-MM-dd hh24:mi:ss') order by ti.tradeTime desc";
				}
				List<Object[]> list2=commonService.list(hql2);
				Pattern pattern = Pattern.compile("\\d+");  
				for(int i=0;i<list2.size();i++){
					Object[] orderObj=list2.get(i);
					Object[] maxObj=null;
					if(i+1==list2.size()){
						maxObj=list2.get(i);
					}else{
						maxObj=list2.get(i+1);
					}
					long orderNo=0;
					long lastOrderNo=0;
					Matcher matcher = pattern.matcher(maxObj[2].toString());   
					    while (matcher.find()) {  
					    if(StringUtils.isNotBlank(matcher.group(0))){
					    	if(matcher.group(0).length()>18){
					    		lastOrderNo=Long.parseLong(matcher.group(0).substring(matcher.group(0).length()-18, matcher.group(0).length()));	
					    	}else{
					    		lastOrderNo=Long.parseLong(matcher.group(0)); 
					    	}
					    	}
					    } 
					Matcher matcher2 = pattern.matcher(orderObj[2].toString());
					while (matcher2.find()) {  
						 if(StringUtils.isNotBlank(matcher2.group(0))){
							 if(matcher2.group(0).length()>18){
								 orderNo=Long.parseLong(matcher2.group(0).substring(matcher2.group(0).length()-18, matcher2.group(0).length()));	
							    }else{
							    orderNo=Long.parseLong(matcher2.group(0));
							    }
						   }
						 } 
					if(orderNo+100<=lastOrderNo){
						Object[] difObj={(orderObj[0].toString()).substring(0, 4),orderObj[1].toString(),orderObj[2],maxObj[2],orderObj[3],maxObj[3]};
						difList.add(difObj);
						break;
					}
				}
			}
		}
		return SUCCESS;
	}

	public List getDifList() {
		return difList;
	}

	public void setDifList(List difList) {
		this.difList = difList;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
