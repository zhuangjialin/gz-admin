package com.lam.gz.base.common;

import com.lam.gz.common.util.StringUtil;

/**
 * @author liubo
 *
 */
public enum PlatformEnum {
	
	/** 思科话务平台  */
	CISCO("cisco");
	
	private String key;
	
	private PlatformEnum(String key){
		this.key = key;
	}
	
	public static PlatformEnum get(String key){
		if(StringUtil.isBlank(key)){
			return null;
		}
		for (PlatformEnum e : PlatformEnum.values()) {
			if(StringUtil.equalsIgnoreCase(key, e.getKey())){
				return e;
			}
		}
		return null;
		
	}

	public String getKey() {
		return key;
	}

}
