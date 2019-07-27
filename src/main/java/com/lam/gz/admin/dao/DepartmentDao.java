package com.lam.gz.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.DepartmentEntity;
import com.lam.gz.admin.vo.DepartmentVo;

 /**
 * 部门表数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface DepartmentDao extends BaseDao<DepartmentEntity, DepartmentVo>{

	List<DepartmentEntity> listByTenantId(@Param("tenantId")String tenantId);

}