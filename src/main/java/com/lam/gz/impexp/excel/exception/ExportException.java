package com.lam.gz.impexp.excel.exception;

/**
 * @author bo.liu01
 *
 */
public class ExportException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExportException() {
        super();
    }

    public ExportException(String msg) {
        super(msg);
    }

    public ExportException(Exception e) {
        super(e);
    }

    public ExportException(String message, Exception e) {
        super(message, e);
    }
}
