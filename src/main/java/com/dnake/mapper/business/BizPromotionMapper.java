package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPromotion;
import com.dnake.domain.business.BizPromotionVO;
public interface BizPromotionMapper {
void insert(BizPromotion bizPromotion);

void delete(long idKey);

void update(BizPromotion bizPromotion);

BizPromotion queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizPromotionVO> queryPage(Page<BizPromotionVO> page);

void insertMulti(List<BizPromotion> list);
}
