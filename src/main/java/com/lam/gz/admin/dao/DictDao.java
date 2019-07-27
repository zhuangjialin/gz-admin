package com.lam.gz.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.DictEntity;
import com.lam.gz.admin.vo.DictVo;
import com.lam.gz.common.base.BaseDao;
import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.page.PageVo;

 /**
 * 数据字典表数据接口类
 * @date 2018-8-15 14:22:43
 * @author bo.liu01
 */
@Repository
public interface DictDao extends BaseDao<DictEntity, DictVo>{
	
	PageRes<DictEntity> listFirstByPage(@Param("obj")DictVo vo, @Param("page")PageVo page);
	
}