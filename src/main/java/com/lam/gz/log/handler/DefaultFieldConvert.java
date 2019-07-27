package com.lam.gz.log.handler;

import com.lam.gz.log.operatelog.OperationType;
import com.lam.gz.common.util.BeanUtil;
import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author liubo
 *
 */
public class DefaultFieldConvert extends AbstractFieldConvert {
	
	@Override
	public String convertValue(Object o, Field field) {
		return FieldDisplayHandler.getFieldDisplayValue(o, field);
	}
	
	@Override
	public String convert(Object o, Field field) {
		return FieldDisplayHandler.getFieldDisplayName(field);
	}

	@Override
	public List<Field> getFields(Object o, String[] fields, OperationType operationType) {
		return getFields(o, fields, operationType, false);
	}
	
	@Override
	public List<Field> getCompareFields(Object o, String[] fields, OperationType operationType) {
		return getFields(o, fields, operationType, false);
	}
	
	private List<Field> getFields(Object o, String[] fields, OperationType operationType, boolean isCheck) {
		List<Field> fds = BeanUtil.getBusinessFields(o.getClass());
		fds = filter(fds);
		
		if(fields == null || fields.length == 0){
			return fds;
		}
		
		Map<String, Field> fdsMap = CollectionUtil.getMapByProperty(fds, "name");
		fds.clear();
		Field fd = null;
		if(isCheck){
			for (String str : fields) {
				fd = fdsMap.get(str);
				if(fd != null && FieldDisplayHandler.checkAnnoCompare(fd, operationType)){
					fds.add(fd);
				}
			}
		}else{
			for (String str : fields) {
				fd = fdsMap.get(str);
				if(fd != null){
					fds.add(fd);
				}
			}
		}
		return fds;
	}
	
	private List<Field> filter(List<Field> fds) {
		//过滤没有注解的字段
		fds = FieldDisplayHandler.filter(fds);
		
		//调用过滤
		List<Field> fdsTmp = new ArrayList<>();
		for (Field fd : fds) {
			if(filterType(fd)){
				continue;
			}
			fdsTmp.add(fd);
		}
		return fdsTmp;
	}
	
	/**
	 * 写点啥吧
	 * @param fd
	 * @return true将被过滤掉，false会参与处理
	 * @date 2017年6月19日
	 * @author liubo04
	 */
	private boolean filterType(Field fd){
		if(fd.getType().isAssignableFrom(Collection.class)){
			logger.info("字段类型为集合，将忽略记录该字段的操作日志，Class:{}, fieldName：{}", fd.getDeclaringClass().getSimpleName(), fd.getName());
			return true;
		}
		if(fd.getType().isAssignableFrom(Map.class)){
			logger.info("字段类型为Map，将忽略记录该字段的操作日志，Class:{}, fieldName：{}", fd.getDeclaringClass().getSimpleName(), fd.getName());
			return true;
		}
		String typeName = fd.getType().getSimpleName();
		if(StringUtil.equalsIgnoreCase(typeName, TYPE_STRING) || StringUtil.equalsIgnoreCase(typeName, TYPE_BOOLEAN)
			|| StringUtil.equalsIgnoreCase(typeName, TYPE_INT)|| StringUtil.equalsIgnoreCase(typeName, TYPE_INTEGER)
			|| StringUtil.equalsIgnoreCase(typeName, TYPE_LONG)  
			|| StringUtil.equalsIgnoreCase(typeName, TYPE_DOUBLE) || StringUtil.equalsIgnoreCase(typeName, TYPE_FLOAT)
			|| StringUtil.equalsIgnoreCase(typeName, TYPE_CHARACTER) || StringUtil.equalsIgnoreCase(typeName, TYPE_CHAR)
			|| StringUtil.equalsIgnoreCase(typeName, TYPE_BYTE)){
			return false;
		}
		logger.info("字段类型为非基础类型，将忽略记录该字段的操作日志，Class:{}, fieldName：{}", fd.getDeclaringClass().getSimpleName(), fd.getName());
		return true;		
	}
	
}
