package com.lam.gz.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.response.MsgCode;

/**
 * @author liubo
 *
 */
public class VerifyUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(VerifyUtil.class);
	
	private VerifyUtil() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void verify(String msg, String oper){
		if(msg != null){
			logger.error("{},{}", oper, msg);
			throw new BusinessException(msg, MsgCode.INVALID_PARAMETER.getCode());
		}
	}

}
