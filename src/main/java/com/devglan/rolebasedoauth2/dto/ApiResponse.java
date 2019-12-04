package com.devglan.rolebasedoauth2.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	private int status;
	private String message;
	private Object payload;
	
	public ApiResponse(HttpStatus status, String message, Object result){
	    this.status = status.value();
	    this.message = message;
	    this.payload = result;
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

    @Override
	public String toString() {
		return "ApiResponse [statusCode=" + status + ", message=" + message +"]";
	}


}
