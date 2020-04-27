package com.mindtree.springbootapi.models;

import java.util.List;

public class ListResponse<T> {
	Integer statusCode;
	String status;
	List<T> data;
	Integer length;

	public ListResponse() {

	}

	public ListResponse(Integer statusCode, String status, List<T> data, Integer length) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
		this.length = length;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getStatus() {
		return status;
	}

	public List<T> getData() {
		return data;
	}

	public Integer getLength() {
		return length;
	}
}
