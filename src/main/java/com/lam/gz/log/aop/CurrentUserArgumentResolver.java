package com.lam.gz.log.aop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lam.gz.common.base.BaseTable;
import com.lam.gz.common.util.DateUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.TokenUtil;
import com.lam.gz.log.interceptor.OperateLogHandlerUtil;
import com.lam.gz.log.interceptor.ParamSet;
import com.lam.gz.log.operatelog.OperationType;

/**
 * @author liubo
 *
 */
public class CurrentUserArgumentResolver {

	private static final Logger logger = LoggerFactory.getLogger(CurrentUserArgumentResolver.class);
	
	private CurrentUserArgumentResolver() {
		throw new IllegalStateException("Utility class");
	}

	private static boolean check(ParamSet paramSet){
		if (paramSet == null) {
			logger.warn("未启用OperateLog注解");
			return false;
		}
		return paramSet.isInjectCurntTenant() || paramSet.isInjectCurntDate() || paramSet.isInjectCurntUser();
	}
	
	public static void inject(Object[] args) {
		if(args == null || args.length == 0){
			return ;
		}
		ParamSet paramSet = OperateLogHandlerUtil.getCurrentParamSet();

		if(!check(paramSet)){
			return;
		}
		
		logger.debug("注入当前用户所在的租户、当前用户、当前时间");
		for (Object param : args) {
			if(param instanceof BaseTable){
				initData(paramSet, (BaseTable)param);
			}
		}
	}

	private static void initData(ParamSet paramSet, BaseTable param) {
		if(paramSet.isInjectCurntTenant()){
			String tenantId = TokenUtil.getTenantId();
			if(StringUtil.isNotBlank(tenantId)){
				param.setTenantId(tenantId);
			}
		}
		if (paramSet.isInjectCurntDate() && needInject(paramSet)) {
			param.setOperTime(DateUtil.getCurrentTime());
		}
		if (paramSet.isInjectCurntUser() && needInject(paramSet)) {
			String username = TokenUtil.getUsername();
			if(StringUtil.isNotBlank(username)){
				param.setOperUser(username);
			}
			String currentName = TokenUtil.getCurrentName();
			if(StringUtil.isNotBlank(currentName)){
				param.setOperName(currentName);
			}
		}
	}

	private static boolean needInject(ParamSet paramSet) {
		return paramSet.getOperType() != OperationType.QUERY;
	}
}
