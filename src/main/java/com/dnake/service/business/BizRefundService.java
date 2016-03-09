package com.dnake.service.business;

import com.dnake.domain.business.BizRefundVO;
import com.dnake.domain.business.BizRefund;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 退货款接口类
 *  @author zgj
 *	日期：2015年9月19日上午11:46:11
 *  描述：
 */
public interface BizRefundService
{
	Page<BizRefundVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	String update(BizRefund bizRefund);

	String insert(BizRefund bizRefund);

	String deleteMulti(String deleteIds);

	/**
	 * 手机端提交申请退款请求
	 *  @author zgj
	 *	日期：2015年9月19日上午11:48:17
	 *  描述：@param parms
	 *  描述：@return
	 */
	String mobileInsert(MobileParms parms);
	
	/**
	 * 手机端上传退款说明图片
	 *  @author zgj
	 *	日期：2015年9月24日下午5:39:55
	 *  描述：@param parms
	 *  描述：@return
	 */
	String mobileUploadImage(MobileParms parms);
}
