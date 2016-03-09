package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

public class BizRefund
{
	// 退款表ID
	private Long idKey;
	// 退款类型 1.我需要退款 2.我需要退货
	private Integer refundType;
	// 订单ID
	private Long orderId;
	// 退款原因 1收到商品破损 2商品错发 3商品需要维修 4商品与描述不符 5商品质量问题 6未按规定时间发货
	private Integer refundReason;
	// 退款金额
	private Double refundMoney;
	// 退款说明
	private Date refundDt;
	// 退款图片1
	private String refundExplain;
	// 退款图片2
	private String imageOne;
	// 退款图片3
	private String imageTwo;
	// 退款图片4
	private String imageThree;
	// 退款表ID
	private String imageFour;

	/**
	 * 退款表ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 * 退款表ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 * 退款类型 1.我需要退款 2.我需要退货
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public Integer getRefundType()
	{
		return refundType;
	}

	/**
	 * 退款类型 1.我需要退款 2.我需要退货
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setRefundType(Integer refundType)
	{
		this.refundType = refundType;
	}

	/**
	 * 订单ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public Long getOrderId()
	{
		return orderId;
	}

	/**
	 * 订单ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}

	/**
	 * 退款原因 1收到商品破损 2商品错发 3商品需要维修 4商品与描述不符 5商品质量问题 6未按规定时间发货
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public Integer getRefundReason()
	{
		return refundReason;
	}

	/**
	 * 退款原因 1收到商品破损 2商品错发 3商品需要维修 4商品与描述不符 5商品质量问题 6未按规定时间发货
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setRefundReason(Integer refundReason)
	{
		this.refundReason = refundReason;
	}

	/**
	 * 退款金额
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public Double getRefundMoney()
	{
		return refundMoney;
	}

	/**
	 * 退款金额
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setRefundMoney(Double refundMoney)
	{
		this.refundMoney = refundMoney;
	}

	/**
	 * 退款说明
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getRefundDt()
	{
		return refundDt;
	}

	/**
	 * 退款说明
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setRefundDt(Date refundDt)
	{
		this.refundDt = refundDt;
	}

	/**
	 * 退款图片1
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public String getRefundExplain()
	{
		return refundExplain;
	}

	/**
	 * 退款图片1
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setRefundExplain(String refundExplain)
	{
		this.refundExplain = refundExplain;
	}

	/**
	 * 退款图片2
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public String getImageOne()
	{
		return imageOne;
	}

	/**
	 * 退款图片2
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setImageOne(String imageOne)
	{
		this.imageOne = imageOne;
	}

	/**
	 * 退款图片3
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public String getImageTwo()
	{
		return imageTwo;
	}

	/**
	 * 退款图片3
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setImageTwo(String imageTwo)
	{
		this.imageTwo = imageTwo;
	}

	/**
	 * 退款图片4
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public String getImageThree()
	{
		return imageThree;
	}

	/**
	 * 退款图片4
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setImageThree(String imageThree)
	{
		this.imageThree = imageThree;
	}

	/**
	 * 退款表ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public String getImageFour()
	{
		return imageFour;
	}

	/**
	 * 退款表ID
	 * 
	 * @author 云服务工具
	 * @date 2015-09-16 19:32:23
	 */
	public void setImageFour(String imageFour)
	{
		this.imageFour = imageFour;
	}
}
