package com.lam.gz.common.constant;

/**
 * @author liubo
 *
 */
public class UtilRedisConstant {
	
	/** redis分布式锁的key, 锁标识 */
	private static final String LOCK_KEY_FORMAT = "REDIS_LOCK:%s";
	
	
	/**
	 * @return redis分布式锁的key
	 */
	public static String getLockKey(String key){
		return String.format(LOCK_KEY_FORMAT, key);
	}
	
}
