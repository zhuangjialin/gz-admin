package com.lam.gz.common.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.lam.gz.common.config.UtilBeanConfigUtil;

/**
 * @author liubo
 *
 */
public class DefaultIcmThreadFactory implements ThreadFactory {

	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultIcmThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        
        namePrefix = String.format("%s-pool-%s-thread-", UtilBeanConfigUtil.getConfig().getApplicationName(), 
        		POOL_NUMBER.getAndIncrement());
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()){
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY){
        	t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
