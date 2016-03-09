package com.dnake.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.fastJson.JsonReturnTPPComInfo;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.domain.system.SysCommunity;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.service.common.GetCommunityCodeService;
import com.dnake.service.common.IHttpHandler;
import com.dnake.service.system.SysCommunityService;
import com.dnake.utils.HttpDataTransferUtil;
import com.dnake.utils.MD5Util;
import com.dnake.utils.ResultBuilderUtil;
@Service
public class GetCommunityCodeServiceImpl implements GetCommunityCodeService
{
	@Resource
	private SysCommunityMapper sysCommunityMapper;
	
	@Override
	public List<SysCommunity> getCommunityInfo()
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetCommunityList";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&name=" + "&phoneNumber=" + "&locationCode=" + "&pageIndex=" + 0 + "&pageSize=" + 150;
		//IHttpHandler iHttpHandler = SpringContextHolder.getBean("getCommunityListImpl");
		String result = HttpDataTransferUtil.sendGet(url, param);
		System.out.println(result);
		JsonReturnTPPComInfo jrtc = JSON.parseObject(result, JsonReturnTPPComInfo.class);
		List<SysCommunity> listEnd = jrtc.getData();
		List<SysCommunity> listLocate = sysCommunityMapper.queryAll();
		List<SysCommunity> listTemp = new ArrayList<SysCommunity>();
		for (SysCommunity sysCommunity1 : listLocate)
		{
			for (SysCommunity sysCommunity2 : listEnd)
			{
				if(sysCommunity2.getCommunityName().equals(sysCommunity1.getCommunityName()))
				{
					listTemp.add(sysCommunity2);
					break;
				}
			}
		}
		String ss = "sdd";
		return listTemp;
	}
	@Override
	public String getCommunityInfo(String code)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetCommunity";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + code;
		//IHttpHandler iHttpHandler = SpringContextHolder.getBean("getCommunityListImpl");
		String result = HttpDataTransferUtil.sendGet(url, param);
		System.out.println(result);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

}
