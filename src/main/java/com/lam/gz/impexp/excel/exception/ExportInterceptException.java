package com.lam.gz.impexp.excel.exception;

/**
 * @author bo.liu01
 *
 */
public class ExportInterceptException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExportInterceptException() {
        super();
    }

    public ExportInterceptException(String msg) {
        super(msg);
    }

    public ExportInterceptException(Exception e) {
        super(e);
    }

    public ExportInterceptException(String message, Exception e) {
        super(message, e);
    }
}
