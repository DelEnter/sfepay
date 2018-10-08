package com.ecpss.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ecpss.service.iservice.SystemManagerService;

public class CloseTerminalNoJob extends QuartzJobBean {
	@Autowired
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		systemManagerService.closeTerminalNoByMore();
	}

	public SystemManagerService getSystemManagerService() {
		return systemManagerService;
	}

	public void setSystemManagerService(SystemManagerService systemManagerService) {
		this.systemManagerService = systemManagerService;
	}
}
