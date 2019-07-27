package com.lam.gz.impexp.excel.entity;

import com.lam.gz.common.base.BaseEntity;
import com.lam.gz.common.util.StringUtil;

/**
 * @author bo.liu01
 *
 */
public class ImportEntity extends BaseEntity{

	private static final long serialVersionUID = 4980585350625650689L;
	
	/** 批量导入结果 */
	protected StringBuilder importRes;  
	/** 导入时的数据排序字段 */
	protected int sortNum;
	
	public String getImportRes() {
		if(importRes == null){
			return null;
		}
		return importRes.toString();
	}
	
	public void setImportRes(String importRes) {
		this.importRes = new StringBuilder(importRes);
	}
	
	public ImportEntity addRes(String res) {
		if(StringUtil.isBlank(res)){
			return this;
		}
		if(StringUtil.isBlank(importRes)){
			importRes = new StringBuilder(res);
		}else {
			importRes.append(";").append(res);
		}
		return this;
	}
	
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}
