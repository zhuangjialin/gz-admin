package com.lam.gz.admin.gz.vo; 

import com.lam.gz.common.base.BaseVo;

/**
 * 表参数封装类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class FileDownloadVo extends BaseVo{ 

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
	/** 查询创建时间开始时间 */
	private String createTimeStart;  
	/** 查询创建时间结束时间 */
	private String createTimeEnd;  
	/** 更新时间 */
	private String updateTime;  
	/** 查询更新时间开始时间 */
	private String updateTimeStart;  
	/** 查询更新时间结束时间 */
	private String updateTimeEnd;  

	public FileDownloadVo id(String id) {
		setId(id);
		return this;
	}

	public FileDownloadVo tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}

	public void setFileName(String fileName){  
        this.fileName = fileName;  
    }  
      
    public String getFileName(){  
        return this.fileName;  
    }
    
	
	public void setArticleContentUrl(String articleContentUrl){  
        this.articleContentUrl = articleContentUrl;  
    }  
      
    public String getArticleContentUrl(){  
        return this.articleContentUrl;  
    }
    
	
	public void setSort(Integer sort){  
        this.sort = sort;  
    }  
      
    public Integer getSort(){  
        return this.sort;  
    }
    
	
	public void setStyle(Integer style){  
        this.style = style;  
    }  
      
    public Integer getStyle(){  
        return this.style;  
    }
    
	
	public void setGzClassificationId(Integer gzClassificationId){  
        this.gzClassificationId = gzClassificationId;  
    }  
      
    public Integer getGzClassificationId(){  
        return this.gzClassificationId;  
    }
    
	
	public void setCreateTime(String createTime){  
        this.createTime = createTime;  
    }  
      
    public String getCreateTime(){  
        return this.createTime;  
    }
    
	public void setCreateTimeStart(String createTimeStart){  
        this.createTimeStart = createTimeStart;  
    }
	
	public String getCreateTimeStart(){  
        return this.createTimeStart;  
    }
	
	public void setCreateTimeEnd(String createTimeEnd){  
        this.createTimeEnd = createTimeEnd;  
    }
	
	public String getCreateTimeEnd(){  
        return this.createTimeEnd;  
    }
	
	public void setUpdateTime(String updateTime){  
        this.updateTime = updateTime;  
    }  
      
    public String getUpdateTime(){  
        return this.updateTime;  
    }
    
	public void setUpdateTimeStart(String updateTimeStart){  
        this.updateTimeStart = updateTimeStart;  
    }
	
	public String getUpdateTimeStart(){  
        return this.updateTimeStart;  
    }
	
	public void setUpdateTimeEnd(String updateTimeEnd){  
        this.updateTimeEnd = updateTimeEnd;  
    }
	
	public String getUpdateTimeEnd(){  
        return this.updateTimeEnd;  
    }
	
}