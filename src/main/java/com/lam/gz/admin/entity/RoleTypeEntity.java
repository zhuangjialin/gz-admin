package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 角色类型实体类
 * @date 2017-11-8 15:34:55
 * @author bo.liu01
 */
public class RoleTypeEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 名称 */
	private String name;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        RoleTypeEntity other = (RoleTypeEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(id, other.id)
				.isEquals();
	}
	
	public void setName(String name){  
        this.name = name;  
    }  
    
	@FieldDisplay("名称")
    public String getName(){
        return this.name;  
    }
	
}