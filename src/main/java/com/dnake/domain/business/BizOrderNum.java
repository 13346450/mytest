package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

public class BizOrderNum
{
	//
	private Long idKey;
	// 本地订单号码
	private String orderNum;
	// 阿里支付统一订单号
	private String alipayOrderNum;
	// 订单提交时间
	private Date orderDt;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 * 本地订单号码
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public String getOrderNum()
	{
		return orderNum;
	}

	/**
	 * 本地订单号码
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public void setOrderNum(String orderNum)
	{
		this.orderNum = orderNum;
	}

	/**
	 * 阿里支付统一订单号
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public String getAlipayOrderNum()
	{
		return alipayOrderNum;
	}

	/**
	 * 阿里支付统一订单号
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	public void setAlipayOrderNum(String alipayOrderNum)
	{
		this.alipayOrderNum = alipayOrderNum;
	}

	/**
	 * 订单提交时间
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getOrderDt()
	{
		return orderDt;
	}

	/**
	 * 订单提交时间
	 * 
	 * @author 云服务工具
	 * @date 2015-09-22 13:55:01
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setOrderDt(Date orderDt)
	{
		this.orderDt = orderDt;
	}
}
