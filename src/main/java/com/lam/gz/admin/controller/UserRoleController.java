package com.lam.gz.admin.controller;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.service.UserRoleService;
import com.lam.gz.admin.vo.UserRoleVo;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ObjectResponse;
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
 * 用户角色表控制类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.USER_ROLE)
@Controller
@RequestMapping("userRole")
public class UserRoleController extends BaseController {
	
	@Autowired
	private UserRoleService userRoleService;
	   
	/**
	 * 分页查询
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listRole", method=RequestMethod.GET)
	public ResultResponse listRole(UserRoleVo userRoleVo){
		
		return new ObjectResponse(true).data(userRoleService.listRole(userRoleVo));
	}
	
	/**
	 * 新增时的参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkAddParam(UserRoleVo userRoleVo) {
		if(StringUtil.isBlank(userRoleVo.getUsername())){
			return "用户名不能为空";
		}
		if(StringUtil.isBlank(userRoleVo.getAddRole()) && StringUtil.isBlank(userRoleVo.getDelRole())){
			return "角色不能为空";
		}
		return null;
	}
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= Operation.SAVE, operType = OperationType.SAVE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="save", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse save(@RequestBody UserRoleVo userRoleVo) {

		String checkRes = checkAddParam(userRoleVo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = userRoleService.insert(userRoleVo);
		return new MsgResponse().rel(n > 0);
	}
	
}