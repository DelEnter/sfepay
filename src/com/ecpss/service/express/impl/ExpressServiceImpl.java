package com.ecpss.service.express.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.service.BaseService;
import com.ecpss.service.express.ExpressService;

@Service("ExpressService")
@Transactional
public class ExpressServiceImpl extends BaseService implements ExpressService {
	
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;

	public List<InternationalTradeinfo> select(Long orderNo) {
		String hql = "select t.tradeState from InternationalTradeinfo t " +
				"where t.orderNo="+orderNo;
		List<InternationalTradeinfo> list = this.commonDao.list(hql);
		return list;
	}
}
