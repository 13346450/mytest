package com.dnake.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizOrderDetails;
import com.dnake.domain.business.BizOrderVO;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizOrderDetailsMapper;
import com.dnake.service.business.BizOrderDetailsService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.StringUtils;

/**
 * 订单详情管理实现类
*  BizOrderDetailsServiceImpl <br/> 
*  2014年10月21日 上午10:08:40 <br/> 
* @author chen qige 
* @version
 */
@Service
public class BizOrderDetailsServiceImpl extends BaseServiceImpl implements
		BizOrderDetailsService {

	@Resource
	private BizOrderDetailsMapper bizOrderDetailsMapper;
	@Resource
	private CommonService commonService;

	@Override
	public Page<BizOrderDetails> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", searchParam.getOrderId());
		Page<BizOrderDetails> page = new Page<BizOrderDetails>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizOrderDetailsMapper.queryPage(page));
		return page;
	}

	@Override
	public String mobileQueryPage(MobileParms parms) {
		Page<BizOrderDetails> page2 = new Page<BizOrderDetails>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		page2.setPageNo(parms.getPage());
		page2.setPageSize(parms.getCount());
		map2.put("orderId", parms.getId());
		page2.setParams(map2);
		List<BizOrderDetails> orderDetailsList = bizOrderDetailsMapper
				.queryPage(page2);
		
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizOrderDetails.class, "goodsId", "goodsName", "goodsUnitPrice",
				"goodsQuantity", "goodsCost", "simages", "showSimages");
		JsonResult jr = new JsonResult();
		jr.setData(orderDetailsList);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	public List<BizOrderDetails> selectByGoodsId(Long idKey) {
		List<BizOrderDetails> list=bizOrderDetailsMapper.selectByGoodsId(idKey);
		return list;
	}

}
