package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dnake.common.error.Validate;
import com.dnake.domain.business.BizShoppingcartVO;
import com.dnake.domain.business.BizShoppingcart;
import com.dnake.mapper.business.BizShoppingcartMapper;
import com.dnake.service.business.BizShoppingcartService;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

@Service
public class BizShoppingcartServiceImpl extends BaseServiceImpl implements
		BizShoppingcartService
{
	@Resource
	private BizShoppingcartMapper bizShoppingcartMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizShoppingcartVO> listPage(int pageIndex, int rows,
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
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizShoppingcartVO> page = new Page<BizShoppingcartVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizShoppingcartMapper.queryPage(page));
		return page;
	}

	@Override
	public String update(BizShoppingcart bizShoopingcart)
	{
		// bizShoopingcart.setChgDt(new Date());
		bizShoppingcartMapper.update(bizShoopingcart);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(BizShoppingcart bizShoopingcart)
	{
		bizShoppingcartMapper.insert(bizShoopingcart);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizShoppingcartMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Override
	public String mobileInsertShoppingcart(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<BizShoppingcart> list = JSON.parseObject(parms.getData(), new TypeReference<List<BizShoppingcart>>(){});
		for (BizShoppingcart bizShoppingcart : list)
		{
			bizShoppingcart.setUserId(sessionBean.getUserId());
			List<BizShoppingcartVO> listTemp = bizShoppingcartMapper.mobileQueryByUserIdAndOtherId(bizShoppingcart);
			if(listTemp.size() == 1)
			{
				for (BizShoppingcartVO bizShoppingcartVO : listTemp)
				{
					bizShoppingcart.setIdKey(bizShoppingcartVO.getIdKey());
				}
				bizShoppingcart.setGoodsCount(bizShoppingcart.getGoodsCount());
				bizShoppingcartMapper.update(bizShoppingcart);
			}
			else if(listTemp.size() == 0)
			{
				bizShoppingcart.setIdKey(null);
				bizShoppingcartMapper.mobileInsertGoodsShoopingCart(bizShoppingcart);
			}
			else
			{
				return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
			}
		}
		return ResultBuilderUtil.RESULT_SUCCESS;
	}

	@Override
	public String mobileDeleteByIdKey(MobileParms parms)
	{
		BizShoppingcart biz = bizShoppingcartMapper.queryByIdkey(parms.getIdKey());
		if(biz != null)
		{
			bizShoppingcartMapper.delete(parms.getIdKey());
			return ResultBuilderUtil.RESULT_SUCCESS;
		}
		return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
	}

	@Override
	public String mobileQueryShoppingcart(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		List<BizShoppingcartVO> list = bizShoppingcartMapper.mobileQueryList(sessionBean.getUserId());
		JsonResult jr = new JsonResult();
		jr.setData(list);
		String json = ResultBuilderUtil.jsonBuild(jr);
		return json;
	}

	@Override
	public String mobileUpdateShoooingcart(MobileParms parms)
	{
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		BizShoppingcart bizShoppingcart = new BizShoppingcart();
		bizShoppingcart.setIdKey(parms.getIdKey());
		bizShoppingcart.setGoodsCount(parms.getQuantity());
		bizShoppingcartMapper.update(bizShoppingcart);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
	public String mobileMultiDelete(MobileParms parms)
	{
		Validate.checkHaveNull(parms.getDeleteIds());
		String deleteIds = parms.getDeleteIds();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		bizShoppingcartMapper.deleteMulti(map);
		return ResultBuilderUtil.RESULT_SUCCESS;
	}
}
