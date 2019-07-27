package com.lam.gz.admin.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.admin.dao.UserAgentDao;
import com.lam.gz.admin.entity.UserAgentEntity;
import com.lam.gz.admin.vo.UserAgentVo;
import com.lam.gz.base.common.PlatformEnum;
import com.lam.gz.base.service.BaseCacheUtil;
import com.lam.gz.common.util.TokenUtil;

/**
 * 坐席工号表服务实现类
 * @date 2018-9-7 15:33:14
 * @author yonghui.wu
 */
@Service
public class UserAgentService extends BaseService {
	
	@Autowired
	private UserAgentDao userAgentDao;

	/**
	 * 单条记录查询
	 * @param id id
	 * @return 单个实体对象，若没有则null
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	public UserAgentEntity findById(String id) {
	
		return userAgentDao.find(new UserAgentVo().id(id).tenantId(TokenUtil.getTenantId()));
	}
	
	/**
	 * 单条记录查询
	 * @param vo vo查询对象
	 * @return 单个实体对象，若没有则null
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	public UserAgentEntity find(UserAgentVo vo) {
	
		return userAgentDao.find(vo);
	}
	
	/**
	 * 带分页的查询
	 * @param vo 查询对象
	 * @return PageRes分页对象
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	public PageRes<UserAgentEntity> listByPage(UserAgentVo vo) {
		
		return userAgentDao.listByPage(vo, vo.getPage());
	}
	
	/**
	 * 不带带分页的查询
	 * @param vo vo查询对象
	 * @return 
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	public List<UserAgentEntity> list(UserAgentVo vo) {
		
		return userAgentDao.list(vo);
	}

	/**
	 * 新增记录
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	@Transactional(readOnly=false)
	public UserAgentEntity insert(UserAgentEntity entity) {
		
		int n = userAgentDao.insert(entity);
		if(n > 0){
			BaseCacheUtil.getBaseUserAgentService().clearUserAgentCache(
					entity.getUsername(), PlatformEnum.get(entity.getPlatform()), entity.getTenantId());
		}
		return n > 0 ? entity : null;
	}

	/**
	 * 修改记录
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	@Transactional(readOnly=false)
	public int update(UserAgentEntity entity) {

		int n = userAgentDao.update(entity);
		if(n > 0){
			BaseCacheUtil.getBaseUserAgentService().clearUserAgentCache(
					entity.getUsername(), PlatformEnum.get(entity.getPlatform()), entity.getTenantId());
		}
		return n;
	}

	/**
	 * 逻辑删除记录，如有修改，请在此备注
	 * @date 2018-9-7 15:33:14
	 * @author yonghui.wu
	 */
	@Transactional(readOnly=false)
	public int delete(UserAgentVo vo) {

		UserAgentEntity entity = find(vo);
		if(entity == null){
			return 0;
		}
		int n = userAgentDao.deleteLogic(vo);
		if(n > 0){
			BaseCacheUtil.getBaseUserAgentService().clearUserAgentCache(
					entity.getUsername(), PlatformEnum.get(entity.getPlatform()), entity.getTenantId());
		}
		return n;
	}
	/**
	 * @desc:检查cisco/awaya是否存在
	 * @param: [agentNo]
	 * @return: java.lang.String
	 * @auther: yonghui.wu
	 * @date: 2018/9/10 11:54
	 */
	public boolean checkAgentNoUnique(UserAgentEntity vo) {
		return userAgentDao.checkAgentNoUnique(vo);
	}


}
