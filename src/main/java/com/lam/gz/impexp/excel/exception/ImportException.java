package com.lam.gz.impexp.excel.exception;

/**
 * @author bo.liu01
 *
 */
public class ImportException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ImportException() {
        super();
    }

    public ImportException(String msg) {
        super(msg);
    }

    public ImportException(Exception e) {
        super(e);
    }

    public ImportException(String message, Exception e) {
        super(message, e);
    }
}
