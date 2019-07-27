package com.lam.gz.impexp.excel.validation;

/**
 * @author liubo
 *
 * @param <T>
 */
public abstract class AbstractVerifier<T> implements Verifier<T>{
	
	protected boolean activeBatchCheck;

	@Override
	public boolean isActiveBatchCheck() {
		return activeBatchCheck;
	}

	public void setActiveBatchCheck(boolean activeBatchCheck) {
		this.activeBatchCheck = activeBatchCheck;
	}

}
