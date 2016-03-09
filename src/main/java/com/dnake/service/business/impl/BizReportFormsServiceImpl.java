package com.dnake.service.business.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizReportForms;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.mapper.business.BizCategoryMapper;
import com.dnake.mapper.business.BizReportFormsMapper;
import com.dnake.service.business.BizReportFormsService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;

/**
 * 报表业务实现类
 * 
 * @author zgj 日期：2015年5月12日上午11:45:58 描述：
 */
@Service
public class BizReportFormsServiceImpl extends BaseServiceImpl implements
		BizReportFormsService {
	@Resource
	private BizReportFormsMapper bizReportFormsMapper;
	@Resource
	private BizCategoryMapper bizCategoryMapper;

	@Override
	public String mobileSearchDailySales(MobileParms parms) {
		Long goodsType = parms.getGoodsTypeId();
		String tempName = null;
		int flag = parms.getGoodsTypeId().intValue();
		switch (flag) {
		case 1:
			tempName = "社区商城";
			break;
		case 2:
			tempName = "餐饮美食";
			break;
		case 3:
			tempName = "休闲健身";
			break;
		}
		if (tempName != null) {
			goodsType = bizCategoryMapper.queryByNameAndCommunity(
					getUserBean().getDeptId(), tempName).getIdKey();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", parms.getShopId());
		map.put("startDt", parms.getStartDt());
		map.put("endDt", parms.getEndDt());
		map.put("goodsTypeId", goodsType);
		map.put("sortType", parms.getSortType());
		List<BizReportForms> list = bizReportFormsMapper
				.mobileSearchDailyReport(map);
		int i = 0;
		int j = 0;
		BizReportForms biz = new BizReportForms();

		for (BizReportForms bizReportForms : list) {
			if (bizReportForms.getGoodsId().equals("总计")) {
				biz = bizReportForms;
				j = i;
			}
			i++;
		}
		list.remove(j);
		list.add(biz);

		if (list.size() >= 2) {
			JsonResult jr = new JsonResult();
			jr.setData(list);
			return ResultBuilderUtil.jsonBuild(jr);
		} else {
			JsonResult jr = new JsonResult();
			list = new ArrayList<BizReportForms>();
			jr.setData(list);
			return ResultBuilderUtil.jsonBuild(jr);
		}
	}

}
