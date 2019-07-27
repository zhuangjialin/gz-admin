package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.vo.ModuleVo;
import com.lam.gz.base.entity.ModuleEntity;
import com.lam.gz.common.base.BaseDao;

 /**
 * 模块表数据接口类
 * @date 2017-11-13 14:14:20
 * @author bo.liu01
 */
@Repository
public interface ModuleDao extends BaseDao<ModuleEntity, ModuleVo>{

}