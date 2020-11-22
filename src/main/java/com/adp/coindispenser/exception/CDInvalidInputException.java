package com.adp.coindispenser.exception;

import org.springframework.http.HttpStatus;

public class CDInvalidInputException extends CDException {
    public CDInvalidInputException(ErrorCode errorCode, ErrorType type, String message) {
    	super(errorCode, type, message );
    	httpStatus = HttpStatus.BAD_REQUEST;
	}

}
