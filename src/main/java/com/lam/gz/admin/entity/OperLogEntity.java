package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.entity.OperateLogsEntity;

/**
 * @author liubo
 *
 */
public class OperLogEntity extends OperateLogsEntity {
	
	private static final long serialVersionUID = 1L;
	
	/** 租户名称 */
	private String tenantName;
	
	@Override
	public int hashCode() {
		return calcHashCode(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        OperLogEntity other = (OperLogEntity) obj;
		return new EqualsBuilder()
				.append(id, other.id)
				.isEquals();
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

}
