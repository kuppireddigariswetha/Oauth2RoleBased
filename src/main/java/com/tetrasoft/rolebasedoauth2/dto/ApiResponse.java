package com.tetrasoft.rolebasedoauth2.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	private int status;
	private String message;
	private Object payload;
	 //private List<String> errors;
	 
	public ApiResponse(HttpStatus status, String message, Object result){
	    this.status = status.value();
	    this.message = message;
	    this.payload = result;
	    //this.errors = errors;
    }

 

	public ApiResponse(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object result) {
        this.payload = result;
    }

	/*
	 * public List<String> getErrors() { return errors; }
	 * 
	 * public void setErrors(List<String> errors) { this.errors = errors; }
	 */
    @Override
	public String toString() {
		return "ApiResponse [statusCode=" + status + ", message=" + message +"]";
	}


}
