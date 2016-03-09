package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.dnake.common.JsonDateTimeSerializer;

public class BizComment
{
	//
	private Long idKey;
	// 上一次评论ID
	private Long parentId;
	// 评论内容
	private String content;
	// 商品ID
	private Long goodsId;
	//
	private Long shopId;
	// 订单ID
	private Long orderId;
	// 商品评分
	private Integer goodsScore;
	//
	private Date publishDt;
	// 评论类型，0用户第一次评论，1用户追加评论，2店家回复评论
	private Integer type;
	// 是否匿名
	private Integer isNullUser;
	// 用户ID
	private Long userId;
	// 是否已经回复 0未回复 1回复
	@JSONField(serialize = false)
	private Integer isReply;
	// 物流速度评分
	@JSONField(serialize = false)
	private Integer logisticsScore;
	// 服务评分
	@JSONField(serialize = false)
	private Integer serviceScore;
	//
	@JSONField(serialize = false)
	private Integer deliveryScore;
	// 好评3 中评2 差评1
	private Integer commentLevel;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 * 上一次评论ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getParentId()
	{
		return parentId;
	}

	/**
	 * 上一次评论ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * 评论内容
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 评论内容
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 商品ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getGoodsId()
	{
		return goodsId;
	}

	/**
	 * 商品ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setGoodsId(Long goodsId)
	{
		this.goodsId = goodsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getShopId()
	{
		return shopId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setShopId(Long shopId)
	{
		this.shopId = shopId;
	}

	/**
	 * 订单ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getOrderId()
	{
		return orderId;
	}

	/**
	 * 订单ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setOrderId(Long orderId)
	{
		this.orderId = orderId;
	}

	/**
	 * 商品评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getGoodsScore()
	{
		return goodsScore;
	}

	/**
	 * 商品评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setGoodsScore(Integer goodsScore)
	{
		this.goodsScore = goodsScore;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Date getPublishDt()
	{
		return publishDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setPublishDt(Date publishDt)
	{
		this.publishDt = publishDt;
	}

	/**
	 * 评论类型，0用户第一次评论，1用户追加评论，2店家回复评论
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getType()
	{
		return type;
	}

	/**
	 * 评论类型，0用户第一次评论，1用户追加评论，2店家回复评论
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setType(Integer type)
	{
		this.type = type;
	}

	/**
	 * 是否匿名
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getIsNullUser()
	{
		return isNullUser;
	}

	/**
	 * 是否匿名
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setIsNullUser(Integer isNullUser)
	{
		this.isNullUser = isNullUser;
	}

	/**
	 * 用户ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Long getUserId()
	{
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	/**
	 * 是否已经回复 0未回复 1回复
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getIsReply()
	{
		return isReply;
	}

	/**
	 * 是否已经回复 0未回复 1回复
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setIsReply(Integer isReply)
	{
		this.isReply = isReply;
	}

	/**
	 * 物流速度评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getLogisticsScore()
	{
		return logisticsScore;
	}

	/**
	 * 物流速度评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setLogisticsScore(Integer logisticsScore)
	{
		this.logisticsScore = logisticsScore;
	}

	/**
	 * 服务评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getServiceScore()
	{
		return serviceScore;
	}

	/**
	 * 服务评分
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setServiceScore(Integer serviceScore)
	{
		this.serviceScore = serviceScore;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getDeliveryScore()
	{
		return deliveryScore;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setDeliveryScore(Integer deliveryScore)
	{
		this.deliveryScore = deliveryScore;
	}

	/**
	 * 好评3 中评2 差评1
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public Integer getCommentLevel()
	{
		return commentLevel;
	}

	/**
	 * 好评3 中评2 差评1
	 * 
	 * @author 云服务工具
	 * @date 2015-04-03 09:33:56
	 */
	public void setCommentLevel(Integer commentLevel)
	{
		this.commentLevel = commentLevel;
	}
}
