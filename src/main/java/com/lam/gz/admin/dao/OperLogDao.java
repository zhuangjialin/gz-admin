package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.vo.OperLogVo;
import com.lam.gz.common.base.BaseDao;
import com.lam.gz.log.entity.OperateLogsEntity;

 /**
 * 操作日志表数据接口类
 * @date 2018-7-12 10:48:45
 * @author bo.liu01
 */
@Repository
public interface OperLogDao extends BaseDao<OperateLogsEntity, OperLogVo>{

}