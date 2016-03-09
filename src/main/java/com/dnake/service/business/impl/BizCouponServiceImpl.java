package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizCouponVO;
import com.dnake.domain.business.BizCoupon;
import com.dnake.mapper.business.BizCouponMapper;
import com.dnake.service.business.BizCouponService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class BizCouponServiceImpl extends BaseServiceImpl implements
		BizCouponService {
	@Resource
	private BizCouponMapper bizCouponMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizCouponVO> listPage(int pageIndex, int rows,
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
		Page<BizCouponVO> page = new Page<BizCouponVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizCouponMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizCoupon bizCoupon) {
		// bizCoupon.setChgDt(new Date());
		bizCouponMapper.update(bizCoupon);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizCoupon bizCoupon) {
		// bizCoupon.setChgDt(new Date());
		bizCouponMapper.insert(bizCoupon);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizCouponMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileUserQuery(MobileParms parms) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizCouponVO> page = new Page<BizCouponVO>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		page.setParams(map);
		List<BizCouponVO> list=bizCouponMapper.queryPage(page);
		JsonResult jr=new JsonResult();
		jr.setData(list);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	public String mobileSalesUpdateToUsed(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getCode())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		BizCouponVO bizCouponVO =new BizCouponVO();
		bizCouponVO.setVerification(parms.getCode());
		bizCouponVO.setSalesId(getUserBean().getUserId());
		bizCouponVO.setUseDt(new Date());
		long l=bizCouponMapper.updateToUsed(bizCouponVO);
		System.out.println("cqg >"+l);
		if(l>0){
			return ResultBuilderUtil.statusMessage(1, "操作成功");
		}
		return ResultBuilderUtil.statusMessage(2,"没有改团购券代码！");
	}
}
