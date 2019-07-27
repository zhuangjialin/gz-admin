package com.lam.gz.admin.gz.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 表实体类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class GzMenuEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 标题 */
	private String menuName;
	/** 权重排序 序号越小排序越前 */
	private Integer sort;
	/** 菜单url */
	private String menuUrl;
	/** 菜单内容 */
	private String menuContent;
	/** 创建时间 */
	private String createTime;
	/** 更新时间 */
	private String updateTime;

	@Override
	public int hashCode() {
		return calcHashCode(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {return true;}
	    if (obj == null || getClass() != obj.getClass()) {return false;}
	    
	    GzMenuEntity other = (GzMenuEntity) obj;
		return new EqualsBuilder().append(id, other.id)
				.isEquals();
	}
	
	public GzMenuEntity id(String id) {
		setId(id);
		return this;
	}

	public GzMenuEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setMenuName(String menuName){  
        this.menuName = menuName;  
    }  
    
	@FieldDisplay("标题")
    public String getMenuName(){
        return this.menuName;  
    }
	
	public void setSort(Integer sort){  
        this.sort = sort;  
    }  
    
	@FieldDisplay("权重排序 序号越小排序越前")
    public Integer getSort(){
        return this.sort;  
    }
	
	public void setMenuUrl(String menuUrl){  
        this.menuUrl = menuUrl;  
    }  
    
	@FieldDisplay("菜单url")
    public String getMenuUrl(){
        return this.menuUrl;  
    }
	
	public void setMenuContent(String menuContent){  
        this.menuContent = menuContent;  
    }  
    
	@FieldDisplay("菜单内容")
    public String getMenuContent(){
        return this.menuContent;  
    }
	
	public void setCreateTime(String createTime){  
        this.createTime = createTime;  
    }  
    
	@FieldDisplay("创建时间")
    public String getCreateTime(){
        return this.createTime;  
    }
	
	public void setUpdateTime(String updateTime){  
        this.updateTime = updateTime;  
    }  
    
	@FieldDisplay("更新时间")
    public String getUpdateTime(){
        return this.updateTime;  
    }
	
}