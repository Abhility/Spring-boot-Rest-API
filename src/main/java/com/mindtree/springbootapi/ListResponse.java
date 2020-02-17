package com.mindtree.springbootapi;

import java.util.List;

public class ListResponse<T> {
	Integer statusCode;
	String status;
	List<T> data;

	public ListResponse() {

	}

	public ListResponse(Integer statusCode, String status, List<T> data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
