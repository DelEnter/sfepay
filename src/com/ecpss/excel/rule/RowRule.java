package com.ecpss.excel.rule;

public interface RowRule {
	public void addCellRule(CellRule cellRule);
	public CellRule[] getCellRules();
}
