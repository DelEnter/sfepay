package com.ecpss.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ecpss.service.implservice.ShopManagerServiceImpl;
import com.ecpss.service.iservice.ShopManagerService;

public class CancelHignRickTradeJob extends QuartzJobBean {
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// 开始清楚大于24小时的待确认交易
		System.out.println("开始清除待确认交易");
		shopManagerService.cancelHignRickTrade();
		System.out.println("清理完毕");
		
	}


	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}


	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}


	public void setShopManagerServiceImpl(
			ShopManagerServiceImpl shopManagerServiceImpl) {
		this.shopManagerService = shopManagerServiceImpl;
	}
}
