package com.lam.gz.common.util.collectioninter;

/**
 * @author bo.liu01 on 2017/11/10.
 * @author liubo
 *
 * @param <T>
 */
public interface FilterCondition<T>{
    /**
     * 过滤条件
     * @param t
     * @return true将会保留，false将会被过滤掉
     */
    boolean evaluate(T t);
}

