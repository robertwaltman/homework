package com.robertwaltman.homework.service.error;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler({UnsupportedOperationException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ServiceErrorResponse unprocessableEntity(UnsupportedOperationException theException) 
	{
		ServiceErrorResponse theError = new ServiceErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), 1, theException.getMessage());
		
		return theError;
	}
	
	@ExceptionHandler({DataRetrievalFailureException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ServiceErrorResponse dataNotFound(DataRetrievalFailureException theException) 
	{
		ServiceErrorResponse theError = new ServiceErrorResponse(HttpStatus.NOT_FOUND.value(), 1, theException.getMessage());
		
		return theError;
	}

}
