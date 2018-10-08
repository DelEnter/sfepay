package com.ecpss.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BaseHibernateDao {

	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
    }

	/**
	 * Ö´ÐÐ»º´æµÄhql
	 */
	public void flushSession(){
		getSession().flush();
	}
	/**
	 *
	 * @param obj
	 */
	public void delete(Object obj) {
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	/**
	 *
	 * @param obj
	 */
	public void delete(Collection<Object> obj) {
		if (obj != null) {
			for (Object o : obj) {
				delete(o);
			}
		}
	}

	/**
	 *
	 * @param objClass
	 * @param id
	 */
	public void delete(Class objClass, Serializable id) {
		delete(load(objClass,id));
	}

	/**
	 *
	 * @param objClass
	 * @param id
	 */
	public void delete(Class objClass, Serializable[] ids) {
		if(ids != null){
			for (Serializable id : ids) {
				delete(objClass,id);
			}
		}
	}

	/**
	 *
	 * @param hql
	 * @param args
	 * @return list
	 */
	public List list(String hql, Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.list();
	}

	/**
	 *
	 * @param hql
	 * @param args
	 * @return list
	 */
	public List listSQL(String hql, Object... args) {
		Query q = getSession().createSQLQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.list();
	}
	/**
	 *
	 * @param oql
	 * @param start
	 * @param size
	 * @param args
	 * @return list
	 */

	public List list(String hql, int start, int rowNum, Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		if (start < 0){
			start = 0;
		}
		if(rowNum < 1){
			rowNum = 1;
		}
		q.setFirstResult(start);
		q.setMaxResults(rowNum);
		return q.list();
	}
	/**
	 *
	 * @param oql
	 * @param start
	 * @param size
	 * @param args
	 * @return list
	 */

	public List listSQL(String hql, int start, int rowNum, Object... args) {
		Query q = getSession().createSQLQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		if (start < 0){
			start = 0;
		}
		if(rowNum < 1){
			rowNum = 1;
		}
		q.setFirstResult(start);
		q.setMaxResults(rowNum);
		return q.list();
	}
	/**
	 *
	 * @param c
	 * @param id
	 * @return Object
	 */
	public Object load(Class c, Serializable id) {
		return getSession().load(c, id);
	}

	/**
	 *
	 * @param obj
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(Object obj) {
		getSession().save(obj);

	}

	/**
	 *
	 * @param obj
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveOrUpdate(Object obj) {
		getSession().saveOrUpdate(obj);

	}

	/**
	 *
	 * @param obj
	 */
	public void save(Collection<Object> obList) {
		if (obList != null) {
			for (Object o : obList) {
				save(o);
			}
		}
	}

	/**
	 *
	 * @param hql
	 * @param args
	 * @return Object
	 */
	public Object uniqueResult(String hql, Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.uniqueResult();
	}

	/**
	 *
	 * @param o
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update(Object o) {
		getSession().update(o);
	}

	/**
	 *
	 * @param o
	 */
	public void update(Collection<Object> c) {
		if (c != null) {
			for (Object o : c) {
				update(o);
			}
		}
	}

	/**
	 *
	 * @param hql
	 * @param args
	 * @return int
	 */
	public int executeUpdate(String hql, Object... args) {
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.executeUpdate();
	}

	/**
	 *
	 * @param sql
	 * @param args
	 * @return int
	 */
	public int executeSQLUpdate(String sql, Object... args) {
		Query q = getSession().createSQLQuery(sql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		return q.executeUpdate();
	}

	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}