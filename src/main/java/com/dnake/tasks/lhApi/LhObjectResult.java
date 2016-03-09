package com.dnake.tasks.lhApi;



public class LhObjectResult<T>  extends LhBaseResult{
	private T Data;

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}
	
}
