package com.lam.gz.admin.gz.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 表实体类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class GuideEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 技术指导标题 */
	private String guideName;
	/** 技术指导小图标 */
	private String guideIcon;
	/** 权重排序 序号越小排序越前 */
	private Integer sort;
	/** 创建时间 */
	private String createTime;
	/** 更新时间 */
	private String updateTime;
	/** 具体内容 */
	private String guideContent;
	/** 标题 */
	private String guideTitle;
	/** 生成的html路径 */
	private String guideUrl;

	@Override
	public int hashCode() {
		return calcHashCode(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {return true;}
	    if (obj == null || getClass() != obj.getClass()) {return false;}
	    
	    GuideEntity other = (GuideEntity) obj;
		return new EqualsBuilder().append(id, other.id)
				.isEquals();
	}
	
	public GuideEntity id(String id) {
		setId(id);
		return this;
	}

	public GuideEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setGuideName(String guideName){  
        this.guideName = guideName;  
    }  
    
	@FieldDisplay("技术指导标题")
    public String getGuideName(){
        return this.guideName;  
    }
	
	public void setGuideIcon(String guideIcon){  
        this.guideIcon = guideIcon;  
    }  
    
	@FieldDisplay("技术指导小图标")
    public String getGuideIcon(){
        return this.guideIcon;  
    }
	
	public void setSort(Integer sort){  
        this.sort = sort;  
    }  
    
	@FieldDisplay("权重排序 序号越小排序越前")
    public Integer getSort(){
        return this.sort;  
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
	
	public void setGuideContent(String guideContent){  
        this.guideContent = guideContent;  
    }  
    
	@FieldDisplay("具体内容")
    public String getGuideContent(){
        return this.guideContent;  
    }
	
	public void setGuideTitle(String guideTitle){  
        this.guideTitle = guideTitle;  
    }  
    
	@FieldDisplay("标题")
    public String getGuideTitle(){
        return this.guideTitle;  
    }
	
	public void setGuideUrl(String guideUrl){  
        this.guideUrl = guideUrl;  
    }  
    
	@FieldDisplay("生成的html路径")
    public String getGuideUrl(){
        return this.guideUrl;  
    }
	
}