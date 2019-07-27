package com.lam.gz.admin.gz.vo; 

import com.lam.gz.common.base.BaseVo;

/**
 * 表参数封装类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class VideoVo extends BaseVo{ 

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
	/** MP4路径 */
	private String videoUrl;  

	public VideoVo id(String id) {
		setId(id);
		return this;
	}

	public VideoVo tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}

	public void setVideoCatory(String videoCatory){  
        this.videoCatory = videoCatory;  
    }  
      
    public String getVideoCatory(){  
        return this.videoCatory;  
    }
    
	
	public void setClassHour(Integer classHour){  
        this.classHour = classHour;  
    }  
      
    public Integer getClassHour(){  
        return this.classHour;  
    }
    
	
	public void setNumberSteps(Integer numberSteps){  
        this.numberSteps = numberSteps;  
    }  
      
    public Integer getNumberSteps(){  
        return this.numberSteps;  
    }
    
	
	public void setPrincipal(String principal){  
        this.principal = principal;  
    }  
      
    public String getPrincipal(){  
        return this.principal;  
    }
    
	
	public void setPhone(String phone){  
        this.phone = phone;  
    }  
      
    public String getPhone(){  
        return this.phone;  
    }
    
	
	public void setDescription(String description){  
        this.description = description;  
    }  
      
    public String getDescription(){  
        return this.description;  
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
	
	public void setVideoUrl(String videoUrl){  
        this.videoUrl = videoUrl;  
    }  
      
    public String getVideoUrl(){  
        return this.videoUrl;  
    }
    
	
}