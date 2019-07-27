package com.lam.gz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.service.ResourceService;
import com.lam.gz.admin.vo.ResourceVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;

/**
 * 资源表控制类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.RESOURCE)
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;
	   
	/**
	 * 分页查询
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(ResourceVo resourceVo){
		
		return new TableResponse(resourceService.listByPage(resourceVo));
	}

}