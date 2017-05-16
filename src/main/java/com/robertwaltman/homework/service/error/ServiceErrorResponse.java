package com.robertwaltman.homework.service.error;

public class ServiceErrorResponse 
{

	protected String message;
	protected int status;
	protected int code;
	
	public ServiceErrorResponse(int theStatus, int theCode, String theMessage)
	{
		message = theMessage;
		status = theStatus;
		code = theCode;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}
	
	@Override
	public String toString()
	{
		return String.format("ServiceErrorResponse[Status (%d), Code(%d), Message(%s)", status, code, message);
	}
}
