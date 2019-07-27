package com.lam.gz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.common.AdminOperation;
import com.lam.gz.admin.entity.RoleEntity;
import com.lam.gz.admin.service.RoleService;
import com.lam.gz.admin.service.UserRoleService;
import com.lam.gz.admin.vo.RoleVo;
import com.lam.gz.admin.vo.UserRoleVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;

/**
 * 角色表控制类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.ROLE)
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	   
	/**
	 * 分页查询
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(RoleVo roleVo){
		
		return new TableResponse(roleService.listByPage(roleVo));
	}
	
	/**
	 * 查询单条记录，默认根据ID查询，如有修改条件，请在此备注
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper=Operation.SINGLE_QUERY, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="find", method=RequestMethod.GET)
	public ResultResponse find(RoleVo roleVo){

		RoleEntity roleEntity = roleService.find(roleVo);
		if(roleEntity != null){
			return new ObjectResponse(true).data(roleEntity);
		}else{
			return new ObjectResponse(false).msg("该记录不存在");
		}
	}

	/**
	 * 查询编码或者名称是否唯一
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= AdminOperation.CHECK_UNIQUE, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="checkUnique", method=RequestMethod.GET)
	public ResultResponse checkUnique(RoleVo roleVo){

		return new ObjectResponse(true).data(roleService.find(roleVo) != null);
	}
	
	/**
	 * 新增时的参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkAddParam(RoleEntity roleEntity) {
		
		return checkParam(roleEntity);
	}
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= Operation.ADD, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse add(@RequestBody RoleEntity roleEntity) {

		String checkRes = checkAddParam(roleEntity);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		roleEntity = roleService.insert(roleEntity);
		return new MsgResponse().rel(roleEntity != null);
	}
	
	/**
	 * 新增和修改时的共用参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkParam(RoleEntity roleEntity) {
		
		return null;
	}
	
	/**
	 * 修改时的参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkUpdateParam(RoleEntity roleEntity) {
		
		return checkParam(roleEntity);
	}
	
	/**
	 * 修改记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= Operation.UPDATE, operType = OperationType.UPDATE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="update", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse update(@RequestBody RoleEntity roleEntity) {

		String checkRes = checkUpdateParam(roleEntity);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = roleService.update(roleEntity);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
	
	/**
	 * 根据ID删除记录，默认为逻辑删除
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.DELETE, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse delete(@RequestBody RoleVo roleVo) {

		int n = roleService.delete(roleVo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= AdminOperation.ROLE_ADD_USER, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="addUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse addUser(@RequestBody UserRoleVo vo) {

		String checkRes = checkAddParam(vo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = userRoleService.addUser(vo);
		return new MsgResponse().rel(n > 0);
	}

	private String checkAddParam(UserRoleVo vo) {
		if(StringUtil.isBlank(vo.getUsernames())){
			return "用户工号不能为空";
		}
		if(StringUtil.isBlank(vo.getRoleCode())){
			return "角色编码不能为空";
		}
		return null;
	}
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= AdminOperation.ROLE_REMOVE_USER, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="removeUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse removeUser(@RequestBody UserRoleVo vo) {

		String checkRes = checkAddParam(vo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = userRoleService.delete(vo);
		return new MsgResponse().rel(n > 0);
	}

}