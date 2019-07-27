package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 用户角色表实体类
 * @date 2017-11-10 11:27:35
 * @author bo.liu01
 */
public class UserRoleEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_USER_ROLE_CODE = ""; 
	
	/** 用户名 */
	private String username;
	/** 角色，admin_role.code */
	private String roleCode;
	
	/** 用户名 */
	private String usernames;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, username, roleCode);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        UserRoleEntity other = (UserRoleEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(roleCode, other.roleCode)
				.isEquals();
	}
	
	public void setUsername(String username){  
        this.username = username;  
    }  
    
	@FieldDisplay("用户名")
    public String getUsername(){
        return this.username;  
    }
	
	public void setRoleCode(String roleCode){  
        this.roleCode = roleCode;  
    }  
    
	@FieldDisplay("角色，admin_role.code")
    public String getRoleCode(){
        return this.roleCode;  
    }

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}
	
}