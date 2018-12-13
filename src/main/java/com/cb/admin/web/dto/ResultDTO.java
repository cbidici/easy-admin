package com.cb.admin.web.dto;

public class ResultDTO<T> {

	private int result;
	
	private T content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
	
}
