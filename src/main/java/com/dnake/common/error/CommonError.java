package com.dnake.common.error;


public class CommonError extends RuntimeException{
	/**
	 * errorBean 用来将错误信息输出，其基本属性与CommonError一致
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String messages;
	private Object data;
	private ErrorBean errorBean;
	public CommonError(int errorCode, String errorMessge, Object data) {
		super("errorCode:"+errorCode+" > "+errorMessge);
		errorBean = new ErrorBean(errorCode, errorMessge, data);
		this.status = errorCode;
		this.messages = errorMessge;
		if(data==null){
			data=new Object();
		}
		this.data = data;
	}
	public CommonError(int errorCode, String errorMessge) {
		this(errorCode, errorMessge, new Object());
		this.data = new Object();
		this.status = errorCode;
		this.messages = errorMessge;
	}

	public int getErrorCode() {
		return status;
	}
	public Object getData() {
		return data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ErrorBean getErrorBean() {
		return errorBean;
	}
	public String getMessages()
	{
		return messages;
	}
	public void setMessages(String messages)
	{
		this.messages = messages;
	}
	
}
