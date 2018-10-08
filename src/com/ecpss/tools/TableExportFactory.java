package com.ecpss.tools;

import com.ecpss.tools.excle.ExcelTableExport;


/**
 * 类TableExportFactory.java的实现描述：导出类的工厂类 
 * @author 
 */
public abstract class TableExportFactory {
	public static TableExport createExcelTableExport(){
		return new ExcelTableExport();
	}
}
