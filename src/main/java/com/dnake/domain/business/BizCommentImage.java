package com.dnake.domain.business;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  @author zgj
 *	日期：2015年3月23日下午4:13:49
 *  描述：评论图片类
 */
public class BizCommentImage
{
	/**
	 * 主键
	 */
	@JSONField(serialize = false)
	private Long idKey;
	/**
	 * 评论ID
	 */
	@JSONField(serialize = false)
	private Long commentId;
	/**
	 * 图片地址
	 */
	private String imageUrl;

	public Long getIdKey()
	{
		return idKey;
	}

	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	public Long getCommentId()
	{
		return commentId;
	}

	public void setCommentId(Long commentId)
	{
		this.commentId = commentId;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
}
