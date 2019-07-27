package com.lam.gz.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.DepartmentUserEntity;
import com.lam.gz.admin.vo.DepartmentUserVo;

/**
 * 公共的用户机构基础操作
 * 
 * @author bo.liu01
 *
 */
@Repository
public interface BaseUserDepartDao {

	/**
	 * 获取用户的机构信息
	 * @param vo
	 * @return
	 */
	List<DepartmentUserEntity> listDepartByUser(DepartmentUserVo vo);
	
	

}
