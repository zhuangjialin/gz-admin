/**
 * 作者：------------------
 */
package com.lam.gz.log.entity;


import com.lam.gz.log.annotation.FieldDisplay;

/**
 * 操作日志表实体类
 * @date 2017-4-19 15:56:41
 * @author zhangjie02
 */
public class OperateLogsEntity extends LoggedEntity {
	
	private static final long serialVersionUID = 1L; 
	
	/** 操作模块 */
	protected String moduleName;
	/** 操作名称 */
	protected String operationName;
	/** 操作IP */
	protected String ipAddress;
	/** 操作内容 */
	protected String content;
	/** 服务id */
	protected String serviceId;
	
	public void setServiceId(String serviceId){  
        this.serviceId = serviceId;  
    }  
    
	@FieldDisplay("服务id")
    public String getServiceId(){
        return this.serviceId;  
    }

	public void setModuleName(String moduleName){  
        this.moduleName = moduleName;  
    }  
    
	@FieldDisplay("操作模块")
    public String getModuleName(){  
        return this.moduleName;  
    }
	
	public void setOperationName(String operationName){  
        this.operationName = operationName;  
    }  
    
	@FieldDisplay("操作名称")
    public String getOperationName(){  
        return this.operationName;  
    }
	
	public void setIpAddress(String ipAddress){  
        this.ipAddress = ipAddress;  
    }  
    
	@FieldDisplay("操作IP")
    public String getIpAddress(){  
        return this.ipAddress;  
    }
	
	public void setContent(String content){  
        this.content = content;  
    }  
    
	@FieldDisplay("操作内容")
    public String getContent(){  
        return this.content;  
    }
	
	
}