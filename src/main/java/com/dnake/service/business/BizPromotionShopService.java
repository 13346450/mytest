package com.dnake.service.business;
import com.dnake.domain.business.BizPromotionShopVO;
import com.dnake.domain.business.BizPromotionShop;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizPromotionShopService {
Page<BizPromotionShopVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizPromotionShop bizPromotionShop);

String insert(BizPromotionShop bizPromotionShop);

String deleteMulti(String deleteIds);

}
