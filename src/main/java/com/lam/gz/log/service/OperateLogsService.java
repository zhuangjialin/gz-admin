package com.lam.gz.log.service;

import com.lam.gz.log.entity.OperateLogsEntity;

/**
 * @author bo.liu01 on 2017/11/1.
 */
public interface OperateLogsService<T extends OperateLogsEntity> {

    /**
     * 插入操作日志
     * @param t
     * @return
     */
    int insert(T t);
}
