package com.lam.gz.admin.dao;

import com.lam.gz.admin.entity.AuthorityEntity;
import com.lam.gz.admin.vo.AuthorityVo;
import com.lam.gz.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限表数据接口类
 *
 * @author bo.liu01
 * @date 2017-11-6 14:06:19
 */
@Repository
public interface AuthorityDao extends BaseDao<AuthorityEntity, AuthorityVo> {

    List<AuthorityEntity> listUserRoleAuth(AuthorityVo authorityVo);
}