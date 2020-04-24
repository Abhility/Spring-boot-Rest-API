package com.mindtree.springbootapi;

public class Response<T> {

	Integer statusCode;
	String status;
	T data;

	public Response() {

	}

	public Response(Integer statusCode, String status, T data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}
}
