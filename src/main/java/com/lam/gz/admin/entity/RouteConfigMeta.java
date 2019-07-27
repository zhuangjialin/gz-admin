package com.lam.gz.admin.entity;

import com.lam.gz.common.base.BaseObject;

/**
 * @author liubo
 *
 */
public class RouteConfigMeta extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	/** 命名路由 */
	private String title;
	/** 是否缓存页面 */
	private boolean keepAlive = true;
	
	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
