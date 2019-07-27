package com.lam.gz.impexp.excel.interceptor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lam.gz.impexp.excel.imp.SheetReaderExecutor;
import com.lam.gz.impexp.excel.imp.model.SheetData;

/**
 * @author liubo
 *
 */
public class ExcelReaderInterceptorAdaptor implements ExcelReaderInterceptor {

	@Override
	public boolean beforeRead(Workbook workbook, Sheet sheet, SheetReaderExecutor sheetExecutor) {
		return true;
	}

	@Override
	public boolean complete(Workbook workbook, Sheet sheet, SheetReaderExecutor sheetExecutor, SheetData sheetData) {
		return true;
	}

}
