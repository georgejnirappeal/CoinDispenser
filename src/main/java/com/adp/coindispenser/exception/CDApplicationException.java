package com.adp.coindispenser.exception;

import org.springframework.http.HttpStatus;

public class CDApplicationException extends CDException {
	
	public CDApplicationException(HttpStatus httpStatus,ErrorCode errorCode, ErrorType type, String message) {
		super(errorCode, type, message);
		
		this.httpStatus = httpStatus;
	}
}
