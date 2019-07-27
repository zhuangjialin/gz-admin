package com.lam.gz.common.base;

import com.lam.gz.common.util.DateUtil;
import com.lam.gz.common.util.TokenUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author bo.liu01 on 2017/10/30.
 * 
 * @author liubo
 */
public class BaseTable extends BaseObject {

    private static final long serialVersionUID = 6805277017469099069L;
    
    /** 删除标记  1正常数据 */
    public static final int ATV_FLAG_YES = 1;
    /** 已打了删除标记，并在每天晚上会自动移动到对应的his表 */
    public static final int ATV_FLAG_NO = 0;
    /** 已打了删除标记，但并不会自动移动到对应的his表 */
    public static final int ATV_FLAG_NO_HIS = 2;

    /** 主键ID */
    protected String id;
    /** 租户ID */
    protected String tenantId;
    /** 备注 */
    protected String remark; 
    
    /** 操作人账号 */
    protected String operUser; 
    /** 操作人名字 */
    protected String operName; 
    /** 操作时间 */
    protected String operTime; 
    /** 删除标记 */
    protected Integer atvFlag = ATV_FLAG_YES;

    /** 表后缀，用于分表情况  */
    protected String tableSuffix;
    
    public BaseTable(){
    	
    }
    
    public BaseTable(boolean init){
    	if(init){
    		setTenantId(TokenUtil.getTenantId());
    	}
    }
    
    /**
     * 复制 operName, operUser, operTime字段
     * @param baseTable
     */
    public void copyOperField(BaseTable baseTable){
    	setOperName(baseTable.getOperName());
		setOperUser(baseTable.getOperUser());
		setOperTime(baseTable.getOperTime());
    }
    
    /**
     * 复制 operName, operUser, operTime, tenantId 字段
     * @param baseTable
     */
    public void copyBaseField(BaseTable baseTable){
    	setOperName(baseTable.getOperName());
    	setOperUser(baseTable.getOperUser());
    	setOperTime(baseTable.getOperTime());
    	setTenantId(baseTable.getTenantId());
    }
    
    /**
     * 初始化 operName, operUser, operTime, tenantId 字段
     * @param baseTable
     */
    public void initBaseField(){
    	setOperName(TokenUtil.getCurrentName());
    	setOperUser(TokenUtil.getUsername());
    	setOperTime(DateUtil.getCurrentTime());
    	setTenantId(TokenUtil.getTenantId());
    }
    
    @JsonIgnore
    public String getTableSuffix() {
        return tableSuffix;
    }

    public void setTableSuffix(String tableSuffix) {
        this.tableSuffix = tableSuffix;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public BaseTable setTenantId(String tenantId){  
        this.tenantId = tenantId;  
        return this;
    }  
    
    public String getTenantId(){
        return this.tenantId;  
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public Integer getAtvFlag() {
        return atvFlag;
    }

    public void setAtvFlag(Integer atvFlag) {
        this.atvFlag = atvFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
