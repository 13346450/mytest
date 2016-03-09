package com.dnake.service.business;

import java.text.ParseException;
import java.util.Map;


/**
 * 支付服务
 * @author 蔡小龙
 */
public interface BizPaymentService {

	void insertFromAlipay(Map<String, String> params, String outTradeNo, String tradeNo, String tradeStatus, String paymentDate) throws ParseException;

}
