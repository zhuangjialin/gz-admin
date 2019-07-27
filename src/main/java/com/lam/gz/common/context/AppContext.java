package com.lam.gz.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liubo
 *
 */
@Component
public class AppContext implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		ContextHolder.setApplicationContext(this);
	}
	
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

}
