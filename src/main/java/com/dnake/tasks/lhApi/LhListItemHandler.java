package com.dnake.tasks.lhApi;

public interface LhListItemHandler<T> {
	public void execute(LhListResult<T> root,T item);
}
