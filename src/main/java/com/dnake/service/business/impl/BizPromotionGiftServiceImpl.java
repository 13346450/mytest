package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dnake.domain.business.BizPromotionGiftVO;
import com.dnake.domain.business.BizPromotionGift;
import com.dnake.domain.business.BizUserGiftVO;
import com.dnake.mapper.business.BizPromotionGiftMapper;
import com.dnake.mapper.business.BizUserGiftMapper;
import com.dnake.service.business.BizPromotionGiftService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.StringUtils;

@Service
public class BizPromotionGiftServiceImpl extends BaseServiceImpl implements
		BizPromotionGiftService {
	@Resource
	private BizPromotionGiftMapper bizPromotionGiftMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizUserGiftMapper bizUserGiftMapper;

	@Override
	public Page<BizPromotionGiftVO> listPage(int pageIndex, int rows,
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
		Page<BizPromotionGiftVO> page = new Page<BizPromotionGiftVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizPromotionGiftMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizPromotionGift bizPromotionGift) {
		// bizPromotionGift.setChgDt(new Date());
		bizPromotionGiftMapper.update(bizPromotionGift);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizPromotionGift bizPromotionGift) {
		// bizPromotionGift.setChgDt(new Date());
		bizPromotionGiftMapper.insert(bizPromotionGift);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizPromotionGiftMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileUserQuery(MobileParms parms) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowTime", new Date());
		map.put("status", 1);// 活动开启状态
		map.put("communityId", getUserBean().getDeptId());
		Page<BizPromotionGiftVO> promotionGiftPage = new Page<BizPromotionGiftVO>();
		promotionGiftPage.setPageSize(9999);
		promotionGiftPage.setParams(map);
		List<BizPromotionGiftVO> promotionGiftList = bizPromotionGiftMapper
				.queryPage(promotionGiftPage);// 该小区已有的活动
		Map<String, Object> map2 = new HashMap<String, Object>();
		Page<BizUserGiftVO> userGiftPage = new Page<BizUserGiftVO>();
		map2.put("userId", getUserBean().getUserId());
		userGiftPage.setPageSize(9999);
		userGiftPage.setParams(map2);
		List<BizUserGiftVO> userGiftList = bizUserGiftMapper
				.queryPage(userGiftPage);// 该用户拥有的优惠券
		for (BizUserGiftVO bizUserGiftVO : userGiftList) {
			if (bizUserGiftVO.getOneMaxUse() == 1) {// 每人只能领取一次时
				for (int i = promotionGiftList.size() - 1; i >= 0; i--) {
					if (promotionGiftList.get(i).getPromotionId()
							.equals(bizUserGiftVO.getPromotionId())) {
						promotionGiftList.remove(i);
					}
				}
			}
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				BizPromotionGiftVO.class, "idKey", "shopName", "minSum",
				"value", "startDt", "endDt");
		JsonResult jr = new JsonResult();
		//jr.setData(promotionGiftList);
		/**
		 *  前端要分页时，对符合条件的分页操作
		 */
		List<BizPromotionGiftVO> returnList = new ArrayList<BizPromotionGiftVO>();
		for (int i = (parms.getPage() - 1) * parms.getCount(); i < promotionGiftList
				.size() && i < parms.getPage() * parms.getCount(); i++) {
			returnList.add(promotionGiftList.get(i));
		}
		jr.setData(returnList);
		return JSON.toJSONString(jr, filter);
	}
}
