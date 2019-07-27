package com.lam.gz.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lam.gz.common.constant.CommonConstants;

/**
 * @author liubo
 *
 */
@Component
public class CommonBeanConfig extends UtilBeanConfig {
	
	public static final long ACCESS_TOKEN_KEEP_TIME_MIN = 600L;
	
	/** 登录session的有效时间  */
	@Value("${access.token.keep-time:0}")
	protected long accessTokenKeepTime;
	
	/** 未登录前白名单url */
	@Value("${access.filter.ignoreAuthUrl:}")
	protected String accessFilterIgnoreAuthUrl;
	/** 登录后的白名单url */
	@Value("${accessFilter.ignoreAuthResUrl:}")
	protected String accessFilterIgnoreAuthResUrl;
	
	public long getAccessTokenKeepTime() {
		if(accessTokenKeepTime < ACCESS_TOKEN_KEEP_TIME_MIN){
			return CommonConstants.ACCESS_TOKEN_TIMEOUT;
		}
		return accessTokenKeepTime;
	}
	
	public String getAccessFilterIgnoreAuthUrl() {
		return accessFilterIgnoreAuthUrl;
	}
	public String getAccessFilterIgnoreAuthResUrl() {
		return accessFilterIgnoreAuthResUrl;
	}

	public static long getAccessTokenKeepTimeMin() {
		return ACCESS_TOKEN_KEEP_TIME_MIN;
	}

}
