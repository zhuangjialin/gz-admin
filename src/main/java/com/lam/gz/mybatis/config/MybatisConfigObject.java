package com.lam.gz.mybatis.config;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;

import com.lam.gz.common.util.StringUtil;
import com.lam.gz.mybatis.interceptor.PagingInterceptor;

/**
 * @author bo.liu01 on 2017/11/23.
 * @author liubo
 *
 */
public class MybatisConfigObject {

    private String mapperLocations;
    private String typeAliasesPackage;
    private String basePackage;
    private String mapUnderscoreToCamelCase;

    private Interceptor[] plugins =  new Interceptor[]{
            new PagingInterceptor()
    };

    public Properties getProperties(){
    	Properties p = new Properties();
    	if(StringUtil.isNotBlank(mapUnderscoreToCamelCase)){
    		p.setProperty("mapUnderscoreToCamelCase", mapUnderscoreToCamelCase);
    	}
    	return p;
    }
    
    public Interceptor[] getPlugins() {
        return plugins;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

	public String getMapUnderscoreToCamelCase() {
		return mapUnderscoreToCamelCase;
	}

	public void setMapUnderscoreToCamelCase(String mapUnderscoreToCamelCase) {
		this.mapUnderscoreToCamelCase = mapUnderscoreToCamelCase;
	}
}
