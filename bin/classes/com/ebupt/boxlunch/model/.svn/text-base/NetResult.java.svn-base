package com.ebupt.boxlunch.model;

public class NetResult {
	
	private String errorCode = null;
	
	private String errorMessage = null;
	
	private String retContent = null;
	
	public NetResult() {
	}
	
	public NetResult(String errorCode, String errorMessage) {
		init( errorCode,  errorMessage, null);
	}
	
	public NetResult(String errorCode, String errorMessage, String retContent) {
		init( errorCode,  errorMessage, retContent);
	}
	
	private void init(String errorCode, String errorMessage, String retContent){
		this.errorCode = errorCode;
		this.retContent = retContent;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRetContent() {
		return retContent;
	}

	public void setRetContent(String retContent) {
		this.retContent = retContent;
	}
	
	// For Test
	public String toString(){
		return errorCode + "|" + errorMessage +"|" + retContent;
	}
}
