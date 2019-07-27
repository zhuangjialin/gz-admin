package com.lam.gz.mybatis.dynamic;

/**
 * @author liubo
 *
 */
public class JdbcContextHolder {
	
	private static ThreadLocal<String> threadLocalJdbc = new ThreadLocal<>();
	
	private JdbcContextHolder() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void putDataSource(String dataSource) {
		threadLocalJdbc.set(dataSource);
	}
	
	public static void delDataSource() {
		threadLocalJdbc.remove();
	}
	
	public static String getDataSource() {
		return threadLocalJdbc.get();
	}
	
}