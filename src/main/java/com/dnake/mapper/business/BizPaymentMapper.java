package com.dnake.mapper.business;

import com.dnake.domain.business.BizPayRefund;
import com.dnake.domain.business.BizPayment;

/**
 * 支付
 */
public interface BizPaymentMapper {
	public void insert(BizPayment bizPayment);
	/**
	 * 根据订单查询用户基本付款信息
	 *  @author zgj
	 *	日期：2015年9月21日下午5:01:53
	 *  描述：
	 */
	public BizPayment queryByOrderId(String orderId);
}
