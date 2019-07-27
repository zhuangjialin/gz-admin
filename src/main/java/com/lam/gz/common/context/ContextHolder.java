package com.lam.gz.common.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.lam.gz.common.constant.UtilConstants;
import com.lam.gz.common.exception.BaseException;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.UUIDUtils;

/**
 * @author bo.liu01
 *
 */
public class ContextHolder{
	
	private static final Logger logger = LoggerFactory.getLogger(ContextHolder.class);

    protected static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
    
    protected static AppContext appContext;
    
    private ContextHolder() {
        throw new IllegalStateException("Utility class");
    }
    
    protected static void setApplicationContext(AppContext appContext1) {
    	appContext = appContext1;
	}

    public static ApplicationContext getApplicationContext() {
    	assertApplicationContext();
        return appContext.getApplicationContext();
    }
    
    private static void assertApplicationContext() {
        if (appContext == null || appContext.getApplicationContext() == null) {
    		throw new BaseException("applicaitonContext属性为null,请检查是否注入了AppContext!");
        }
    }
    
    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(10);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(10);
            threadLocal.set(map);
        }
        return map.get(key);
    }
    
    public static HttpServletRequest getRequest() {
		return (HttpServletRequest) get("request");
	}
    
    public static HttpServletResponse getResponse() {
    	return (HttpServletResponse) get("response");
    }

    public static void initContext(HttpServletRequest request, ServletResponse response){
        set("request", request);
        set("response", response);
    }
    
    public static void clearCurrentContext(){
        threadLocal.remove();
    }
    
    public static void put(String key, Object value){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(16);
            threadLocal.set(map);
        }
        map.put(key, value);
    }
    
    public static String getTraceId(){
        Object value = get(UtilConstants.CONTEXT_TRACE_ID);
        if(value == null){
        	value = UUIDUtils.getUuid();
            put(UtilConstants.CONTEXT_TRACE_ID, value);
        }
        return returnObjectValue(value);
    }
    
    public static void setTraceId(String traceId){
    	if(StringUtil.isBlank(traceId)){
    		return ;
    	}
        Object value = get(UtilConstants.CONTEXT_TRACE_ID);
        if(value != null){
            logger.warn("原traceId不为空, 将被覆盖, 原traceId:{}, 新traceId:{}", value, traceId);
        }
        put(UtilConstants.CONTEXT_TRACE_ID, traceId);
    }

    protected static String returnObjectValue(Object value) {
        return value==null?null:value.toString();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
