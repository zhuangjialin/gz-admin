package com.lam.gz.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.UserAgentEntity;
import com.lam.gz.admin.vo.UserAgentVo;

/**
 * @author liubo
 *
 */
@Repository
public interface BaseUserAgentDao extends BaseDao<UserAgentEntity, UserAgentVo>{

	/**
	 * 查询用户工号
	 * @param vo
	 * @return
	 */
	List<UserAgentEntity> listUserAgent(UserAgentVo vo);
}