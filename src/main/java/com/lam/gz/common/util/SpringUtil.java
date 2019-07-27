package com.lam.gz.common.util;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author liubo
 *
 */
public class SpringUtil {
	
	private static final PathMatchingResourcePatternResolver RESOURCE_LOADER = new PathMatchingResourcePatternResolver();
	
	private SpringUtil(){
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean match(String pattern, String path) {
		
		return RESOURCE_LOADER.getPathMatcher().match(pattern, path);
		
	}

}
