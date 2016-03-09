package com.dnake.service.business;
import com.dnake.domain.business.BizPromotionVO;
import com.dnake.domain.business.BizPromotion;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizPromotionService {
Page<BizPromotionVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizPromotion bizPromotion);

String insert(BizPromotion bizPromotion);

String deleteMulti(String deleteIds);
/**
 * 手机端添加活动
 * @param parms
 * @return
 */
String mobileInsert(MobileParms parms);
/**
 * 
 * @param parms
 * @return
 */
String mobileQueryByuserId(MobileParms parms);
/**
 * 手机端修改促销
 * @param parms
 * @return
 *@author cqg 
 *2015年4月8日
 */
String mobileUpdate(MobileParms parms);
}
