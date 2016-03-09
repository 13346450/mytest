package com.dnake.service.business;

import com.dnake.domain.business.BizCouponVO;
import com.dnake.domain.business.BizCoupon;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizCouponService {
	Page<BizCouponVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	String update(BizCoupon bizCoupon);

	String insert(BizCoupon bizCoupon);

	String deleteMulti(String deleteIds);
	/**
	 * 手机端查询团购券
	 * @param parms
	 * @return
	 *@author cqg 
	 *2015年4月13日
	 */
	String mobileUserQuery(MobileParms parms);
	/**
	 * 商户将团购券更改为已消费
	 * @param parms
	 * @return
	 *@author cqg 
	 *2015年4月13日
	 */
	String mobileSalesUpdateToUsed(MobileParms parms);
}
