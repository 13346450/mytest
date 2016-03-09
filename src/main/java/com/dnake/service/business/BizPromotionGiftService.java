package com.dnake.service.business;
import com.dnake.domain.business.BizPromotionGiftVO;
import com.dnake.domain.business.BizPromotionGift;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizPromotionGiftService {
Page<BizPromotionGiftVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizPromotionGift bizPromotionGift);

String insert(BizPromotionGift bizPromotionGift);

String deleteMulti(String deleteIds);
/**
 * 手机端查询是否有可以领取的红包
 * @return
 *@author cqg 
 *2015年4月8日
 */
String mobileUserQuery(MobileParms parms);
}
