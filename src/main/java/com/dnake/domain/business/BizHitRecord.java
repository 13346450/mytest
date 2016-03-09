package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 记录链接的点击次数类
 * @ClassName BizHitRecord
 * @author zgj
 * 2014年9月1日 上午11:42:54
 */
public class BizHitRecord
{
	private Long idKey;//记录主键
	private Long userId;//用户ID
	private Date hitDate;//点击时间
	private Long chainId;//链接ID
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
