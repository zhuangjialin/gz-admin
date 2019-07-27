package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 租户菜单模板表实体类
 * @date 2018-8-2 15:08:10
 * @author bo.liu01
 */
public class MenuTemplateEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 模板编码 */
	private String code;
	/** 模板名称 */
	private String name;
	
	@Override
	public int hashCode() {
		return calcHashCode(code);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        MenuTemplateEntity other = (MenuTemplateEntity) obj;
		return new EqualsBuilder()
				.append(code, other.code)
				.isEquals();
	}
	
	public MenuTemplateEntity id(String id) {
		setId(id);
		return this;
	}

	public MenuTemplateEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setCode(String code){  
        this.code = code;  
    }  
    
	@FieldDisplay("模板编码")
    public String getCode(){
        return this.code;  
    }
	
	public void setName(String name){  
        this.name = name;  
    }  
    
	@FieldDisplay("模板名称")
    public String getName(){
        return this.name;  
    }

}