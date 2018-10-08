package com.ecpss.excel.cell;
public interface CellValueConvertor<T,V> {
	public T getCellValue(V cell);
}
