package com.dnake.common.error;

@SuppressWarnings("serial")
public class ServiceError extends CommonError{

	public ServiceError(int errorCode, String errorMessge, Object data) {
		super(errorCode, errorMessge, data);
	}

	public ServiceError(int errorCode, String errorMessge) {
		super(errorCode, errorMessge);
	}
}
