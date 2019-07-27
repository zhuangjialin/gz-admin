package com.lam.gz.admin.entity;

import com.lam.gz.common.util.UUIDUtils;
import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 租户表实体类
 * @date 2018-7-5 13:05:21
 * @author bo.liu01
 */
public class TenantEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	private String name;//租户名称
	private String templateCode;//菜单模板编码
	
	public void setName(String name){  
        this.name = name;  
    }  
    
	@FieldDisplay("租户名称")
    public String getName(){
        return this.name;  
    }
	
	public static String generatorNo() {
		return  UUIDUtils.getUuid();
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	
}