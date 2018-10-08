package com.ecpss.service.sample.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.dao.sample.SampleDao;
import com.ecpss.model.user.User;
import com.ecpss.service.BaseService;
import com.ecpss.service.sample.SampleService;

@Service("sampleService")
@Transactional
public class SampleServiceImpl extends BaseService implements SampleService{
	@Autowired
	@Qualifier("sampleDao")
	private SampleDao sampleDao;
	
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	public List<User> getUserList() {
		
		return sampleDao.getUserList();
	}
}
