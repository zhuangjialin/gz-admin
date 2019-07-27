package com.lam.gz.admin.gz.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 表实体类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class VideoEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 所属分类 */
	private String videoCatory;
	/** 实验学时 */
	private Integer classHour;
	/** 实验操作步数 */
	private Integer numberSteps;
	/** 实验项目负责人 */
	private String principal;
	/** 联系电话 */
	private String phone;
	/** 实验简介 */
	private String description;
	/** 权重排序 序号越小排序越前 */
	private Integer sort;
	/** 创建时间 */
	private String createTime;
	/** 更新时间 */
	private String updateTime;
	/** MP4路径 */
	private String videoUrl;

	@Override
	public int hashCode() {
		return calcHashCode(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {return true;}
	    if (obj == null || getClass() != obj.getClass()) {return false;}
	    
	    VideoEntity other = (VideoEntity) obj;
		return new EqualsBuilder().append(id, other.id)
				.isEquals();
	}
	
	public VideoEntity id(String id) {
		setId(id);
		return this;
	}

	public VideoEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setVideoCatory(String videoCatory){  
        this.videoCatory = videoCatory;  
    }  
    
	@FieldDisplay("所属分类")
    public String getVideoCatory(){
        return this.videoCatory;  
    }
	
	public void setClassHour(Integer classHour){  
        this.classHour = classHour;  
    }  
    
	@FieldDisplay("实验学时")
    public Integer getClassHour(){
        return this.classHour;  
    }
	
	public void setNumberSteps(Integer numberSteps){  
        this.numberSteps = numberSteps;  
    }  
    
	@FieldDisplay("实验操作步数")
    public Integer getNumberSteps(){
        return this.numberSteps;  
    }
	
	public void setPrincipal(String principal){  
        this.principal = principal;  
    }  
    
	@FieldDisplay("实验项目负责人")
    public String getPrincipal(){
        return this.principal;  
    }
	
	public void setPhone(String phone){  
        this.phone = phone;  
    }  
    
	@FieldDisplay("联系电话")
    public String getPhone(){
        return this.phone;  
    }
	
	public void setDescription(String description){  
        this.description = description;  
    }  
    
	@FieldDisplay("实验简介")
    public String getDescription(){
        return this.description;  
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
	
	public void setVideoUrl(String videoUrl){  
        this.videoUrl = videoUrl;  
    }  
    
	@FieldDisplay("MP4路径")
    public String getVideoUrl(){
        return this.videoUrl;  
    }
	
}