package com.lam.gz.admin.gz.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.admin.gz.dao.GuideDao;
import com.lam.gz.admin.gz.entity.GuideEntity;
import com.lam.gz.admin.gz.vo.GuideVo;
import com.lam.gz.common.util.TokenUtil;

/**
 * 表服务实现类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
@Service
public class GuideService extends BaseService {
	
	@Autowired
	private GuideDao guideDao;

	/**
	 * 单条记录查询
	 * @param id id
	 * @return 单个实体对象，若没有则null
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public GuideEntity findById(String id) {
	
		return guideDao.find(new GuideVo().id(id).tenantId(TokenUtil.getTenantId()));
	}
	
	/**
	 * 单条记录查询
	 * @param vo vo查询对象
	 * @return 单个实体对象，若没有则null
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public GuideEntity find(GuideVo vo) {
	
		return guideDao.find(vo);
	}
	
	/**
	 * 带分页的查询
	 * @param vo 查询对象
	 * @return PageRes分页对象
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public PageRes<GuideEntity> listByPage(GuideVo vo) {
		
		return guideDao.listByPage(vo, vo.getPage());
	}
	
	/**
	 * 不带带分页的查询
	 * @param vo vo查询对象
	 * @return 
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public List<GuideEntity> list(GuideVo vo) {
		
		return guideDao.list(vo);
	}

	/**
	 * 新增记录
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public GuideEntity insert(GuideEntity entity) {
		
		int n = guideDao.insert(entity);
		return n > 0 ? entity : null;
	}

	/**
	 * 修改记录
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int update(GuideEntity entity) {

		return guideDao.update(entity);
	}

	/**
	 * 逻辑删除记录，如有修改，请在此备注
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int delete(GuideVo vo) {

		return guideDao.deleteLogic(vo);
	}

}
