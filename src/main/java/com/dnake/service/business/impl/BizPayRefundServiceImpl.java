package com.dnake.service.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.dnake.domain.business.BizPayRefund;
import com.dnake.domain.business.BizPayment;
import com.dnake.mapper.business.BizPayRefundMapper;
import com.dnake.mapper.business.BizPaymentMapper;
import com.dnake.service.business.BizPayRefundService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.GenerateRandomStringUtil;

@Service
public class BizPayRefundServiceImpl extends BaseServiceImpl implements BizPayRefundService {
	@Resource
	private BizPayRefundMapper bizPayRefundMapper;
	@Resource
	private BizPaymentMapper bizPaymentMapper;
	/**
	 * 《退款》支付宝服务器异步通知
	 * 将参数保存到数据库
	 */
	@Override
	public void insertFromAlipay(Map<String, String> params, String batchNo, String successNum, String resultDetails) {
		//参数说明 《即时到账批量退款有密接口(refund_fastpay_by_platform_pwd).pdf》5.2章
		BizPayRefund payRefund = new BizPayRefund();
		payRefund.setAlipayBatchNo(batchNo);
		payRefund.setAlipayParams(JSON.toJSONString(params));
		payRefund.setAlipaySuccessNum(Long.parseLong(successNum));
		payRefund.setAlipayResultDetails(resultDetails);
		payRefund.setType(BizPayRefund.PaymentType.ALIPAY);
		payRefund.setReceiveDate(new Date());
		payRefund.setAlipayNotifyId("notify-id");
		payRefund.setAlipayNotifyTime("notify-time");
		payRefund.setAlipayNotifyType("notify-type");
		payRefund.setAlipaySign("alipay-sign");
		payRefund.setAlipaySignType("sign-type");
		bizPayRefundMapper.insert(payRefund);
	}
	/**
	 * 退款接口
	 * 具体参数，请查看 《即时到账批量退款有密接口(refund_fastpay_by_platform_pwd).pdf》4.2章
	 * @param seller_email 卖家支付宝帐户
	 * @param refund_date 退款当天日期
	 * @param batch_no
	 * @param batch_num
	 * @param detail_data
	 * @return
	 */

	public String refundSubmit(String seller_email, String detail_data) {
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数
		// 卖家支付宝帐户
		//String seller_email = new String(request.getParameter("WIDseller_email").getBytes("ISO-8859-1"), "UTF-8");
		// 必填
		// 退款当天日期
		String notifyUrl = Constants.NOTIFY_URL;
		String refund_date = DateTimeUtil.getDateToStrFullFormat(new Date());
		// 必填，格式：年[4位]-月[2位]-日[2位] 小时[2位 24小时制]:分[2位]:秒[2位]，如：2007-10-01 13:13:13
		// 批次号
		String batch_no = GenerateRandomStringUtil.getBatchNo();
		// 必填，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001
		// 退款笔数
		String batch_num = "1";
		// 必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
		// 退款详细数据
		//String detail_data = new String(request.getParameter("WIDdetail_data").getBytes("ISO-8859-1"), "UTF-8");
		// 必填，具体格式请参见接口技术文档

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notifyUrl);
		sParaTemp.put("seller_email", "park@dnake.com");
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		return sHtmlText;
	}
}
