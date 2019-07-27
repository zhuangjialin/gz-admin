package com.lam.gz.admin.controller;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.service.MenuResourceService;
import com.lam.gz.admin.vo.MenuResourceVo;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单资源表控制类
 * @date 2017-11-10 13:45:34
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.MENU_RESOURCE)
@Controller
@RequestMapping("menuResource")
public class MenuResourceController extends BaseController {
	
	@Autowired
	private MenuResourceService menuResourceService;
	   
	/**
	 * 新增记录
	 * @date 2017-11-10 13:45:34
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= Operation.ADD, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse add(@RequestBody MenuResourceVo menuResourceVo) {

		String checkRes = checkParam(menuResourceVo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = menuResourceService.insert(menuResourceVo);
		return new MsgResponse().rel(n > 0);
	}
	
	/**
	 * 新增和删除时的共用参数校验
	 * @date 2017-11-10 13:45:34
	 * @author bo.liu01
	 */
	private String checkParam(MenuResourceVo menuResourceVo) {
		if(StringUtil.isBlank(menuResourceVo.getMenuCode())){
			return "菜单编码不能为空";
		}
		if(StringUtil.isBlank(menuResourceVo.getResourceCode())){
			return "资源编码不能为空";
		}
		return null;
	}

	/**
	 * 根据ID删除记录，默认为逻辑删除
	 * @date 2017-11-10 13:45:34
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.DELETE, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse delete(@RequestBody MenuResourceVo menuResourceVo) {

		String checkRes = checkParam(menuResourceVo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}

		int n = menuResourceService.delete(menuResourceVo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
}