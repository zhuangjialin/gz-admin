package com.lam.gz.log.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liubo
 *
 */
public abstract class AbstractFieldConvert implements FieldConvert {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String TYPE_STRING = "String";
	public static final String TYPE_BOOLEAN = "Boolean";
	public static final String TYPE_INT = "int";
	public static final String TYPE_INTEGER = "Integer";
	public static final String TYPE_LONG = "Long";
	public static final String TYPE_DOUBLE = "Double";
	public static final String TYPE_FLOAT = "Float";
	public static final String TYPE_CHARACTER = "Character";
	public static final String TYPE_CHAR = "char";
	public static final String TYPE_BYTE = "Byte";
	
}
