package com.ecpss.tools;

import java.io.OutputStream;
import java.util.List;

/**
 * ��TableExport类TableExport.java的实现描述：导出表格 
 * @author 
 */
public interface TableExport {
	public void addTitle(String[] strArray);
	public void addRow(Object[] row);
	public void addRow(Object[] row,DataFormat[] dataFormats);
	public void addRows(List<Object[]> rows);
	public void addRows(List<Object[]> rows, DataFormat[] dataFormats);
	public void setTableName(String tableName);
	public String getTableName();
	public void export(OutputStream output);
}