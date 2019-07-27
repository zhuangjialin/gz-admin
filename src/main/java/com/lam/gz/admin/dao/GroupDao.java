package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.GroupEntity;
import com.lam.gz.admin.vo.GroupVo;

 /**
 * 质检小组表数据接口类
 * @date 2018-8-17 18:12:05
 * @author jianming.chen
 */
@Repository
public interface GroupDao extends BaseDao<GroupEntity, GroupVo>{

}