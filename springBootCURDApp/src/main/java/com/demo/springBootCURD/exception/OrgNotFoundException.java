package com.demo.springBootCURD.exception;

public class OrgNotFoundException extends ResourceNotFoundException {
	
	public OrgNotFoundException(String message){
		super(message);
	}

}
