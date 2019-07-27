package com.lam.gz.impexp.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lam.gz.common.exception.BusinessException;
import com.lam.gz.common.util.Base64Util;
import com.lam.gz.common.util.DateUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.impexp.excel.exp.ContentWriterHandler;
import com.lam.gz.impexp.excel.exp.SheetWriterExecutor;
import com.lam.gz.impexp.excel.exp.executor.SimpleContentSheetWriterExecutor;
import com.lam.gz.impexp.excel.exp.writer.SimpleSheetWriter;

/**
 * @author liubo
 *
 */
public class DownloadUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadUtil.class);
	
	public static final String KEY = "bqjr_icm_589I4jsZes=";
	
	private DownloadUtil() {
		throw new IllegalStateException("Utility  class");
	}
	
	public static String decode(String file, String sign) {
		if(!StringUtil.checkSign(file, KEY, sign)){
			logger.error("下载签名sign不一致！");
			throw new BusinessException("下载签名sign不一致，请联系管理员!");
		}
		return Base64Util.safeUrlBase64Decode(file);
	}
	
	public static String encode(String filename) {
		return Base64Util.encodeBase64URLSafeString(filename);
	}
	
	public static String sign(String filename) {
		return StringUtil.sign(Base64Util.encodeBase64URLSafeString(filename), KEY);
	}
	
	public static JSONObject simpleDownload(String name, List<?> list, String ... fields) {
		
		return simpleDownload(name, new SimpleContentSheetWriterExecutor(name, list, fields));
	}
	
	public static JSONObject simpleDownload(String name, String[] header, ContentWriterHandler bodyWriterHandler) {
		
		return simpleDownload(name, new SimpleContentSheetWriterExecutor(name, header, bodyWriterHandler));
	}
	
	public static JSONObject simpleDownload(String name, SheetWriterExecutor executor) {
		
		String fileName = String.format("%s_%s.xlsx", name, DateUtil.formatDate(new Date(), DateUtil.PATTERN_YYYYMMDDHHMMSS_SSS));
		String path = new SimpleSheetWriter(fileName, executor).create().getAbsolutePath();
		JSONObject json = new JSONObject();
		json.put("f", encode(path));
		json.put("s", sign(path));
		return json;
	}

}
