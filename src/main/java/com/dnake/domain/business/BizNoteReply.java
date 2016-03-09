package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 帖子回复表
 * @ClassName BizNoteReply
 * @author zgj
 * 2014年9月16日 上午11:55:00
 */
public class BizNoteReply
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 帖子Id
	 */
	private Long noteId;
	/**
	 * 回复人Id
	 */
	private Long replyUserId;
	/**
	 * 回复时间
	 */
	private Date replyDt;
	/**
	 * 回复内容
	 */
	private String replyContent;
	public Long getIdKey()
	{
		return idKey;
	}
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}
	public Long getNoteId()
	{
		return noteId;
	}
	public void setNoteId(Long noteId)
	{
		this.noteId = noteId;
	}
	public Long getReplyUserId()
	{
		return replyUserId;
	}
	public void setReplyUserId(Long replyUserId)
	{
		this.replyUserId = replyUserId;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getReplyDt()
	{
		return replyDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setReplyDt(Date replyDt)
	{
		this.replyDt = replyDt;
	}
	public String getReplyContent()
	{
		return replyContent;
	}
	public void setReplyContent(String replyContent)
	{
		this.replyContent = replyContent;
	}
}
