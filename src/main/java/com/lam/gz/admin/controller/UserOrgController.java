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
import com.lam.gz.admin.entity.UserOrgEntity;
import com.lam.gz.admin.service.UserOrgService;
import com.lam.gz.admin.vo.UserOrgVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.common.util.NumberUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;

/**
 * 组织人员关系表控制类
 * @date 2018-9-4 14:40:25
 * @author yonghui.wu
 */
@OperateLog(module = AdminModule.USERORG)
@Controller
@RequestMapping("userOrg")
public class UserOrgController extends BaseController {
	
	@Autowired
	private UserOrgService userOrgService;
	/**
	 * 分页查询
	 * @date 2018-9-4 14:40:25
	 * @author yonghui.wu
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(UserOrgVo vo){
		
		if(vo.getCodeType() == null || NumberUtil.equals(UserOrgEntity.CODE_TYPE_GROUP, vo.getCodeType())){
			vo.setCodeType(UserOrgEntity.CODE_TYPE_GROUP);
			return new TableResponse(userOrgService.listByGroupPage(vo));
		}else{
			return new TableResponse(userOrgService.listByUserPage(vo));
		}
	}
	
	private String checkAddBatchParam(UserOrgVo vo) {
		if(StringUtil.isBlank(vo.getItemCodes())){
			return "用户工号不能为空";
		}
		if(StringUtil.isBlank(vo.getUpperSuperior())){
			return "上级领导不能为空";
		}
		return null;
	}
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 * @return
	 */
	@OperateLog(oper= AdminOperation.USER_ORG_ADD_USER, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="addUserOrg", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse addUserOrg(@RequestBody UserOrgVo vo) {

		String checkRes = checkAddBatchParam(vo);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		int n = userOrgService.addUserOrg(vo);
		return new MsgResponse().rel(n > 0);
	}
	
	/**
	 * 根据ID删除记录，默认为逻辑删除
	 * @date 2018-9-4 14:40:25
	 * @author yonghui.wu
	 */
	@OperateLog(oper= AdminOperation.USER_ORG_REMOVE_USER, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY, logOperation=false)
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse delete(@RequestBody UserOrgVo vo) {

		if(StringUtil.isBlank(vo.getItemCode())){
			logger.error("小组编码不能为空");
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg("小组编码不能为空");
		}
		vo.setCodeType(UserOrgEntity.CODE_TYPE_GROUP);
		int n = userOrgService.delete(vo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该小组的组织关系已解绑，无需再次解绑!");
	}
}