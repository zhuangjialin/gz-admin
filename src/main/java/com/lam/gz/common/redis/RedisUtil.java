package com.lam.gz.common.redis;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.lam.gz.common.util.HttpServletUtil;

/**
 * @author liubo
 *
 */
public class RedisUtil {
	
	private static volatile StringRedisTemplate stringRedisTemplate;
	private static final Object LOCK = new Object();
	
	private RedisUtil(){
		throw new IllegalStateException("Utility class");
	}
	
	public static StringRedisTemplate getRedisTemplate(){
		if(stringRedisTemplate == null){
			synchronized (LOCK) {
				if(stringRedisTemplate == null){
					stringRedisTemplate = HttpServletUtil.getBean(StringRedisTemplate.class);
				}
			}
		}
		return stringRedisTemplate;
	}
	
	public static ValueOperations<String, String> opsForValue(){
		return getRedisTemplate().opsForValue();
	}
	
	public static ListOperations<String, String> opsForList(){
		return getRedisTemplate().opsForList();
	}
	
	public static SetOperations<String, String> opsForSet(){
		return getRedisTemplate().opsForSet();
	}
	
	public static ZSetOperations<String, String> opsForZSet(){
		return getRedisTemplate().opsForZSet();
	}
	
	public static HashOperations<String, Object, Object> opsForHash(){
		return getRedisTemplate().opsForHash();
	}
	
	public static Boolean expire(String key, long timeout, TimeUnit unit){
		return getRedisTemplate().expire(key, timeout, unit);
	}
	
	public static Long getExpire(String key){
		return getRedisTemplate().getExpire(key);
	}
	
	public static Long getExpire(String key, TimeUnit timeUnit){
		return getRedisTemplate().getExpire(key, timeUnit);
	}
	
	public static Boolean hasKey(String key){
		return getRedisTemplate().hasKey(key);
	}
	
	public static Set<String> keys(String pattern){
		return getRedisTemplate().keys(pattern);
	}
	
	public static void delete(String key){
		getRedisTemplate().delete(key);
	}
	
	public static void delete(Collection<String> keys){
		getRedisTemplate().delete(keys);
	}
	
}
