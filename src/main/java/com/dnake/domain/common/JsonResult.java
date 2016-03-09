package com.dnake.domain.common;

/**
 *  @author zgj
 *	日期：2015年3月27日下午4:59:23
 *  描述：返回json的结果集基类
 */
public class JsonResult
{
	/**
	 * 返回状态
	 */
	private Integer status=1;
	/**
	 * 状态信息
	 */
	private String messages="操作成功";
	/**
	 * 数据集
	 */
	private Object data = new Object();
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public String getMessages()
	{
		return messages;
	}
	public void setMessages(String messages)
	{
		this.messages = messages;
	}
	public Object getData()
	{
		return data;
	}
	public void setData(Object data)
	{
		this.data = data;
	}
}
