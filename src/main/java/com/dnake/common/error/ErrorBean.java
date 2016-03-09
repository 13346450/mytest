package com.dnake.common.error;

public class ErrorBean{
	private int status;
	private String messages;
	private Object data;
	public ErrorBean(int errorCode, String errorMessge, Object data) {
		this.setStatus(errorCode);
		this.setMessages(errorMessge);
		this.data = data;
	}
	public Object getData()
	{
		return data;
	}
	public String getMessages()
	{
		return messages;
	}
	public int getStatus()
	
	{
		return status;
	}
	public void setData(Object data)
	{
		this.data = data;
	}
	public void setMessages(String messages)
	{
		this.messages = messages;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	

}
