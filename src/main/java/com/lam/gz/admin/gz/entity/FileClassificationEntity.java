package com.lam.gz.admin.gz.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 表实体类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class FileClassificationEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 文章标题 */
	private String classificationName;
	/** 权重排序 序号越小排序越前 */
	private Integer sort;
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
	    
	    FileClassificationEntity other = (FileClassificationEntity) obj;
		return new EqualsBuilder().append(id, other.id)
				.isEquals();
	}
	
	public FileClassificationEntity id(String id) {
		setId(id);
		return this;
	}

	public FileClassificationEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setClassificationName(String classificationName){  
        this.classificationName = classificationName;  
    }  
    
	@FieldDisplay("文章标题")
    public String getClassificationName(){
        return this.classificationName;  
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
	
}