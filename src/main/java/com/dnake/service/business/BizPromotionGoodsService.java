package com.dnake.service.business;
import com.dnake.domain.business.BizPromotionGoodsVO;
import com.dnake.domain.business.BizPromotionGoods;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizPromotionGoodsService {
Page<BizPromotionGoodsVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizPromotionGoods bizPromotionGoods);

String insert(BizPromotionGoods bizPromotionGoods);

String deleteMulti(String deleteIds);

}
