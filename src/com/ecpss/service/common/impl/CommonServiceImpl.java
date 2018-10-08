package com.ecpss.service.common.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ecpss.service.BaseService;
import com.ecpss.service.common.CommonService;
import com.ecpss.web.PageInfo;

@Service("commonService")
public class CommonServiceImpl extends BaseService implements CommonService{

	public void delete(Class objClass, Serializable id) {
		commonDao.delete(objClass, id);

	}

	public void delete(Class objClass, Serializable[] ids) {
		commonDao.delete(objClass, ids);

	}

	public void delete(Collection<Object> c) {
		commonDao.delete(c);

	}

	public void delete(Object o) {
		commonDao.delete(o);

	}

	public Object load(Class c, Serializable id) {
		return commonDao.load(c, id);
	}

	public void save(Collection<Object> obList) {
		commonDao.save(obList);
	}

	public void save(Object o) {
		commonDao.save(o);
	}

	public void saveOrUpdate(Object o) {
		commonDao.saveOrUpdate(o);

	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update(Collection<Object> c) {
		commonDao.update(c);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update(Object o) {
		commonDao.update(o);
	}
	
	public List list(String hql, Object... args){
		return commonDao.list(hql, args);
	}
	
	public Object uniqueResult(String hql, Object... args) {
		return commonDao.uniqueResult(hql, args);
	}

	public PageInfo listQueryResultByHql(String hql,PageInfo pageInfo,Object...args){
		if(pageInfo==null ){
			pageInfo = new PageInfo();
		}
		pageInfo.setTotalCount(getTotalNumHQL(hql, args));
		pageInfo.setResult(commonDao.findList(hql, pageInfo.getStartOfPage(), pageInfo.getPageSize(), args));
		return pageInfo;
	}
	
	public int getTotalNumHQL(String hql, Object... args) {
		String countQueryString = removeSelect(removeOrders(hql));
		//String countQueryString = removeOrders(hql);
		//return commonDao.executeStat(countQueryString,args).intValue();
		String count = "select count(*) " + countQueryString;
		return ((Long) commonDao.uniqueResult(count)).intValue();
	}
	
	public PageInfo listQueryResultByHqlGroupBy(String hql,PageInfo pageInfo,Object...args){
		if(pageInfo==null ){
			pageInfo = new PageInfo();
		}
		pageInfo.setTotalCount(getTotalNumSQLGroupby(hql, args));
		pageInfo.setResult(commonDao.findList(hql, pageInfo.getStartOfPage(), pageInfo.getPageSize(), args));
		return pageInfo;
	}
	
	public int getTotalNumSQLGroupby(String hql, Object... args) {
		//String countQueryString = removeSelect(removeOrders(hql));
		String countQueryString = removeOrders(hql);
		return commonDao.executeStat(countQueryString,args).intValue();
		
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #listQueryResult(String,PageInfo,Object[])
	 */
	private static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #listQueryResult(String,PageInfo,Object[])
	 */
	private static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public List getByList(String sql) {
		List list=this.commonDao.getBySql(sql);
		return list;
	}
	public void deleteBySql(String sql){
		this.commonDao.deleteBySql(sql);
		
	}
}
