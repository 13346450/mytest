package com.dnake.service.business.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.mapper.business.BizPropertiesRepairMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.utils.DateTimeUtil;

/**
 * 故障报修三方接口回调函数
 *  @author zgj
 *	日期：2015年7月11日上午10:04:39
 *  描述：
 */
@Service
public class BizHttpProperRepairServiceImpl implements BizIHttpHandler
{
	@Resource
	private BizPropertiesRepairMapper bizPropertiesRepairMapper;
	
	public void queryList(List<Map<String, String>> listMap, String communityCode)
	{
		List<BizPropertiesRepairVO> listProperRepair = bizPropertiesRepairMapper.queryByCommunityCode(communityCode);
		for (Map<String, String> map : listMap)
		{
			boolean flags = true;
			BizPropertiesRepair biz = new BizPropertiesRepair();
			biz.setContent(map.get("Content"));
			biz.setPublishDt(DateTimeUtil.formatStrToDate1(map.get("WriteTime")));
			biz.setCommunityCode(map.get("CommunityCode"));
			biz.setAddress(map.get("Address"));
			biz.setContacts(map.get("Contacts"));
			biz.setContactsTel(map.get("Tel"));
			biz.setTypeName(map.get("Type"));
			biz.setTitle(map.get("Title"));
			biz.setUserId(Long.parseLong(map.get("userID")));
			
			for (BizPropertiesRepairVO bizPropertiesRepairVO : listProperRepair)
			{
				String pubDt = DateTimeUtil
						.formatDateToStr(bizPropertiesRepairVO.getPublishDt(), "yyyy-MM-dd HH:mm:ss");
				if(pubDt.equals(map.get("WriteTime")) && bizPropertiesRepairVO.getContent().equals(map.get("Content")))
				{
					flags = false;
				}
			}
			if(flags)
			{
				bizPropertiesRepairMapper.insert(biz);
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
