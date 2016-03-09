package com.dnake.service.business;

import java.util.List;

import com.dnake.domain.business.BizNoteReply;
import com.dnake.domain.business.BizNoteReplyVO;

/**
 * 帖子回复服务层
 * @ClassName BizNoteReplyService
 * @author zgj
 * 2014年9月16日 下午6:20:14
 */
public interface BizNoteReplyService
{
	/**
	 * 插入一条回复
	 * @param bizNoteReply
	 */
	public String insert(BizNoteReply bizNoteReply);
	/**
	 * 删除一条回复
	 * @param idKey
	 * @return
	 */
	public String delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizNoteReplyVO> listPage(Long noteId);
}
