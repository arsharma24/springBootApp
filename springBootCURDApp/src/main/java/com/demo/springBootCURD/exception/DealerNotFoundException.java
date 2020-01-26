package com.demo.springBootCURD.exception;

public class DealerNotFoundException extends ResourceNotFoundException {
	
	public DealerNotFoundException(String message){
		super(message);
	}

}
