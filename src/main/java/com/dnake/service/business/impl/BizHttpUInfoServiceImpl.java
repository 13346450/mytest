package com.dnake.service.business.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.mapper.business.BizUinfoCollectMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.utils.DateTimeUtil;

/**
 * 住户信息采集回调函数
 *  @author zgj
 *	日期：2015年7月11日上午10:02:24
 *  描述：
 */
@Service
public class BizHttpUInfoServiceImpl implements BizIHttpHandler
{
	@Resource
	private BizUinfoCollectMapper bizUinfoMapper;
	
	
	public void insertData(List<Map<String, String>> listMap)
	{
		for (Map<String, String> map : listMap)
		{
			
		}
	}
	
	public void appendData(List<Map<String, String>> listMap)
	{
		for (Map<String, String> map : listMap)
		{
			
		}
	}
	
	public void queryList(List<Map<String, String>> listMap, String communityCode)
	{
		List<BizUinfoCollectVO> listUinfo = bizUinfoMapper.queryByCommunityCode(communityCode);
		for (Map<String, String> map : listMap)
		{
			boolean flags = true;
			BizUinfoCollect biz = new BizUinfoCollect();
			biz.setUserContent(map.get("Content"));
			biz.setPublishDt(DateTimeUtil.formatStrToDate1(map.get("WriteTime")));
			//biz.setUserId(Long.parseLong(map.get("UserID")));
			biz.setUserName(map.get("UserName"));
			biz.setTel(map.get("Tel"));
			biz.setRemark(map.get("Remark"));
			biz.setType(map.get("Type"));
			biz.setCommunityCode(map.get("CommunityCode"));
			biz.setLastUpdated(DateTimeUtil.formatStrToDate1(map.get("LastUpdated")));
			biz.setLhID(map.get("ID"));
			biz.setStatus(map.get("Flag"));
			biz.setAddress(map.get("Address"));
			biz.setLinkMan(map.get("LinkMan"));
			biz.setEmail(map.get("Email"));
			biz.setQq(map.get("QQ"));
			String mm = map.get("ImageList");
			//String[] tempStr = (String[]) JSON.parse(map.get("ImageList"));
			//biz.setListImage(mm);
			for (BizUinfoCollectVO bizUinfoCollectVO : listUinfo)
			{
				String pubDt = DateTimeUtil
						.formatDateToStr(bizUinfoCollectVO.getPublishDt(), "yyyy-MM-dd HH:mm:ss");
				if(pubDt.equals(map.get("WriteTime")) && bizUinfoCollectVO.getUserContent().equals(map.get("Content")))
				{
					flags = false;
				}
			}
			if(flags)
			{
				bizUinfoMapper.insert(biz);
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
