package com.mbvan.mbvan.common.datahandle;

import com.mbvan.mbvan.common.Constants;
import com.mbvan.mbvan.common.ErrorCodeConstants;
import com.mbvan.mbvan.common.dto.ClientResponseHeader;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;

public class ExceptionHandler {



	private static ClientResponseHeader generateErrorHeader(Exception ex){
		ClientResponseHeader responseHeader = new ClientResponseHeader();
		responseHeader.setResult(Constants.RESULT_FLAG_FAILURE);
		responseHeader.setResultDescription(ex.getMessage());
		if(ex instanceof IllegalArgumentException){
			responseHeader.setResultCode(ErrorCodeConstants.ERR_CODE_ILLEGAL_ARGUMENT);
		}
		else{
			responseHeader.setResultCode(ErrorCodeConstants.ERR_CODE_SYSTEM_EXCEPTION);
		}
		return responseHeader;
	}	
	
	public static <T> SingleDataClientResponse<T> handleExceptionToSingleDataClientResponse(Exception ex){

		SingleDataClientResponse<T> response = new SingleDataClientResponse<T>();
		ClientResponseHeader blank = generateErrorHeader(ex);
		response.setResponseHeader(blank);
		return response;
	}
	
	
}
