package com.lam.gz.common.executor;

import java.util.concurrent.ThreadPoolExecutor;

import com.lam.gz.common.util.HttpServletUtil;

/**
 * @author liubo
 *
 */
public class TaskExecutor {
	
	private static ThreadPoolExecutor threadPoolExecutor;
	
	private TaskExecutor(){
		throw new IllegalStateException("Utility class");
	}
	
	public static ThreadPoolExecutor getThreadPoolExecutor() {
		if(threadPoolExecutor == null){
			threadPoolExecutor = HttpServletUtil.getBean(ThreadPoolExecutor.class);
		}
		return threadPoolExecutor;
	}
	
	public static void submit(Runnable command){
		threadPoolExecutor.execute(command);
	}

}
