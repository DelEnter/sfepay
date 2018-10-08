package com.ecpss.dao.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.ecpss.model.permissions.Role;

/**
 * @author
 */
public interface CommonDao {
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
	
	public Number executeStat(String hql, Object... args);
	public List findList(String oql, int start, int size, Object... args);
	
	public List list(String hql,Object... args);
	public List getSql(String sql, String name, Object obj);
	public void saveturnname(Object obj);
	public List getBySql(String sql);
	public void deleteBySql(String sql);
}
