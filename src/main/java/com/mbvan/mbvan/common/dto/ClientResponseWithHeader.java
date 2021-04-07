package com.mbvan.mbvan.common.dto;

//base interface of all client response with response header
public interface ClientResponseWithHeader extends ClientResponse{

	public ClientResponseHeader getResponseHeader();
	
	public void setResponseHeader(ClientResponseHeader responseHeader);
}
