package com.lam.gz.common.util;

import java.util.Date;

/**
 * id生成器
 * 参考至  oam SerialNumberUtils
 * @author xulu
 *
 */
public class IdGeneratorUtils {

    private static final long START_COUNT = 100000L;
    private static final long MAX_COUNT = 999998L;
    private static final String DATE_FORMAT = "yyMMddHHmmssSSS";
    
    private static long iCount = START_COUNT;
    
    private IdGeneratorUtils(){
		throw new IllegalStateException("Utility class");
	}

    /**
     * 获取序列号
     *
     * @return yyyyMMddHHmmssSSSxxxxxxx000000
     */
    public static synchronized String getSerialNo() {
        if (iCount > MAX_COUNT) {
            iCount = START_COUNT;
        }

        String sTime = DateUtil.formatDate(new Date(), DATE_FORMAT);
        //获取7位随机数
        String sRandom = getMathRandom(); 
        return sTime + sRandom + iCount++;
    }

    /**
     * 获取以“sPrefix”开头的序列号
     *
     * @param sPrefix [sPrefix字符建议长度两位以内字符,超过两位去前两位]
     * @return **MMddHHmmssSSSxxxxxxx000000
     */
    public static synchronized String getSerialNo(String sPrefix) {

        if (StringUtil.isNotBlank(sPrefix)) {
            sPrefix = sPrefix.trim();
            sPrefix = sPrefix.length() > 2 ? sPrefix.substring(2).toUpperCase():
            	sPrefix.toUpperCase();
            return sPrefix + getSerialNo();
        }
        return getSerialNo();
    }

    /**
     * 获取随7位机数（为避免重复，分别获取4位和3位两组随机数）
     * Math.random()产生一个[0，1)之间的随机double数值，是Java、js等语言常用代码。
     * 例如：var a = Math.random() * 2 + 1，设置一个随机1到3(取不到3)的变量。
     *
     * @return
     */
    private static String getMathRandom() {
        //获取4位随机数
        String r1 = Double.toString(Math.random()).substring(2);
        r1 = r1.length() > 4 ? r1.substring(0, 4):
        	(r1 + "000").substring(0, 4);

        //获取3位随机数
        String r2 = Double.toString(Math.random()).substring(2);
        r2 = r2.length() > 3 ? r2.substring(0, 3):
        	(r2 + "00").substring(0, 3);
            
        return r1 + r2;
    }

}
