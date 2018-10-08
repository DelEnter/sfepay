package com.ecpss.struts.converter;

import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 类FloatConverter.java的实现描述：浮点数转�?
 */
public class DoubleConverter extends StrutsTypeConverter {
	//使用千分号格�?
	private DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(values !=null && values.length >0 && StringUtils.isNotBlank(values.toString())){
			if(StringUtils.isNotBlank(values[0])){
				return Double.parseDouble(values[0]);
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if(o != null){
			return decimalFormat.format(o);
		}
		return null;
	}

}
