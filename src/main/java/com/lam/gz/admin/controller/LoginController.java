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
import com.lam.gz.admin.common.AdminConstant;
import com.lam.gz.admin.entity.FrontUser;
import com.lam.gz.admin.entity.LoginResult;
import com.lam.gz.admin.entity.LoginUserEntity;
import com.lam.gz.admin.service.LoginService;
import com.lam.gz.base.service.BaseCacheUtil;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.util.Md5Algorithm;
import com.lam.gz.common.util.NumberUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.TokenUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;

/**
 * 登录登出操作控制类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@OperateLog(module = AdminModule.LOGIN_USER)
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	
	private String checkParam(LoginUserEntity loginUser) {
		if(StringUtil.isBlank(loginUser.getUsername())){
			return "登录名不能为空";
		}
		if(StringUtil.isBlank(loginUser.getPassword())){
			return "密码不能为空";
		}
		if(StringUtil.isBlank(loginUser.getLoginType())){
			loginUser.setLoginType(LoginUserEntity.LOGIN_TYPE_SIMPLE);
		}
		return null;
	}
	
	
	@OperateLog(oper= AdminOperation.LOGIN_IN, injectCurntUser=false, injectCurntTenant=false)
	@ResponseBody
	@RequestMapping(value = "loginIn", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse loginIn(@RequestBody LoginUserEntity loginUser) {
		String checkRes = checkParam(loginUser);
		if(StringUtil.isNotBlank(checkRes)){
			logger.error(checkRes);
			return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
		}
		LoginResult loginResult = loginService.authentication(loginUser);
		if(loginResult.isSuccess()){
			return new ObjectResponse(true).data(loginResult.getData());
		}else{
			return new MsgResponse(MsgCode.INCORRECT_USER_PSWW).msg(loginResult.getMsg());
		}
	}
	   
	@OperateLog(oper= AdminOperation.FRONT_USER, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value = "/front/info", method = RequestMethod.GET)
	public ResultResponse getUserInfo() {
		FrontUser userInfo = loginService.getUserInfo(TokenUtil.getUsername());
		return userInfo==null ? new MsgResponse(MsgCode.UN_LOGIN_IN):
							new ObjectResponse(true).data(userInfo);
	}
	
	@OperateLog(oper= AdminOperation.LOGIN_OUT, parameterType= ParameterType.QUERY, injectCurntTenant=false)
	@ResponseBody
	@RequestMapping(value = "loginOut")
	public ResultResponse loginOut() {
        
		loginService.loginOut();
        return new MsgResponse(MsgCode.SUCCESS).msg("登出成功!");
	}
	/**
	 * @desc:修改密码
	 * 1、用户自己登陆后台修改个人密码
	 * 2、管理员重置用户密码
	 * @param: [loginUserEntity]
	 * @return: com.lam.gz.common.response.ResultResponse
	 * @auther: yonghui.wu
	 * @date: 2018/9/11 13:29
	 */
	@OperateLog(oper= AdminOperation.CHANGE_PAWD, injectCurntTenant=false)
	@ResponseBody
	@RequestMapping(value = "changePassword",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse changePassword(@RequestBody LoginUserEntity loginUserEntity) {
		
		loginUserEntity.setTenantId(TokenUtil.getActualTenantId());
		if(NumberUtil.equals(LoginUserEntity.OPER_TYPE_CHANGE_PASSWORD, loginUserEntity.getOperType())){
			loginUserEntity.setUsername(loginUserEntity.getOperUser());
			String checkRes = checkPasswordParam(loginUserEntity);
			if(StringUtil.isNotBlank(checkRes)){
				logger.error(checkRes);
				return new MsgResponse(MsgCode.INVALID_PARAMETER).msg(checkRes);
			}
			loginUserEntity.setPassword(Md5Algorithm.getInstance().md5Encode(loginUserEntity.getNewPassword()));
		}else if(NumberUtil.equals(LoginUserEntity.OPER_TYPE_RESET_PASSWORD, loginUserEntity.getOperType())){
			String passwd = BaseCacheUtil.getParam(AdminConstant.PARAM_DEFAULT_PASSWORD, loginUserEntity.getTenantId());
			if(StringUtil.isBlank(passwd)){
				passwd = AdminConstant.DEFAULT_PASSWD;
			}
			loginUserEntity.setPassword(Md5Algorithm.getInstance().md5Encode(passwd));
		}else{
			return new MsgResponse(MsgCode.INVALID_PARAMETER);
		}
		int n = loginService.update(loginUserEntity);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("修改失败");
	}
	
	private String checkPasswordParam(LoginUserEntity loginUserEntity) {
		LoginResult loginResult = loginService.dbLoginAuth(loginUserEntity);
		if(!loginResult.isSuccess()){
			return loginResult.getMsg();
		}
		return null;
	}
	
}