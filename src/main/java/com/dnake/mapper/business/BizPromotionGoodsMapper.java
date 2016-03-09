package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPromotionGoods;
import com.dnake.domain.business.BizPromotionGoodsVO;
public interface BizPromotionGoodsMapper {
void insert(BizPromotionGoods bizPromotionGoods);

void delete(long idKey);

void update(BizPromotionGoods bizPromotionGoods);

BizPromotionGoods queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizPromotionGoodsVO> queryPage(Page<BizPromotionGoodsVO> page);

void insertMulti(List<BizPromotionGoods> list);
}
