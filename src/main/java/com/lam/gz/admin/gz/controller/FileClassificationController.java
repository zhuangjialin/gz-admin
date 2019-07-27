package com.lam.gz.admin.gz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lam.gz.admin.common.AdminModule;
import com.lam.gz.admin.gz.entity.FileClassificationEntity;
import com.lam.gz.admin.gz.service.FileClassificationService;
import com.lam.gz.admin.gz.vo.FileClassificationVo;
import com.lam.gz.common.base.BaseController;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.response.ObjectResponse;
import com.lam.gz.common.response.ResultResponse;
import com.lam.gz.common.response.TableResponse;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.VerifyUtil;
import com.lam.gz.log.annotation.OperateLog;
import com.lam.gz.log.interceptor.ParameterType;
import com.lam.gz.log.operatelog.Operation;
import com.lam.gz.log.operatelog.OperationType;

/**
 * 表控制类
 * @date 2019-7-24 22:04:25
 * @author liubo
 */
@OperateLog(module = AdminModule.FILE_CLASSIFICATION)
@Controller
@RequestMapping("fileClassification")
public class FileClassificationController extends BaseController {
	
	@Autowired
	private FileClassificationService fileClassificationService;
	   
	/**
	 * 分页查询
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	@OperateLog(oper= Operation.QUERY_BY_PAGE, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="listByPage", method=RequestMethod.GET)
	public ResultResponse listByPage(FileClassificationVo vo){
		
		return new TableResponse(fileClassificationService.listByPage(vo));
	}
	
	/**
	 * 不带分页的查询（此方法使用时请注意性能）
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	@OperateLog(oper= Operation.QUERY, parameterType= ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ResultResponse list(FileClassificationVo vo){
		
		return new ObjectResponse(true).data(CollectionUtil.javaList2MapList(fileClassificationService.list(vo), 
				"id", "classificationName=>name"));
	}
	
	/**
	 * 查询单条记录，默认根据ID查询，如有修改条件，请在此备注
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	@OperateLog(oper=Operation.SINGLE_QUERY, parameterType=ParameterType.QUERY)
	@ResponseBody
	@RequestMapping(value="find", method=RequestMethod.GET)
	public ResultResponse find(FileClassificationVo vo){

		FileClassificationEntity entity = fileClassificationService.find(vo);
		if(entity != null){
			return new ObjectResponse(true).data(entity);
		}else{
			return new ObjectResponse(false).msg("该记录不存在");
		}
	}
	
	/**
	 * 新增时的参数校验
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	private String checkAddParam(FileClassificationEntity entity) {
		
		return checkParam(entity);
	}
	
	/**
	 * 新增记录
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 * @return
	 */
	@OperateLog(oper= Operation.ADD, operType = OperationType.INSERT,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse add(@RequestBody FileClassificationEntity entity) {

		VerifyUtil.verify(checkAddParam(entity), Operation.ADD);
		
		entity = fileClassificationService.insert(entity);
		return new MsgResponse().rel(entity != null);
	}
	
	/**
	 * 新增和修改时的共用参数校验
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	private String checkParam(FileClassificationEntity entity) {
		
		return null;
	}
	
	/**
	 * 修改时的参数校验
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	private String checkUpdateParam(FileClassificationEntity entity) {
		
		return checkParam(entity);
	}
	
	/**
	 * 修改记录
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 * @return
	 */
	@OperateLog(oper= Operation.UPDATE, operType = OperationType.UPDATE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="update", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse update(@RequestBody FileClassificationEntity entity) {

		VerifyUtil.verify(checkUpdateParam(entity), Operation.ADD);
		
		int n = fileClassificationService.update(entity);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
	
	/**
	 * 根据ID删除记录，默认为逻辑删除
	 * @date 2019-7-24 22:04:25
	 * @author liubo
	 */
	@OperateLog(oper= Operation.DELETE, operType = OperationType.DELETE,
			parameterType= ParameterType.BODY)
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultResponse delete(@RequestBody FileClassificationVo vo) {

		int n = fileClassificationService.delete(vo);
		return n > 0 ? new MsgResponse(MsgCode.SUCCESS) : new MsgResponse(MsgCode.FAIL).msg("该记录不存在或已删除");
	}
}