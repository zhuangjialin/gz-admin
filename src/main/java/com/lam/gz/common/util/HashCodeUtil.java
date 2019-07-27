package com.lam.gz.common.util;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author liubo
 *
 */
public class HashCodeUtil {
	
	private HashCodeUtil(){
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * 计算hashcode，可以计算复杂对象
	 * @param objects
	 * @return
	 */
	public static int calcHashCodeWithBuilder(Object ...objects){
    	HashCodeBuilder builder = new HashCodeBuilder();
    	if(objects != null && objects.length > 0){
    		for (Object object : objects) {
    			builder.append(object);
			}
    	}
    	return builder.build();
    }
    
	/**
	 * 计算hashcode， 不能计算复杂对象，只能计算简单对象
	 * @param objects
	 * @return
	 */
	public static int calcHashCode(Object ...objects){
    	final int prime = 31;
		int result = 1;
		if(objects == null || objects.length == 0){
			result = prime * result;
		}else{
			for (Object object : objects) {
				result = prime * result + (object == null ? 0 : object.hashCode());
			}
		}
		return result;
    }

}
