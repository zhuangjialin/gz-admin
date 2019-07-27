package com.lam.gz.common.util.collectioninter;

/**
 * @author liubo
 *
 * @param <T>
 * @param <P>
 */
public interface CollectionProperty<T, P> {

	/**
	 * 用于获取集合的属性操作
	 * @param t
	 * @return
	 */
	P getProperty(T t);

}
