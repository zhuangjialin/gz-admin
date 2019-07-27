package com.lam.gz.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author bo.liu01
 */
@Configuration
@ComponentScan(value={"com.lam.gz.common.context",
		"com.lam.gz.common.config"})
public class AutoConfigContext {
    
}
