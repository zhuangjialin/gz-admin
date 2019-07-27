package com.lam.gz.common.constant;

/**
 * 分隔符静态类
 * @author liubo
 *
 */
public class SepConstants {
	
	/** 分隔符，逗号 */
	public static final String COMMA = ",";
	/** 分隔符，点 */
	public static final String POINT = ".";
	/** 分隔符，连接线 */
	public static final String HYPHEN = "-";
	/** 分隔符，下划线 */
	public static final String UNDERLINE = "_";
	/** 分隔符，正斜杠 */
	public static final String SLASH = "/";
	/** 分隔符，反斜杠 */
	public static final String BACK_SLASH = "\\";
	
	/** 后缀，wav */
	public static final String SUFFIX_WAV = "wav";
	/** 后缀，.wav */
	public static final String SUFFIX_P_WAV = ".wav";
	
	private SepConstants() {
		throw new IllegalStateException("Utility class");
	}
}
