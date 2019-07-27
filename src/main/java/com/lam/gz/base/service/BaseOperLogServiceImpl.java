package com.lam.gz.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.gz.base.config.BaseBeanConfig;
import com.lam.gz.base.dao.BaseOperDao;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.util.TokenUtil;
import com.lam.gz.log.entity.OperateLogsEntity;
import com.lam.gz.log.service.OperateLogsService;

/**
 * @author liubo
 *
 */
@Service
public class BaseOperLogServiceImpl extends BaseService implements OperateLogsService<OperateLogsEntity> {
	
	@Autowired
	private BaseOperDao baseOperDao;
	@Autowired
	private BaseBeanConfig baseBeanConfig;

	@Override
	public int insert(OperateLogsEntity t) {
		t.setServiceId(baseBeanConfig.getServiceId());
		t.setTenantId(TokenUtil.getTenantId());
		return baseOperDao.insertOperLog(t);
	}

}
