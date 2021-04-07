package com.mbvan.mbvan.common.datahandle;

import com.mbvan.mbvan.common.dto.ClientResponseWithHeader;

public interface ResponseHandler {

	public ClientResponseWithHeader wrapResponseHead(ClientResponseWithHeader clientResponse);
	
}
