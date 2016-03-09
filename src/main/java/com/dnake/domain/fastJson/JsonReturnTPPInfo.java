package com.dnake.domain.fastJson;

import java.util.List;
import java.util.Map;


/**
 * 接收外部接口的JSON字串。
 *  @author zgj
 *	日期：2015年7月2日下午6:32:26
 *  描述：
 */
public class JsonReturnTPPInfo
{
	private List<Map<String, String>> Data;
	private JsonReturnTPPInfoElement Info;
	private String RowCount;
	
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
	public List<Map<String, String>> getData()
	{
		return Data;
	}
	public void setData(List<Map<String, String>> data)
	{
		Data = data;
	}
}
