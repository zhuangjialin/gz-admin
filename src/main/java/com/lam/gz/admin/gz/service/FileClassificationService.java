package com.lam.gz.admin.gz.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.lam.gz.common.page.PageRes;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.admin.gz.dao.FileClassificationDao;
import com.lam.gz.admin.gz.entity.FileClassificationEntity;
import com.lam.gz.admin.gz.vo.FileClassificationVo;
import com.lam.gz.common.util.TokenUtil;

/**
 * 表服务实现类
 * @date 2019-7-24 22:52:57
 * @author liubo
 */
@Service
public class FileClassificationService extends BaseService {
	
	@Autowired
	private FileClassificationDao fileClassificationDao;

	/**
	 * 单条记录查询
	 * @param id id
	 * @return 单个实体对象，若没有则null
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public FileClassificationEntity findById(String id) {
	
		return fileClassificationDao.find(new FileClassificationVo().id(id).tenantId(TokenUtil.getTenantId()));
	}
	
	/**
	 * 单条记录查询
	 * @param vo vo查询对象
	 * @return 单个实体对象，若没有则null
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public FileClassificationEntity find(FileClassificationVo vo) {
	
		return fileClassificationDao.find(vo);
	}
	
	/**
	 * 带分页的查询
	 * @param vo 查询对象
	 * @return PageRes分页对象
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public PageRes<FileClassificationEntity> listByPage(FileClassificationVo vo) {
		
		return fileClassificationDao.listByPage(vo, vo.getPage());
	}
	
	/**
	 * 不带带分页的查询
	 * @param vo vo查询对象
	 * @return 
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	public List<FileClassificationEntity> list(FileClassificationVo vo) {
		
		return fileClassificationDao.list(vo);
	}

	/**
	 * 新增记录
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public FileClassificationEntity insert(FileClassificationEntity entity) {
		
		int n = fileClassificationDao.insert(entity);
		return n > 0 ? entity : null;
	}

	/**
	 * 修改记录
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int update(FileClassificationEntity entity) {

		return fileClassificationDao.update(entity);
	}

	/**
	 * 逻辑删除记录，如有修改，请在此备注
	 * @date 2019-7-24 22:52:57
	 * @author liubo
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int delete(FileClassificationVo vo) {

		return fileClassificationDao.deleteLogic(vo);
	}

}
