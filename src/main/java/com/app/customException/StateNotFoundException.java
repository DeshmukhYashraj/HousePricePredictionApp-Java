package com.app.customException;

public class StateNotFoundException extends Exception{
	private static String message;
	public StateNotFoundException(String message){
		this.message = message;
	}
	
	public static String errMessage() {
		return message;
	}
}
