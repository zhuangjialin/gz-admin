package com.lam.gz.admin.dao;

import org.springframework.stereotype.Repository;

import com.lam.gz.common.base.BaseDao;
import com.lam.gz.admin.entity.MenuResourceEntity;
import com.lam.gz.admin.vo.MenuResourceVo;

 /**
 * 菜单资源表数据接口类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Repository
public interface MenuResourceDao extends BaseDao<MenuResourceEntity, MenuResourceVo>{
	
	int deleteLogicByMenu(MenuResourceVo vo);

}