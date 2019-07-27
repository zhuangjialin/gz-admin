package com.lam.gz.admin.service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lam.gz.admin.dao.RoleDao;
import com.lam.gz.admin.dao.RoleTypeDao;
import com.lam.gz.admin.entity.RoleEntity;
import com.lam.gz.admin.entity.RoleTypeEntity;
import com.lam.gz.admin.vo.RoleTypeVo;
import com.lam.gz.admin.vo.RoleVo;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.StringUtil;

/**
 * 角色类型服务实现类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Service
public class RoleTypeService extends BaseService {
	
	@Autowired
	private RoleTypeDao roleTypeDao;
	@Autowired
	private RoleDao roleDao;

	/**
	 * 单条记录查询
	 * @param roleTypeVo vo查询对象
	 * @return 单个实体对象，若没有则null
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public RoleTypeEntity find(RoleTypeVo roleTypeVo) {
		
		return roleTypeDao.find(roleTypeVo);
	}
	
	public Object checkUnique(RoleTypeVo roleTypeVo) {
		if(StringUtil.isBlank(roleTypeVo.getId())){
			return roleTypeDao.find(roleTypeVo);
		}
		return roleTypeDao.checkUnique(roleTypeVo);
	}
	
	/**
	 * 带分页的查询
	 * @param roleTypeVo vo查询对象
	 * @return PageRes分页对象
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public PageRes<RoleTypeEntity> listByPage(RoleTypeVo roleTypeVo) {
		
		return roleTypeDao.listByPage(roleTypeVo, roleTypeVo.getPage());
	}
	
	/**
	 * 不带带分页的查询
	 * @param roleTypeVo vo查询对象
	 * @return 
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public List<RoleTypeEntity> list(RoleTypeVo roleTypeVo) {
		
		return roleTypeDao.list(roleTypeVo);
	}

	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public RoleTypeEntity insert(RoleTypeEntity roleTypeEntity) {
		
		int n = roleTypeDao.insert(roleTypeEntity);
		return n > 0 ? roleTypeEntity : null;
	}

	/**
	 * 修改记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public int update(RoleTypeEntity roleTypeEntity) {

		return roleTypeDao.update(roleTypeEntity);
	}

	/**
	 * 逻辑删除记录，如有修改，请在此备注
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public int delete(RoleTypeVo roleTypeVo) {

		RoleVo v = new RoleVo();
		v.setRoleTypeId(roleTypeVo.getId());
		v.setTenantId(roleTypeVo.getTenantId());
		List<RoleEntity> list = roleDao.list(v);
		if(CollectionUtil.isNotEmpty(list)){
			logger.error("该角色类型正在被使用，不可删除, 角色个数:{}, roleTypeId:{}", list.size(), roleTypeVo.getId());
			throw new BusinessException("该角色类型正在被使用，不可删除");
		}
		return roleTypeDao.deleteLogic(roleTypeVo);
	}

}
