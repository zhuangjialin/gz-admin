package com.lam.gz.admin.entity;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * 菜单表实体类
 * @date 2017-11-10 11:18:18
 * @author bo.liu01
 */
public class MenuEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L;

    /** 类型, 1父菜单 */
	public static final int TYPE_MENU_DIR = 1;
    /** 类型, 2子菜单 */
	public static final int TYPE_MENU = 2;
    /** 类型, 3按钮 */
	public static final int TYPE_BUTTON = 3;

	/** 父菜单名称 */
	private String parentName;
	/** 父菜单 */
	private String parentCode;
	/** 路由名称 */
	private String name;
	/** 权限名称提示，只有按钮才使用该字段 */
	private String permissionName;
	/** 编码 */
	private String code;
	/** 类型, 1父菜单，2子菜单，3按钮 */
	private Integer type;
	/** 链接 */
	private String url;
	/** 图标 */
	private String icon;
	/** 排序号 */
	private Integer sortNum;
	
	/** 完成路径 */
	private String fullPath;
	/** 在前端树中是否禁用 */
	private boolean disabled;
	
	@Override
	public int hashCode() {
		return calcHashCode(code);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;}
		if (this == obj) {return true;}
        if (getClass() != obj.getClass()) {return false;}
        
        MenuEntity other = (MenuEntity) obj;
		return new EqualsBuilder().append(code, other.code)
				.isEquals();
	}

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    private List<MenuEntity> children = new ArrayList<>();

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<MenuEntity> getChildren() {
        return children;
    }

    public void setChildren(List<MenuEntity> children) {
        this.children = children;
    }

    public void add(MenuEntity menu) {
        this.children.add(menu);
    }

    public void setParentCode(String parentCode){
        this.parentCode = parentCode;  
    }  
    
	@FieldDisplay("父菜单")
    public String getParentCode(){
        return this.parentCode;  
    }
	
	public void setName(String name){  
        this.name = name;  
    }  
    
	@FieldDisplay("名称")
    public String getName(){
        return this.name;  
    }
	
	public void setCode(String code){  
        this.code = code;  
    }  
    
	@FieldDisplay("编码")
    public String getCode(){
        return this.code;  
    }
	
	public void setType(Integer type){  
        this.type = type;  
    }  
    
	@FieldDisplay("类型, 1父菜单，2子菜单，3按钮")
    public Integer getType(){
        return this.type;  
    }
	
	public void setUrl(String url){  
        this.url = url;  
    }  
    
	@FieldDisplay("链接")
    public String getUrl(){
        return this.url;  
    }
	
	public void setIcon(String icon){  
        this.icon = icon;  
    }  
    
	@FieldDisplay("图标")
    public String getIcon(){
        return this.icon;  
    }
	
	public void setSortNum(Integer sortNum){  
        this.sortNum = sortNum;  
    }  
    
	@FieldDisplay("排序号")
    public Integer getSortNum(){
        return this.sortNum;  
    }
	
	@FieldDisplay("完整路径")
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}