package com.lam.gz.impexp.excel.interceptor;

import java.util.List;

/**
 * @author liubo
 *
 * @param <T>
 */
public class ImportInterceptorAdaptor<T> implements ImportInterceptor<T>{

	@Override
	public boolean beforeImport(List<T> list) {
		return true;
	}

	@Override
	public boolean beforeWrite(List<T> list) {
		return true;
	}

}
