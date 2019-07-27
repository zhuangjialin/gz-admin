package com.lam.gz.mybatis.dynamic.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lam.gz.common.exception.BaseException;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.mybatis.dynamic.DataSourceConstant;

/**
 * @author liubo
 *
 */
public class DynamicDataSourceMap implements DataSourceMap {
	
	private static final String DEFAULT_DS_NAME_SUFFIX = "DataSource";
	
	protected Map<String, String> nameMap = new HashMap<>();
	protected String defaultTargetKey;
	
	private DynamicDataSourceMap(){}
	
	private DynamicDataSourceMap(String dsName){
		register(dsName, true);
	}

	public static DynamicDataSourceMap create(){
		return new DynamicDataSourceMap();
	}
	
	public static DynamicDataSourceMap create(String masterName){
		return new DynamicDataSourceMap(masterName);
	}
	
	@Override
	public Map<String, String> getNameMap() {
		String dtk = getDefaultTargetKey();
		if(dtk.equals(DataSourceConstant.MASTER) && !nameMap.containsKey(dtk)){
			register(dtk, true);
		}
		return Collections.unmodifiableMap(nameMap);
	}
	
	@Override
	public String getDefaultTargetKey(){
		if(StringUtil.isBlank(defaultTargetKey)){
			return DataSourceConstant.MASTER;
		}
		return defaultTargetKey;
	}
	
	public DynamicDataSourceMap register(String dsName, String dataSource){
		register(dsName, dataSource, false);
		return this;
	}
	
	public DynamicDataSourceMap register(String dsName, boolean isMaster){
		register(dsName, null, isMaster);
		return this;
	}
	
	public DynamicDataSourceMap register(String dsName){
		register(dsName, null, false);
		return this;
	}
	
	/**
	 * 
	 * @param dsName
	 * @param dataSource
	 * @param isMaster
	 * @return
	 */
	public DynamicDataSourceMap register(String dsName, String dataSource, boolean isMaster){
		if(StringUtil.isBlank(dsName)){
			throw new BaseException("注册数据源失败, 数据源名称不能为空!");
		}
		if(isMaster){
			defaultTargetKey = dsName;
		}
		if(StringUtil.isBlank(dataSource)){
			dataSource = dsName + DEFAULT_DS_NAME_SUFFIX;
		}
		nameMap.put(dsName, dataSource);
		return this;
	}
	
}
