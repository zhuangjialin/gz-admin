package com.lam.gz.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liubo
 *
 */
public class FileUtil extends IOUtils{
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static final String FILE_SUFFIX_SEPARATOR = ".";
	public static final String FILE_SEPARATOR_UNIX = "/";
	public static final String FILE_SEPARATOR_WINDOW = "\\";
	
	private FileUtil(){
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * 给出一个路径，返回该路径对应的文件名
	 * @param path
	 * @return 返回文件名, 如果路径对应的是一个目录，则返回空
	 */
	public static String getFileName(String path){
		if(StringUtil.isBlank(path)){
			return StringUtil.EMPTY;
		}
		int lastIndex1 = path.lastIndexOf(FILE_SEPARATOR_UNIX);
		int lastIndex2 = path.lastIndexOf(FILE_SEPARATOR_WINDOW);
		int lastIndex = lastIndex1 > lastIndex2 ? lastIndex1: lastIndex2;
		if(lastIndex < 0){
			return path;
		}
		if(lastIndex == path.length()-1){
			return StringUtil.EMPTY;
		}
		return path.substring(lastIndex+1);
	}
	
	/**
	 * 获取文件后缀名
	 * @param fileName
	 * @return 返回jpg, rar, zip等, 如果该文件没有后缀，则返回空字符串
	 */
	public static String getFileSuffix(String fileName){
		if(StringUtil.isBlank(fileName)){
			return StringUtil.EMPTY;
		}
		if (fileName.indexOf(FILE_SUFFIX_SEPARATOR) != -1) {
			int lastIndex = fileName.lastIndexOf(FILE_SUFFIX_SEPARATOR);
			if(fileName.length() > lastIndex){
				return fileName.substring(lastIndex);
			}
		}
		return StringUtil.EMPTY;
	}
	
	public static boolean deleteFile(String fileName){
		return deleteFile(new File(fileName));
	}
	
	public static boolean deleteFile(File file){
		if(file == null){
			return false;
		}
		if (!file.exists()) {
			return false;
		}
		if (!file.isFile()) {
			return false;
		}
		try {
			Files.delete(Paths.get(file.getPath()));
			return true;
		} catch (IOException e) {
			logger.error("删除文件失败", e);
			return false;
		}
	}
	
	public static void close(Closeable c){
		if(c != null){
			try {
				c.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
