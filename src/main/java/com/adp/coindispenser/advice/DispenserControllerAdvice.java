package com.adp.coindispenser.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adp.coindispenser.exception.CDApplicationException;
import com.adp.coindispenser.exception.CDErrorResp;
import com.adp.coindispenser.exception.CDException;
import com.adp.coindispenser.exception.ErrorCode;
import com.adp.coindispenser.exception.ErrorType;

@ControllerAdvice
public class DispenserControllerAdvice {
	
	@ExceptionHandler(value = CDException.class)	
	public ResponseEntity<Object>exception(CDException infe){
		
		CDErrorResp errorResp = new CDErrorResp();
		errorResp.setErrorCode(infe.getErrorCode().name());
		errorResp.setType(infe.getType().name());
		errorResp.setMessage(infe.getMessage());
		
		return ResponseEntity.status(infe.getHttpStatus()).body(errorResp);
	}
	
	@ExceptionHandler(value = RuntimeException.class)	
	public ResponseEntity<Object>exception(RuntimeException re){
		
		CDApplicationException applicationException = new CDApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.CD_APPLICATION_100, ErrorType.APPLICATION, "Application error");
		CDErrorResp errorResp = new CDErrorResp();
		errorResp.setErrorCode(applicationException.getErrorCode().name());
		errorResp.setType(applicationException.getType().name());
		errorResp.setMessage(applicationException.getMessage());
		
		return ResponseEntity.status(applicationException.getHttpStatus()).body(errorResp);
	}


}
