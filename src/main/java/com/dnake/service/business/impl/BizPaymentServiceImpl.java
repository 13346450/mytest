package com.dnake.service.business.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;





import com.alibaba.fastjson.JSON;
import com.dnake.domain.business.BizPayment;
import com.dnake.domain.business.BizPayment.PaymentType;
import com.dnake.mapper.business.BizOrderNumMapper;
import com.dnake.mapper.business.BizPaymentMapper;
import com.dnake.service.business.BizPaymentService;
import com.dnake.service.common.BaseServiceImpl;

@Service
public class BizPaymentServiceImpl extends BaseServiceImpl implements BizPaymentService {
	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtils.parseDate("2001-11-11 11:11:11",new String[]{"yyyy-MM-dd HH:mm:ss"}));
	}
	@Resource
	private BizPaymentMapper bizPaymentMapper;
	@Resource
	private BizOrderNumMapper bizOrderNumMapper;
	/**
	 * 《支付》支付宝服务器异步通知
	 * 将参数保存到数据库
	 */
	@Override
	public void insertFromAlipay(Map<String, String> params, String outTradeNo, String tradeNo, String tradeStatus, String paymentDate) throws ParseException {
		//参数详情请查看<移动支付接口SDK2.0标准版.pdf>7.2章
		BizPayment bizPayment = new BizPayment();
		bizPayment.setAlipayOutTradeNo(outTradeNo);
		bizPayment.setAlipayParams(JSON.toJSONString(params));
		bizPayment.setAlipayTradeNo(tradeNo);
		bizPayment.setAlipayTradeStatus(tradeStatus);
		if(paymentDate!=null){
			bizPayment.setPaymentDate(DateUtils.parseDate(paymentDate,new String[]{"yyyy-MM-dd HH:mm:ss"}));
		}
		bizPayment.setType(PaymentType.ALIPAY);
		bizPayment.setAlipayBody(params.get("body"));
		bizPayment.setAlipayBuyerEmail(params.get("buyer_email"));
		bizPayment.setAlipayBuyerId(params.get("buyer_id"));
		bizPayment.setAlipayDiscount(params.get("discount"));
		bizPayment.setAlipayGmtCreate(params.get("gmt_create"));
		bizPayment.setAlipayGmtPayment(params.get("gmt_payment"));
		bizPayment.setAlipayIsTotalFeeAdjust(params.get("is_total_fee_adjust"));
		bizPayment.setAlipayNotifyId(params.get("notify_id"));
		bizPayment.setAlipayNotifyTime(params.get("notify_time"));
		bizPayment.setAlipayNotifyType(params.get("notify_type"));
		bizPayment.setAlipayPaymentType(params.get("payment_type"));
		bizPayment.setAlipayUseCoupon(params.get("use_coupon"));
		bizPayment.setAlipayTotalFee(params.get("total_fee"));
		bizPayment.setAlipaySubject(params.get("subject"));
		bizPayment.setAlipayPrice(params.get("price"));
		bizPayment.setAlipayQuantity(params.get("quantity"));
		bizPayment.setAlipaySellerEmail(params.get("seller_email"));
		bizPayment.setAlipaySellerId(params.get("seller_id"));
		bizPayment.setAlipaySign(params.get("sign"));
		bizPayment.setAlipaySignType(params.get("sign_type"));
		bizPaymentMapper.insert(bizPayment);
	}

}
