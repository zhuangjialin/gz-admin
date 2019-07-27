package com.lam.gz.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.UserOrgEntity;
import com.lam.gz.admin.vo.UserOrgVo;
import com.lam.gz.common.base.BaseDao;
import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.page.PageVo;

/**
 * 组织人员关系表数据接口类
 * @date 2018-9-4 14:40:24
 * @author yonghui.wu
 */
@Repository
public interface UserOrgDao extends BaseDao<UserOrgEntity, UserOrgVo>{

 PageRes<UserOrgEntity> listByGroupPage(@Param("obj")UserOrgVo vo, @Param("page")PageVo page);
 PageRes<UserOrgEntity> listByUserPage(@Param("obj")UserOrgVo vo, @Param("page")PageVo page);
 
 int deleteLogicByLeader(UserOrgVo vo);

 }