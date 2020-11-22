package com.adp.coindispenser.exception;

public class CDDatabaseException extends CDException {
	public CDDatabaseException(ErrorCode errorCode, ErrorType type, String message) {
		super(errorCode, type, message);
	}
}
