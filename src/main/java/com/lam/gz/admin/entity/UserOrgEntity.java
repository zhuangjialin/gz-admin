package com.lam.gz.admin.entity;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 组织人员关系表实体类
 * @date 2018-9-4 14:40:25
 * @author yonghui.wu
 */
public class UserOrgEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L;
	/** 工号类型，1用户工号 */
	public static final int CODE_TYPE_USER = 1;
	/** 工号类型，2小组编码 */
	public static final int CODE_TYPE_GROUP = 2;
	
	/** 用户工号或组编码 */
	private String itemCode;
	/** item类型，1用户工号, 2组编码 */
	private Integer codeType;
	/** 上级领导 */
	private String upperSuperior;
	
	//非数据库字段
	/** 上级领导 */
	private String upperSuperiorName;
	/** 工号姓名或组名称 */
	private String name;
	
	public UserOrgEntity id(String id) {
		setId(id);
		return this;
	}

	public UserOrgEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setItemCode(String itemCode){  
        this.itemCode = itemCode;  
    }  
    
	@FieldDisplay("用户工号或组编码")
    public String getItemCode(){
        return this.itemCode;  
    }
	
	public void setCodeType(Integer codeType){  
        this.codeType = codeType;  
    }  
    
	@FieldDisplay("item类型，1用户工号, 2组编码")
    public Integer getCodeType(){
        return this.codeType;  
    }
	
	public void setUpperSuperior(String upperSuperior){  
        this.upperSuperior = upperSuperior;  
    }  
    
	@FieldDisplay("上级领导")
    public String getUpperSuperior(){
        return this.upperSuperior;  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpperSuperiorName() {
		return upperSuperiorName;
	}

	public void setUpperSuperiorName(String upperSuperiorName) {
		this.upperSuperiorName = upperSuperiorName;
	}
}