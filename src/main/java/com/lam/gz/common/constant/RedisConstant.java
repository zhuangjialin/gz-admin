package com.lam.gz.common.constant;

import com.lam.gz.common.util.StringUtil;

/**
 * @author liubo
 *
 */
public class RedisConstant extends UtilRedisConstant {
	
	/** Redis中普通键的存放时间，单位秒 */
	public static final long DEFAULT_KEY_TIMEOUT = 3600*24*10;
	
	/** feign调用时非认证情况下需要传递的认证信息  redis的key */
	private static final String FEIGN_TRACE_ID_KEY = "FEIGN_TRACE_ID:%s*";
	
	/** 用户登录session redis的key */
	private static final String LOGIN_SESSION_PATTERN = "SESSION:%s*";
	/** 用户登录session信息redis的key */
	private static final String LOGIN_SESSION_INFO = "SESSION:%s:INFO";
	/** 用户登录session权限编码redis的key */
	private static final String LOGIN_SESSION_AUTH = "SESSION:%s:AUTH";
	/** 用户登录session资源链接redis的key */
	private static final String LOGIN_SESSION_RES = "SESSION:%s:RES";
	/** 用户登录session路由(菜单)redis的key */
	private static final String LOGIN_SESSION_ROUTE = "SESSION:%s:ROUTE";
	
	/** 全局的所有菜单redis的key */
	public static final String ALL_MENU_KEY = "MENU";
	/** 全局的所有机构redis的key, 租户id */
	private static final String ALL_DEPARTMENT_KEY = "DEPART:%s";
	/** 全局的所有数据字典redis的key, 数据字典编码 */
	private static final String ALL_DICT_KEY = "DICT:%s";
	/** 全局的所有参数配置redis的key, 参数编码 */
	private static final String ALL_PARAM_KEY = "PARAM:%s";
	
	/**
	 * @return feign调用时非认证情况下需要传递的认证信息 redis的key
	 */
	public static String getFeignTraceIdKey(String traceId){
		return String.format(FEIGN_TRACE_ID_KEY, traceId);
	}
	
	/**
	 * @return 用户登录session redis的key pattern
	 */
	public static String getLoginSessionPattern(String token){
		return String.format(LOGIN_SESSION_PATTERN, token);
	}
	/**
	 * @return 用户登录session信息redis的key 
	 */
	public static String getLoginSessionInfoKey(String token){
		return String.format(LOGIN_SESSION_INFO, token);
	}
	/**
	 * @return 用户登录session权限编码redis的key 
	 */
	public static String getLoginSessionAuthKey(String token){
		return String.format(LOGIN_SESSION_AUTH, token);
	}
	/**
	 * @return 用户登录session资源链接redis的key 
	 */
	public static String getLoginSessionResKey(String token){
		return String.format(LOGIN_SESSION_RES, token);
	}
	/**
	 * @return 用户登录session路由(菜单)redis的key 
	 */
	public static String getLoginSessionRouteKey(String token){
		return String.format(LOGIN_SESSION_ROUTE, token);
	}
	
	/**
	 * @return 当前租户所有机构redis的key
	 */
	public static String getDepartKey(String tenantId){
		return String.format(ALL_DEPARTMENT_KEY, tenantId);
	}
	
	/**
	 * 根本编码获取数据字典键值对
	 * @param code
	 * @param tenantId 
	 * @return
	 */
	public static String getDictKey(String code, String tenantId) {
		if(StringUtil.isBlank(tenantId)){
			return String.format(ALL_DICT_KEY, code);
		}
		return String.format(ALL_DICT_KEY, String.format("%s:%s", tenantId, code));
	}

	/**
	 * 根据编码获取参数值
	 * @param code
	 * @param tenantId 
	 * @return
	 */
	public static String getParamKey(String code, String tenantId) {
		if(StringUtil.isBlank(tenantId)){
			return String.format(ALL_PARAM_KEY, code);
		}
		return String.format(ALL_PARAM_KEY, String.format("%s:%s", tenantId, code));
	}
	

}
