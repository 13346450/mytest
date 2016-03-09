package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dnake.domain.business.BizPromotionGoodsVO;
import com.dnake.domain.business.BizPromotionGoods;
import com.dnake.mapper.business.BizPromotionGoodsMapper;
import com.dnake.service.business.BizPromotionGoodsService;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.StringUtils;

@Service
public class BizPromotionGoodsServiceImpl extends BaseServiceImpl implements BizPromotionGoodsService {
@Resource
private BizPromotionGoodsMapper bizPromotionGoodsMapper;
@Resource
private CommonService commonService;

@Override
public Page<BizPromotionGoodsVO> listPage(int pageIndex, int rows, SearchParam searchParam){
if( StringUtils.isNull(searchParam.getStartDatetime()) ){
searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
DateTimeUtil.getDateAfter(new Date(), Constants.START_PERIOD) ,"yyyy-mm-dd")+" 00:00:00");
}else{
searchParam.setStartDatetime(searchParam.getStartDatetime()+" 00:00:00");}
if( StringUtils.isNull(searchParam.getEndDatetime()) ){
searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(), "yyyy-mm-dd")+" 23:59:59");
}else{
searchParam.setEndDatetime(searchParam.getEndDatetime()+" 23:59:59");}
Map<String, Object> map = new HashMap<String, Object>();
map.put("sort",searchParam.getSort());
map.put("order",searchParam.getOrder());
Page<BizPromotionGoodsVO> page = new Page<BizPromotionGoodsVO>();
page.setPageNo(pageIndex);
page.setPageSize(rows);
page.setParams(map);
page.setRows(bizPromotionGoodsMapper.queryPage(page));
return page;
}


@Override
public String update(BizPromotionGoods bizPromotionGoods){
bizPromotionGoods.setChgDt(new Date());
bizPromotionGoodsMapper.update(bizPromotionGoods);
return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
}


@Override
public String insert(BizPromotionGoods bizPromotionGoods){
bizPromotionGoods.setChgDt(new Date());
bizPromotionGoodsMapper.insert(bizPromotionGoods);
return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
}

@Override
public String deleteMulti(String deleteIds){
Map<String, Object> map = new HashMap<String, Object>();
map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
bizPromotionGoodsMapper.deleteMulti(map);
return "{\"successMsg\":\"删除成功！\"}";
}
}
