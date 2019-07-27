package com.lam.gz.admin.gz.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.gz.entity.FileClassificationEntity;
import com.lam.gz.admin.gz.vo.FileClassificationVo;

 /**
 * 表数据接口类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
@Repository
public interface FileClassificationDao extends BaseDao<FileClassificationEntity, FileClassificationVo>{

}