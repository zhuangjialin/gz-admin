package com.lam.gz.mybatis.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.lam.gz.common.exception.BaseException;
import com.lam.gz.mybatis.dynamic.model.DataSourceMap;
import com.lam.gz.mybatis.dynamic.model.DynamicDataSource;

/**
 * @author liubo
 *
 */
@Configuration  
public class DataSourceConfig {  
	
	@Autowired
	private ApplicationContext applicationContext;
  
    @Bean(name="dynamicDataSource")  
    @Primary    //优先使用，多数据源  
    public DataSource dataSource(DataSourceMap dataSourceMap) {  
        DynamicDataSource dynamicDataSource = new DynamicDataSource();  
        
        Map<Object, Object> dbMapping = getDbMapping(dataSourceMap);
        //设置默认数据源  
        dynamicDataSource.setDefaultTargetDataSource(dbMapping.get(dataSourceMap.getDefaultTargetKey()));     
        //配置多数据源  
        dynamicDataSource.setTargetDataSources(dbMapping);              
        return dynamicDataSource;  
    }  
    
    private Map<Object,Object> getDbMapping(DataSourceMap dataSourceMap){
    	Map<String, String> nameMap = dataSourceMap.getNameMap();
    	Map<Object, Object> dsMap = new HashMap<>(nameMap.size());
    	Object ds;
    	for (Entry<String, String> enry : nameMap.entrySet()) {
			ds = getBean(enry.getValue());
			if(ds == null){
				throw new BaseException("未找到注册的数据源: "+ enry.getValue());
			}
			dsMap.put(enry.getKey(), ds);
		}
    	return dsMap;
    }
    
    private Object getBean(String name){
    	return applicationContext.getBean(name, DataSource.class);
    }
      
}  
