package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
import com.dnake.common.JsonDateTimeSerializer;

/**
 * 住户信息采集基础类(现阶段投诉建议基类)
 * @ClassName BizUserInfo
 * @author zgj
 * 2014年9月11日 下午4:24:38
 */
public class BizUinfoCollect
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 住户信息
	 */
	private String userContent;
	/**
	 * 住户ID
	 */
	private Long userId;
	private Long parentId;
	/**
	 * 回复信息类型  1用户插入 2回复信息
	 */
	private Integer contentType;
	/**
	 * 回复状态 1未回复 2已经回复
	 */
	private Integer replyStatus;
	/**
	 * 发布时间
	 */
	private Date publishDt;
	/**
	 * 住户信息类型
	 */
	private Long userinfoType;
	/**
	 * 社区Id
	 */
	private Long communityId;
	
	
	/**
	 * 领航对接参数
	 */
	private String userName;
	//
	private String title;
	//
	private String tel;
	//
	private String communityCode;
	//
	private String base64Images;
	//
	private String type;
	//
	private Long flag;
	//
	private String remark;
	//
	private String address;
	//
	private String email;
	//
	private String qq;
	//
	private String linkMan;
	//
	private String status;
	//
	private Date lastUpdated;
	private String lhID;
	private String[] listImage;
	
	
	public Long getIdKey()
	{
		return idKey;
	}
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}
	public String getUserContent()
	{
		return userContent;
	}
	public void setUserContent(String userContent)
	{
		this.userContent = userContent;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
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
	public Long getUserinfoType()
	{
		return userinfoType;
	}
	public void setUserinfoType(Long userinfoType)
	{
		this.userinfoType = userinfoType;
	}
	public Long getCommunityId()
	{
		return communityId;
	}
	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public String getCommunityCode()
	{
		return communityCode;
	}
	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
	}
	public String getBase64Images()
	{
		return base64Images;
	}
	public void setBase64Images(String base64Images)
	{
		this.base64Images = base64Images;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Long getFlag()
	{
		return flag;
	}
	public void setFlag(Long flag)
	{
		this.flag = flag;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	public String getLinkMan()
	{
		return linkMan;
	}
	public void setLinkMan(String linkMan)
	{
		this.linkMan = linkMan;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getLastUpdated()
	{
		return lastUpdated;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}
	public String[] getListImage()
	{
		return listImage;
	}
	public void setListImage(String[] listImage)
	{
		this.listImage = listImage;
	}
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	public Integer getContentType()
	{
		return contentType;
	}
	public void setContentType(Integer contentType)
	{
		this.contentType = contentType;
	}
	public Integer getReplyStatus()
	{
		return replyStatus;
	}
	public void setReplyStatus(Integer replyStatus)
	{
		this.replyStatus = replyStatus;
	}
	public String getLhID()
	{
		return lhID;
	}
	public void setLhID(String lhID)
	{
		this.lhID = lhID;
	}
}
