package com.lam.gz.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lam.gz.admin.entity.DepartmentUserEntity;
import com.lam.gz.admin.vo.DepartmentUserVo;
import com.lam.gz.base.common.BaseRedisConstant;
import com.lam.gz.base.dao.BaseUserDepartDao;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.redis.RedisUtil;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.StringUtil;

/**
 * @author liubo
 *
 */
@Service
public class BaseUserDepartService extends BaseService{
	
	@Autowired
	private BaseUserDepartDao baseUserDepartDao;
	
	/**
	 * 根据用户工号清除用户的机构缓存
	 * @param username 
	 * @param tenantId 租户id
	 * @return
	 */
	public boolean clearGroupCache(String username, String tenantId) {
		RedisUtil.opsForHash().delete(BaseRedisConstant.getUserDepartKey(tenantId), username);
		return true;
	}
	
	/**
	 * 清除全部的用户的机构缓存
	 * @param tenantId 租户id
	 * @return
	 */
	public boolean clearGroupCache(String tenantId) {
		RedisUtil.delete(BaseRedisConstant.getUserDepartKey(tenantId));
		return true;
	}
	
	/**
	 * 重新加载全部的用户的机构缓存
	 * @param tenantId 租户id
	 * @return
	 */
	public void reloadGroupCache(String tenantId) {
		String key = BaseRedisConstant.getUserDepartKey(tenantId);
		RedisUtil.delete(key);
		List<DepartmentUserEntity> list = baseUserDepartDao.listDepartByUser(new DepartmentUserVo().tenantId(tenantId));
		if(CollectionUtil.isEmpty(list)){
			return ;
		}
		
		for (DepartmentUserEntity entity : list) {
			RedisUtil.opsForHash().put(key, entity.getUsername(), JSONObject.toJSONString(entity));
		}
	}
	
	/**
	 * 根据用户工号获取的机构
	 * @param username
	 * @param tenantId
	 * @return
	 */
	public DepartmentUserEntity getDepart(String username, String tenantId) {
		if(StringUtil.isBlank(username) || StringUtil.isBlank(tenantId)){
			return null;
		}
		
		//先从redis缓存中获取
		String key = BaseRedisConstant.getUserDepartKey(tenantId);
		Object object = RedisUtil.opsForHash().get(key, username);
		String value;
		if(object != null){
			value = String.valueOf(object);
			if(StringUtil.isNotBlank(value)){
				return JSONObject.parseObject(value, DepartmentUserEntity.class);
			}
		}
		DepartmentUserVo vo = new DepartmentUserVo().tenantId(tenantId);
		vo.setUsername(username);
		List<DepartmentUserEntity> list = baseUserDepartDao.listDepartByUser(vo);
		if(CollectionUtil.isEmpty(list)){
			logger.error("该用户没有配置机构, username:{}", username);
			return null;
		}
		if(list.size() > 1){
			logger.warn("该用户数据有的机构{}条, 将默认取第一条, username:{}", list.size(), username);
		}
		DepartmentUserEntity entity = list.get(0);
		RedisUtil.opsForHash().put(key, username, JSONObject.toJSONString(entity));
		return entity;
	}
	

}
