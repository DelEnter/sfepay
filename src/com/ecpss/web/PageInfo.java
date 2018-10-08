package com.ecpss.web;

import java.io.Serializable;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * 
 */
@SuppressWarnings("unchecked")
public class PageInfo implements Serializable {
	private static final long	serialVersionUID	= 6796458141843445623L;

	private int					curPage;

	private int					totalCount;								// 总记录数

	private int					pageSize;									// 每页的记录数

	private Object				data;										// 当前页中存放的记录,类型一般为List

	private static int			ROWS_PER_PAGE		= 20;

	public PageInfo() {
		curPage = 1;
		totalCount = 0;
		pageSize = ROWS_PER_PAGE;
	}

	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getResult() {
		return data;
	}

	/**
	 * 取当前页中的记录.
	 */
	public void setResult(Object data) {
		this.data=data;
	}

	/**
	 * 取总页数.
	 */
	public int getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setPageSize(int rows) {
		if (rows > 0) {
			pageSize = rows;
		} else {
			pageSize = ROWS_PER_PAGE;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getDefaultRowsPerPage() {
		return ROWS_PER_PAGE;
	}
	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public int getStartOfPage() {
		return (curPage - 1) * pageSize;
	}
	public static void main(String[] args){
		PageInfo pi=new PageInfo();
		pi.setTotalCount(21);
		pi.setCurPage(2);
		System.out.println(pi.getStartOfPage());
		System.out.println(pi.getPageSize());
	}
}