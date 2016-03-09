package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.business.BizCommentImage;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizRepairImage;
import com.dnake.mapper.business.BizPropertiesRepairMapper;
import com.dnake.mapper.business.BizRepairImageMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.service.business.BizPropertiesRepairService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.domain.system.SysUser;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.tasks.event.BizRepairCreateEvent;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.HttpDataTransferUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.JPushUtil;
import com.dnake.utils.MD5Util;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

@Service
public class BizPropertiesRepairServiceImpl extends BaseServiceImpl implements
		BizPropertiesRepairService
{
	@Resource
	private BizPropertiesRepairMapper bizPropertyRepairMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizRepairImageMapper bizRepairMapper;
	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public Page<BizPropertiesRepairVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
	{
		if (StringUtils.isNull(searchParam.getStartDatetime()))
		{
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else
		{
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime()))
		{
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else
		{
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		map.put("repairStatus", searchParam.getRepairStatus());
		Page<BizPropertiesRepairVO> page = new Page<BizPropertiesRepairVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		
		List<BizPropertiesRepairVO> list = bizPropertyRepairMapper.queryPage(page);
		List<BizPropertiesRepairVO> listTemp = new ArrayList<BizPropertiesRepairVO>();
		for (BizPropertiesRepairVO bizPropertiesRepairVO : list)
		{
			Map<String, Object> maps1 = new HashMap<String, Object>();
			maps1.put("repairId", bizPropertiesRepairVO.getIdKey());
			maps1.put("type", "1");
			bizPropertiesRepairVO.setListImage(bizRepairMapper.queryByRepairId(maps1));
			if(bizPropertiesRepairVO.getListImage() != null)
			{
				String stringTemp = "";
				for (BizRepairImage stemp : bizPropertiesRepairVO.getListImage())
				{
					stringTemp += stemp.getImageUrl() + "@@";
				}
				String sTemp = stringTemp.equals("")?"":stringTemp.substring(0, stringTemp.length()-2);
				bizPropertiesRepairVO.setImageString(sTemp);
			}
			listTemp.add(bizPropertiesRepairVO);
		}
		
		//对保修列表
		List<BizPropertiesRepairVO> listTemp1 = new ArrayList<BizPropertiesRepairVO>();
		List<BizPropertiesRepairVO> listTemp2 = new ArrayList<BizPropertiesRepairVO>();
		List<BizPropertiesRepairVO> listTemp3 = new ArrayList<BizPropertiesRepairVO>();
		if(listTemp != null)
		{
			for (BizPropertiesRepairVO bizPropertiesRepairVOs : listTemp)
			{
				if(bizPropertiesRepairVOs.getParentId() != null)
				{
					if(bizPropertiesRepairVOs.getParentId() != -1L)
					{
						listTemp1.add(bizPropertiesRepairVOs);
					}
					else
					{
						listTemp3.add(bizPropertiesRepairVOs);
					}
				}
			}
			
			for (BizPropertiesRepairVO bizPropertiesRepairVO : listTemp3)
			{
				listTemp2.add(bizPropertiesRepairVO);
				for (BizPropertiesRepairVO bizPropertiesRepairVO1 : listTemp1)
				{
					if(bizPropertiesRepairVO1.getParentId().equals(bizPropertiesRepairVO.getIdKey()))
					{
						listTemp2.add(bizPropertiesRepairVO1);
					}
				}
			}
		}
		page.setRows(listTemp2);
		return page;
	}

	@Override
	public String update(BizPropertiesRepair bizPropertyRepair)
	{
		// bizPropertyRepair.setChgDt(new Date());
		BizPropertiesRepairVO bizTemp = bizPropertyRepairMapper.queryByIdkey(bizPropertyRepair.getIdKey());
		if(bizTemp != null)
		{
			SysUser user = sysUserMapper.queryById(bizTemp.getUserId());
			Set customer = new HashSet();
			if(user != null)
				if(user.getUserCd() != null)
					customer.add(user.getUserCd());
			JPushUtil.sendPushByRegisterIds("customer", customer, "您有新的报修信息回复！请注意查收！");
		}
		bizPropertyRepair.setRepairStatus(2);
		bizPropertyRepair.setReplyDt(new Date());
		bizPropertyRepairMapper.update(bizPropertyRepair);
		
		if(bizTemp.getParentId() != -1L)
		{
			List<BizPropertiesRepairVO> lists = bizPropertyRepairMapper.queryNoReplyRepair(bizTemp.getParentId());
			if(lists.size() == 0)
			{
				BizPropertiesRepair bizs = new BizPropertiesRepair();
				BizPropertiesRepairVO bizTemps = bizPropertyRepairMapper.queryByIdkey(bizTemp.getParentId());
				if(bizTemps.getReplyDt() != null)
				{
					bizs.setIdKey(bizTemp.getParentId());
					bizs.setRepairStatus(2);
					bizPropertyRepairMapper.update(bizs);
				}
			}
		}
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizPropertiesRepair bizPropertyRepair)
	{
		// bizPropertyRepair.setChgDt(new Date());
		
		bizPropertyRepairMapper.insert(bizPropertyRepair);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizPropertyRepairMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileQueryList(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		Page<BizPropertiesRepairVO> page = new Page<BizPropertiesRepairVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		map.put("userId", sessionBean.getUserId());
		map.put("repairStatus", parms.getRepairStatus());
		page.setParams(map);
		
		List<BizPropertiesRepairVO> list = bizPropertyRepairMapper.mobileQueryList(page);
		List<BizPropertiesRepairVO> listTemp = new ArrayList<BizPropertiesRepairVO>();
		for (BizPropertiesRepairVO bizPropertiesRepairVO : list)
		{
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("repairId", bizPropertiesRepairVO.getIdKey());
			maps.put("type", "1");
			bizPropertiesRepairVO.setListImage(bizRepairMapper.queryByRepairId(maps));
			
			listTemp.add(bizPropertiesRepairVO);
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizPropertiesRepairVO.class, "idKey", "title", "content", "contacts",
				"contactsTel", "contactsAddr", "publishDt", "communityName",
				"listImage", "repairStatus", "replyDt");
		JsonResult jr = new JsonResult();
		
		jr.setData(listTemp);
		
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileQueryDetails(MobileParms parms)
	{
		List<BizPropertiesRepairVO> list = bizPropertyRepairMapper.mobileQueryDetails(parms.getIdKey());
		List<BizPropertiesRepairVO> listTemp = new ArrayList<BizPropertiesRepairVO>();
		BizPropertiesRepairVO bizTemp = bizPropertyRepairMapper.queryByIdkey(parms.getIdKey());
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("repairId", bizTemp.getIdKey());
		maps.put("type", "1");
		
		bizTemp.setListImage(bizRepairMapper.queryByRepairId(maps));
		listTemp.add(bizTemp);
		
		for (BizPropertiesRepairVO bizPropertiesRepairVO : list)
		{
			Map<String, Object> maps1 = new HashMap<String, Object>();
			maps1.put("repairId", bizPropertiesRepairVO.getIdKey());
			maps1.put("type", "1");
			bizPropertiesRepairVO.setListImage(bizRepairMapper.queryByRepairId(maps1));
			
			listTemp.add(bizPropertiesRepairVO);
		}
		JsonResult jr = new JsonResult();
		jr.setData(listTemp);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileDeleteMulti(MobileParms parms)
	{
		String deleteIds = parms.getDeleteIds();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("propertyIdin", Arrays.asList(deleteIds.split(",")));
		bizPropertyRepairMapper.mobileDeleteMulti(map);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobileInsert(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		
		BizPropertiesRepair biz = new BizPropertiesRepair();
		biz.setTitle(parms.getTitle());
		biz.setType(parms.getType());
		biz.setContent(parms.getContent());
		biz.setContacts(parms.getContacts());
		biz.setContactsAddr(parms.getContactsAddr());
		biz.setContactsTel(parms.getContactsTel());
		biz.setPublishDt(new Date());
		biz.setUserId(sessionBean.getUserId());
		biz.setCommunityId(sessionBean.getDeptId());
		if(parms.getParentId() != null)
		{
			biz.setParentId(parms.getParentId());
			BizPropertiesRepairVO bizs = bizPropertyRepairMapper.queryByIdkey(parms.getParentId());
			biz.setContacts(bizs.getContacts());
			biz.setContactsAddr(bizs.getContactsAddr());
			biz.setContactsTel(bizs.getContactsTel());
			BizPropertiesRepair bizTemp = new BizPropertiesRepair();
			bizTemp.setIdKey(bizs.getIdKey());
			bizTemp.setRepairStatus(1);
			bizPropertyRepairMapper.update(bizTemp);
		}
		else
		{
			biz.setParentId(-1L);
		}
		biz.setRepairStatus(1);
		bizPropertyRepairMapper.insert(biz);
		SpringContextHolder.getApplicationContext().publishEvent(new BizRepairCreateEvent(biz));
		return ResultBuilderUtil.resultIncludeValue("idKey", biz.getIdKey());
	}

	@Override
	public String mobileUploadImage(MobileParms parms)
	{
		String path = session.getServletContext().getRealPath("/")+Constants.MOBILE_REPAIR_UPLOAD_FILE;
		List<MultipartFile> listFile = ImageUtil.getMobileImageFiles(parms);
		if(listFile.size() != 0)
		{
			for (MultipartFile mfile : listFile)
			{
				if(!mfile.isEmpty())
				{
					String imageUrl = commonService.mobileUploadFile(mfile, path, "repair");
					BizRepairImage biz = new BizRepairImage();
					biz.setImageUrl(imageUrl);
					biz.setRepairId(parms.getIdKey());
					biz.setType(parms.getType().toString());
					bizRepairMapper.insert(biz);
				}
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String queryTPPData(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetUserFeedback";
		String param1 = MD5Util.getEncrypt();
		String param2 = param1 + "&userID=" + parms.getIdKey() + "&type=故障申告&Index=1&pageIndex=0&pageSize=200";
		BizIHttpHandler bizHttpProperRepairServiceImpl = SpringContextHolder.getBean("bizHttpProperRepairServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param2, parms.getCommunityCode(), bizHttpProperRepairServiceImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}
}
