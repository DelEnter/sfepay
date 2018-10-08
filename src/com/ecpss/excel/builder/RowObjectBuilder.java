package com.ecpss.excel.builder;

import com.ecpss.excel.rule.RowRule;


public interface RowObjectBuilder {
	public void setRule(int startRow,int endRow , RowRule rowRule);
	public RowResult[] parseExcel();
}
