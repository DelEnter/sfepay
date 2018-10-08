package com.ecpss.dao.sample.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecpss.dao.BaseHibernateDao;
import com.ecpss.dao.sample.SampleDao;
import com.ecpss.model.user.User;

@Repository("sampleDao")
public class SampleDaoImpl extends BaseHibernateDao implements SampleDao{

	/* (non-Javadoc)
	 * @see com.ecpss.dao.sample.SampleDao#getUserList()
	 */
	public List<User> getUserList() {
		return super.list("from User");
	}
}
