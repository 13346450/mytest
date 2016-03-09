package com.dnake.service.business.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.domain.system.SysCommunity;
import com.dnake.mapper.system.SysCommunityInfoMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.utils.DateTimeUtil;

@Service
public class BizHttpGetCommunityInfoServiceImpl implements BizIHttpHandler
{
	@Resource
	private SysCommunityMapper sysCommunityMapper;
	
	public void queryList(List<Map<String, String>> listMap, String communityCode)
	{
		for (Map<String, String> map : listMap)
		{
			SysCommunity sys = new SysCommunity();
			sys.setCommunityCode(map.get("CommunityCode"));
			sys.setAddress(map.get("Address"));
			sys.setCommunityName(map.get("Name"));
			sys.setLeader(map.get("Leader"));
			sys.setLeaderPhone(map.get("LeaderPhone"));
			sys.setSummary(map.get("Summary"));
			sys.setOperateTime(map.get("OperateTime"));
			sys.setSecurityRoomPhone(map.get("SecurityRoomPhone"));
			
			List<SysCommunity> listTemp = sysCommunityMapper.queryCommunityByName(map.get("Name"));
			if(listTemp.size() > 0)
			{
				sysCommunityMapper.updateCommunityByName(sys);
			}
			else
			{
				sysCommunityMapper.insert(sys);
			}
		}
	}

	@Override
	public void failedHandler(String parms)
	{
		
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
