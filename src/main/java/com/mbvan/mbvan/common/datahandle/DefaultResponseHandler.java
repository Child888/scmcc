package com.mbvan.mbvan.common.datahandle;

import com.mbvan.mbvan.common.Constants;
import com.mbvan.mbvan.common.dto.BaseClientResponse;
import com.mbvan.mbvan.common.dto.ClientResponseHeader;
import com.mbvan.mbvan.common.dto.ClientResponseWithHeader;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;

public class DefaultResponseHandler {
	
	public static ClientResponseWithHeader wrapResponseHead(ClientResponseWithHeader clientResponse){
		return wrapResponseHead(clientResponse,Constants.RESULT_FLAG_SUCCESS);
	}
	
	public static ClientResponseWithHeader wrapResponseHead(ClientResponseWithHeader clientResponse,int result){

		ClientResponseHeader header = clientResponse.getResponseHeader();
		if(header ==null){
			header = new ClientResponseHeader();
			clientResponse.setResponseHeader(header);
		}
		header.setResult(result);
		return clientResponse;
	}
	
	public static <T> SingleDataClientResponse<T> generateSingleDataClientResponse(T t){
		SingleDataClientResponse<T> clientResponse = new SingleDataClientResponse<T>();
		
		if(t == null ){
			wrapResponseHead(clientResponse,Constants.RESULT_FLAG_EMPTY);
		}
		else{
			wrapResponseHead(clientResponse,Constants.RESULT_FLAG_SUCCESS);
		}
		clientResponse.setSingleData(t);
		return clientResponse;
	}
	
	public static ClientResponseWithHeader generateBlankResponse(){
		ClientResponseWithHeader clientResponse = new BaseClientResponse();
		wrapResponseHead(clientResponse);
		return clientResponse;
	}
	
}
