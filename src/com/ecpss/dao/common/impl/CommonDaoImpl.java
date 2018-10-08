package com.ecpss.dao.common.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.BaseHibernateDao;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.permissions.Role;

@Repository("commonDao")
public class CommonDaoImpl extends BaseHibernateDao implements CommonDao{

	public Number findcount(String hql){
		Query q = getSession().createQuery(hql);
		return q.list().size();
	}
	
	public Number executeStat(String hql, Object... args) {
		return (Number)findcount(hql);
	}
	
	public List findList(final String hql, final int start, final int size, final Object... args){
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			q.setParameter(i, args[i]);
		}
		if (start > 0)
			q.setFirstResult(start);
		if (size > 0)
			q.setMaxResults(size);
		return q.list();
	}
	public List getSql(String sql, String name, Object obj){
//		String sql="select {SYSTEMROLE.*} from SYSTEMROLE_2010_02 {SYSTEMROLE}";
		List<Role> list= this.getSession().createSQLQuery(sql).addEntity(name,obj.getClass()).list();
		return list;
	}
	
	public void saveturnname(Object obj){
		
	}
	public List getBySql(String sql){
//		List list=new ArrayList();
//		 list=this.getSession().createSQLQuery(sql).list();
		return this.getSession().createSQLQuery(sql).list();
		
	}
	public void deleteBySql(String sql){
		this.getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
}
