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
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonReturnTPPInfo;
import com.dnake.domain.system.SysUser;
import com.dnake.mapper.business.BizProperInfoMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.business.BizIHttpHandler;
import com.dnake.service.business.BizProperInfoService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.common.IHttpHandler;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.HttpDataTransferUtil;
import com.dnake.utils.JPushUtil;
import com.dnake.utils.MD5Util;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 物业信息接口实现层
 * @ClassName BizProperInfoServiceImpl
 * @author zgj
 * 2014年9月4日 下午5:24:51
 */
@Service
public class BizProperInfoServiceImpl extends BaseServiceImpl implements BizProperInfoService
{
	@Resource
	private BizProperInfoMapper bizProperInfoMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private CommonService commonService;
	
	@Override
	public String update(BizProperInfo bizProperInfo)
	{
		bizProperInfo.setCreateId(((((SessionBean) session.getAttribute("sessionBean")).getUserId())));
		bizProperInfo.setCreateDt(new Date());
		//bizProperInfo.setCommunityId(((SessionBean) session.getAttribute("sessionBean")).getDeptId());
		bizProperInfoMapper.update(bizProperInfo);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey)
	{
		bizProperInfoMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功!\",\"flag\":true}";
	}

	@Override
	public String insert(BizProperInfo bizProperInfo)
	{
		bizProperInfo.setCreateId(((SessionBean) session.getAttribute("sessionBean")).getUserId());
		bizProperInfo.setCreateDt(new Date());
		//bizProperInfo.setCommunityId(((SessionBean) session.getAttribute("sessionBean")).getDeptId());
		bizProperInfo.setInfoStatus(-1);
		bizProperInfoMapper.insert(bizProperInfo);
		return "{\"successMsg\":\"插入成功!\",\"flag\":true}";
	}

	@Override
	public Page<BizProperInfoVO> listPage(int pageIndex, int rows,
			SearchParam searchParam)
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
		map.put("infoStatus", searchParam.getInfoStatus());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds().split(","));//增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		map.put("sort",searchParam.getSort());
		map.put("order",searchParam.getOrder());
		
		Page<BizProperInfoVO> page = new Page<BizProperInfoVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		List<BizProperInfoVO> bizProperInfoVOList = bizProperInfoMapper.queryPage(page);
		page.setRows(bizProperInfoVOList);
		return page;
	}

	@Override
	public String updateStatus(Long idKey, String type)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(type.equals("publish"))
		{
			map.put("publishId", ((SessionBean) session.getAttribute("sessionBean")).getUserId());
			map.put("publishDt", new Date());
			map.put("infoStatus", 1);
			map.put("oper", "publish");
		}else if(type.equals("cancelPublish"))
		{
			map.put("publish",  null);
			map.put("publish",  null);
			map.put("infoStatus", 2);
			map.put("oper", "publish");
		}
		map.put("idKey", idKey);
		bizProperInfoMapper.updateStatus(map);
		if(type.equals("publish"))
		{
			BizProperInfo bizProperInfo = bizProperInfoMapper.queryById(idKey);
			List<SysUser> list = sysUserMapper.queryBydeptID(bizProperInfo.getCommunityId());
			Set customer = new HashSet();
			for (SysUser sysUser : list)
			{
				if(sysUser != null && sysUser.getRoleId() != null)
				{
					if(sysUser.getRoleId() == Constants.USER_ROLE_ID)
					{
						customer.add(sysUser.getUserCd());
					}
				}
			}
			JPushUtil.sendPushByRegisterIds("customer", customer, "您有新的物业通知！请注意查收！");
		}
		return "{\"successMsg\":\"操作成功！\"}";
	}

	@Override
	public String queryForMobile(MobileParms parms)
	{
		String sContent = "";
		String cContent = "";
		SessionBean sessionBean=(SessionBean) session.getAttribute("sessionBean");
		Page<BizProperInfoVO> page = new Page<BizProperInfoVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		map.put("proInfoType", parms.getProInfoType());
		map.put("communityId", sessionBean.getDeptId());
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizProperInfoVO> list = bizProperInfoMapper.queryForMobile(page);
		
		List<BizProperInfoVO> list1 = new ArrayList<BizProperInfoVO>();
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizProperInfoVO.class, "idKey", "infoTitle", "infoContent",
				"publishDt", "publishName");
		JsonResult jr = new JsonResult();
		for (BizProperInfoVO bpif : list) 
		{
			//cContent = StringUtils.replaceNewline(bpif.getInfoContent());
			sContent = (cContent.length() >= 30)?cContent.substring(0, 30):cContent;
			bpif.setInfoContent(sContent);
			list1.add(bpif);
		}
		jr.setData(list1);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public BizProperInfoVO queryDetailForMobile(Long idKey)
	{
		BizProperInfoVO bizProperInfoVO = bizProperInfoMapper.queryForDetail(idKey);
		return bizProperInfoVO;
	}
	
	public String deleteMulti(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(ids.split(",")));
		bizProperInfoMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}
	
	
	@Override
	public String queryOtherPlatformNoticeList(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetNoticeList";
		String param1 = MD5Util.getEncrypt();
		String param = param1 + "&communityCode=" + parms.getCommunityCode() + "&type=" + parms.getType() + "&pageIndex=" + parms.getPageIndex() + "&pageSize=" + parms.getPageSize();
		BizIHttpHandler bizHttpProperInfoImpl = SpringContextHolder.getBean("bizHttpProperInfoServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param, parms.getCommunityCode(), bizHttpProperInfoImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String queryOtherPlatformNoticeDetail(MobileParms parms)
	{
		String url = "http://app.pmsaas.net/Api_Sqt_v3/GetNotice";
		String param1 = MD5Util.getEncrypt();
		String id = parms.getID().toString();
		String param = param1 + "&ID=" + id;
		BizIHttpHandler bizHttpProperInfoImpl = SpringContextHolder.getBean("bizHttpProperInfoServiceImpl");
		String result = HttpDataTransferUtil.sendGet(url, param, bizHttpProperInfoImpl);
		JsonReturnTPPInfo jrbp = JSON.parseObject(result, JsonReturnTPPInfo.class);
		JsonResult jr = new JsonResult();
		jr.setStatus(Integer.parseInt(jrbp.getInfo().getCode()));
		jr.setMessages(jrbp.getInfo().getName());
		jr.setData(jrbp.getData());
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String updateByDate(BizProperInfo biz)
	{
		bizProperInfoMapper.updateByDate(biz);
		return "{\"successMsg\":\"操作成功！\"}";
	}

	@Override
	public String mobileQueryProperDetail(MobileParms parms)
	{
		BizProperInfoVO bizProperInfoVO = bizProperInfoMapper.queryForDetail(parms.getIdKey());
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizProperInfoVO.class, "idKey", "infoTitle", "infoType",
				"infoContent", "publishDt", "publishName", "communityName",
				"typeName", "properName");
		JsonResult jr = new JsonResult();
		jr.setData(bizProperInfoVO);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

}
