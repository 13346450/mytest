package com.dnake.service.common;
/**
 * http访问失败接口
*  HttpHandler <br/> 
*  2014年9月28日 下午2:34:52 <br/> 
* @author chen qige 
* @version
 */
public interface IHttpHandler {

	/**
	 * 消息发送失败处理
	* @title      failedHandler 
	* @author  chen qige     
	* @date      2014年9月28日
	 */
	public void failedHandler(String parms);
	/**
	 * 消息发送成功
	* @title      successHandler 
	* @author  chen qige     
	* @date      2014年9月28日 
	* @param id
	 */
	public void successHandler(Long id);
	
}
