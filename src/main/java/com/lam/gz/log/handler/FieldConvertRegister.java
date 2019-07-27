package com.lam.gz.log.handler;

/**
 * @author liubo
 *
 */
public enum FieldConvertRegister {
	
//	USER(new UserDefinedFieldConvert()),
	DEF(new DefaultFieldConvert());
	
	/**
	 * @param fieldConvert
	 */
	private FieldConvertRegister(FieldConvert fieldConvert) {
		this.fieldConvert = fieldConvert;
	}

	private FieldConvert fieldConvert;

	public FieldConvert getFieldConvert() {
		return fieldConvert;
	}

}
