package com.ecpss.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ecpss.model.shop.InternationalMailInfo;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.CCSendMail;

public class SendRefundMailJob  extends QuartzJobBean {

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		this.shopManagerService.runGoudui();
	}

	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}

}
