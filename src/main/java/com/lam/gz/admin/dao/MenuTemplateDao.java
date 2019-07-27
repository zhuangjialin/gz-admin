package com.lam.gz.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lam.gz.admin.entity.MenuTemplateEntity;
import com.lam.gz.admin.entity.Template2MenuEntity;
import com.lam.gz.admin.vo.MenuTemplateVo;
import com.lam.gz.common.base.BaseDao;

 /**
 * 租户菜单模板表数据接口类
 * @date 2018-8-2 15:08:10
 * @author bo.liu01
 */
@Repository
public interface MenuTemplateDao extends BaseDao<MenuTemplateEntity, MenuTemplateVo>{
	
	int batchInsertTempMenu(List<Template2MenuEntity> list);

	int deleteTempMenuLogic(MenuTemplateVo vo);

}