package com.dnake.mapper.business;

import java.util.List;

import com.dnake.domain.business.BizNoteReply;
import com.dnake.domain.business.BizNoteReplyVO;
import com.dnake.domain.common.Page;

/**
 * 回复表接口
 * @ClassName BizNoteReplyMapper
 * @author zgj
 * 2014年9月16日 下午5:33:23
 */
public interface BizNoteReplyMapper
{
	/**
	 * 插入一条评论
	 * @param bizNoteReply
	 */
	public void insert(BizNoteReply bizNoteReply);
	/**
	 * 删除一条评论
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizNoteReplyVO> queryPage(Long idKey);
	/**
	 * 單條查詢
	 * @param idKey
	 * @return
	 */
	public BizNoteReplyVO queryById(Long idKey);
}
