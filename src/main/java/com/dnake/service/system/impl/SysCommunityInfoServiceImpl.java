package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.system.SysCommunityInfoVO;
import com.dnake.domain.system.SysCommunityInfo;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.mapper.system.SysCommunityInfoMapper;
import com.dnake.mapper.system.SysCommunityMapper;
import com.dnake.service.system.SysCommunityInfoService;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.HttpDataTransferUtil;
import com.dnake.utils.MD5Util;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

@Service
public class SysCommunityInfoServiceImpl extends BaseServiceImpl implements
		SysCommunityInfoService
{
	@Resource
	private SysCommunityInfoMapper sysCommunityInfoMapper;
	@Resource
	private SysCommunityMapper sysCommunityMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<SysCommunityInfoVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", searchParam.getStatus());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		Page<SysCommunityInfoVO> page = new Page<SysCommunityInfoVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(sysCommunityInfoMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(SysCommunityInfo sysCommunityInfo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		// sysCommunityInfo.setChgDt(new Date());
		map.put("communityId", sysCommunityInfo.getCommunityId());
		map.put("type", sysCommunityInfo.getType());
		List<SysCommunityInfo> list = sysCommunityInfoMapper.queryByCommunityAndType(map);
		if(list.size() > 0)
		{
			for (SysCommunityInfo sysCommunityInfos : list)
			{
				if((sysCommunityInfo.getIdKey() != sysCommunityInfos.getIdKey()) && (sysCommunityInfo.getType() == sysCommunityInfos.getType()))
				{
					if(sysCommunityInfo.getType() == 1)
					{
						return "{\"successMsg\":\"已经添加物业公司信息，请勿重复添加！\",\"flag\":false}";
					}
					else if(sysCommunityInfo.getType() == 2)
					{
						return "{\"successMsg\":\"已经添加小区信息，请勿重复添加！\",\"flag\":false}";
					}
				}
			}
		}
		sysCommunityInfoMapper.update(sysCommunityInfo);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(SysCommunityInfo sysCommunityInfo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("communityId", sysCommunityInfo.getCommunityId());
		map.put("type", sysCommunityInfo.getType());
		List<SysCommunityInfo> list = sysCommunityInfoMapper.queryByCommunityAndType(map);
		if(list.size() > 0)
		{
			for (SysCommunityInfo sysCommunityInfos : list)
			{
				if(sysCommunityInfo.getType() == sysCommunityInfos.getType())
				{
					if(sysCommunityInfo.getType() == 1)
					{
						return "{\"successMsg\":\"已经添加物业公司信息，请勿重复添加！\",\"flag\":false}";
					}
					else if(sysCommunityInfo.getType() == 2)
					{
						return "{\"successMsg\":\"已经添加小区信息，请勿重复添加！\",\"flag\":false}";
					}
				}
			}
		}
		sysCommunityInfoMapper.insert(sysCommunityInfo);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		sysCommunityInfoMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileQueryCommunityDetail(MobileParms parms)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", parms.getIdKey());
		map.put("type", parms.getType());
		List<SysCommunityInfoVO> list = sysCommunityInfoMapper.mobileQueryCommunityDetail(map);
		JsonResult jr = new JsonResult();
		
		for (SysCommunityInfoVO sysCommunityInfoVO : list)
		{
			jr.setData(sysCommunityInfoVO);
			break;
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				SysCommunityInfoVO.class, "displayName", "address", "tel",
				"phoneNum", "summary", "logoUrl", "email", "contact", "url");

		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public String mobileQueryCommuntiyInfo(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<SysCommunityVO> list = sysCommunityMapper.mobileQueryCommunityInfo(sessionBean.getDeptId());
		JsonResult jr = new JsonResult();
		
		for (SysCommunityVO sysCommunityVO : list)
		{
			jr.setData(sysCommunityVO);
			break;
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				SysCommunityVO.class, "address", "cellphone",
				"telephone", "leader", "leaderPhone", "securityRoomPhone",
				"operateTime", "communityInfo", "communityInfoId",
				"properInfo", "properInfoId");
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public String queryTPP(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetCommunitInfo";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + parms.getCommunityCode();
		BizIHttpHandler bizHttpGetCommunityInfoServiceImpl = SpringContextHolder.getBean("bizHttpGetCommunityInfoServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param, parms.getCommunityCode(), bizHttpGetCommunityInfoServiceImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String queryTPPComDetail(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetTenantInfo";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + parms.getCommunityCode() + "&type=1";
		BizIHttpHandler bizHttpCommunityInfoDetailServiceImpl = SpringContextHolder.getBean("bizHttpCommunityInfoDetailServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param, parms.getCommunityCode(), bizHttpCommunityInfoDetailServiceImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String queryTPPProDetail(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetTenantInfo";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + parms.getCommunityCode() + "&type=0";
		return null;
	}

	@Override
	public SysCommunityInfo queryByCommiunityId(Long id) {
		return sysCommunityInfoMapper.queryByCommiunityId(id);
	}
}
