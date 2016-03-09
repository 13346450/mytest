package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
import com.dnake.common.JsonDateTimeSerializer;

/**
 * @ClassName BizProperInfoVO
 * @author zgj
 * 2014年9月4日 下午4:27:16
 */
public class BizProperInfoVO
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 物业信息标题
	 */
	private String infoTitle;
	/**
	 * 物业信息类型
	 */
	private int infoType;
	/**
	 * 物业信息内容
	 */
	private String infoContent;
	/**
	 * 小区ID
	 */
	private Long communityId;
	/**
	 * 图片地址
	 */
	private String imagesUrl;
	/**
	 * 链接地址
	 */
	private String linksUrl;
	/**
	 * 发布人ID
	 */
	private Long publishId;
	/**
	 * 发布时间
	 */
	private Date publishDt;
	/**
	 * 信息发布状态
	 */
	private Integer infoStatus;
	/**
	 * 创建日期
	 */
	private Date createDt;
	/**
	 * 创建人ID
	 */
	private Long createId;
	
	
	

	/**
	 * 创建人姓名
	 */
	private String createName;
	/**
	 * 发布人姓名
	 */
	private String publishName;
	/**
	 * 小区名
	 */
	private String communityName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态名
	 */
	private String statusName;
	/**
	 * 类型名
	 */
	private String typeName;
	/**
	 * 类型ID
	 */
	private Long typeId;
	/**
	 * 物业部门
	 */
	private String properName;
	
	/**
	 * 其他公司对接添加字段
	 * @return
	 */
	/**
	 * 最后更新时间
	 */
	private Date lastUpdated;
	//序号
	private int oindex;
	//过期时间
	private Date expirationTime;
	//预留字段
	private String flag;
	//发送的用户类别  1是公司内部，2是其他公司接口
	private int userType;
	//小区编码
	private String communityCode;
	//领航接口ID
	private String lhID;
	
	public Long getTypeId()
	{
		return typeId;
	}
	public void setTypeId(Long typeId)
	{
		this.typeId = typeId;
	}
	public Long getCreateId()
	{
		return createId;
	}
	public void setCreateId(Long createId)
	{
		this.createId = createId;
	}
	public String getCreateName()
	{
		return createName;
	}
	public void setCreateName(String createName)
	{
		this.createName = createName;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getCreateDt()
	{
		return createDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDt(Date createDt)
	{
		this.createDt = createDt;
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
	public Long getIdKey()
	{
		return idKey;
	}
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}
	public String getInfoTitle()
	{
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle)
	{
		this.infoTitle = infoTitle;
	}
	public int getInfoType()
	{
		return infoType;
	}
	public void setInfoType(int infoType)
	{
		this.infoType = infoType;
	}
	public String getInfoContent()
	{
		return infoContent;
	}
	public void setInfoContent(String infoContent)
	{
		this.infoContent = infoContent;
	}
	public Long getCommunityId()
	{
		return communityId;
	}
	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
	public String getImagesUrl()
	{
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl)
	{
		this.imagesUrl = imagesUrl;
	}
	public String getLinksUrl()
	{
		return linksUrl;
	}
	public void setLinksUrl(String linksUrl)
	{
		this.linksUrl = linksUrl;
	}
	public Long getPublishId()
	{
		return publishId;
	}
	public void setPublishId(Long publishId)
	{
		this.publishId = publishId;
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
	public Integer getInfoStatus()
	{
		return infoStatus;
	}
	public void setInfoStatus(Integer infoStatus)
	{
		this.infoStatus = infoStatus;
	}
	public String getPublishName()
	{
		return publishName;
	}
	public void setPublishName(String publishName)
	{
		this.publishName = publishName;
	}
	public String getCommunityName()
	{
		return communityName;
	}
	public void setCommunityName(String communityName)
	{
		this.communityName = communityName;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getProperName()
	{
		return properName;
	}
	public void setProperName(String properName)
	{
		this.properName = properName;
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
	public int getOindex()
	{
		return oindex;
	}
	public void setOindex(int oindex)
	{
		this.oindex = oindex;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getExpirationTime()
	{
		return expirationTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setExpirationTime(Date expirationTime)
	{
		this.expirationTime = expirationTime;
	}
	public String getFlag()
	{
		return flag;
	}
	public void setFlag(String flag)
	{
		this.flag = flag;
	}
	public int getUserType()
	{
		return userType;
	}
	public void setUserType(int userType)
	{
		this.userType = userType;
	}
	public String getCommunityCode()
	{
		return communityCode;
	}
	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
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
