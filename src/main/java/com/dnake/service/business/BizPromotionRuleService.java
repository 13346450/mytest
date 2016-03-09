package com.dnake.service.business;
import com.dnake.domain.business.BizPromotionRuleVO;
import com.dnake.domain.business.BizPromotionRule;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizPromotionRuleService {
Page<BizPromotionRuleVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizPromotionRule bizPromotionRule);

String insert(BizPromotionRule bizPromotionRule);

String deleteMulti(String deleteIds);

}
