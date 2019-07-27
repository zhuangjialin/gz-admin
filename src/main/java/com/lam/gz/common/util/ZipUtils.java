package com.lam.gz.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩或解压zip：
 * 由于直接使用java.util.zip工具包下的类，会出现中文乱码问题，所以使用ant.jar中的org.apache.tools.zip下的工具类
 *
 * @author liubo
 */
public class ZipUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private static final String CHARSET = "utf-8";
    
    private static final String ZIP_SUFFIX = ".zip";
    
    private ZipUtils(){
		throw new IllegalStateException("Utility class");
	}

    /**
     * 压缩文件或路径
     *
     * @param zip      压缩的目的地址
     * @param srcFilePaths 压缩的源文件
     * @param relativePath 在zip中的相对路径
     */
    public static void zipFile(String zip, List<String> srcFilePaths, String relativePath) {
        if(!zip.toLowerCase().endsWith(ZIP_SUFFIX)) {
            logger.info("target file[{}] is not .zip type file", zip);
            return ;
        }
        try (OutputStream os = new FileOutputStream(new File(zip));
    			java.util.zip.ZipOutputStream zipOut = new ZipOutputStream(os, Charset.forName(CHARSET));){
	        for (String filePath : srcFilePaths) {
	            handlerFile(zip, zipOut, new File(filePath), relativePath);
	        }
	    } catch (IOException e) {
	    	logger.error(e.getMessage(), e);
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

    /**
     * @param zip     压缩的目的地址
     * @param zipOut
     * @param srcFile 被压缩的文件信息
     * @param path    在zip中的相对路径
     * @throws IOException
     */
    private static void handlerFile(String zip, ZipOutputStream zipOut, File srcFile, String path) throws IOException {
    	
        logger.debug("start compress file [{}]", srcFile.getName());

        if(!srcFile.exists()){
            logger.error("文件不存在，srcFile:{}", srcFile);
            return ;
        }
        if (StringUtil.isNotBlank(path) && !path.endsWith(File.separator)) {
            path += File.separator;
        }
        if (StringUtil.equals(srcFile.getPath(), zip)) {
            logger.debug("要压缩的文件和目标文件相同!");
            return ;
        }
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            if (files.length == 0) {
                zipOut.putNextEntry(new ZipEntry(path + srcFile.getName() + File.separator));
                zipOut.closeEntry();
            } else {
                for (File file : files) {
                    handlerFile(zip, zipOut, file, path + srcFile.getName());
                }
            }
        } else {
            try(InputStream in = new FileInputStream(srcFile);){
                zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()));
                FileUtil.copy(in, zipOut);
                zipOut.closeEntry();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw e;
            }
        }
    }

    /**
     * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
     *
     * @param zipPath 待解压缩的ZIP文件名
     * @param descDir 目标目录
     */
    public static List<File> upzipFile(String zipPath, String descDir) {
        return upzipFile(new File(zipPath), descDir);
    }

    /**
     * 对.zip文件进行解压缩
     *
     * @param zipFile 解压缩文件
     * @param descDir 压缩的目标地址，如：D:\\测试 或 /mnt/d/测试
     * @return
     */
    public static List<File> upzipFile(File zipFile, String descDir) {
        List<File> listTmp = new ArrayList<>();
        try (ZipFile zipFileTmp = new ZipFile(zipFile, Charset.forName("GBK"))){
            for (Enumeration<?> entries = zipFileTmp.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File fileTmp = new File(descDir + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    fileTmp.mkdirs();
                } else {
                    if(copyFile(zipFileTmp, entry, fileTmp)){
                    	listTmp.add(fileTmp);
                    }
                }
            }
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }
        return listTmp;
    }

	private static boolean copyFile(ZipFile zipFileTmp, ZipEntry entry, File fileTmp) {
		File parentTmp = fileTmp.getParentFile();
        if (!parentTmp.exists()) {
            parentTmp.mkdirs();
        }
		try (FileOutputStream out = new FileOutputStream(fileTmp); 
				InputStream in = zipFileTmp.getInputStream(entry)){
			FileUtil.copy(in, out);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

    /**
     * 对临时生成的文件夹和文件夹下的文件进行删除
     */
    public static void deletefile(String delpath) {
        File file = new File(delpath);
        if (!file.isDirectory()) {
        	FileUtil.deleteFile(file);
        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File delfile = new File(delpath + File.separator + filelist[i]);
                if (!delfile.isDirectory()) {
                	FileUtil.deleteFile(delfile);
                } else if (delfile.isDirectory()) {
                    deletefile(delpath + File.separator + filelist[i]);
                }
            }
            FileUtil.deleteFile(file);
        }
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("D:/voiceAudios\\/RecWavXH2/20171011/95009//56092_20171011102334.wav");
        list.add("D:/voiceAudios\\/RecWavSZ2/20171012/92046//56011_20171012160105.wav");
        list.add("D:/voiceAudios\\//RecWavWH5//20171009//97153///58383_20171009090939.wav");
        zipFile("D:/aaaaa.zip", list,"audios");

    }

}