package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 
 * @ClassName BizHitRecord
 * @author zgj
 * 2014年9月1日 上午11:42:54
 */
public class BizHitRecordVO
{
	private Long idKey;//记录主键
	private Long userId;//用户ID
	private Date hitDate;//点击时间
	private Long chainId;//链接ID
	
	private String chainName;//连接名称
	private String linkUrl;//链接地址
	
	public String getChainName()
	{
		return chainName;
	}
	public void setChainName(String chainName)
	{
		this.chainName = chainName;
	}
	public String getLinkUrl()
	{
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}
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
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getHitDate()
	{
		return hitDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setHitDate(Date hitDate)
	{
		this.hitDate = hitDate;
	}

	public Long getChainId()
	{
		return chainId;
	}
	public void setChainId(Long chainId)
	{
		this.chainId = chainId;
	}
}
