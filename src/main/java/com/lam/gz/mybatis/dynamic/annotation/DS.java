package com.lam.gz.mybatis.dynamic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lam.gz.mybatis.dynamic.DataSourceConstant;

/**
 * @author liubo
 *
 */
@Retention(RetentionPolicy.RUNTIME)    
@Target(ElementType.METHOD)   
public @interface DS {  
  
    /**
     * 数据源，默认主库
     * @return
     */
    String value() default DataSourceConstant.MASTER;  
      
} 
