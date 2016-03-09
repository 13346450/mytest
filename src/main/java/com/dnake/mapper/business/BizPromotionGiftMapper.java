package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPromotionGift;
import com.dnake.domain.business.BizPromotionGiftVO;
public interface BizPromotionGiftMapper {
void insert(BizPromotionGift bizPromotionGift);

void delete(long idKey);

void update(BizPromotionGift bizPromotionGift);

BizPromotionGift queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizPromotionGiftVO> queryPage(Page<BizPromotionGiftVO> page);

void insertMulti(List<BizPromotionGift> list);
/**
 * 根据促销id查询优惠条件
 * @param promotionId
 * @return
 *@author cqg 
 *2015年4月8日
 */
List<BizPromotionGift> queryByPromotionId(Long promotionId);
/**
 * 查询店铺优惠，包括满减，满送
 * @param shopId
 */
void queryShopGift(Long shopId);
/**
 * 优惠数量减一
 * @param idKey
 *@author cqg 
 *2015年4月9日
 */
void remainMinusOne(Long idKey);
}
