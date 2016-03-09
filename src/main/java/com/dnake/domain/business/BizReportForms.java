package com.dnake.domain.business;

/**
 * 报表实体类
 *  @author zgj
 *	日期：2015年5月12日上午11:09:41
 *  描述：
 */
public class BizReportForms
{
	/**
	 * 商品ID
	 */
	private String goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 销售数量
	 */
	private Integer salesQuantity;
	/**
	 * 销售金额
	 */
	private Double salesCost;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	public String getGoodsId()
	{
		return goodsId;
	}
	public void setGoodsId(String goodsId)
	{
		this.goodsId = goodsId;
	}
	public String getGoodsName()
	{
		return goodsName;
	}
	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}
	public Integer getSalesQuantity()
	{
		return salesQuantity;
	}
	public void setSalesQuantity(Integer salesQuantity)
	{
		this.salesQuantity = salesQuantity;
	}
	public Double getSalesCost()
	{
		return salesCost;
	}
	public void setSalesCost(Double salesCost)
	{
		this.salesCost = salesCost;
	}
	public Double getGoodsPrice()
	{
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice)
	{
		this.goodsPrice = goodsPrice;
	}
}
