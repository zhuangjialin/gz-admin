package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.ParamEntity;
import com.lam.gz.admin.vo.ParamVo;

 /**
 * 参数配置表数据接口类
 * @date 2018-8-15 14:22:43
 * @author bo.liu01
 */
@Repository
public interface ParamDao extends BaseDao<ParamEntity, ParamVo>{

}