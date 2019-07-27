package com.lam.gz.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author bo.liu01 on 2017/11/23.
 * @author liubo
 */
/**
 * @author liubo
 *
 */
public class Env2BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(Env2BeanUtil.class);
    
    private Env2BeanUtil(){
		throw new IllegalStateException("Utility class");
	}

    public static <T> T transfer(Environment env, T bean, String prefix){

        prefix = StringUtil.isNotBlank(prefix) ? prefix + "." : "";

        List<Field> fields = BeanUtil.getBusinessFields(bean.getClass());
        String val;
        for(Field fd:fields){
            val = env.getProperty(prefix + fd.getName());
            if(val != null){
            	try {
            		// 获得set方法
        			Method setMethod = new PropertyDescriptor(fd.getName(), bean.getClass()).getWriteMethod();
        			if(setMethod != null){
        				setMethod.invoke(bean, val);
        			}
        		} catch (Exception e) {
        			logger.debug(e.getMessage(), e);
        		}
            }
        }
        return bean;
    }
}
