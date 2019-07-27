package com.lam.gz.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.base.entity.ModuleEntity;
import com.lam.gz.base.entity.ResourceEntity;
import com.lam.gz.log.entity.OperateLogsEntity;

/**
 * 公共的基础操作
 * 
 * @author bo.liu01
 *
 */
@Repository
public interface BaseOperDao {

	/**
	 * 批量插入模块
	 * @param e
	 * @return
	 */
	int insertModule(ModuleEntity e);
	/**
	 * 批量插入模块
	 * @param list
	 * @return
	 */
	int insertModuleBatch(List<ModuleEntity> list);

	/**
	 * 删除所有的模块
	 * @param serviceId
	 * @return
	 */
	int deleteAllModule(@Param("serviceId")String serviceId);

	/**
	 * 激活所有模块
	 * @param serviceId
	 * @return
	 */
	int activeModule(@Param("serviceId")String serviceId);
	

	/**
	 * 批量插入资源
	 * @param e
	 * @return
	 */
	int insertResource(ResourceEntity e);
	/**
	 * 批量插入资源
	 * @param list
	 * @return
	 */
	int insertResourceBatch(List<ResourceEntity> list);

	/**
	 * 删除所有资源
	 * @param serviceId
	 * @return
	 */
	int deleteAllResource(@Param("serviceId")String serviceId);

	/**
	 * 激活所有资源
	 * @param serviceId
	 * @return
	 */
	int activeResource(@Param("serviceId")String serviceId);
	
	/**
	 * 插入操作日志
	 * @param e
	 * @return
	 */
	int insertOperLog(OperateLogsEntity e);
	/**
	 * 批量插入操作日志
	 * @param list
	 * @return
	 */
	int insertOperLogBatch(List<OperateLogsEntity> list);
	
	/**
	 * 根据参数编码查询参数配置项
	 * @param code
	 * @param tenantId
	 * @return
	 */
	String findParam(@Param("code")String code, @Param("tenantId")String tenantId);
	
	/**
	 * 根据id获取数据字典项的编码
	 * @param id
	 * @param tenantId
	 * @return
	 */
	String findFirstDict(@Param("id")String id, @Param("tenantId")String tenantId);
	
	/**
	 * 根据数据字典编码获取该编码下的所有数据字典
	 * @param code
	 * @param tenantId
	 * @return
	 */
	List<Map<String, String>> listDictByCode(@Param("code")String code, @Param("tenantId")String tenantId);
	
	/**
	 * 查询全部的租户，只包含租户id和名称
	 * @return
	 */
	List<Map<String, String>> listAllTenant();
	
	
}
