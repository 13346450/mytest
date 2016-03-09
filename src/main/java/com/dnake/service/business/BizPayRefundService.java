package com.dnake.service.business;

import java.util.Map;


/**
 * 支付退款服务
 * @author 蔡小龙
 */
public interface BizPayRefundService {

	void insertFromAlipay(Map<String, String> params, String batchNo, String successNum, String resultDetails);
	
	/**
	 * 退款调用接口
	 *  @author zgj
	 *	日期：2015年9月22日下午5:57:27
	 *  描述：@param seller_email
	 *  描述：@param detail_data
	 *  描述：@return
	 */
	String refundSubmit(String seller_email, String detail_data);
}
