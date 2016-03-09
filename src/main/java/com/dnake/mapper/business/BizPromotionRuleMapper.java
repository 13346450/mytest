package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPromotionRule;
import com.dnake.domain.business.BizPromotionRuleVO;
public interface BizPromotionRuleMapper {
void insert(BizPromotionRule bizPromotionRule);

void delete(long idKey);

void update(BizPromotionRule bizPromotionRule);

BizPromotionRule queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizPromotionRuleVO> queryPage(Page<BizPromotionRuleVO> page);

void insertMulti(List<BizPromotionRule> list);
/**
 * 根据促销id查询促销规则
 * @param PromotionId
 * @return
 *@author cqg 
 *2015年4月8日
 */
List<BizPromotionRule> queryByPromotionId(Long PromotionId);
}
