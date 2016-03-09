package com.dnake.domain.business;

/**
 *  @author zgj
 *	日期：2015年3月24日下午6:50:49
 *  描述：地址基类
 */
public class BizAddr
{
	/**
	 * 主键
	 */
	private Long idKey;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 电话
	 */
	private String phoneNum;
	/**
	 * 邮编
	 */
	private String postalcode;
	/**
	 * 省
	 */
	private Long province;
	/**
	 * 市
	 */
	private Long city;
	/**
	 * 区
	 */
	private Long district;
	/**
	 * 小区ID
	 */
	private Long communityId;
	/**
	 * 地址详情
	 */
	private String addressDetail;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否为默认地址
	 */
	private int isDefault;
	

	public Long getIdKey()
	{
		return idKey;
	}

	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPhoneNum()
	{
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	public String getPostalcode()
	{
		return postalcode;
	}

	public void setPostalcode(String postalcode)
	{
		this.postalcode = postalcode;
	}

	public Long getProvince()
	{
		return province;
	}

	public void setProvince(Long province)
	{
		this.province = province;
	}

	public Long getCity()
	{
		return city;
	}

	public void setCity(Long city)
	{
		this.city = city;
	}

	public Long getDistrict()
	{
		return district;
	}

	public void setDistrict(Long district)
	{
		this.district = district;
	}

	public Long getCommunityId()
	{
		return communityId;
	}

	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}

	public String getAddressDetail()
	{
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public int getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(int isDefault)
	{
		this.isDefault = isDefault;
	}
}
