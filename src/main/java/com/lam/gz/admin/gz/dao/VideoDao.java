package com.lam.gz.admin.gz.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.gz.entity.VideoEntity;
import com.lam.gz.admin.gz.vo.VideoVo;

 /**
 * 表数据接口类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
@Repository
public interface VideoDao extends BaseDao<VideoEntity, VideoVo>{

}