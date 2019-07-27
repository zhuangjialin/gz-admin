package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.UserRoleEntity;
import com.lam.gz.admin.vo.UserRoleVo;

 /**
 * 用户角色表数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface UserRoleDao extends BaseDao<UserRoleEntity, UserRoleVo>{



}