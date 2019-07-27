package com.lam.gz.log.entity;

import com.lam.gz.common.base.BaseEntity;
import com.lam.gz.log.annotation.FieldDisplay;

/**
 * @author bo.liu01 on 2017/11/1.
 */
public class LoggedEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Override
	@FieldDisplay("id")
    public String getId() {
        return id;
    }
	
	@Override
	@FieldDisplay("租户编码")
    public String getTenantId(){
        return this.tenantId;  
    }

	@Override
    @FieldDisplay("操作人账号")
    public String getOperUser() {
        return operUser;
    }

	@Override
    @FieldDisplay("操作人名字")
    public String getOperName() {
        return operName;
    }

	@Override
    @FieldDisplay("操作时间")
    public String getOperTime() {
        return operTime;
    }

	@Override
    @FieldDisplay("备注")
    public String getRemark() {
        return remark;
    }

}
