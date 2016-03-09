package com.dnake.domain.fastJson;

import java.util.List;

import com.dnake.domain.business.BizOrderVO;

/**
 *  返回订单信息
 *  @author zgj
 *	日期：2015年5月25日上午11:16:47
 *  描述：
 */
public class JsonReturnBizOrder
{
	private int totalPage;	//总页数
	private int total;		//总记录数
	private List<BizOrderVO> list;	//订单集合
	public int getTotalPage()
	{
		return totalPage;
	}
	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public List<BizOrderVO> getList()
	{
		return list;
	}
	public void setList(List<BizOrderVO> list)
	{
		this.list = list;
	}
	
}
