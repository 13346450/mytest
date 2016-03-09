package com.dnake.domain.fastJson;

import java.util.List;

import com.dnake.domain.system.SysCommunity;

/**
 * 小区Json信息接收
 *  @author zgj
 *	日期：2015年7月9日上午11:39:56
 *  描述：
 */
public class JsonReturnTPPComInfo
{
	private List<SysCommunity> Data;
	private JsonReturnTPPInfoElement Info;
	private String RowCount;
	public List<SysCommunity> getData()
	{
		return Data;
	}
	public void setData(List<SysCommunity> data)
	{
		Data = data;
	}
	public JsonReturnTPPInfoElement getInfo()
	{
		return Info;
	}
	public void setInfo(JsonReturnTPPInfoElement info)
	{
		Info = info;
	}
	public String getRowCount()
	{
		return RowCount;
	}
	public void setRowCount(String rowCount)
	{
		RowCount = rowCount;
	}
}
