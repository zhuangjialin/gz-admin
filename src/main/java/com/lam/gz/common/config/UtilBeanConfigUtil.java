package com.lam.gz.common.config;

import com.lam.gz.common.util.HttpServletUtil;

/**
 * @author liubo
 *
 */
public class UtilBeanConfigUtil {
	
	private static UtilBeanConfig commonBeanConfig;
	private static final Object CONFIG_LOCKER = new Object();
	
	private UtilBeanConfigUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	public static UtilBeanConfig getConfig(){
    	if(commonBeanConfig == null){
    		synchronized (CONFIG_LOCKER) {
				commonBeanConfig = (UtilBeanConfig)HttpServletUtil.getBean("utilBeanConfig");
    		}
    	}
    	return commonBeanConfig;
    }
	
}
