package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

public class SysCommunityInfo
{
	//
	private Long idKey;
	// 小区名称
	private String displayName;
	//
	private String address;
	// 小区所在的区域ID
	private String tel;
	// 地址
	private String phoneNum;
	/**
	 * 三方接口小区编码
	 */
	private String communityCode;
	/**
	 * 关联小区信息的小区ID
	 */
	private Long communityId;
	// 物业管理单位ID
	private String summary;
	//
	private String logoUrl;
	// 物业手机
	private String tenantCode;
	// 物业电话
	private String email;
	//
	private Date createTime;
	// 备注
	private Integer isEnable;
	// 小区所在的省id
	private Long smsCount;
	// 小区所在的市id
	private String contact;
	// 编号ID
	private String url;
	// 名称
	private Date lastUpdated;
	// 负责人
	private String code;
	// 负责人电话
	private Integer type;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 * 小区名称
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * 小区名称
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * 小区所在的区域ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getTel()
	{
		return tel;
	}

	/**
	 * 小区所在的区域ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * 地址
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * 地址
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * 物业管理单位ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getSummary()
	{
		return summary;
	}

	/**
	 * 物业管理单位ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getLogoUrl()
	{
		return logoUrl;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}

	/**
	 * 物业手机
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getTenantCode()
	{
		return tenantCode;
	}

	/**
	 * 物业手机
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setTenantCode(String tenantCode)
	{
		this.tenantCode = tenantCode;
	}

	/**
	 * 物业电话
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * 物业电话
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getCreateTime()
	{
		return createTime;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * 备注
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public Integer getIsEnable()
	{
		return isEnable;
	}

	/**
	 * 备注
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setIsEnable(Integer isEnable)
	{
		this.isEnable = isEnable;
	}

	/**
	 * 小区所在的省id
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public Long getSmsCount()
	{
		return smsCount;
	}

	/**
	 * 小区所在的省id
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setSmsCount(Long smsCount)
	{
		this.smsCount = smsCount;
	}

	/**
	 * 小区所在的市id
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getContact()
	{
		return contact;
	}

	/**
	 * 小区所在的市id
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setContact(String contact)
	{
		this.contact = contact;
	}

	/**
	 * 编号ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * 编号ID
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * 名称
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getLastUpdated()
	{
		return lastUpdated;
	}

	/**
	 * 名称
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	/**
	 * 负责人
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 负责人
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * 负责人电话
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public Integer getType()
	{
		return type;
	}

	/**
	 * 负责人电话
	 * 
	 * @author 云服务工具
	 * @date 2015-07-08 20:24:06
	 */
	public void setType(Integer type)
	{
		this.type = type;
	}

	public String getCommunityCode()
	{
		return communityCode;
	}

	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
	}

	public Long getCommunityId()
	{
		return communityId;
	}

	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
}
