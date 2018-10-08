package com.ecpss.action.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.service.BaseService;
@Service("tradeManager")
public class TradeManagerImpl extends BaseService implements TradeManager {
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	@Autowired
	@Qualifier("tradeManagerDao")
	private TradeManagerDao tradeManagerDao;
	
	public int intBySql(String sql){
		
		return this.tradeManagerDao.intBySql(sql);
	}


	public Double getAcount(Long merchin) {
	return	this.tradeManagerDao.getAcount(merchin);
	
	}
}
