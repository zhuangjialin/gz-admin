package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 角色表实体类
 * @date 2017-11-8 15:34:55
 * @author bo.liu01
 */
public class RoleEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 角色类型,admin_role_type.id */
	private Integer roleTypeId;
	/** 角色类型,admin_role_type.id */
	private String roleTypeName;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, code);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        RoleEntity other = (RoleEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(code, other.code)
				.isEquals();
	}

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public void setCode(String code){
        this.code = code;  
    }  
    
	@FieldDisplay("编码")
    public String getCode(){
        return this.code;  
    }
	
	public void setName(String name){  
        this.name = name;  
    }  
    
	@FieldDisplay("名称")
    public String getName(){
        return this.name;  
    }
	
	public void setRoleTypeId(Integer roleTypeId){  
        this.roleTypeId = roleTypeId;  
    }  
    
	@FieldDisplay("角色类型,admin_role_type.id")
    public Integer getRoleTypeId(){
        return this.roleTypeId;  
    }
	
}