package com.lam.gz.admin.service; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lam.gz.admin.common.AdminRedisConstant;
import com.lam.gz.admin.dao.MenuDao;
import com.lam.gz.admin.dao.MenuResourceDao;
import com.lam.gz.admin.entity.MenuEntity;
import com.lam.gz.admin.vo.MenuResourceVo;
import com.lam.gz.admin.vo.MenuVo;
import com.lam.gz.common.base.BaseService;
import com.lam.gz.common.constant.RedisConstant;
import com.lam.gz.common.constant.SepConstants;
import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.redis.RedisUtil;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.NumberUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.TokenUtil;

/**
 * 菜单表服务实现类
 * @date 2017-11-6 14:06:20
 * @author bo.liu01
 */
@Service
public class MenuService extends BaseService {
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private MenuResourceDao menuResourceDao;
	
	private String calc(MenuEntity menu, Map<String, MenuEntity> menuMap){
		if(menu == null){
			return null;
		}
		if(StringUtil.isNotBlank(menu.getFullPath())){
			return menu.getFullPath();
		}
		if(StringUtil.isBlank(menu.getParentCode())){
			menu.setFullPath(menu.getCode());
		}else{
			String pFullPath = calc(menuMap.get(menu.getParentCode()), menuMap);
			if(StringUtil.isBlank(pFullPath)){
				menu.setFullPath(menu.getCode());
			}else{
				menu.setFullPath(pFullPath + "." + menu.getCode());
			}
		}
		return menu.getFullPath();
	}
	
	public void resetCache(){
		RedisUtil.delete(RedisConstant.ALL_MENU_KEY);
		getAllMenu();
	}
	
	public List<MenuEntity> getAllMenu(){
		//先从redis缓存中获取
		String value = RedisUtil.opsForValue().get(RedisConstant.ALL_MENU_KEY);
		if(StringUtil.isNotBlank(value)){
			return JSONObject.parseArray(value, MenuEntity.class);
		}
		
		List<MenuEntity> list = menuDao.list(new MenuVo().tenantId(TokenUtil.getTenantId()));
		if(CollectionUtil.isEmpty(list)){
			return new ArrayList<>();
		}
		Map<String, MenuEntity> menuMap = CollectionUtil.getMapByProperty(list, "code");
		for (MenuEntity menu : list) {
			calc(menu, menuMap);
		}
		MenuEntity pmenu ;
		for (MenuEntity entity : list) {
			if(!NumberUtil.equals(entity.getType(), MenuEntity.TYPE_BUTTON)){
				continue;
			}
			if(StringUtil.isBlank(entity.getParentCode())){
				logger.error("权限按钮数据出错了, 该按钮无父节点, code:{}", entity.getCode());
				continue;
			}
			pmenu = menuMap.get(entity.getParentCode());
			if(pmenu == null){
				logger.error("权限按钮数据出错了, 该按钮根据父编码未找到父节点, code:{}, parentCode:{}", entity.getCode(), entity.getParentCode());
				continue;
			}
			entity.setPermissionName(pmenu.getName() +"-"+entity.getName());
		}
		RedisUtil.opsForValue().set(RedisConstant.ALL_MENU_KEY, JSON.toJSONString(list), 
				AdminRedisConstant.DEFAULT_KEY_TIMEOUT, TimeUnit.SECONDS);
		return list;
	}
	
	public Map<String, MenuEntity> getAllMenuMap(){
		return CollectionUtil.getMapByProperty(getAllMenu(), "code");
	}
	
	public List<MenuEntity> setCalcInfo(List<MenuEntity> list){
		Map<String, MenuEntity> map = getAllMenuMap();
		MenuEntity menu ;
		for (MenuEntity entity : list) {
			menu = map.get(entity.getCode());
			if(menu != null){
				entity.setFullPath(menu.getFullPath());
			}
		}
		return list;
	}

	/**
	 * 单条记录查询
	 * @param menuVo vo查询对象
	 * @return 单个实体对象，若没有则null
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public MenuEntity find(MenuVo menuVo) {
		
		return menuDao.find(menuVo);
	}
	
	/**
	 * 树查询
	 * @param menuVo vo查询对象
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public List<MenuEntity> listTree() {
		
		return MenuTreeUtil.treeFilter(listTreeByCon(null));
	}
	
	/**
	 * 树查询
	 * @param menuCodeSet 要禁用的编码
	 * @return
	 * @author bo.liu01
	 */
	public List<MenuEntity> listTreeByCon(Set<String> menuCodeSet) {
		
		return MenuTreeUtil.buildTree(getAllMenu(), menuCodeSet);
	}

	/**
	 * 不带带分页的查询
	 * @param menuVo vo查询对象
	 * @return 
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	public List<MenuEntity> list(MenuVo menuVo) {
		
		return setCalcInfo(menuDao.list(menuVo));
	}

	public List<MenuEntity> listBtnByOwner(String ownerCode, int ownerType) {
        Map<String, Object> map = new HashMap<>(10);
		map.put("ownerCode", ownerCode);
		map.put("ownerType", ownerType);
		map.put("type", MenuEntity.TYPE_BUTTON);
		map.put("tenantId", TokenUtil.getActualTenantId());
		return setCalcInfo(menuDao.listBtnByOwner(map));
	}

	public List<MenuEntity> listRoleBtnByUser(String username) {

		Map<String, Object> map = new HashMap<>(10);
		map.put("username", username);
		map.put("type", MenuEntity.TYPE_BUTTON);
		map.put("tenantId", TokenUtil.getActualTenantId());
		return setCalcInfo(menuDao.listRoleBtnByUser(map));
	}
	
	public List<MenuEntity> listBtnByTemplate(String templateCode) {
		
		return menuDao.listBtnByTemplate(templateCode);
	}
	
	public List<MenuEntity> listBtnByTenant(String tenantId) {
		
		return menuDao.listBtnByTenant(tenantId);
	}
	
	public List<MenuEntity> listTenantTempMenu(String tenantId) {
		
		return menuDao.listTenantTempMenu(tenantId);
	}
	
	
	
	/**
	 * 新增记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public MenuEntity insert(MenuEntity menuEntity) {
		
		if(menuEntity.getType() == MenuEntity.TYPE_BUTTON){
			String pcode = menuEntity.getParentCode();
			String code = menuEntity.getCode();
			if(!code.startsWith(pcode + SepConstants.HYPHEN) 
					&& !code.startsWith(pcode + SepConstants.POINT)){
				menuEntity.setCode(pcode + SepConstants.POINT + code);
			}
		}
		
		//检查是否有已存在的编码
		MenuVo v = new MenuVo();
		v.setCode(menuEntity.getCode());
		List<MenuEntity> list = menuDao.list(v );
		if(CollectionUtil.isNotEmpty(list)){
			logger.error("添加菜单时检查到该菜单编码已存在, code:{}", menuEntity.getCode());
			throw new BusinessException("该菜单编码已存在!");
		}
		
		int n = menuDao.insert(menuEntity);
		if(n > 0){
			resetCache();
			return menuEntity;
		}else{
			return null;
		}
	}

	/**
	 * 修改记录
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public int update(MenuEntity menuEntity) {

		int n = menuDao.update(menuEntity);
		if(n > 0){
			resetCache();
		}	
		return n;
	}

	/**
	 * 逻辑删除记录，如有修改，请在此备注
	 * @date 2017-11-6 14:06:20
	 * @author bo.liu01
	 */
	@Transactional(readOnly=false)
	public int delete(MenuVo menuVo) {

		MenuVo v = new MenuVo();
		v.setParentCode(menuVo.getCode());
		List<MenuEntity> list = menuDao.list(v);
		if(CollectionUtil.isNotEmpty(list)){
			logger.error("该菜单下有子菜单，不可删除，menuCode:{}", menuVo.getCode());
			throw new BusinessException("该菜单下有子菜单，不可删除");
		}
		
		int n = menuDao.deleteLogic(menuVo);
		if(n > 0){
			MenuResourceVo vo = new MenuResourceVo();
			vo.setMenuCode(menuVo.getCode());
			menuResourceDao.deleteLogicByMenu(vo );
			resetCache();
		}	
		return n; 
	}

}
