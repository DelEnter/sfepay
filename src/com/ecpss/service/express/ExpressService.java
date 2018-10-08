package com.ecpss.service.express;

import java.util.List;

import com.ecpss.model.payment.InternationalTradeinfo;

public interface ExpressService {
	//根据蜂皇传来的流水号在INTERNATIONAL_TRADEINFO中查一下有没有对应的orderNo	
	public List<InternationalTradeinfo> select(Long orderNo);
}
