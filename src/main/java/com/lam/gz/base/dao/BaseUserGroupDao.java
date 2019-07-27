package com.lam.gz.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.GroupUserEntity;
import com.lam.gz.admin.vo.GroupUserVo;

/**
 * 公共的用户组基础操作
 * 
 * @author bo.liu01
 *
 */
/**
 * @author liubo
 *
 */
@Repository
public interface BaseUserGroupDao {

	/**
	 * 获取小组组织关系
	 * @param vo
	 * @return
	 */
	List<GroupUserEntity> listGroupByUser(GroupUserVo vo);
	
	

}
