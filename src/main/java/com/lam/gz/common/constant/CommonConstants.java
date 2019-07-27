package com.lam.gz.common.constant;

/**
 * @author liubo
 *
 */
public class CommonConstants extends UtilConstants{
	
	/** 系统默认的租户id  */
	public static final String DEFAULT_TENANT_ID = "DEFAULT";

	/** 认证的header名称  */
	public static final String AUTH_HEADER_NAME = "Authorization";
	/** feign认证的header名称  */
	public static final String FEIGN_AUTH_HEADER_NAME = "X-Auth-Feign";
	/** feign调用serviceid的header名称  */
	public static final String FEIGN_SERVICE_HEADER_NAME = "X-Service-Id";
	/** 认证的parameter名称  */
	public static final String AUTH_PARAMETER_NAME = "token";
	/** 登录用户的Token名称  */
	public static final String ACCESS_TOKEN_NAME = "accessToken";
	/** 默认的登录用户的accessToken有效时间  */
	public static final long ACCESS_TOKEN_TIMEOUT = 7200;
	/** 默认的feign调用非认证情况下认证信息的有效时间  */
	public static final long FEIGN_AUTH_TIMEOUT = 60;
	
    /** 业务线 数据字典key */
	public static final String DICT_BUSINESS_KEY = "quality.business";
    
	/** 话务平台, cisco */
	public static final String PLATFORM_CISCO = "cisco";
	/** 话务平台, avaya */
	public static final String PLATFORM_AVAYA = "avaya";

	/** 定时器操作的操作人名称 */
	public static final String TIMER_OPER_NAME = "定时器";
	/** 定时器操作的操作人 */
	public static final String TIMER_OPER_USER = "timer";
	
	/** 数据来源-租户映射 数据字典key */
	public static final String TAPE_RELATES_DICT_CODE = "tapeRelates";
	
	/** 1M的字节大小 */
	public static final long ONE_M = 1024*1024L;
	
}
