package com.lam.gz.admin.dao;

import com.lam.gz.admin.entity.UserEntity;
import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.LoginUserEntity;
import com.lam.gz.admin.vo.LoginUserVo;

/**
 * 用户信息表数据接口类
 * 
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface LoginUserDao extends BaseDao<LoginUserEntity, LoginUserVo> {

	UserEntity findByUsername(String username);
	
	LoginUserEntity findForLogin(String username);

}