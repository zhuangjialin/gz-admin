package com.lam.gz.impexp.excel.interceptor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lam.gz.impexp.excel.exp.SheetWriterExecutor;

/**
 * @author liubo
 *
 */
public interface ExcelWriterInterceptor{
	
	/**
	 * 写前置拦截
	 * @param workbook
	 * @param sheet
	 * @param sheetHandler
	 * @return
	 */
	boolean beforeWrite(Workbook workbook, Sheet sheet, SheetWriterExecutor sheetHandler);
	
	/**
	 * 写完成时的拦截
	 * @param workbook
	 * @param sheet
	 * @param sheetHandler
	 * @return
	 */
	boolean complete(Workbook workbook, Sheet sheet, SheetWriterExecutor sheetHandler);

}
