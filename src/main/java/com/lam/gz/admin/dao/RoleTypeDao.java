package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.RoleTypeEntity;
import com.lam.gz.admin.vo.RoleTypeVo;

 /**
 * 角色类型数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface RoleTypeDao extends BaseDao<RoleTypeEntity, RoleTypeVo>{

}