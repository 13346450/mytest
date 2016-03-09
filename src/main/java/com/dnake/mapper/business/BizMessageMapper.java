package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizMessage;
import com.dnake.domain.business.BizNote;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.common.Page;

/**
 * 帖子映射接口
 * @ClassName BizNoteMapper
 * @author zgj
 * 2014年9月16日 下午1:40:22
 */
public interface BizMessageMapper
{
	/**
	 * 插入一条新帖子
	 * @param BizNote
	 */
	public void insert(BizMessage bizMessage);
	
	/**
	 * 根据ID查询
	 * @param idKey
	 * @return
	 */
	public BizMessage queryById(Long idKey);
	/**
	 * 根据ID删除一条帖子
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 查询全部
	* @title      queryAll 
	* @author  chen qige     
	* @date      2014年9月27日 
	* @return
	 */
	public List<BizMessage> queryAll();

}
