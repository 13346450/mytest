package com.dnake.domain.business;

import com.alibaba.fastjson.annotation.JSONField;

public class BizGoodsImage
{
	//主键
	private Long idKey;
	//商品ID
	@JSONField(serialize = false)
	private Long goodsId;
	//图片地址
	private String imgUrl;
	//是否为默认图片
	private int isDefault;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public Long getIdKey()
	{
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public void setIdKey(Long idKey)
	{
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public Long getGoodsId()
	{
		return goodsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public void setGoodsId(Long goodsId)
	{
		this.goodsId = goodsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public String getImgUrl()
	{
		return imgUrl;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-08 10:20:19
	 */
	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
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
