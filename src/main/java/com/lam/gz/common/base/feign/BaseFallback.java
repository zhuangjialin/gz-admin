package com.lam.gz.common.base.feign;

import com.alibaba.fastjson.JSON;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.response.ResultResponse;

/**
 * @author liubo
 *
 */
public abstract class BaseFallback extends BaseService{
	
	protected ResultResponse defaultFallback(Object param, String msg) {
		logger.error("{}, param:{}", msg, JSON.toJSONString(param));
		return new ResultResponse().msg(msg);
	}

}
