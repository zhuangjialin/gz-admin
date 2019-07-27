package com.lam.gz.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.TenantPolicyEntity;
import com.lam.gz.admin.vo.TenantPolicyVo;
import com.lam.gz.common.base.BaseDao;

 /**
 * 租户策略表数据接口类
 * @date 2018-8-1 14:10:22
 * @author bo.liu01
 */
@Repository
public interface TenantPolicyDao extends BaseDao<TenantPolicyEntity, TenantPolicyVo>{

	TenantPolicyEntity findByUsername(@Param("username")String username);

	List<TenantPolicyEntity> listByUsername(@Param("username")String username);

	int clearCurrent(@Param("username")String username);

	int setCurrent(@Param("username")String username, @Param("tenantId")String tenantId);

}