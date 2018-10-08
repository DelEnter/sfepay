package com.ecpss.util;

import java.sql.Date;
import java.util.Calendar;

public class GetBatchNo {
	
	/**
	 * 获取cookies
	 * @return
	 */
	public String getCookies(){
		Calendar currenttime = Calendar.getInstance();
		String cookies = "ecpss.com" + currenttime.get(currenttime.YEAR) + ""
				+ (currenttime.get(currenttime.MONTH) + 1) + ""
				+ currenttime.get(currenttime.DAY_OF_MONTH) + ""
				+ currenttime.get(currenttime.HOUR_OF_DAY) + ""
				+ currenttime.get(currenttime.MINUTE) + ""
				+ currenttime.get(currenttime.SECOND)+
				+ currenttime.get(currenttime.MILLISECOND);
		return cookies;
	}
	/**
	 * 获取批次号
	 * @return
	 */
	public static String getBatchNo() {
		long itmp1 = Math.round(Math.random() * 9);
		long itmp2 = Math.round(Math.random() * 9);
		Calendar currenttime = Calendar.getInstance();
		String batchno = "" + (currenttime.get(currenttime.YEAR)+"").substring(2) + ""
				+ (currenttime.get(currenttime.MONTH) + 1) + ""
				+ currenttime.get(currenttime.DAY_OF_MONTH) + ""
				+ currenttime.get(currenttime.HOUR_OF_DAY) + ""
				+ currenttime.get(currenttime.MINUTE) + ""
				+ currenttime.get(currenttime.SECOND)
				+ String.valueOf(itmp1) +""
				+ String.valueOf(itmp2);
		return batchno;
	}
	//获取订单流水号
	public String getOrderinfo(String MerNo){
		long itmp1 = Math.round(Math.random() * 9);
		long itmp2 = Math.round(Math.random() * 9);
		Calendar currenttime = Calendar.getInstance();
		String orderinfo = "" + (currenttime.get(currenttime.YEAR)+"").substring(2) + ""
				+ (currenttime.get(currenttime.MONTH) + 1) + ""
				+ currenttime.get(currenttime.DAY_OF_MONTH) + ""
				+ currenttime.get(currenttime.HOUR_OF_DAY) + ""
				+ currenttime.get(currenttime.MINUTE) + ""
				+ currenttime.get(currenttime.SECOND)+
				+ currenttime.get(currenttime.MILLISECOND) +""
				+ String.valueOf(itmp1) +""
				+ String.valueOf(itmp2);
		return MerNo+orderinfo;
	}
	public static Date getTime(){
//		生成系统时间
		Calendar rightNow = Calendar.getInstance();
		Date now = new Date(rightNow.getTimeInMillis());
		return now;
	}
	
	public int getMonthTime(){
		Calendar c  = Calendar.getInstance();   
		c.setTime(new java.util.Date());   
		int month = c.get(Calendar.MONTH)+1;   
		return month;
	}
	
	
	 
	public static void main(String[] args) {
//		Calendar currenttime = Calendar.getInstance();
//		String ssss=""+ currenttime.get(currenttime.YEAR);
//		System.out.println(ssss.substring(2));
		GetBatchNo aa=new GetBatchNo();
		aa.getOrderinfo("1122");
	
	}
	
}
