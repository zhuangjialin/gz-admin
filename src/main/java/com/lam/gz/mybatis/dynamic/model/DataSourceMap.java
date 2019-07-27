package com.lam.gz.mybatis.dynamic.model;

import java.util.Map;

/**
 * @author liubo
 *
 */
public interface DataSourceMap {
	
	Map<String, String> getNameMap();
	
	String getDefaultTargetKey();
	
}
