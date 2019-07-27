package com.lam.gz.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.UserEntity;
import com.lam.gz.admin.vo.UserVo;
import com.lam.gz.common.base.BaseDao;
import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.page.PageVo;

/**
 * 用户表数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface UserDao extends BaseDao<UserEntity, UserVo>{

  PageRes<UserEntity> listByDepartment(@Param("obj")UserVo userVo, @Param("page")PageVo page);
  
  PageRes<UserEntity> listByDepartmentRs(@Param("obj")UserVo userVo, @Param("page")PageVo page);

  PageRes<UserEntity> listByRole(@Param("obj")UserVo userVo, @Param("page")PageVo page);

  PageRes<UserEntity> listByRoleRs(@Param("obj")UserVo userVo, @Param("page")PageVo page);
  
  PageRes<UserEntity> listByGroup(@Param("obj")UserVo userVo, @Param("page")PageVo page);

  PageRes<UserEntity> listByGroupRs(@Param("obj")UserVo userVo, @Param("page")PageVo page);
  
  List<UserEntity> listAll();
  
  int updatePortrait(UserEntity e);

  


}