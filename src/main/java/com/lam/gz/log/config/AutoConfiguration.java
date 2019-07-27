package com.lam.gz.log.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author bo.liu01
 */
@Configuration
@ComponentScan({"com.lam.gz.log", "com.lam.gz.common.filters"})
public class AutoConfiguration {
    
}
