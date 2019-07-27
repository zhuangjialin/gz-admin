package com.lam.gz.common.filters;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author bo.liu01
 */
@Configuration
@ComponentScan({"com.lam.gz.common.context", 
	"com.lam.gz.common.filters",
	"com.lam.gz.common.config"})
public class AutoConfigFilter{
    
}
