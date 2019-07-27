package com.lam.gz.common.config;

import com.lam.gz.common.util.HttpServletUtil;

/**
 * @author liubo
 *
 */
public class CommonBeanConfigUtil {
	
	private static CommonBeanConfig commonBeanConfig;
	private static final Object CONFIG_LOCKER = new Object();
	
	private CommonBeanConfigUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	public static CommonBeanConfig getConfig(){
    	if(commonBeanConfig == null){
    		synchronized (CONFIG_LOCKER) {
				commonBeanConfig = (CommonBeanConfig)HttpServletUtil.getBean("commonBeanConfig");
    		}
    	}
    	return commonBeanConfig;
    }
	
}
