package com.dnake.domain.fastJson;

import java.util.List;

import com.dnake.domain.business.BizGoodsVO;

public class JsonReturnBizGoodsList
{
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 商品列表
	 */
	private List<BizGoodsVO> goodsList;
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public int getTotalPage()
	{
		return totalPage;
	}
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}
	public List<BizGoodsVO> getGoodsList()
	{
		return goodsList;
	}
	public void setGoodsList(List<BizGoodsVO> goodsList)
	{
		this.goodsList = goodsList;
	}
}
