package com.dnake.tasks.lhApi;

import java.util.List;


public class LhListResult<T> extends LhBaseResult{
	private List<T> Data;

	public List<T> getData() {
		return Data;
	}

	public void setData(List<T> data) {
		Data = data;
	}
}
