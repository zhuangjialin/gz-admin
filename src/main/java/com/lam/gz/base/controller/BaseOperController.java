package com.lam.gz.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.base.common.BaseModule;
import com.lam.gz.base.common.BaseOperation;
import com.lam.gz.base.service.BaseOperService;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;

/**
 * @author liubo
 *
 */
@OperateLog(module = BaseModule.MODULE_RESOURCE)
@Controller
@RequestMapping("baseOper")
public class BaseOperController extends BaseController {
	
	@Autowired
	private BaseOperService baseOperService;
	/**
	 * 重新刷新资源表数据，此接口调用后会自动从代码中提取接口路径保存到数据库
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= BaseOperation.REFLUSH_RES, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="reflush", method=RequestMethod.GET)
	public ResultResponse reflush(){

		int n = baseOperService.reflush();
		return new ObjectResponse(n > 0).data(n);
	}

}
