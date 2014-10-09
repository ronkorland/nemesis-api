package com.nemesis.api.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.nemesis.api.service.SeleniumGridService;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SyncNodeDataJob extends QuartzJobBean {

	@Autowired
	private SeleniumGridService seleniumGridService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		seleniumGridService.reloadAllNodeInfo();
	}

}
