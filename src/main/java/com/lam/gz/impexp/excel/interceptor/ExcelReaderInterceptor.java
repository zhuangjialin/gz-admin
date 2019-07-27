package com.lam.gz.impexp.excel.interceptor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lam.gz.impexp.excel.imp.SheetReaderExecutor;
import com.lam.gz.impexp.excel.imp.model.SheetData;

/**
 * @author bo.liu01
 *
 */
public interface ExcelReaderInterceptor {
	
	/**
	 * 读前置拦截
	 * @param workbook
	 * @param sheet
	 * @param sheetExecutor
	 * @return
	 */
	boolean beforeRead(Workbook workbook, Sheet sheet, SheetReaderExecutor sheetExecutor);
	
	/**
	 * 完成后的拦截
	 * @param workbook
	 * @param sheet
	 * @param sheetExecutor
	 * @param sheetData
	 * @return
	 */
	boolean complete(Workbook workbook, Sheet sheet, SheetReaderExecutor sheetExecutor, SheetData sheetData);

}
