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
import com.lam.gz.admin.entity.GroupEntity;
import com.lam.gz.admin.service.GroupService;
import com.lam.gz.admin.service.GroupUserService;
import com.lam.gz.admin.vo.GroupUserVo;
import com.lam.gz.admin.vo.GroupVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.constant.SepConstants;
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
 * 质检小组表控制类
 * @date 2018-8-17 18:12:05
 * @author jianming.chen
 */
@OperateLog(module = AdminModule.GROUP)
@Controller
@RequestMapping("group")
public class GroupController extends BaseController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupUserService groupUserService;
	   
	/**
	 * 分页查询
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(GroupVo groupVo){

		return new TableResponse(groupService.listByPage(groupVo));
	}
	
	/**
	 * 小组下拉列表
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	@OperateLog(oper= Operation.QUERY_FOR_COM, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listForCom", method=RequestMethod.GET)
	public ResultResponse listForCom(GroupVo groupVo){

		return new ObjectResponse(true).data(groupService.list(groupVo));
	}
	
	/**
	 * 查询单条记录，默认根据ID查询，如有修改条件，请在此备注
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	@OperateLog(oper=Operation.SINGLE_QUERY, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="find", method=RequestMethod.GET)
	public ResultResponse find(GroupVo vo){

		GroupEntity entity = groupService.find(vo);
		if(entity != null){
			return new ObjectResponse(true).data(entity);
		}else{
			return new ObjectResponse(false).msg("该记录不存在");
		}
	}
	
	/**
	 * 新增时的参数校验
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	private String checkAddParam(GroupEntity entity) {
		
		return checkParam(entity);
	}
	
	/**
	 * 新增记录
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 * @return
	 */
	@OperateLog(oper= Operation.ADD, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse add(@RequestBody GroupEntity entity) {

		String checkRes = checkAddParam(entity);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		entity = groupService.insert(entity);
		return new MsgResponse().rel(entity != null);
	}
	
	/**
	 * 新增和修改时的共用参数校验
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	private String checkParam(GroupEntity entity) {
		
		return null;
	}
	
	/**
	 * 修改时的参数校验
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	private String checkUpdateParam(GroupEntity entity) {
		
		return checkParam(entity);
	}
	
	/**
	 * 修改记录
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 * @return
	 */
	@OperateLog(oper= Operation.UPDATE, operType = OperationType.UPDATE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="update", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse update(@RequestBody GroupEntity entity) {

		String checkRes = checkUpdateParam(entity);
		if(StringUtil.isNotBlank(checkRes)) {
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = groupService.update(entity);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
	
	/**
	 * 根据ID删除记录，默认为逻辑删除
	 * @date 2018-8-17 18:12:05
	 * @author jianming.chen
	 */
	@OperateLog(oper= Operation.DELETE, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse delete(@RequestBody GroupVo vo) {

		int n = groupService.delete(vo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}

	/**
	 * 查询编码是否唯一
	 * @date 2018-8-15 14:22:43
	 * @author bo.liu01
	 */
	@OperateLog(oper=Operation.CHECK_UNIQUE, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="checkUnique", method=RequestMethod.GET)
	public ResultResponse checkUnique(GroupVo vo){

		return new ObjectResponse(true).data(groupService.find(vo) != null);
	}
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= AdminOperation.GROUP_REMOVE_USER, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="removeUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse removeUser(@RequestBody GroupUserVo vo) {

		String checkRes = checkAddParam(vo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = groupUserService.delete(vo);
		return new MsgResponse().rel(n > 0);
	}

	private String checkAddParam(GroupUserVo vo) {
		if(StringUtil.isBlank(vo.getUsername())){
			return "用户工号不能为空";
		}
		if(StringUtil.isBlank(vo.getGroupCode())){
			return "小组编码不能为空";
		}
		if(vo.getType() == null){
			return "成员类型不能为空";
		}
		if(vo.getType() == GroupEntity.USER_TYPE_LEADER && vo.getUsername().contains(SepConstants.COMMA)){
			return "一个组最多只能有一个组长";
		}
		return null;
	}

	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= AdminOperation.GROUP_ADD_USER, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="addUser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse addUser(@RequestBody GroupUserVo vo) {

		String checkRes = checkAddParam(vo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = groupUserService.addUser(vo);
		return new MsgResponse().rel(n > 0);
	}
}