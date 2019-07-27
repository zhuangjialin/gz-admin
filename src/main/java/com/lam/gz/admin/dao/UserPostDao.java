package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.UserPostEntity;
import com.lam.gz.admin.vo.UserPostVo;

 /**
 * 用户职位表数据接口类
 * @date 2018-7-13 15:07:28
 * @author bo.liu01
 */
@Repository
public interface UserPostDao extends BaseDao<UserPostEntity, UserPostVo>{
	
	String checkExist(UserPostVo vo);

}