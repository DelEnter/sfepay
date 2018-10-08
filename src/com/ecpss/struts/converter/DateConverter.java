package com.ecpss.struts.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

/**
 * 类DateConverter.java的实现描述：日期格式转换
 */
public class DateConverter extends StrutsTypeConverter {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(values !=null && values.length >0){
			try {
				return dateFormat.parse(values[0]);
			} catch (ParseException e) {
				throw new TypeConversionException("["+values[0]+"]日期格式不正确，应该为yyyy-M-d，例2008-8-8,2008-10-20",e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	@Override
	public String convertToString(Map context, Object o) {
		if(o != null){
			return dateFormat.format((Date)o);
		}
		return null;
	}

}
