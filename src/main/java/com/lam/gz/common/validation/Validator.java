package com.lam.gz.common.validation;

import java.io.Serializable;

/**
 * @author liubo
 *
 */
public interface Validator extends Serializable{
	
	/**
	 * 校验数据方法，
	 * @param value
	 * @return 校验通过返回null，否则返回提示内容
	 */
	String check(String value);

}
