package com.dnake.domain.business;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 用户帖子类，升级
 * @ClassName BizNoteVO
 * @author zgj
 * 2014年9月16日 上午11:15:00
 */
public class BizNoteVO
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 排序号
	 */
	private Long orderId;
	/**
	 * 帖子标题
	 */
	private String noteTitle;
	/**
	 * 帖子类型
	 */
	private Long noteType;
	/**
	 * 帖子内容
	 */
	private String noteContent;
	/**
	 * 最后回复时间
	 */
	private Date lastReplyDt;
	/**
	 * 发布时间
	 */
	private Date publishDt;
	/**
	 * 回复数量
	 */
	private int replyCount;
	/**
	 * 点击数量
	 */
	private int clicksCount;
	/**
	 * 是否精华
	 */
	private int isElite;
	/**
	 * 是否置顶
	 */
	private int isTop;
	/**
	 * 是否禁止回复
	 */
	private int noReply;
	/**
	 * 图片地址
	 */
	private String imagesUrl;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 小区Id
	 */
	private Long communityId;
	
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 小区名
	 */
	private String communityName;
	/**
	 * 帖子类型名
	 */
	private String noteTypeName;
	
	/**
	 * 回复帖子列表
	 */
	private List<BizNoteReplyVO> listReply;
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getCommunityName()
	{
		return communityName;
	}
	public void setCommunityName(String communityName)
	{
		this.communityName = communityName;
	}
	public String getNoteTypeName()
	{
		return noteTypeName;
	}
	public void setNoteTypeName(String noteTypeName)
	{
		this.noteTypeName = noteTypeName;
	}
	public Long getIdKey()
	{
		return idKey;
	}
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}
	public String getNoteTitle()
	{
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle)
	{
		this.noteTitle = noteTitle;
	}
	public Long getNoteType()
	{
		return noteType;
	}
	public void setNoteType(Long noteType)
	{
		this.noteType = noteType;
	}
	public String getNoteContent()
	{
		return noteContent;
	}
	public void setNoteContent(String noteContent)
	{
		this.noteContent = noteContent;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getLastReplyDt()
	{
		return lastReplyDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setLastReplyDt(Date lastReplyDt)
	{
		this.lastReplyDt = lastReplyDt;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPublishDt()
	{
		return publishDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPublishDt(Date publishDt)
	{
		this.publishDt = publishDt;
	}
	public int getReplyCount()
	{
		return replyCount;
	}
	public void setReplyCount(int replyCount)
	{
		this.replyCount = replyCount;
	}
	public int getClicksCount()
	{
		return clicksCount;
	}
	public void setClicksCount(int clicksCount)
	{
		this.clicksCount = clicksCount;
	}
	public int getIsElite()
	{
		return isElite;
	}
	public void setIsElite(int isElite)
	{
		this.isElite = isElite;
	}
	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}
	public int getNoReply()
	{
		return noReply;
	}
	public void setNoReply(int noReply)
	{
		this.noReply = noReply;
	}
	public String getImagesUrl()
	{
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl)
	{
		this.imagesUrl = imagesUrl;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public Long getCommunityId()
	{
		return communityId;
	}
	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
	public Long getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}
	public List<BizNoteReplyVO> getListReply()
	{
		return listReply;
	}
	public void setListReply(List<BizNoteReplyVO> listReply)
	{
		this.listReply = listReply;
	}
}
