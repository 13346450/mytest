package com.dnake.domain.fastJson;

import java.util.List;

import com.dnake.domain.business.BizCommentVO;

/**
 * 查看商品评论的总数评分等
 *  @author zgj
 *	日期：2015年5月11日上午10:13:01
 *  描述：
 */
public class JsonReturnBizComment
{
	/**
	 * 评论总数
	 */
	private Integer commentCount;
	/**
	 * 商品平均分
	 */
	private float goodsAveScore;
	/**
	 * 商品评论总数
	 */
	private List<BizCommentVO> list;
	public Integer getCommentCount()
	{
		return commentCount;
	}
	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}
	public float getGoodsAveScore()
	{
		return goodsAveScore;
	}
	public void setGoodsAveScore(float goodsAveScore)
	{
		this.goodsAveScore = goodsAveScore;
	}
	public List<BizCommentVO> getList()
	{
		return list;
	}
	public void setList(List<BizCommentVO> list)
	{
		this.list = list;
	}
	
}
