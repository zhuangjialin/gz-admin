package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 参数配置表实体类
 * @date 2018-8-15 14:22:43
 * @author bo.liu01
 */
public class ParamEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 值 */
	private String value;
	
	/** 租户名称 */
	private String tenantName;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, code);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        ParamEntity other = (ParamEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(code, other.code)
				.isEquals();
	}
	
	public ParamEntity id(String id) {
		setId(id);
		return this;
	}

	public ParamEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
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
	
	public void setValue(String value){  
        this.value = value;  
    }  
    
	@FieldDisplay("值")
    public String getValue(){
        return this.value;  
    }

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
}