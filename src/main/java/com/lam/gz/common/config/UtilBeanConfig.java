package com.lam.gz.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lam.gz.common.constant.UtilConstants;
import com.lam.gz.common.util.StringUtil;

/**
 * @author liubo
 *
 */
@Component
public class UtilBeanConfig {
	
	public static final long ACCESS_TOKEN_KEEP_TIME_MIN = 600L;
	
	/** 应用的id */
	@Value("${spring.application.name:}")
	protected String applicationName;
	/** 应用的上下文根路径 */
	@Value("${server.context-path:}")
	protected String contextPath;
	
	/** 导入文件时默认的存储位置 */
	@Value("${import.tmp.path:}")
	protected String importTmpFilePath;

	public static long getAccessTokenKeepTimeMin() {
		return ACCESS_TOKEN_KEEP_TIME_MIN;
	}

	public String getImportTmpFilePath() {
		if(StringUtil.isBlank(importTmpFilePath)){
			return UtilConstants.IMPORT_TMP_FILE_PATH;
		}
		return importTmpFilePath;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
}
