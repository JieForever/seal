package com.xy.exception;

public class NumNoEqul extends RuntimeException{
	
	private static final long serialVersionUID = 5507772971941417044L;
	
	public NumNoEqul(){
		super();
	}
	
	public NumNoEqul(String msg){
		super(msg);
	}
	public NumNoEqul(String msg, Throwable cause){
		super(msg,cause);
	}
}
