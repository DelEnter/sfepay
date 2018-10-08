package com.ecpss.action.tradestatistics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ecpss.action.BaseAction;
import com.ecpss.util.CalcuLate;

public class EveryDayTrade extends BaseAction {
	private String startDate;
	private String endDate;
	private List tradeeveryDayList = new ArrayList();
	private List tradeeverySumList=new ArrayList();
	private List<String> list = new ArrayList();
	private String showTable;

	public String everyDayTradeinfo() {
		String tmp = "";
		CalcuLate caclulate = new CalcuLate();
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp = " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate+""
					+ "','yyyy-MM') ";
		}
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -20);
			tmp = " and t.tradetime>=to_date('" + c.getTime().toLocaleString()
					+ "','yyyy-MM-dd hh24:mi:ss') "
					+ "and t.tradetime<=sysdate ";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("select f.merno,aa.xxx,aa.kkk,aa.nnn,bb.mmm");

		sb
				.append(" from (select to_char(t.tradetime,'yyyy-mm') xxx,t.merchantid, count(*) kkk,sum(t.tradeamount) nnn from international_tradeinfo t  "
						+ " where substr(t.tradestate,1,1)=1 "
						+ tmp
						+ " "
						+ " group by to_char(t.tradetime,'yyyy-mm'),t.merchantid) aa");
		sb
				.append(" left join "
						+ " (select to_char(t.tradetime,'yyyy-mm') ,t.merchantid, count(*) mmm,sum(t.tradeamount) from international_tradeinfo t  "
						+ " where substr(t.tradestate,3,1)=1 "
						+ tmp
						+ " "
						+ " group by to_char(t.tradetime,'yyyy-mm') ,t.merchantid) bb"
						+ " on aa.merchantid=bb.merchantid,international_merchant f  where f.id=aa.merchantid");
		tradeeveryDayList = commonService.getByList(sb.toString());
		String datas[] = new String[] {};
		if (!(StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate))) {

			try {
				datas = this.getDateArray3(this.startDate, this.endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 0 商户号  1 时间   2 交易金额   3  成功笔数  4 失败笔数
		Map temMap = new HashMap();
		for (int i = 0; i < tradeeveryDayList.size(); i++) {
			Object[] temObj = (Object[]) tradeeveryDayList.get(i);
			if (temMap.containsKey(temObj[0])) {
				DateVo vo = (DateVo) temMap.get(temObj[0]);
				vo.setDataValue(temObj);
			} else {
				DateVo vo = new DateVo();
				vo.initValue(temObj);
				vo.setDates(datas);
				temMap.put(temObj[0], vo);

			}
		}

		List listKey = new ArrayList();
		List listValue = new ArrayList();
		Iterator it = temMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			listKey.add(key);
			listValue.add(temMap.get(new BigDecimal(key)));
		}
		String tem = "";
		//output the context of the ArrayList
		for (int i = 0; i < listKey.size(); i++) {
			DateVo vo = (DateVo) listValue.get(i);

			tem = tem + vo.getHtml();
		}

		String head = "<tr>"
				+ " <td rowspan='2' align='center' bgcolor='#FFFFFF'><strong>商户号</strong></td>";
		for (int i = 0; i < datas.length; i++) {
			head = head + " <td colspan='3' align='center' bgcolor='#FFFFFF'>"
					+ datas[i] + "</td>";
		}
		head = head
				+ "<td colspan='4' align='center' bgcolor='#FFFFFF'><strong>小计</strong></td> </tr> <tr>";
		for (int i = 0; i < datas.length; i++) {

			head = head
					+ "<td align='center' bgcolor='#CCCCCC'><strong>成功</strong></td>"
					+ "<td align='center' bgcolor='#CCCCCC'><strong>拒付</strong></td>"
					+ "<td align='center' bgcolor='#CCCCCC'><strong>比例</strong></td>";
		}
		head = head
				+ "<td align='center' bgcolor='#CCCCCC'><strong>成功笔数</strong></td>"
				+ "<td align='center' bgcolor='#CCCCCC'><strong>金额</strong></td>"
				+ "<td align='center' bgcolor='#CCCCCC'><strong>拒付笔数</strong></td>"
				+ "<td align='center' bgcolor='#CCCCCC'><strong>拒付率</strong></td> </tr>";
		tem = head + tem;

//1. 查出值， 2.建立一个Map  KEY 日期，  
		StringBuffer str=new StringBuffer();
		str 
		        .append("select aa.datefrom,aa.nnn,aa.mmm,bb.kkk");
		str
		        .append(" from (select to_char(t.tradetime,'yyyy-mm') as datefrom, count(*) nnn ,sum(t.tradeamount) mmm from international_tradeinfo t  "
				        + " where substr(t.tradestate,1,1)=1 "
				        + tmp
				        + " "
				        + " group by to_char(t.tradetime,'yyyy-mm') ) aa ");
		str
		        .append(" left join "
				        + " (select to_char(t.tradetime,'yyyy-mm') as datefrom, count(*) kkk ,sum(t.tradeamount) from international_tradeinfo t  "
				        + " where substr(t.tradestate,3,1)=1 "
				        + tmp
				        + " "
				        + " group by to_char(t.tradetime,'yyyy-mm') ) bb"
				        + " on aa.datefrom=bb.datefrom");
		tradeeverySumList=commonService.getByList(str.toString());
		Map temMapTatol = new HashMap();
		
		for(int i=0;i<tradeeverySumList.size();i++){
			Object[] temObj = (Object[]) tradeeverySumList.get(i);
			if(!temMapTatol.containsKey(temObj[0].toString())){
			
			temMapTatol.put(temObj[0].toString(), temObj);
			}

		}
	
		//写HTML
		 tem = tem+ " <tr> <td align='center' bgcolor='#FFFFFF'></td>";
	        Double successMoney = 0d;
	        int jufu = 0;
	        int chenggong = 0;
		for(int i=0;i<datas.length;i++){
			if (temMapTatol.containsKey(datas[i])) {
			  Object[] temObj=(Object[]) temMapTatol.get(datas[i]);
			      int  jufubishu=0;
			  if(temObj[3]!=null){
					jufubishu=((BigDecimal)temObj[3]).intValue();
				}
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
						+ temObj[1] + "</td>";
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
					+ jufubishu + "</td>";
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
						+ caclulate.getValue2(jufubishu, Double.valueOf(chenggong)) + "</td>";
				
			/*	Integer i1;
				int
				Long a;
				long
				String  a; */
			
				successMoney = successMoney
						+ ((BigDecimal) temObj[2]).doubleValue();
				if(temObj[3]!=null){
			jufu = jufu + Integer.valueOf(temObj[3].toString());
				}
				chenggong = chenggong + Integer.valueOf(temObj[1].toString());
  
			} else {
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
			}
		}
		
		System.out.println(tem);
		this.showTable = tem;
		return "success";

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

	public List getTradeeveryDayList() {
		return tradeeveryDayList;
	}

	public void setTradeeveryDayList(List tradeeveryDayList) {
		this.tradeeveryDayList = tradeeveryDayList;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public static Date[] getDateArrays(Date start, Date end, int calendarType) {
		ArrayList<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(calendarType, 1);
			tmpDate = calendar.getTime();
		}

		Date[] dates = new Date[ret.size()];
		return (Date[]) ret.toArray(dates);
	}

	public String[] getDateArrays2(String dateBegin, String dataEdn)
			throws java.text.ParseException {
		SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd ");
		Date date = sim.parse(dateBegin + " ");
		Date date2 = sim.parse(dataEdn + " ");
		Date[] strArray = getDateArrays(date, date2, Calendar.DAY_OF_YEAR);
		String[] tem = new String[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			tem[i] = sim1.format(strArray[i]);
		}
		return tem;
	}
	
	/**
	 * 循环遍历出来日期之间的月份
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public String [] getDateArray3(String start, String end) throws ParseException{
		// 字符串转换成日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date startDate = format.parse(start);
		Calendar startTime = Calendar.getInstance();
		startTime.clear();
		startTime.setTime(startDate);
		int startYear = startTime.get(Calendar.YEAR);
		int startMonth = startTime.get(Calendar.MONTH);
		Date endDate = format.parse(end);
		Calendar endTime = Calendar.getInstance();
		endTime.clear();
		endTime.setTime(endDate);
		int endYear = endTime.get(Calendar.YEAR);
		int endMonth = endTime.get(Calendar.MONTH);
		System.out.println("start date : " + start);
		System.out.println("end date : " + end);
		List<String> list = new ArrayList<String>();
		for (int x = startYear; x <= endYear; x++) {
			// 循环每个月
			// 如果在日期范围内月份循环时自增到了一年的最后一个月就将月份初始化到一月份
			int y = 0;
			// 如果是开始日期的第一个年的月数就从开始月数循环
			if (x == startYear) {
				y = startMonth;
			}
			for (; y < 12; y++) {
				int ty = y + 1;
				System.out.println(x + "-" + ty + "");
				String d = null;
				if(ty<10){
					d =x + "-0" + ty;
				}else{
					d =x + "-" + ty;
				}
				list.add(d);
				// 如果已经遍历过了截至日期的最后月份就中止月份的循环
				if (x == endYear && y == endMonth) {
					break;
				}
			}
		}
		String[] tem = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tem[i] = list.get(i);
		}
		return tem;
	}

	public String getShowTable() {
		return showTable;
	}

	public void setShowTable(String showTable) {
		this.showTable = showTable;
	}

}
