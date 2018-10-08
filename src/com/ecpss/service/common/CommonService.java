package com.ecpss.service.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.ecpss.web.PageInfo;

public interface CommonService {
	public void save(Object o);

	public void save(Collection<Object> obList);

	public void saveOrUpdate(Object o);

	public void delete(Object o);

	public void delete(Collection<Object> c);

	public void delete(Class objClass, Serializable id);

	public void delete(Class objClass, Serializable[] ids);

	public void update(Object o);

	public void update(Collection<Object> c);

	public Object load(Class c, Serializable id);
	
	public Object uniqueResult(String hql, Object... args);
	
	/**
	 * 正常分页使用
	 * @param hql
	 * @param pageInfo
	 * @param args
	 * @return
	 */
	public PageInfo listQueryResultByHql(String hql,PageInfo pageInfo,Object...args);

	/**
	 * 用于group by语句的分页标签
	 * @param hql
	 * @param pageInfo
	 * @param args
	 * @return
	 */
	public PageInfo listQueryResultByHqlGroupBy(String hql,PageInfo pageInfo,Object...args);
	
	public List list(String hql, Object... args);
	public List getByList(String sql);
	public void deleteBySql(String sql);
}
