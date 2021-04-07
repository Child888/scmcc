package com.mbvan.mbvan.common.dto;

//base interface of all client request with request header
public interface ClientRequestWithHeader extends ClientRequest {

	public ClientRequestHeader getRequestHeader();
}
