package com.lam.gz.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lam.gz.base.config.BaseBeanConfig;
import com.lam.gz.base.dao.BaseOperDao;
import com.lam.gz.base.entity.ModuleEntity;
import com.lam.gz.base.entity.ResourceEntity;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.util.BatchUtil;
import com.lam.gz.common.util.HttpServletUtil;
import com.lam.gz.log.entity.Module;
import com.lam.gz.log.entity.ModuleResEntry;
import com.lam.gz.log.entity.Resource;
import com.lam.gz.log.util.SpringUtil;

/**
 * @author liubo
 *
 */
@Service
public class BaseOperService extends BaseService{
	
	@Autowired
	private BaseOperDao baseOperDao;
	@Autowired
	private BaseBeanConfig baseBeanConfig;
	
	public int reflush() {

		//通过上下文对象获取RequestMappingHandlerMapping实例对象
		RequestMappingHandlerMapping bean = HttpServletUtil.getBean(RequestMappingHandlerMapping.class);
		ModuleResEntry moduleRes = SpringUtil.reflushResource(bean.getHandlerMethods());

		List<Module> modules = moduleRes.getModules();
		List<Resource> resources = moduleRes.getResources();
		List<ModuleEntity> moduleList = transferM(modules);
		List<ResourceEntity> resourceList = transferR(resources);

		BatchUtil.batchInsert(baseOperDao, "insertModuleBatch",moduleList);
		baseOperDao.deleteAllModule(baseBeanConfig.getServiceId());
		baseOperDao.activeModule(baseBeanConfig.getServiceId());
		BatchUtil.batchInsert(baseOperDao, "insertResourceBatch", resourceList);
		baseOperDao.deleteAllResource(baseBeanConfig.getServiceId());
		baseOperDao.activeResource(baseBeanConfig.getServiceId());

		return resourceList.size();
	}
	
	private List<ModuleEntity> transferM(List<Module> modules){
		List<ModuleEntity> list = new ArrayList<>(modules.size());
		for(Module module : modules){
			ModuleEntity m = new ModuleEntity(module);
			m.setServiceId(baseBeanConfig.getServiceId());
			list.add(m);
		}
		return list;
	}

	private List<ResourceEntity> transferR(List<Resource> resources){
		List<ResourceEntity> list = new ArrayList<>(resources.size());
		for(Resource resource : resources){
			ResourceEntity r = new ResourceEntity(resource);
			r.setServiceId(baseBeanConfig.getServiceId());
			list.add(r);
		}
		return list;
	}

}
