package com.mbvan.mbvan.common.dto;


public class SingleDataClientResponse<T> extends BaseClientResponse{

	protected T singleData;
	protected ClientResponseHeader responseHeader = new ClientResponseHeader();
	
	public void setSingleData(T singleData){
		this.singleData = singleData;
	}
	
	public T getSingleData(){
		return this.singleData;
	}

	@Override
	public ClientResponseHeader getResponseHeader() {
		return responseHeader;
	}

	@Override
	public void setResponseHeader(ClientResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	
}
