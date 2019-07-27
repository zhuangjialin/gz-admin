package com.lam.gz.common.util.paramter;

/**
 * @author liubo
 *
 */
public interface ParameterConvert {
	
	/**
	 * 转换方法
	 * @param obj 要转换的对象
	 * @param format 给定的格式
	 * @return 转换结果
	 * @date 2017年5月12日
	 * @author liubo04
	 */
	Object convert(Object obj, String format);

}
