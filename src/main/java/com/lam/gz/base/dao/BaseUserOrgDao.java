package com.lam.gz.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.UserOrgEntity;
import com.lam.gz.admin.vo.UserOrgVo;

/**
 * 公共的用户组织关系基础操作
 * 
 * @author bo.liu01
 *
 */
@Repository
public interface BaseUserOrgDao {

	/**
	 * 获取用户组织关系
	 * @param vo
	 * @return
	 */
	List<UserOrgEntity> listUserOrg(UserOrgVo vo);
	

}
