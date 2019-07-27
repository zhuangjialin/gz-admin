package com.lam.gz.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liubo
 *
 */
@Component
public class BaseBeanConfig{
	
	/** 服务id */
	@Value("${spring.application.name}")
	protected String serviceId;

	public String getServiceId() {
		return serviceId;
	}

	
}
