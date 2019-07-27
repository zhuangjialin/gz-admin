package com.lam.gz.admin.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 数据字典表实体类
 * @date 2018-8-15 14:22:43
 * @author bo.liu01
 */
public class DictEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 默认的编码 */
	public static final String DEFAULT_PARENT_ID = "0";
	
	/** 租户名称 */
	private String tenantName;
	
	/** 父code */
	private String parentCode;
	/** 父名称 */
	private String parentName;
	/** 父id */
	private String parentId;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 键值对个数 */
	private int count;
	
	@Override
	public int hashCode() {
		return calcHashCode(tenantId, parentCode, code);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        DictEntity other = (DictEntity) obj;
		return new EqualsBuilder()
				.append(String.valueOf(tenantId), String.valueOf(other.tenantId))
				.append(parentCode, other.parentCode)
				.append(code, other.code)
				.isEquals();
	}
	
	public DictEntity id(String id) {
		setId(id);
		return this;
	}

	public DictEntity tenantId(String tenantId) {
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
	
	public DictEntity name(String name){  
		this.name = name;  
		return this;
	}  
    
	@FieldDisplay("名称")
    public String getName(){
        return this.name;  
    }

	@FieldDisplay("父id")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
}