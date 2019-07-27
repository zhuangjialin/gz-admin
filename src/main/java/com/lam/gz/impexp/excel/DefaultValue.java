package com.lam.gz.impexp.excel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bo.liu01
 *
 */
public class DefaultValue {
	
	private DefaultValue() {
		throw new IllegalStateException("Utility  class");
	}
	
	/** 默认的列宽 */
	public static final int DEFAULT_COLUMN_WIDTH = 20;
	/** 默认的行高  */
	public static final int DEFAULT_ROW_HEIGHT_IN_POINTS = 16;
	
	/** 默认的sheet名称，如果是多个sheet都使用默认的名称，则会是sheet_1，sheet_2...  */
	public static final String DEFAULT_SHEET_NAME = "sheet";
	
	/** 默认一次导入的最大数量  */
	public static final int DEFAULT_IMPORT_MAX_ROW = 20000;
	/** 默认导入时表头的行数  */
	public static final int DEFAULT_IMPORT_HEADER_ROW_NUM = 1;
	/** 默认导入时是否使用map来承载数据  */
	public static final boolean DEFAULT_IMPORT_USE_MAP = true;
	/** 默认导入时，解析出来的数据使用自定义对象时，转换过程是否使用默认转换程序，true则使用*/
	public static final boolean DEFAULT_IMPORT_USE_SELF = false;
	
	/** 支持的后缀及对应的版本 */
	public static final Map<String, ExcelVersion> SUPPORT_VERSION_MAP;
	
	public static final String EXCEL_2003_SUFFIX = ".xls";
	public static final String EXCEL_2007_UPPER_SUFFIX = ".xlsx";
	
	static{
		Map<String, ExcelVersion> map = new HashMap<>();
		map.put(EXCEL_2003_SUFFIX, ExcelVersion.V_2003);
		map.put(EXCEL_2007_UPPER_SUFFIX, ExcelVersion.V_2007_2010);
		SUPPORT_VERSION_MAP = Collections.unmodifiableMap(map);
	}

}
