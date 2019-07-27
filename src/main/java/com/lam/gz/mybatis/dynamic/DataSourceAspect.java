package com.lam.gz.mybatis.dynamic;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lam.gz.mybatis.dynamic.annotation.DS;

/**
 * @author liubo
 *
 */
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE-1)	//设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Component
public class DataSourceAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//切点 
	@Pointcut("execution(* com.lam.gz..*.service..*.*(..)) "
			+ "&&@annotation(com.lam.gz.mybatis.dynamic.annotation.DS)")
	public void aspect() { }
	
	@Around("aspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	//获取方法的参数
    	Object[] args = joinPoint.getArgs();
    	String curDataSource = null;
    	Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();  
        if (m != null && m.isAnnotationPresent(DS.class)) {  
        	DS data = m.getAnnotation(DS.class);
        	curDataSource = JdbcContextHolder.getDataSource();
            JdbcContextHolder.putDataSource(data.value());
            logger.debug("动态切换数据源:{}", data.value());
        }
        Object object;
		try {
			object = joinPoint.proceed(args);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}finally {
			curDataSource = curDataSource == null? DataSourceConstant.MASTER:curDataSource;
			JdbcContextHolder.putDataSource(curDataSource);
		}
		return object;
	}
}
