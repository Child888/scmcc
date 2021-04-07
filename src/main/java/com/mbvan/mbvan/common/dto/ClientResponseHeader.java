package com.mbvan.mbvan.common.dto;

//the response header
public class ClientResponseHeader {
	
	//success failure flag , 1 success, 0 empty , -1 failure
	private int result = 1; 
	
	//failure code
	private String resultCode; 
	
	//failure description
	private String resultDescription;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
}
