package com.lam.gz.impexp.excel.exe;

import java.util.List;

import com.lam.gz.impexp.excel.interceptor.ImportInterceptor;

/**
 * @author liubo
 *
 * @param <T>
 */
public interface ExcelImpExecutor<T extends Object> {
	
	/**
	 * 返回导入执行的拦截器
	 * @return 拦截器
	 */
	ImportInterceptor<T> getInterceptor();

	/**
	 * 执行导入操作
	 * @param addList 要导入的数据list
	 * @return 导入后的数据list，跟原始的一样
	 * @date 2017年4月7日
	 * @author liubo04
	 */
	List<T> execute(List<T> addList);

}
