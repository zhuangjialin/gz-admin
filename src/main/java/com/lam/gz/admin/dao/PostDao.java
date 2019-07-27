package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.PostEntity;
import com.lam.gz.admin.vo.PostVo;

 /**
 * 职位表数据接口类
 * @date 2017-11-16 14:28:31
 * @author bo.liu01
 */
@Repository
public interface PostDao extends BaseDao<PostEntity, PostVo>{

}