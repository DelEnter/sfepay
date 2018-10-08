package com.ecpss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.dao.common.CommonDao;

public class BaseService {
	@Autowired
	@Qualifier("commonDao")
	protected CommonDao commonDao;
}
