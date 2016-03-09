package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysLogoSettings;
import com.dnake.mapper.system.SysLogoSettingsMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysLogoSettingsService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class SysLogoSettingsServiceImpl extends BaseServiceImpl implements
		SysLogoSettingsService {
	@Resource
	private SysLogoSettingsMapper sysLogoSettingsMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<SysLogoSettings> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		if (StringUtils.isNull(searchParam.getStartDatetime())) {
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else {
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime())) {
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else {
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<SysLogoSettings> page = new Page<SysLogoSettings>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(sysLogoSettingsMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(SysLogoSettings sysLogoSettings) {
		// sysLogoSettings.setChgDt(new Date());
		sysLogoSettingsMapper.update(sysLogoSettings);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(SysLogoSettings sysLogoSettings) {
		// sysLogoSettings.setChgDt(new Date());
		sysLogoSettingsMapper.insert(sysLogoSettings);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		sysLogoSettingsMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public List<SysLogoSettings> queryAll() {
		Map<String ,Object> map=new HashMap<String, Object>();
		return sysLogoSettingsMapper.queryAll(map);
	}

	@Override
	public String delete(Long idKey, String subFilePath) {
		SysLogoSettings sls = sysLogoSettingsMapper.queryByIdkey(idKey);
		commonService.deleteImage(subFilePath + sls.getImageUrl());
		sls.setImageUrl(null);
		sysLogoSettingsMapper.update(sls);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileQueryLogo(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getType())){//参数为空验证
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("way", "APP");//设置为app访问
		switch (parms.getType()) {
		case 7://住户版所有图片
			map.put("type", "resident");
			break;
		case 71://71住户版欢迎页
			map.put("type", "residentAppWel");
			break;
		case 72://住户版logo
			map.put("type", "residentAppLogo");
			break;
		case 73://住户版引导页
			map.put("type", "residentAppGuide");
			break;
			
		case 8://商户版所有图片
			map.put("type", "sales");
			break;
		case 81://商户版欢迎页
			map.put("type", "salesAPPWel");
			break;
		case 82://商户版logo
			map.put("type", "salesAPPLogo");
			break;
		case 83://商户版引导页
			map.put("type", "salesAPPGuide");
			break;
			
		default:
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		List<SysLogoSettings> list=sysLogoSettingsMapper.queryAll(map);
		JsonResult jr=new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public List<SysLogoSettings> queryByType(String type) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("type",type);
		List<SysLogoSettings> list=sysLogoSettingsMapper.queryAll(map);
		return list;
	}
}
