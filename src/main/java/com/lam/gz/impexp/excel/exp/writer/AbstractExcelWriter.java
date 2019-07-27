package com.lam.gz.impexp.excel.exp.writer;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lam.gz.common.exception.BaseException;
import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.impexp.common.ImpexpBeanConfig;
import com.lam.gz.impexp.excel.DefaultValue;
import com.lam.gz.impexp.excel.ExcelVersion;
import com.lam.gz.impexp.excel.exp.ContentWriterHandler;
import com.lam.gz.impexp.excel.exp.ExcelWriter;
import com.lam.gz.impexp.excel.exp.SheetWriterExecutor;
import com.lam.gz.impexp.util.ExcelUtil;

/**
 * 抽象的Excel写工具，包含了文件目录和文件名称
 * @author bo.liu01
 *
 */
public abstract class AbstractExcelWriter implements ExcelWriter{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String UNIX_FILE_SEPARATOR = "/";
	public static final String WINDOW_FILE_SEPARATOR = "\\";
	
	/** 默认存放的文件路径 */
	protected String filePath = ImpexpBeanConfig.getTmpPath();
	protected String fileName;
	
	/**
	 * @param fileName 带后缀的文件名称，不包含文件路径
	 */
	public AbstractExcelWriter(String fileName){
		this.fileName = fileName;
	}
	
	protected Workbook getWorkbook() {
		ExcelVersion version = ExcelUtil.getExcelVersion(fileName);
		return version == ExcelVersion.V_2003? new HSSFWorkbook() : new SXSSFWorkbook();
	}
	
	protected Sheet createSheet(Workbook workbook, SheetWriterExecutor sheetExecutor) {
		if(sheetExecutor == null){
			logger.warn("SheetWriterHandler为空, 已跳过！");
			return null;
		}
		Sheet sheet;
		if(workbook.getSheet(sheetExecutor.getSheetName()) != null){
			int n = workbook.getNumberOfSheets()+1;
			sheet = workbook.createSheet(sheetExecutor.getSheetName()+"_"+n);
		}else{
			sheet = workbook.createSheet(sheetExecutor.getSheetName());
		}
		//设置默认宽度
		sheet.setDefaultColumnWidth(DefaultValue.DEFAULT_COLUMN_WIDTH);  
		//设置默认高度
		sheet.setDefaultRowHeightInPoints(DefaultValue.DEFAULT_ROW_HEIGHT_IN_POINTS); 
		ContentWriterHandler[] handlers = sheetExecutor.getHandlers();
		if(handlers == null || handlers.length == 0){
			logger.error("未初始化ExcelWriterHandler!");
			throw new BaseException("未初始化ExcelWriterHandler!");
		}
		if(sheetExecutor.getInterceptor() != null
			&& !sheetExecutor.getInterceptor().beforeWrite(workbook, sheet, sheetExecutor)){
			workbook.removeName(sheetExecutor.getSheetName());
			logger.info("写入Excel sheet[{}]之前已中断！", sheetExecutor.getSheetName());
			return null;
		}
		
		int startRow = 0;
		for (ContentWriterHandler handler : handlers) {
			if(handler == null){
				logger.warn("ContentWriterHandler为空, 已跳过!");
				continue;
			}
			startRow += handler.setStartRow(startRow).write(sheet, sheetExecutor);
		}
		
		logger.debug("已写入sheet[{}]数据{}行!", sheetExecutor.getSheetName(), startRow);
		
		if(sheetExecutor.getInterceptor() != null 
				&& !sheetExecutor.getInterceptor().complete(workbook, sheet, sheetExecutor)){
			workbook.removeName(sheetExecutor.getSheetName());
			logger.info("写入Excel sheet[{}]完成时已中断！", sheetExecutor.getSheetName());
			return null;
		}
		
		return sheet;
	}
	
	/**
	 * 生成文件
	 * @param workbook
	 * @param path
	 * @return
	 * @date 2016年11月4日
	 * @author liubo04
	 * @param fileName2 
	 */
	protected void createFile(Workbook workbook, String filePath, String fileName){
		 FileOutputStream fileOutputStream = null;
		try {
			if(StringUtil.isNotBlank(filePath)){
				File dirFile = new File(filePath);
				dirFile.mkdirs();
				filePath = filePath + File.separator + fileName;
			}else{
				filePath = fileName;
			}
			File file = new File(filePath);
			fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
			fileOutputStream.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("生成Excel文件失败");
		} finally {
			close(fileOutputStream);
		}
	}
	
	@Override
	public String getFilePath() {
		return filePath;
	}
	
	public AbstractExcelWriter setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	@Override
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String getAbsolutePath() {
		if(StringUtil.isBlank(filePath)){
			return fileName;
		}
		if(filePath.endsWith(UNIX_FILE_SEPARATOR) || filePath.endsWith(WINDOW_FILE_SEPARATOR)){
			return filePath + fileName;
		}
		return filePath + File.separator + fileName;
	}
	
	protected void close(Closeable c){
    	if(c != null){
			try {
				c.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
    }

}
