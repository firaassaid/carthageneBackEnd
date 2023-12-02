package com.tta.carthagene.response;

import org.springframework.http.HttpStatus;

public class BasicResponse {
	private String message;
	private HttpStatus response; // 200= OK / 400= BAD_REQUEST

	public BasicResponse(String message, HttpStatus response) {
		super();
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getResponse() {
		return response;
	}

	public void setResponse(HttpStatus response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "BasicResponse [message=" + message + ", response=" + response + "]";
	}
}
