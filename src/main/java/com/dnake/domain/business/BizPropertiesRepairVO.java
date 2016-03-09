package com.dnake.domain.business;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

public class BizPropertiesRepairVO
{
	//
	private Long idKey;
	//
	private String title;
	// 1个人报修 2公共报修
	private Integer type;
	//
	private String content;
	//
	private Long parentId;
	//
	private String contacts;
	//
	private String contactsTel;
	//
	private String contactsAddr;
	//
	private Date publishDt;
	//
	private Long userId;
	//
	private Long communityId;
	//状态 1未处理  2已处理
	private Integer repairStatus;
	/**
	 * 回复内容
	 */
	private String replyContent;
	/**
	 * 物业申报图片
	 */
	private List<BizRepairImage> listImage;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户所属楼栋
	 */
	private String userBuilding;
	/**
	 * 用户所属单元
	 */
	private String userUnit;
	/**
	 * 用户所属楼层
	 */
	private String userFloor;
	/**
	 * 用户所属房号
	 */
	private String userRoom;
	/**
	 * 小区名称
	 */
	private String communityName;
	private String typeNames;
	
	/**
	 * 状态名称 1未处理 2已处理
	 */
	private String statusName;
	/**
	 * 回复时间
	 */
	private Date replyDt;
	/**
	 * 回复人ID
	 */
	private Long replyUserId;
	/**
	 * 图片字串集合
	 */
	private String imageString;
	
	
	
	//三方接口扩展字段
	private String communityCode;
	private String typeName;
	private String address;
	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 1个人报修 2公共报修
	 * 
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public Integer getType()
	{
		return type;
	}

	/**
	 * 1个人报修 2公共报修
	 * 
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setType(Integer type)
	{
		this.type = type;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public Long getParentId()
	{
		return parentId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public String getContacts()
	{
		return contacts;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setContacts(String contacts)
	{
		this.contacts = contacts;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public String getContactsTel()
	{
		return contactsTel;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setContactsTel(String contactsTel)
	{
		this.contactsTel = contactsTel;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public String getContactsAddr()
	{
		return contactsAddr;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setContactsAddr(String contactsAddr)
	{
		this.contactsAddr = contactsAddr;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getPublishDt()
	{
		return publishDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setPublishDt(Date publishDt)
	{
		this.publishDt = publishDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public Long getUserId()
	{
		return userId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
	public Long getCommunityId()
	{
		return communityId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-06-23 13:54:06
	 */
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

	public String getCommunityName()
	{
		return communityName;
	}

	public void setCommunityName(String communityName)
	{
		this.communityName = communityName;
	}

	public Integer getRepairStatus()
	{
		return repairStatus;
	}

	public void setRepairStatus(Integer repairStatus)
	{
		this.repairStatus = repairStatus;
	}

	public List<BizRepairImage> getListImage()
	{
		return listImage;
	}

	public void setListImage(List<BizRepairImage> listImage)
	{
		this.listImage = listImage;
	}

	public String getReplyContent()
	{
		return replyContent;
	}

	public void setReplyContent(String replyContent)
	{
		this.replyContent = replyContent;
	}

	public String getCommunityCode()
	{
		return communityCode;
	}

	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
	}
	public String getTypeNames()
	{
		return typeNames;
	}

	public void setTypeNames(String typeNames)
	{
		this.typeNames = typeNames;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getReplyDt()
	{
		return replyDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setReplyDt(Date replyDt)
	{
		this.replyDt = replyDt;
	}

	public Long getReplyUserId()
	{
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId)
	{
		this.replyUserId = replyUserId;
	}

	public String getImageString()
	{
		return imageString;
	}

	public void setImageString(String imageString)
	{
		this.imageString = imageString;
	}

	public String getUserUnit()
	{
		return userUnit;
	}

	public void setUserUnit(String userUnit)
	{
		this.userUnit = userUnit;
	}

	public String getUserFloor()
	{
		return userFloor;
	}

	public void setUserFloor(String userFloor)
	{
		this.userFloor = userFloor;
	}

	public String getUserRoom()
	{
		return userRoom;
	}

	public void setUserRoom(String userRoom)
	{
		this.userRoom = userRoom;
	}

	public String getUserBuilding()
	{
		return userBuilding;
	}

	public void setUserBuilding(String userBuilding)
	{
		this.userBuilding = userBuilding;
	}
}
