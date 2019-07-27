package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 权限表实体类
 * @date 2017-11-10 11:29:23
 * @author bo.liu01
 */
public class AuthorityEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L;
    /** 权限主身份，1用户身份 */
	public static final int OWNER_TYPE_USER = 1;
    /** 权限主身份，2角色身份 */
	public static final int OWNER_TYPE_ROLE = 2;

	/** 权限主，用户名(username)或者角色编码(role_code) */
	private String ownerCode;
	/** 菜单编码 */
	private String menuCode;
	/** 权限主身份，1用户身份，2角色身份 */
	private Integer ownerType;
	
	@Override
	public int hashCode() {
		return calcHashCode(ownerCode, menuCode, ownerType);
	}

	@Override
	public boolean equals(Object obj) {
		
        if (obj == null) {return false;}
        if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        AuthorityEntity other = (AuthorityEntity) obj;
		return new EqualsBuilder().append(ownerCode, other.ownerCode)
				.append(menuCode, other.menuCode)
				.append(ownerType, other.ownerType)
				.isEquals();
	}

	public void setOwnerCode(String ownerCode){  
        this.ownerCode = ownerCode;  
    }  
    
	@FieldDisplay("权限主")
    public String getOwnerCode(){
        return this.ownerCode;  
    }
	
	public void setMenuCode(String menuCode){  
        this.menuCode = menuCode;  
    }  
    
	@FieldDisplay("菜单编码")
    public String getMenuCode(){
        return this.menuCode;  
    }
	
	public void setOwnerType(Integer ownerType){  
        this.ownerType = ownerType;  
    }  
    
	@FieldDisplay("权限主身份")
    public Integer getOwnerType(){
        return this.ownerType;  
    }
	
}