package com.lam.gz.impexp.excel.exp.executor;

import java.util.List;
import java.util.Map;

import com.lam.gz.common.util.CollectionUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.impexp.excel.exp.ContentWriterHandler;
import com.lam.gz.impexp.excel.exp.handler.BodyContentWriterHandler;
import com.lam.gz.impexp.excel.exp.handler.HeaderContentWriterHandler;

/**
 * 简单的sheet写入处理器，只支持单页sheet，而且只能有一个表头（可以是多行表头）
 * @author bo.liu01
 *
 */
public class SimpleContentSheetWriterExecutor extends MultipleContentSheetWriterExecutor {
	
	public <T> SimpleContentSheetWriterExecutor(String sheetName, List<T> list, String ... fields) {
		
		super(sheetName, getContentWriterHandler(list, fields));
	}
	
	public SimpleContentSheetWriterExecutor(String sheetName, String[][] headers, Object[][] dataList) {
		super(sheetName, new ContentWriterHandler[]{
			new HeaderContentWriterHandler(headers),
			new BodyContentWriterHandler(dataList)
		});
	}
	
	public SimpleContentSheetWriterExecutor(String sheetName, String[] headers, Object[][] dataList) {
		this(sheetName, new String[][]{headers}, dataList);
	}
	
	/**
	 * 多表头
	 * @param sheetName 簿名称
	 * @param headers 多行表头
	 * @param bodyContentWriterHandler 内容处理器
	 */
	public SimpleContentSheetWriterExecutor(String sheetName, String[][] headers, ContentWriterHandler objectWriterHandler) {
		super(sheetName, new ContentWriterHandler[]{
			new HeaderContentWriterHandler(headers),
			objectWriterHandler
		});
	}
	
	public SimpleContentSheetWriterExecutor(String sheetName, String[] headers, ContentWriterHandler objectWriterHandler) {
		this(sheetName, new String[][]{headers}, objectWriterHandler);
	}
	
	private static ContentWriterHandler[] getContentWriterHandler(List<?> list, String[] fields){
		Object[][] dataList = null;
		String[] headers = null;
		if(fields != null && fields.length > 0){
			String[][] fieldArr = StringUtil.splitFields(fields);
			headers = fieldArr[1];
			
			if(CollectionUtil.isNotEmpty(list)){
				List<Map<String, Object>> mapList = CollectionUtil.javaList2MapList(list, fields);
				dataList = new Object[list.size()][headers.length];
				int i = 0;
				int j = 0;
				for (Map<String, Object> map : mapList) {
					j = 0;
					for (String headerName : headers) {
						dataList[i][j++] = map.get(headerName);
					}
					i++;
				}
			}
		}
		
		return new ContentWriterHandler[]{
			new HeaderContentWriterHandler(new String[][]{headers}),
			new BodyContentWriterHandler(dataList)
		};
	}
	
}
