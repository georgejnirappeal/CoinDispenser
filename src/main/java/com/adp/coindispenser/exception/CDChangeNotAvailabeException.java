package com.adp.coindispenser.exception;

import org.springframework.http.HttpStatus;

public class CDChangeNotAvailabeException extends CDException {
    public CDChangeNotAvailabeException(ErrorCode errorCode, ErrorType type, String message) {
    	super(errorCode, type, message );
    	httpStatus = HttpStatus.NOT_FOUND;
	}

}
