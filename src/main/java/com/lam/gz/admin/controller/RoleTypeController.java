package com.lam.gz.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.common.AdminOperation;
import com.lam.gz.admin.entity.RoleTypeEntity;
import com.lam.gz.admin.service.RoleTypeService;
import com.lam.gz.admin.vo.RoleTypeVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.TokenUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;

/**
 * 角色类型控制类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.ROLE_TYPE)
@Controller
@RequestMapping("roleType")
public class RoleTypeController extends BaseController {
	
	@Autowired
	private RoleTypeService roleTypeService;
	   
	/**
	 * 分页查询
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(RoleTypeVo roleTypeVo){
		
		return new TableResponse(roleTypeService.listByPage(roleTypeVo));
	}
	
	/**
	 * 不带分页的查询（此方法使用时请注意性能）
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= Operation.QUERY, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listForCom", method=RequestMethod.GET)
	public ResultResponse listForCom(RoleTypeVo roleTypeVo){

		List<RoleTypeEntity> list = roleTypeService.list(roleTypeVo);
		return new ObjectResponse(true).data(CollectionUtil.javaList2MapList(list, "id", "name"));
	}

	/**
	 * 查询单条记录，默认根据ID查询，如有修改条件，请在此备注
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper=Operation.SINGLE_QUERY, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="find", method=RequestMethod.GET)
	public ResultResponse find(RoleTypeVo roleTypeVo){

		RoleTypeEntity roleTypeEntity = roleTypeService.find(roleTypeVo);
		if(roleTypeEntity != null){
			return new ObjectResponse(true).data(roleTypeEntity);
		}else{
			return new ObjectResponse(false).msg("该记录不存在");
		}
	}

	/**
	 * 查询名称是否唯一
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@OperateLog(oper= AdminOperation.CHECK_UNIQUE, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="checkUnique", method=RequestMethod.GET)
	public ResultResponse checkUnique(String name, String id){

		RoleTypeVo roleTypeVo = new RoleTypeVo();
		roleTypeVo.setId(id);
		roleTypeVo.setName(name.trim());
		roleTypeVo.setTenantId(TokenUtil.getTenantId());
		return new ObjectResponse(true).data(roleTypeService.checkUnique(roleTypeVo) != null);
	}

	/**
	 * 新增时的参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkAddParam(RoleTypeEntity roleTypeEntity) {
		
		return checkParam(roleTypeEntity);
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
	public ResultResponse add(@RequestBody RoleTypeEntity roleTypeEntity) {

		String checkRes = checkAddParam(roleTypeEntity);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		roleTypeEntity = roleTypeService.insert(roleTypeEntity);
		return new MsgResponse().rel(roleTypeEntity != null);
	}
	
	/**
	 * 新增和修改时的共用参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkParam(RoleTypeEntity roleTypeEntity) {
		
		return null;
	}
	
	/**
	 * 修改时的参数校验
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	private String checkUpdateParam(RoleTypeEntity roleTypeEntity) {

		return checkParam(roleTypeEntity);
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
	public ResultResponse update(@RequestBody RoleTypeEntity roleTypeEntity) {

		String checkRes = checkUpdateParam(roleTypeEntity);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = roleTypeService.update(roleTypeEntity);
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
	public ResultResponse deleteLogic(@RequestBody RoleTypeVo roleTypeVo) {

		int n = roleTypeService.delete(roleTypeVo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
}