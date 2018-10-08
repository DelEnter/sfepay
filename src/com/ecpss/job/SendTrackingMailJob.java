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

public class SendTrackingMailJob  extends QuartzJobBean {

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		/**
		 * 跟踪单号定时发送邮件设置
		 */
		InternationalMailInfo m = shopManagerService.getResultMail("2");
		if(m!=null){
			boolean blag = CCSendMail.setSendMail(m.getCardhorderEmail(), m.getMailInfo(), m.getSendEmail());
			if(blag){
				//System.out.println("发送成功后删除");
				this.shopManagerService.deleteInfo(m);
			}
		}
	}

	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}

}
