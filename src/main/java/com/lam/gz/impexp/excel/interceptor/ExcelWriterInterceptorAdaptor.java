package com.lam.gz.impexp.excel.interceptor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lam.gz.impexp.excel.exp.SheetWriterExecutor;

/**
 * @author liubo
 *
 */
public class ExcelWriterInterceptorAdaptor implements ExcelWriterInterceptor {

	@Override
	public boolean beforeWrite(Workbook workbook, Sheet sheet, SheetWriterExecutor sheetHandler) {
		return true;
	}

	@Override
	public boolean complete(Workbook workbook, Sheet sheet, SheetWriterExecutor sheetHandler) {
		return true;
	}

}
