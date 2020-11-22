package com.adp.coindispenser.exception;

import org.springframework.http.HttpStatus;

public class CDBusinessException extends CDException {
	
	public CDBusinessException(HttpStatus httpStatus, ErrorCode errorCode, ErrorType type, String message) {
		super(errorCode, type, message);
		
		this.httpStatus = httpStatus;
	}
}
