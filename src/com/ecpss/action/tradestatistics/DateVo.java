package com.ecpss.action.tradestatistics;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecpss.util.CalcuLate;

public class DateVo {
	private String[] dates = new String[]{} ;
	private String merchantNo;
	private Map data = new HashMap();

	public void setDates(String[] tem) {

		this.dates = tem;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public void initValue(Object[] obj) {
		this.data.put(obj[1].toString(), obj);
		this.merchantNo = obj[0].toString();
	}

	public void setDataValue(Object[] obj) {
		if (!this.data.containsValue(obj[1].toString())) {
			this.data.put(obj[1].toString(), obj);
		}
		
	}
       
	public String getHtml() {
		String tem = " <tr> <td align='center' bgcolor='#FFFFFF'>"
				+ this.merchantNo + "</td>";
		Double successMoney = 0d;
		int jufu = 0;
		int chenggong = 0;
		CalcuLate caclulate = new CalcuLate();
		for (int i = 0; i < this.dates.length; i++) {
			if (this.data.containsKey(this.dates[i].toString())) {
				Object[] obj = (Object[]) this.data.get(this.dates[i]);
				int jufubishu=0;
				if(obj[4]!=null){
					jufubishu=((BigDecimal)obj[4]).intValue();
				}
				String b = "0";
				if(obj[4]!=null && obj[2]!=null){
					b = caclulate.getValue2(((BigDecimal)obj[4]).doubleValue(), ((BigDecimal)obj[2]).doubleValue());
				}
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
						+ obj[2] + "</td>";
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
						+ jufubishu + "</td>";
				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
				+ b + "</td>";
//				tem = tem + " <td align='center' bgcolor='#FFFFFF'>&nbsp;"
//						+ b + "</td>";
				
				
				successMoney = successMoney
						+ ((BigDecimal) obj[3]).doubleValue();
				if(obj[4]!=null){
					//jufu = jufu + Integer.valueOf(obj[4].toString());
					jufu = jufu +jufubishu;
				}
				chenggong = chenggong + Integer.valueOf(obj[2].toString());
    
			} else {
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
				tem = tem
						+ " <td align='center' bgcolor='#FFFFFF'>&nbsp;0</td>";
			}

		}
		tem = tem + "<td align='center' bgcolor='#FFFFFF'>&nbsp;" + chenggong
				+ "</td>" + "<td align='center' bgcolor='#FFFFFF'>&nbsp;"
				+ successMoney + "</td>"
				+ "<td align='center' bgcolor='#FFFFFF'>&nbsp;" + jufu
				+ "</td>" + "<td align='center' bgcolor='#FFFFFF'>&nbsp;"
				+ Double.valueOf(jufu) / Double.valueOf(chenggong) + "</td>";

		return tem + " </tr>";
	}
     
}
