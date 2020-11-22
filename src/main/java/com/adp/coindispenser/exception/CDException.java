package com.adp.coindispenser.exception;

import org.springframework.http.HttpStatus;

public class CDException extends RuntimeException {
	protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
	private ErrorCode errorCode;
	private ErrorType type;
	private String message;

	public CDException(ErrorCode errorCode, ErrorType type, String message) {

		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public ErrorType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
