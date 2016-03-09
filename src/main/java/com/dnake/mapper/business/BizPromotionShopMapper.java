package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPromotionShop;
import com.dnake.domain.business.BizPromotionShopVO;
public interface BizPromotionShopMapper {
void insert(BizPromotionShop bizPromotionShop);

void delete(long idKey);

void update(BizPromotionShop bizPromotionShop);

BizPromotionShop queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizPromotionShopVO> queryPage(Page<BizPromotionShopVO> page);

void insertMulti(List<BizPromotionShop> list);
/**
 * 根据促销的id查询促销的店名
 * @param promotionId
 * @return
 *@author cqg 
 *2015年4月8日
 */
List<BizPromotionShop> queryByPromotionId(Long promotionId);
}
