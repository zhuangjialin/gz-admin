package com.lam.gz.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.service.ModuleService;
import com.lam.gz.admin.vo.ModuleVo;
import com.lam.gz.base.entity.ModuleEntity;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;

/**
 * 模块表控制类
 * @date 2017-11-13 14:14:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.MODULE)
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
	
	@Autowired
	private ModuleService moduleService;
	   
	/**
	 * 分页查询
	 * @date 2017-11-13 14:14:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(ModuleVo moduleVo){
		
		return new TableResponse(moduleService.listByPage(moduleVo));
	}

	/**
	 * 不带分页的查询,适用于下拉列表
	 * @date 2017-11-13 14:14:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listForCom", method=RequestMethod.GET)
	public ResultResponse list(ModuleVo moduleVo){
		List<ModuleEntity> list = moduleService.list(moduleVo);
		return new ObjectResponse(true).data(CollectionUtil.javaList2MapList(list,
				"name", "code"));
	}
	
}