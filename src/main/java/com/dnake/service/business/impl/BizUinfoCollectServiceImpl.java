package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.business.BizRepairImage;
import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.mapper.business.BizRepairImageMapper;
import com.dnake.mapper.business.BizUinfoCollectMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.service.business.BizUinfoCollectService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.tasks.event.BizAdviceCreatedEvent;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.HttpDataTransferUtil;
import com.dnake.utils.ImageUtil;
import com.dnake.utils.MD5Util;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 住户信息服务层实现类
 * @ClassName BizUserInfoServiceImpl
 * @author zgj
 * 2014年9月11日 下午5:27:18
 */
@Service
public class BizUinfoCollectServiceImpl extends BaseServiceImpl implements BizUinfoCollectService
{
	@Resource
	private BizUinfoCollectMapper bizUinfoCollectMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizRepairImageMapper bizRepairMapper;
	
	@Override
	public String insert(BizUinfoCollect bizUinfoCollect)
	{
		bizUinfoCollect.setIdKey(null);
		bizUinfoCollect.setUserId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizUinfoCollect.setCommunityId(((SessionBean) session.getAttribute("sessionBean")).getDeptId());
		bizUinfoCollect.setPublishDt(new Date());
		bizUinfoCollect.setContentType(2);
		BizUinfoCollect biz = new BizUinfoCollect();
		bizUinfoCollectMapper.insert(bizUinfoCollect);
		
		biz.setIdKey(bizUinfoCollect.getParentId());
		biz.setReplyStatus(2);
		bizUinfoCollectMapper.update(biz); 
		
		
		BizUinfoCollectVO bizTemp = bizUinfoCollectMapper.queryMobileByIdKey(bizUinfoCollect.getParentId());
		List<BizUinfoCollectVO> list = bizUinfoCollectMapper.queryNoReplyListByParentId(bizTemp.getParentId());
		if(list.size() <= 0)	//如果查询到所有的回复均已完成则表明恢复完成
		{
			BizUinfoCollect bizs = new BizUinfoCollect();
			BizUinfoCollectVO bizTemps = bizUinfoCollectMapper.queryMobileByParentId(bizTemp.getParentId());
			if(bizTemps != null)
			{
				bizs.setIdKey(bizTemp.getParentId());
				bizs.setReplyStatus(2);
				bizUinfoCollectMapper.update(bizs);
			}
		}
		return "{\"successMsg\":\"更新成功!\",\"flag\":true}";
	}

	@Override
	public String delete(Long idKey)
	{
		bizUinfoCollectMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功!\"}";
	}

	@Override
	public Page<BizUinfoCollectVO> listPage(int pageIndex, int rows, SearchParam searchParam)
	{
		if( StringUtils.isNull(searchParam.getStartDatetime()) ){
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(), Constants.START_PERIOD) ,"yyyy-mm-dd")+" 00:00:00");
		}else{
			searchParam.setStartDatetime(searchParam.getStartDatetime()+" 00:00:00");
		}
		if( StringUtils.isNull(searchParam.getEndDatetime()) ){
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(), "yyyy-mm-dd")+" 23:59:59");
		}else{
			searchParam.setEndDatetime(searchParam.getEndDatetime()+" 23:59:59");
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
		map.put("sort",searchParam.getSort());
		map.put("order",searchParam.getOrder());
		
		Page<BizUinfoCollectVO> page = new Page<BizUinfoCollectVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		List<BizUinfoCollectVO> bizUinfoCollectVOList = bizUinfoCollectMapper.queryPage(page);
		List<BizUinfoCollectVO> list = new ArrayList<BizUinfoCollectVO>();
		for (BizUinfoCollectVO bizUinfoCollectVO : bizUinfoCollectVOList)
		{
			List<BizUinfoCollectVO> listTemp = bizUinfoCollectMapper.queryUinfoDetailList(bizUinfoCollectVO.getIdKey());
			Map<String, Object> maps1 = new HashMap<String, Object>();
			maps1.put("repairId", bizUinfoCollectVO.getIdKey());
			maps1.put("type", "2");
			List<BizRepairImage> listImage = bizRepairMapper.queryByRepairId(maps1);
			String strImage = "";
			for (BizRepairImage bizRepairImage : listImage)
			{
				strImage += bizRepairImage.getImageUrl() + "@@";
			}
			String sTemp = strImage.equals("")?"":strImage.substring(0, strImage.length()-2);
			bizUinfoCollectVO.setImageString(sTemp);
			for (BizUinfoCollectVO bizUinfoCollectVOs : listTemp)
			{
				list.add(bizUinfoCollectVOs);
			}
		}
		
		List<BizUinfoCollectVO> list1 = new ArrayList<BizUinfoCollectVO>();
		List<BizUinfoCollectVO> list2 = new ArrayList<BizUinfoCollectVO>();
		List<BizUinfoCollectVO> list3 = new ArrayList<BizUinfoCollectVO>();
		if(bizUinfoCollectVOList != null)
		{
			for (BizUinfoCollectVO bizUinfoCollectVO : bizUinfoCollectVOList)
			{
				if(bizUinfoCollectVO.getParentId() != null)
				{
					if(bizUinfoCollectVO.getParentId() != -1L)
					{
						list1.add(bizUinfoCollectVO);
					}
					else
					{
						list2.add(bizUinfoCollectVO);
					}
				}
			}
			for (BizUinfoCollectVO bizUinfoCollectVO1 : list2)
			{
				list3.add(bizUinfoCollectVO1);
				for (BizUinfoCollectVO bizUinfoCollectVO2 : list1)
				{
					if(bizUinfoCollectVO2.getParentId().equals(bizUinfoCollectVO1.getIdKey()))
					{
						list3.add(bizUinfoCollectVO2);
					}
				}
			}
		}
		
		page.setRows(list3);
		return page;
	}

	@Override
	public String queryListForMobile(Long idKey)
	{
		StringBuilder sb = new StringBuilder();
		Page<BizUinfoCollectVO> page = new Page<BizUinfoCollectVO>();
		List<BizUinfoCollectVO> list = bizUinfoCollectMapper.queryListForMobile(page);
		if (null != list) {
			sb.append("{\"data\":[");
			for (BizUinfoCollectVO bu : list) {
				sb.append("{\"idKey\":").append( bu.getIdKey()); 
				sb.append(",\"userContent\":\"").append( bu.getUserContent());
				sb.append(",\"publishDt\":\"").append( bu.getPublishDt()); 
				sb.append(",\"userId\":\"").append( bu.getUserId());
				sb.append(",\"userName\":\"").append( bu.getUserName()); 
				sb.append(",\"communityId\":\"").append( bu.getCommunityId());
				sb.append(",\"communityName\":\"").append( bu.getCommunityName()); 
				sb.append("\"},");
			}
			if (sb.lastIndexOf(",") != -1) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			sb.append("]}");
		}
		return sb.toString();
	}

	@Override
	public List<BizUinfoCollectVO> queryListForWeb(Long idKey)
	{
		List<BizUinfoCollectVO> listBizUinfo = bizUinfoCollectMapper.queryListForWeb(idKey);
		return listBizUinfo;
	}
	public String deleteMulti(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(ids.split(",")));
		bizUinfoCollectMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\",\"flag\":true}";
	}

	@Override
	public String insertTPP(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/CreateFeedback";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + parms.getCommunityCode();
		BizIHttpHandler bizHttpUInfoServiceImpl = SpringContextHolder.getBean("bizHttpUInfoServiceImpl");
		String result = HttpDataTransferUtil.sendPost(url, param, parms.getCommunityCode(), bizHttpUInfoServiceImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String insertAppendTPP(MobileParms parms)
	{
		return null;
	}

	@Override
	public String queryTPP(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetUserFeedback";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&userId=35029997000463&type=故障申告&Index=1&pageIndex=0&pageSize=10";
		BizIHttpHandler bizHttpUInfoServiceImpl = SpringContextHolder.getBean("bizHttpUInfoServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param, "1", bizHttpUInfoServiceImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		return jrbp.getInfo().getCode();
	}

	@Override
	public String mobileInsert(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizUinfoCollect bizUinfoCollect = new BizUinfoCollect();
		bizUinfoCollect.setUserContent(parms.getContent());
		bizUinfoCollect.setCommunityId(sessionBean.getDeptId());
		bizUinfoCollect.setUserId(sessionBean.getUserId());
		bizUinfoCollect.setPublishDt(new Date());
		bizUinfoCollect.setTitle(parms.getTitle());
		bizUinfoCollect.setTel(parms.getTelephone());
		bizUinfoCollect.setLinkMan(parms.getContacts());
		bizUinfoCollect.setAddress(parms.getAddr());
		if(parms.getParentId() != null)
		{
			bizUinfoCollect.setParentId(parms.getParentId());
			BizUinfoCollectVO bizs = bizUinfoCollectMapper.queryMobileByIdKey(parms.getParentId());
			bizUinfoCollect.setTitle(bizs.getTitle());
			bizUinfoCollect.setTel(bizs.getTel());
			bizUinfoCollect.setLinkMan(bizs.getLinkMan());
			bizUinfoCollect.setAddress(bizs.getAddress());
			BizUinfoCollect bizTemp = new BizUinfoCollect();
			bizTemp.setIdKey(bizs.getIdKey());
			bizTemp.setReplyStatus(1);
			bizUinfoCollectMapper.update(bizTemp);
		}
		else
		{
			bizUinfoCollect.setParentId(-1L);
		}
		bizUinfoCollect.setContentType(1);
		bizUinfoCollect.setReplyStatus(1);
		bizUinfoCollectMapper.insert(bizUinfoCollect);
		SpringContextHolder.getApplicationContext().publishEvent(new BizAdviceCreatedEvent(bizUinfoCollect));
		return ResultBuilderUtil.resultIncludeValue("idKey", bizUinfoCollect.getIdKey());
	}

	@Override
	public String mobileQueryList(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		
		Page<BizUinfoCollectVO> page = new Page<BizUinfoCollectVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		map.put("communityId", sessionBean.getDeptId());
		map.put("userId", sessionBean.getUserId());
		page.setParams(map);
		
		List<BizUinfoCollectVO> list = bizUinfoCollectMapper.queryListForMobile(page);
		List<BizUinfoCollectVO> listTemp = new ArrayList<BizUinfoCollectVO>();
		for (BizUinfoCollectVO bizUinfoCollectVO : list)
		{
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("repairId", bizUinfoCollectVO.getIdKey());
			maps.put("type", "2");
			bizUinfoCollectVO.setListImage(bizRepairMapper.queryByRepairId(maps));
			listTemp.add(bizUinfoCollectVO);
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizUinfoCollectVO.class, "idKey", "title", "publishDt",
				"communityName", "contacts", "tel", "userContent");
		JsonResult jr = new JsonResult();
		
		jr.setData(listTemp);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public String mobileQueryDetail(MobileParms parms)
	{
		List<BizUinfoCollectVO> list = new ArrayList<BizUinfoCollectVO>();
		BizUinfoCollectVO bizUinfoCollectVO = bizUinfoCollectMapper.queryMobileByIdKey(parms.getIdKey());
		Map<String, Object> maps = new HashMap<String, Object>();
		if(bizUinfoCollectVO != null)
		{
			maps.put("repairId", bizUinfoCollectVO.getIdKey());
			maps.put("type", "2");
			bizUinfoCollectVO.setListImage(bizRepairMapper.queryByRepairId(maps));
			list.add(bizUinfoCollectVO);
		}
		List<BizUinfoCollectVO> listTemp = bizUinfoCollectMapper.queryUinfoDetailList(bizUinfoCollectVO.getIdKey());
		for (BizUinfoCollectVO bizUinfoCollectVOs : listTemp)
		{
			Map<String, Object> maps1 = new HashMap<String, Object>();
			maps1.put("repairId", bizUinfoCollectVOs.getIdKey());
			maps1.put("type", "2");
			bizUinfoCollectVOs.setListImage(bizRepairMapper.queryByRepairId(maps1));
			list.add(bizUinfoCollectVOs);
		}
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizUinfoCollectVO.class, "idKey", "title", "publishDt",
				"communityName", "contacts", "tel", "userContent", "listImage", "contentType", "replyContent", "replyDt");
		JsonResult jr = new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr,filter);
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

}
