package com.dnake.controller.business;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.util.AlipayNotify;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.business.BizPayRefund;
import com.dnake.service.business.BizPayRefundService;
import com.dnake.service.business.BizPaymentService;

/**
 * @author zgj 日期：2015年3月24日下午7:02:27 描述：地址处理控制层
 */
@Controller
@RequestMapping("/business/biz-pay-callback/*")
public class BizPayCallbackControllor extends BaseController {

	private Logger logger = Logger.getLogger(BizPayCallbackControllor.class.getSimpleName());
	@Autowired
	private BizPaymentService bizPaymentService;
	@Autowired
	private BizPayRefundService bizPayRefundService;
	@RequestMapping(value = "alipay")
	public void alipay(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		logger.info("支付宝支付回调[start]");
		Map<String, String> params = alipayValidate(request, response);
		if (params != null) {
			logger.info("支付宝支付回调参数：" + params);
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝支付回调参数[商户订单号]：" + out_trade_no);
			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝支付回调参数[支付宝交易号]：" + trade_no);
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝支付回调参数[交易状态]：" + trade_status);
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			// bizPaymentService.insert();
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}
			bizPaymentService.insertFromAlipay(params, out_trade_no, trade_no, trade_status, params.get("gmt_payment"));
			response.getWriter().println("success"); // 请不要修改或删除
		} else {// 验证失败
			response.getWriter().println("fail");
		}
	}

	private Map<String, String> alipayValidate(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);

		}
		if (AlipayNotify.verify(params)) {
			return params;
		}
		return null;
	}

	/** 支付宝退款通知 (未测试)*/
	@RequestMapping(value = "alipay-refund")
	public void alipayRefund(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		logger.info("支付宝退款回调[start]");
		Map<String, String> params = alipayValidate(request, response);
		if (params != null) {// 验证成功
			logger.info("支付宝退款回调参数：" + params);
			String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝退款回调参数[批次号]：" + batch_no);
			String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝退款回调参数[批量退款数据中转账成功的笔数]：" + success_num);
			String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("支付宝退款回调参数[批量退款数据中的详细信息]：" + result_details);
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			// 判断是否在商户网站中已经做过了这次通知返回的处理
			// 如果没有做过处理，那么执行商户的业务程序
			// 如果有做过处理，那么不执行商户的业务程序
			bizPayRefundService.insertFromAlipay(params, batch_no, success_num, result_details);
			response.getWriter().println("success"); // 请不要修改或删除

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			response.getWriter().println("fail");
		}
	}
	/** 支付宝批量付款给支付宝账号通知 (未测试)*/
	@RequestMapping(value = "alipay-batch-trans")
	public void alipayBatchTrans(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		logger.info("支付宝批量回调[start]");
		Map<String, String> params = alipayValidate(request, response);
		if (params != null) {// 验证成功
			logger.info("支付宝批量回调[参数]：" + params);
			//批量付款数据中转账成功的详细信息

			String success_details = new String(request.getParameter("success_details").getBytes("ISO-8859-1"),"UTF-8");
			logger.info("支付宝批量回调[参数]：" + success_details);
			//批量付款数据中转账失败的详细信息
			String fail_details = new String(request.getParameter("fail_details").getBytes("ISO-8859-1"),"UTF-8");
			logger.info("支付宝批量回调[参数]：" + fail_details);
			response.getWriter().println("success"); // 请不要修改或删除
		} else {// 验证失败
			response.getWriter().println("fail");
		}
	}
}
