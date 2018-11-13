package com.xy.exception;

public class ExlDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExlDataException(){super();}
	
	public ExlDataException(String message){
		super(message);
	}
	
	public ExlDataException(String message, Throwable cause){
		super(message,cause);
	}
	public ExlDataException(Throwable cause){
		super(cause);
	}
}
