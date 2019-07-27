package com.lam.gz.log.operatelog;

/**
 * 对数据的操作分类
 *
 * @author liubo04
 * @date 2017年10月28日
 */
public enum OperationType {
	
    /** 保存 */
    SAVE, 
    /** 新增 */
    INSERT, 
    /** 修改 */
    UPDATE, 
    /** 删除 */
    DELETE, 
    /** 查询 */
    QUERY, 
    /** 其他 */
    OTHER;
}
