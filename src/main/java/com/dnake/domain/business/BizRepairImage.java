package com.dnake.domain.business;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 	报修图片基类
 *  @author zgj
 *	日期：2015年6月24日上午10:28:25
 *  描述：
 */
public class BizRepairImage
{
	//主键
	private Long idKey;
	//报修ID
	@JSONField(serialize = false)
	private Long repairId;
	//图片地址
	private String imageUrl;
	private String type;
	private String remark;
	
	
	public Long getIdKey()
	{
		return idKey;
	}
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}
	public Long getRepairId()
	{
		return repairId;
	}
	public void setRepairId(Long repairId)
	{
		this.repairId = repairId;
	}
	public String getImageUrl()
	{
		return imageUrl;
	}
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
