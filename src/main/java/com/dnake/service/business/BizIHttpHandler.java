package com.dnake.service.business;

public interface BizIHttpHandler
{
	/**
	 * 请求发送失败处理
	* @title      failedHandler 
	* @author  chen qige     
	* @date      2014年9月28日
	 */
	public void failedHandler(String parms);
	/**
	 * 请求发送成功
	* @title      successHandler 
	* @author  chen qige     
	* @date      2014年9月28日 
	* @param id
	 */
	public void successHandler(String parms, String info);
	/**
	 * 不带小区ID的发送成功
	 * @param parms
	 * @param url
	 */
	public void successHandler(String parms);
}
