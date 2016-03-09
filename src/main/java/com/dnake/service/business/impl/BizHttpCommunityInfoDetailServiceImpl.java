package com.dnake.service.business.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.domain.system.SysCommunity;
import com.dnake.domain.system.SysCommunityInfo;
import com.dnake.mapper.system.SysCommunityInfoMapper;
import com.dnake.service.business.BizIHttpHandler;

/**
 * 获取小区详情处理回调函数
 *  @author zgj
 *	日期：2015年7月11日下午4:20:33
 *  描述：
 */
@Service
public class BizHttpCommunityInfoDetailServiceImpl implements BizIHttpHandler
{
	@Resource
	private SysCommunityInfoMapper sysCommunityInfoMapper;
	
	public void queryList(List<Map<String, String>> listMap, String communityCode)
	{
		for (Map<String, String> map : listMap)
		{
			SysCommunityInfo sys = new SysCommunityInfo();
			sys.setCommunityCode(map.get("CommunityCode"));
			sys.setAddress(map.get("Address"));
			sys.setDisplayName(map.get("Name"));
			sys.setContact(map.get("Contact"));
			sys.setSummary(map.get("Summary"));
			
			List<SysCommunityInfo> listTemp = sysCommunityInfoMapper.queryTPPDetailByName(map.get("Name"));
			if(listTemp.size() > 0)
			{
				sysCommunityInfoMapper.updateByDisplayName(sys);
			}
			else
			{
				sysCommunityInfoMapper.insert(sys);
			}
		}
	}
	@Override
	public void failedHandler(String parms)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void successHandler(String parms, String info)
	{
		int flag = Integer.parseInt(info);
		JsonReturnTPPInfo jrbp = JSON.parseObject(parms, JsonReturnTPPInfo.class);
		if(jrbp.getInfo().getCode().equals("1"))
		{
			List<Map<String, String>> listMap = jrbp.getData();
			if(listMap.size() > 0)
			{
				switch(flag)
				{
					case 1:
						queryList(listMap, "35020601990001");
						break;
					case 2:
						break;
				}
			}
		}
	}

	@Override
	public void successHandler(String parms)
	{
		// TODO Auto-generated method stub
		
	}

}
