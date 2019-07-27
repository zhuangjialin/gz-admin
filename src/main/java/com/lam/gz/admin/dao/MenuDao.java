package com.lam.gz.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.MenuEntity;
import com.lam.gz.admin.vo.MenuVo;
import com.lam.gz.common.base.BaseDao;

/**
 * 菜单表数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface MenuDao extends BaseDao<MenuEntity, MenuVo>{

 List<MenuEntity> listBtnByOwner(Map<String, Object> map);

 List<MenuEntity> listRoleBtnByUser(Map<String, Object> map);

List<MenuEntity> listBtnByTemplate(@Param("templateCode")String templateCode);

List<MenuEntity> listTenantTempMenu(@Param("tenantId")String tenantId);

List<MenuEntity> listBtnByTenant(@Param("tenantId")String tenantId);

}