package com.lam.gz.admin.gz.vo; 

import com.lam.gz.common.base.BaseVo;

/**
 * 表参数封装类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
public class GuideVo extends BaseVo{ 

	private static final long serialVersionUID = 1L; 
	
	/** 技术指导标题 */
	private String guideName;  
	/** 技术指导小图标 */
	private String guideIcon;  
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
	/** 具体内容 */
	private String guideContent;  
	/** 标题 */
	private String guideTitle;  
	/** 生成的html路径 */
	private String guideUrl;  

	public GuideVo id(String id) {
		setId(id);
		return this;
	}

	public GuideVo tenantId(String tenantId) {
		setTenantId(tenantId);
		return this;
	}

	public void setGuideName(String guideName){  
        this.guideName = guideName;  
    }  
      
    public String getGuideName(){  
        return this.guideName;  
    }
    
	
	public void setGuideIcon(String guideIcon){  
        this.guideIcon = guideIcon;  
    }  
      
    public String getGuideIcon(){  
        return this.guideIcon;  
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
	
	public void setGuideContent(String guideContent){  
        this.guideContent = guideContent;  
    }  
      
    public String getGuideContent(){  
        return this.guideContent;  
    }
    
	
	public void setGuideTitle(String guideTitle){  
        this.guideTitle = guideTitle;  
    }  
      
    public String getGuideTitle(){  
        return this.guideTitle;  
    }
    
	
	public void setGuideUrl(String guideUrl){  
        this.guideUrl = guideUrl;  
    }  
      
    public String getGuideUrl(){  
        return this.guideUrl;  
    }
    
	
}