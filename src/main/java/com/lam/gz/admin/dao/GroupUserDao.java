package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.GroupUserEntity;
import com.lam.gz.admin.vo.GroupUserVo;

 /**
 * 质检小组人员表数据接口类
 * @date 2018-8-21 10:28:48
 * @author jianming.chen
 */
@Repository
public interface GroupUserDao extends BaseDao<GroupUserEntity, GroupUserVo>{

     int insertGroupUser(GroupUserVo groupUserVo);
 }