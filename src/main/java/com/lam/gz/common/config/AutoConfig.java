package com.lam.gz.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author bo.liu01
 */
@Configuration
@ComponentScan(value={"com.lam.gz.common.config", "com.lam.gz.common.init", 
				"com.lam.gz.common.context",})
public class AutoConfig {
    
}
