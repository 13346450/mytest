package com.dnake.service.business.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.mapper.business.BizProperInfoMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.service.business.BizProperInfoService;
import com.dnake.utils.DateTimeUtil;

/**
 * 物业信息访问三方接口回调函数
 *  @author zgj
 *	日期：2015年7月11日上午10:03:16
 *  描述：
 */
@Service
public class BizHttpProperInfoServiceImpl implements BizIHttpHandler
{
	@Resource
	private BizProperInfoService bizProperInfoService;
	@Resource
	private BizProperInfoMapper bizProperInfoMapper;
	
	@Override
	public void failedHandler(String parms)
	{
		
	}

	@Override
	public void successHandler(String parms, String communityCode)
	{
		//Long communityId = Long.valueOf(communityCode);
		List<BizProperInfoVO> listPro = bizProperInfoMapper.queryByCommunityId(10028L);
		JsonReturnTPPInfo jrbp = JSON.parseObject(parms, JsonReturnTPPInfo.class);
		if(jrbp.getInfo().getCode().equals("1"))
		{
			List<Map<String, String>> listMap = jrbp.getData();
			if(listMap.size() > 0)
			{
				for (Map<String, String> map : listMap)
				{
					boolean flags = true;
					try
					{
						BizProperInfo biz = new BizProperInfo();
						biz.setInfoTitle(map.get("Title"));
						Date dt = DateTimeUtil.formatStrToDate1(map.get("LastUpdated"));
						biz.setLastUpdated(dt);
						biz.setFlag(map.get("Flag"));
						biz.setCommunityCode(map.get("CommunityCode"));
						biz.setLhID(map.get("ID"));
						biz.setOindex(Integer.parseInt(map.get("OIndex")));
						biz.setPublishDt(DateTimeUtil.formatStrToDate1(map.get("IssuesTime")));
						biz.setExpirationTime(DateTimeUtil.formatStrToDate1(map.get("ExpirationTime")));
						for (BizProperInfoVO bizProperInfo: listPro)
						{
							if(bizProperInfo.getPublishDt() != null && bizProperInfo.getExpirationTime() != null)
							{
								String publishDt = DateTimeUtil
										.formatDateToStr(
												bizProperInfo.getPublishDt(),
												"yyyy-MM-dd HH:mm:ss");
								String ExpirationTime = DateTimeUtil
										.formatDateToStr(bizProperInfo
												.getExpirationTime(),
												"yyyy-MM-dd HH:mm:ss");
								if (bizProperInfo.getInfoTitle().equals(
										map.get("Title"))
										&& publishDt.equals(map
												.get("IssuesTime"))
										&& ExpirationTime.equals(map
												.get("ExpirationTime")))	//都相同说明有该数据
								{
									flags = false;
								}
								else
								{
									if(
											bizProperInfo.getInfoTitle().equals(
											map.get("Title"))
											&& publishDt.equals(map
													.get("IssuesTime"))
											&& !ExpirationTime.equals(map
													.get("ExpirationTime")))	//标题发布时间相同，修改时间不同则更新数据
									{
										bizProperInfoService.updateByDate(biz);
									}
								}
							}
						}
						if(flags)	//如果没有找到一样的数据则说明无此数据，插入新的数据
						{
							bizProperInfoService.insert(biz);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void successHandler(String parms)
	{
		JsonReturnTPPInfo jrbp = JSON.parseObject(parms, JsonReturnTPPInfo.class);
		if(jrbp.getInfo().getCode().equals("1"))
		{
			List<Map<String, String>> listMap = jrbp.getData();
			boolean flags = true;
			if(listMap.size() > 0)
			{
				for (Map<String, String> map : listMap)
				{
					try
					{
						BizProperInfo biz = new BizProperInfo();
						biz.setInfoTitle(map.get("Title"));
						biz.setFlag(map.get("Flag"));
						biz.setInfoContent(map.get("Content"));
						biz.setCommunityCode(map.get("CommunityCode"));
						String inde = map.get("OIndex");
						if(inde != "" && inde != null)
							biz.setOindex(Integer.parseInt(map.get("OIndex")));
						biz.setPublishDt(DateTimeUtil.formatStrToDate1(map.get("IssuesTime")));
						List<BizProperInfo> list = bizProperInfoMapper.queryByDate(biz);
						if(list.size() >= 1)
						{
							bizProperInfoMapper.updateByDate(biz);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
