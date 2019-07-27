package com.lam.gz.log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lam.gz.log.interceptor.DefinedInterceptor;

/**
 * @author liubo
 *
 */
@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 配置拦截器
		registry.addInterceptor(definedInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public DefinedInterceptor definedInterceptor() {
		return new DefinedInterceptor();
	} 

}
