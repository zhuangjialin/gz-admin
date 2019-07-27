package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.UserAgentEntity;
import com.lam.gz.admin.vo.UserAgentVo;

 /**
 * 坐席工号表数据接口类
 * @date 2018-9-7 15:33:14
 * @author yonghui.wu
 */
@Repository
public interface UserAgentDao extends BaseDao<UserAgentEntity, UserAgentVo>{
    boolean checkAgentNoUnique(UserAgentEntity vo);
}