package com.lam.gz.admin.gz.vo; 

import com.lam.gz.common.base.BaseVo;

/**
 * 表参数封装类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class FileClassificationVo extends BaseVo{ 

	private static final long serialVersionUID = 1L; 
	
	/** 文章标题 */
	private String classificationName;  
	/** 权重排序 序号越小排序越前 */
	private Integer sort;  
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

	public FileClassificationVo id(String id) {
		setId(id);
		return this;
	}

	public FileClassificationVo tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}

	public void setClassificationName(String classificationName){  
        this.classificationName = classificationName;  
    }  
      
    public String getClassificationName(){  
        return this.classificationName;  
    }
    
	
	public void setSort(Integer sort){  
        this.sort = sort;  
    }  
      
    public Integer getSort(){  
        return this.sort;  
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