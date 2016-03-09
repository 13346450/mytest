package com.dnake.domain.business;

/**
 *  @author zgj
 *	日期：2015年4月2日上午10:30:30
 *  描述：购物车基类
 */
public class BizShoppingcart
{
	//
	private Long idKey;
	// 用户ID
	private Long userId;
	// 商品ID
	private Long goodsId;
	// 商品数量
	private Integer goodsCount;
	// 店铺ID
	private Long shopId;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 * 用户ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public Long getUserId()
	{
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	/**
	 * 商品ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public Long getGoodsId()
	{
		return goodsId;
	}

	/**
	 * 商品ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public void setGoodsId(Long goodsId)
	{
		this.goodsId = goodsId;
	}

	/**
	 * 商品数量
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public Integer getGoodsCount()
	{
		return goodsCount;
	}

	/**
	 * 商品数量
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public void setGoodsCount(Integer goodsCount)
	{
		this.goodsCount = goodsCount;
	}

	/**
	 * 店铺ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public Long getShopId()
	{
		return shopId;
	}

	/**
	 * 店铺ID
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 10:26:13
	 */
	public void setShopId(Long shopId)
	{
		this.shopId = shopId;
	}
}
