package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizCommentImage;

/**
 *  @author zgj
 *	日期：2015年3月23日下午4:31:31
 *  描述：评论图片持久化层
 */
public interface BizCommentImageMapper
{
	/**
	 * 根据评论ID查询
	 * @param idKey
	 * @return
	 */
	public List<BizCommentImage> queryByCommentIdkey(long commentId);

	/**
	 * 删除
	 * @param map
	 */
	public void deleteMulti(long commentId);

	/**
	 * 插入
	 * @param 
	 */
	public void insert(BizCommentImage bizCommentImage);
}
