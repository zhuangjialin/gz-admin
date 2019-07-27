package com.lam.gz.common.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lam.gz.common.util.HashCodeUtil;

/**
 * @author liubo
 */
public class BaseObject implements Serializable {
	
    private static final long serialVersionUID = 4539343756018673380L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    public String toJSONString() {
    	return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
    
	protected int calcHashCode(Object ... objects) {
		return HashCodeUtil.calcHashCode(objects);
	}

}
