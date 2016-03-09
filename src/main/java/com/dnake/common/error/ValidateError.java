package com.dnake.common.error;

@SuppressWarnings("serial")
public class ValidateError extends CommonError{

	public ValidateError(int errorCode, String errorMessge, Object data) {
		super(errorCode, errorMessge, data);
	}

	public ValidateError(int errorCode, String errorMessge) {
		super(errorCode, errorMessge);
	}
}
