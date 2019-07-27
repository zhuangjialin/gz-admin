package com.lam.gz.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.common.exception.BaseException;
import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.exception.LoginException;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;

/**
 * 控制器支持类
 * 
 * @author liubo
 * @date 2016年9月8日 下午4:42:51
 * @version V1.0
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public Logger getLogger(){
		return this.logger;
	}
	
	@ResponseBody
	@ExceptionHandler(value = {BusinessException.class })
	public Object handleBusinessException(BusinessException e) {
		logger.error(e.getMessage());
		return new MsgResponse(e);
	}
	
	@ResponseBody
	@ExceptionHandler(value = {LoginException.class })
	public Object handleLoginException(LoginException e) {
		logger.error(e.getMessage());
		return new MsgResponse(e);
	}

	@ResponseBody
	@ExceptionHandler(BaseException.class)
	public Object baseExceptionHandler(BaseException e) {
		logger.error(e.getMessage(),e);
		return new MsgResponse(e);
	}

	@ResponseBody
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	protected Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error(MsgCode.INVALID_PARAMETER.getMsg(), e);
		return new MsgResponse(MsgCode.INVALID_PARAMETER);
	}

	@ResponseBody
	@ExceptionHandler(value = { RuntimeException.class })
	protected Object handleException(RuntimeException e) {
		logger.error(e.getMessage(), e);
		return new MsgResponse(MsgCode.SYSTEM_EXCEPTION);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Object otherExceptionHandler(Exception e) {
		logger.error(e.getMessage(),e);
		return new MsgResponse(MsgCode.SYSTEM_EXCEPTION);
	}
}
