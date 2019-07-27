package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 租户-菜单 表实体类
 * @date 2018-8-2 15:08:10
 * @author bo.liu01
 */
public class Tenant2MenuEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 模板编码 */
	private String menuCode;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, menuCode);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        Tenant2MenuEntity other = (Tenant2MenuEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(menuCode, other.menuCode)
				.isEquals();
	}
	
	public Tenant2MenuEntity() {
		super();
	}
	
	public Tenant2MenuEntity(String tenantId, String menuCode) {
		super();
		this.tenantId = tenantId;
		this.menuCode = menuCode;
	}

	public Tenant2MenuEntity id(String id) {
		setId(id);
		return this;
	}

	public Tenant2MenuEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	@FieldDisplay("菜单编码")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}