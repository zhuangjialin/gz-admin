package com.lam.gz.admin.gz.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.lam.gz.log.annotation.FieldDisplay;
import com.lam.gz.log.entity.LoggedEntity;

/**
 * 表实体类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class FileDownloadEntity extends LoggedEntity{
	
	private static final long serialVersionUID = 1L; 
	
	/** 文章标题 */
	private String fileName;
	/** 文章内路径 */
	private String articleContentUrl;
	/** 权重排序 序号越小排序越前 */
	private Integer sort;
	/** 文件类型 0-html，1-文件 */
	private Integer style;
	/** 所属分类 */
	private Integer gzClassificationId;
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
	    
	    FileDownloadEntity other = (FileDownloadEntity) obj;
		return new EqualsBuilder().append(id, other.id)
				.isEquals();
	}
	
	public FileDownloadEntity id(String id) {
		setId(id);
		return this;
	}

	public FileDownloadEntity tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}
	
	public void setFileName(String fileName){  
        this.fileName = fileName;  
    }  
    
	@FieldDisplay("文章标题")
    public String getFileName(){
        return this.fileName;  
    }
	
	public void setArticleContentUrl(String articleContentUrl){  
        this.articleContentUrl = articleContentUrl;  
    }  
    
	@FieldDisplay("文章内路径")
    public String getArticleContentUrl(){
        return this.articleContentUrl;  
    }
	
	public void setSort(Integer sort){  
        this.sort = sort;  
    }  
    
	@FieldDisplay("权重排序 序号越小排序越前")
    public Integer getSort(){
        return this.sort;  
    }
	
	public void setStyle(Integer style){  
        this.style = style;  
    }  
    
	@FieldDisplay("文件类型 0-html，1-文件")
    public Integer getStyle(){
        return this.style;  
    }
	
	public void setGzClassificationId(Integer gzClassificationId){  
        this.gzClassificationId = gzClassificationId;  
    }  
    
	@FieldDisplay("所属分类")
    public Integer getGzClassificationId(){
        return this.gzClassificationId;  
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