package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 用户职位表实体类
 * @date 2018-7-13 15:07:28
 * @author bo.liu01
 */
public class UserPostEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 用户名/工号 */
	private String username;
	/** 职务id */
	private String postId;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, username, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        UserPostEntity other = (UserPostEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(postId, other.postId)
				.isEquals();
	}
	
	public void setUsername(String username){  
        this.username = username;  
    }  
    
	@FieldDisplay("用户名/工号")
    public String getUsername(){
        return this.username;  
    }
	
	public void setPostId(String postId){  
        this.postId = postId;  
    }  
    
	@FieldDisplay("职务id")
    public String getPostId(){
        return this.postId;  
    }
	
	@Override
	public UserPostEntity setTenantId(String tenantId){  
        this.tenantId = tenantId;  
        return this;
    }  
    
}