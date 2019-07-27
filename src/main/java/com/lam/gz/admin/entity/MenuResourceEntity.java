package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 菜单资源表实体类
 * @date 2017-11-10 13:45:34
 * @author bo.liu01
 */
public class MenuResourceEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 菜单编码,admin_menu.code */
	private String menuCode;
	/** 资源编码, admin_resource.code */
	private String resourceCode;
	
	@Override
	public int hashCode() {
		return calcHashCode(menuCode, resourceCode);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        MenuResourceEntity other = (MenuResourceEntity) obj;
		return new EqualsBuilder()
				.append(menuCode, other.menuCode)
				.append(resourceCode, other.resourceCode)
				.isEquals();
	}
	
	public void setMenuCode(String menuCode){  
        this.menuCode = menuCode;  
    }  
    
	@FieldDisplay("菜单编码")
    public String getMenuCode(){
        return this.menuCode;  
    }
	
	public void setResourceCode(String resourceCode){  
        this.resourceCode = resourceCode;  
    }  
    
	@FieldDisplay("资源编码, admin_resource.code")
    public String getResourceCode(){
        return this.resourceCode;  
    }
	
}