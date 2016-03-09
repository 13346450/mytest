package com.dnake.service.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dnake.domain.business.BizPromotionRuleVO;
import com.dnake.domain.business.BizPromotionRule;
import com.dnake.mapper.business.BizPromotionRuleMapper;
import com.dnake.service.business.BizPromotionRuleService;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.StringUtils;

@Service
public class BizPromotionRuleServiceImpl extends BaseServiceImpl implements BizPromotionRuleService {
@Resource
private BizPromotionRuleMapper bizPromotionRuleMapper;
@Resource
private CommonService commonService;

@Override
public Page<BizPromotionRuleVO> listPage(int pageIndex, int rows, SearchParam searchParam){
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
Page<BizPromotionRuleVO> page = new Page<BizPromotionRuleVO>();
page.setPageNo(pageIndex);
page.setPageSize(rows);
page.setParams(map);
page.setRows(bizPromotionRuleMapper.queryPage(page));
return page;
}


@Override
public String update(BizPromotionRule bizPromotionRule){
//bizPromotionRule.setChgDt(new Date());
bizPromotionRuleMapper.update(bizPromotionRule);
return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
}


@Override
public String insert(BizPromotionRule bizPromotionRule){
//bizPromotionRule.setChgDt(new Date());
bizPromotionRuleMapper.insert(bizPromotionRule);
return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
}

@Override
public String deleteMulti(String deleteIds){
Map<String, Object> map = new HashMap<String, Object>();
map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
bizPromotionRuleMapper.deleteMulti(map);
return "{\"successMsg\":\"删除成功！\"}";
}
}
