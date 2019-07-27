package com.lam.gz.mybatis.dynamic.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.lam.gz.common.util.StringUtil;
import com.lam.gz.mybatis.dynamic.DataSourceConstant;
import com.lam.gz.mybatis.dynamic.JdbcContextHolder;

/**
 * @author liubo
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected Object determineCurrentLookupKey() {
		String key = JdbcContextHolder.getDataSource();
		if(StringUtil.isBlank(key)){
			key = DataSourceConstant.MASTER;
		}
		log.debug("当前数据源为: {}", key);
		return key;
	}
	
}