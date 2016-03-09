package com.dnake.service.business;

import com.dnake.domain.business.BizMessage;

/**
 * 消息推送
*  BizMessageService <br/> 
*  2014年9月27日 下午5:39:46 <br/> 
* @author chen qige 
* @version
 */
public interface BizMessageService {
	/**
	 * 插入一条消息
	* @title      insert 
	* @author  chen qige     
	* @date      2014年9月27日 
	* @param bizMessage
	 */
	void insert(BizMessage bizMessage);
	/**
	 * 删除一条
	* @title      delete 
	* @author  chen qige     
	* @date      2014年9月27日 
	* @param idKey
	 */
	void delete(Long idKey);
	/**
	 * 选择一条
	* @title      select 
	* @author  chen qige     
	* @date      2014年9月27日 
	* @param idKey
	* @return
	 */
	BizMessage selectById(Long idKey);
	/**
	 * 定时推送消息
	* @title      timerMessage 
	* @author  chen qige     
	* @date      2014年9月27日
	 */
	void timerSendMessage();
}
