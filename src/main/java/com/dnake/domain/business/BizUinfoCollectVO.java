package com.dnake.domain.business;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
import com.dnake.common.JsonDateTimeSerializer;

/**
 * 住户信息采集基础类
 * @ClassName BizUserInfo
 * @author zgj
 * 2014年9月11日 下午4:24:38
 */
public class BizUinfoCollectVO
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 住户发布信息
	 */
	private String userContent;
	/**
	 * 住户ID
	 */
	private Long userId;
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
	
	private Long parentId;
	/**
	 * 回复状态 1未回复 2已经回复
	 */
	private Integer replyStatus;
	/**
	 * 回复信息类型  1用户插入 2回复信息
	 */
	private Integer contentType;
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
	 * 住户姓名
	 */
	private String userName;
	/**
	 * 类型
	 */
	private String infoTypeName;
	private String communityName;
	
	
	/**
	 * 领航对接参数
	 */
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
	private List<BizRepairImage> listImage;
	/**
	 * 回复内容
	 */
	private String replyContent;
	/**
	 * 回复时间
	 */
	private Date replyDt;
	/**
	 * 状态名称 1未回复 2 已回复
	 */
	private String statusName;
	/**
	 * 图片字串
	 */
	private String imageString;
	
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
	public Long getCommunityId()
	{
		return communityId;
	}
	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
	public String getCommunityName()
	{
		return communityName;
	}
	public void setCommunityName(String communityName)
	{
		this.communityName = communityName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getInfoTypeName()
	{
		return infoTypeName;
	}
	public void setInfoTypeName(String infoTypeName)
	{
		this.infoTypeName = infoTypeName;
	}
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
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getPublishDt()
	{
		return publishDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	public List<BizRepairImage> getListImage()
	{
		return listImage;
	}
	public void setListImage(List<BizRepairImage> listImage)
	{
		this.listImage = listImage;
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
	public String getReplyContent()
	{
		return replyContent;
	}
	public void setReplyContent(String replyContent)
	{
		this.replyContent = replyContent;
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
	public String getStatusName()
	{
		return statusName;
	}
	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
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
