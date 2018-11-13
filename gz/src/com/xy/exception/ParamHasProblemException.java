package com.xy.exception;

public class ParamHasProblemException extends RuntimeException{

	private static final long serialVersionUID = -8854181541789141138L;
	
	public ParamHasProblemException(){}
	
	public ParamHasProblemException(String msg){
		super(msg);
	}
	public ParamHasProblemException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
