package com.dnake.service.business;

import com.dnake.domain.business.BizUserGiftVO;
import com.dnake.domain.business.BizUserGift;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizUserGiftService {
	Page<BizUserGiftVO> listPage(int pageIndex, int rows,
			SearchParam searchParam);

	String update(BizUserGift bizUserGift);

	String insert(BizUserGift bizUserGift);

	String deleteMulti(String deleteIds);

	/**
	 * 手机端查询优惠券
	 * 
	 * @title mobileQueryByUserId
	 * @author chen qige
	 * @date 2015年3月28日
	 * @return
	 */
	String mobileQuery(MobileParms parms);

	/**
	 * 手机提交订单时，查询是有优惠
	 * 
	 * @param parms
	 * @return
	 */
	String mobileQueryIsHaveGift(MobileParms parms);
	/**
	 * 手机端用户领取红包
	 * @param parms
	 * @return
	 *@author cqg 
	 *2015年4月9日
	 */
	String mobileInsert(MobileParms parms);
}
