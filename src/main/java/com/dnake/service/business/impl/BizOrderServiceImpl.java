package com.dnake.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.business.BizCoupon;
import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizOrderDetails;
import com.dnake.domain.business.BizOrderNum;
import com.dnake.domain.business.BizOrderVO;
import com.dnake.domain.business.BizPayment;
import com.dnake.domain.business.BizRefund;
import com.dnake.domain.business.BizUserGift;
import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.fastJson.JsonRequestGoodsAndQuantity;
import com.dnake.domain.fastJson.JsonRequestOrder;
import com.dnake.domain.fastJson.JsonReturnBizOrder;
import com.dnake.domain.system.SysUser;
import com.dnake.mapper.business.BizCommentMapper;
import com.dnake.mapper.business.BizCouponMapper;
import com.dnake.mapper.business.BizGoodsMapper;
import com.dnake.mapper.business.BizOrderDetailsMapper;
import com.dnake.mapper.business.BizOrderMapper;
import com.dnake.mapper.business.BizOrderNumMapper;
import com.dnake.mapper.business.BizPaymentMapper;
import com.dnake.mapper.business.BizRefundMapper;
import com.dnake.mapper.business.BizUserGiftMapper;
import com.dnake.mapper.system.SysUserMapper;
import com.dnake.service.business.BizMessageService;
import com.dnake.service.business.BizOrderService;
import com.dnake.service.business.BizPayRefundService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.utils.Constants;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.DoubleUtil;
import com.dnake.utils.GenerateRandomStringUtil;
import com.dnake.utils.JPushUtil;
import com.dnake.utils.PropertyPreFilterHaveNull;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;

/**
 * 商品管理实现类 BizOrderServiceImpl <br/>
 * 2014年10月21日 上午10:06:54 <br/>
 * 
 * @author chen qige
 * @version
 */
@Service
public class BizOrderServiceImpl extends BaseServiceImpl implements
		BizOrderService {

	@Resource
	private BizOrderMapper bizOrderMapper;
	@Resource
	private CommonService commonService;
	@Resource
	private BizGoodsMapper bizGoodsMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private BizOrderDetailsMapper bizOrderDetailsMapper;

	@Resource
	private BizMessageService bizMessageService;
	@Resource
	private BizUserGiftMapper bizUserGiftMapper;
	@Resource
	private BizCouponMapper bizCouponMapper;
	@Resource
	private BizCommentMapper bizCommentMapper;
	@Resource
	private BizRefundMapper bizRefundMapper;
	@Resource
	private BizPayRefundService bizPayRefundService;
	@Resource
	private BizOrderNumMapper bizOrderNumMapper;
	@Resource
	private BizPaymentMapper bizPaymentMapper;

	@Override
	public String update(BizOrder bizOrder) {
		return null;
	}

	@Override
	public String delete(HttpServletResponse response, Long idKey) {
		return null;
	}

	@Override
	public String insert(BizOrder bizOrder) {
		return null;
	}

	@Override
	public Page<BizOrderVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		if (StringUtils.isNull(searchParam.getStartDatetime())) {
			searchParam.setStartDatetime(DateTimeUtil.formatDateToStr(
					DateTimeUtil.getDateAfter(new Date(),
							Constants.START_PERIOD), "yyyy-mm-dd")
					+ " 00:00:00");
		} else {
			searchParam.setStartDatetime(searchParam.getStartDatetime()
					+ " 00:00:00");
		}
		if (StringUtils.isNull(searchParam.getEndDatetime())) {
			searchParam.setEndDatetime(DateTimeUtil.formatDateToStr(new Date(),
					"yyyy-mm-dd") + " 23:59:59");
		} else {
			searchParam.setEndDatetime(searchParam.getEndDatetime()
					+ " 23:59:59");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderStatus", searchParam.getOrderStatus());
		map.put("startDatetime", searchParam.getStartDatetime());
		map.put("endDatetime", searchParam.getEndDatetime());
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		map.put("shopName", searchParam.getShopName());
		map.put("salesName", searchParam.getUserName());
		if (searchParam.getCommunityId() == null
				|| searchParam.getCommunityId().equals(-1L)) {
			map.put("communityIds", getUserBean().getPopedomCommunityIds()
					.split(","));// 增加管理员是否可以查看该小区商品的权限判断
		} else {
			map.put("communityId", searchParam.getCommunityId());
		}
		Page<BizOrderVO> page = new Page<BizOrderVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizOrderMapper.queryPage(page));
		return page;
	}

	@Override
	public String updateStatus(Long idKey, String type) {
		return type;
	}

	public String deleteMulti(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(ids.split(",")));
		bizOrderMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\"}";
	}

	@Transactional
	public String insert(MobileParms parms) {
		SessionBean sessionBean = getUserBean();
		String[] gd = parms.getIdString().split("-");
		String[] gq = parms.getCountString().split("-");
		List<Integer> gqList = new ArrayList<Integer>();// 用于存放商品数量
		for (String q : gq) {
			gqList.add(Integer.valueOf(q));
		}
		List<BizGoodsVO> gList = new ArrayList<BizGoodsVO>();// 用于存放商品
		final Set<Long> salesId = new HashSet<Long>();
		BizGoodsVO bgvo = null;
		for (String d : gd) {
			bgvo = bizGoodsMapper.mobileQueryGoodsDetails(Long.valueOf(d));
			gList.add(bgvo);
			salesId.add(bgvo.getSalesId());
		}
		List<BizOrderDetails> goodsSortBySales = new ArrayList<BizOrderDetails>();
		BizOrder bizOrder = new BizOrder();
		bizOrder.setOrderAddr(parms.getOrderAddr() != null ? parms
				.getOrderAddr() : sessionBean.getAddr());
		bizOrder.setOrderTelephone(parms.getOrderTelephone() != null ? parms
				.getOrderTelephone() : sessionBean.getTelephone());
		bizOrder.setReceiver(parms.getReceiver() != null ? parms.getReceiver()
				: sessionBean.getUserName());
		bizOrder.setUserId(sessionBean.getUserId());
		bizOrder.setUserName(sessionBean.getUserName());
		bizOrder.setDeliveryTime(parms.getDeliveryTime());
		bizOrder.setPaymentType(0);// 等待商家接单
		bizOrder.setOrderStatus(1);// 在线支付
		bizOrder.setOrderDt(new Date());
		for (Long sId : salesId) {// 对同一商家进行订单合并
			Double sum = 0.0;
			for (int i = 0; i < gList.size(); i++) {// 将商品id和商家id一样的进行合并处理
				BizOrderDetails bizOrderDetails = new BizOrderDetails();
				BizGoodsVO tempGoods = gList.get(i);
				if (tempGoods.getSalesId().equals(sId)) {
					sum = DoubleUtil.add(
							sum,
							DoubleUtil.mul(tempGoods.getUnitPrice(),
									gqList.get(i)));// 算出总价，其中商品在list中的位置与其数量在list中的位置一致
					bizOrder.setSalesName(tempGoods.getSalesName());
					bizOrderDetails.setGoodsId(tempGoods.getIdKey());
					bizOrderDetails.setGoodsQuantity(gqList.get(i));
					bizOrderDetails.setGoodsName(tempGoods.getGoodsName());
					bizOrderDetails.setShowSimages(tempGoods.getShowSimages());
					bizOrderDetails.setSimages(tempGoods.getSimages());
					bizOrderDetails.setGoodsUnitPrice(tempGoods.getUnitPrice());
					bizOrderDetails.setGoodsCost(DoubleUtil.mul(
							tempGoods.getUnitPrice(), gqList.get(i)));
					goodsSortBySales.add(bizOrderDetails);// 将同一订单的商品放入集合中
				}
			}
			bizOrder.setSalesId(sId);
			bizOrder.setOrderCost(sum);
			bizOrder.setOrderNumber(GenerateRandomStringUtil.getOrderNumber());
			bizOrder.setIdKey(null);
			bizOrderMapper.insert(bizOrder);// 插入订单
			for (BizOrderDetails bd : goodsSortBySales) {
				bd.setOrderId(bizOrder.getIdKey());
				bizOrderDetailsMapper.insert(bd);// 插入订单详情
			}
			goodsSortBySales.clear();
		}
		/* 下面是推送消息 */

		Set<String> userCdSet = new HashSet<String>();
		String notifyString = DateTimeUtil.getDateToStrFullFormat(new Date())
				+ " 您的i尚商铺有一个新订单";
		for (Long sId : salesId) {
			SysUser sysUser = sysUserMapper.queryById(sId);
			userCdSet.add(sysUser.getUserCd());
		}
		JPushUtil.sendPushByRegisterIds("sales", userCdSet, notifyString);// 极光推送
		return "{\"msg\":1}";
	}

	@Override
	public String mobileQueryOrderAndDetails(MobileParms parms) {// 先根据用户id查询订单，再根据订单号查询订单详情
		Page<BizOrderVO> page = new Page<BizOrderVO>();// 订单page
		Page<BizOrderDetails> page2 = new Page<BizOrderDetails>();// 订单详情page
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		page2.setPageNo(1);
		page2.setPageSize(100);
		SessionBean sessionBean = getUserBean();
		map.put("orderStatus", parms.getOrderStatus());
		if (sessionBean.getRoleId() == Constants.SALES_ROLE_ID) {
			map.put("salesId", sessionBean.getUserId());// 获取商户id
			if (parms.getOrderStatus()==-1) {
				map.put("orderNotStatus", 6);//商家的单子里去除未付款的订单
			}
		}
		if (sessionBean.getRoleId() == Constants.USER_ROLE_ID)
			map.put("userId", sessionBean.getUserId());// 获取用户id
		page.setParams(map);
		List<BizOrderVO> orderList = bizOrderMapper.queryPage(page);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (BizOrderVO bovo : orderList) {
			BizRefund bizRefund = bizRefundMapper.queryByOrderId(bovo.getIdKey());
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("shopName", bovo.getShopName());
			returnMap.put("shopId", bovo.getShopId());
			returnMap.put("idKey", bovo.getIdKey());
			returnMap.put("orderNumber", bovo.getOrderNumber());
			returnMap.put("receiver", bovo.getReceiver());
			returnMap.put("orderTelephone", bovo.getOrderTelephone());
			returnMap.put("orderAddr", bovo.getOrderAddr());
			returnMap.put("orderStatus", bovo.getOrderStatus());
			returnMap.put("orderDt", bovo.getOrderDt());
			returnMap.put("orderCost", bovo.getOrderCost());
			returnMap.put("userCost", bovo.getUserCost());
			returnMap.put("shopGift", bovo.getShopGift());
			returnMap.put("shopGet", bovo.getShopGet());
			returnMap.put("salesTelephone", bovo.getSalesTelephone());
			returnMap.put("paymentType", bovo.getPaymentType());
			//退款属性参数
			if(bizRefund != null)
			{
				returnMap.put("refundType", bizRefund.getRefundType()==null?null:bizRefund.getRefundType());
				returnMap.put("refundReason", bizRefund.getRefundReason()==null?null:bizRefund.getRefundReason());
				returnMap.put("refundMoney", bizRefund.getRefundMoney()==null?null:bizRefund.getRefundMoney());
				returnMap.put("refundDt", bizRefund.getRefundDt()==null?null:bizRefund.getRefundDt());
				returnMap.put("refundExplain", bizRefund.getRefundExplain()==null?null:bizRefund.getRefundExplain());
				returnMap.put("imageOne", bizRefund.getImageOne()==null?null:bizRefund.getImageOne());
				returnMap.put("imageTwo", bizRefund.getImageTwo()==null?null:bizRefund.getImageTwo());
				returnMap.put("imageThree", bizRefund.getImageThree()==null?null:bizRefund.getImageThree());
				returnMap.put("imageFour", bizRefund.getImageFour()==null?null:bizRefund.getImageFour());
			}
			map2.put("orderId", bovo.getIdKey());
			page2.setParams(map2);
			List<BizOrderDetails> orderDetailsList = bizOrderDetailsMapper
					.queryPage(page2);// 订单详情
			returnMap.put("goodsNum", orderDetailsList.size());// 商品个数
			boolean haveComment = false;
			List<BizOrderDetails> orderDetailsList1 = new ArrayList<BizOrderDetails>();
			for (BizOrderDetails bizOrderDetails : orderDetailsList) {
				BizGoods bizGoods = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
				bizOrderDetails.setGoodStatus(bizGoods.getGoodsStatus());
				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("orderId", bovo.getIdKey());
				map3.put("goodsId", bizOrderDetails.getGoodsId());
				List<BizCommentVO> bizComment = bizCommentMapper
						.queryCommentIsReply(map3);
				if (bizComment.size() > 0) {
					bizOrderDetails.setIsReply(1);
				} else {
					bizOrderDetails.setIsReply(0);
					haveComment = true;
					returnMap.put("orderComment", 0);
				}
				orderDetailsList1.add(bizOrderDetails);
			}
			// 1已经评论，0等待评论
			if (!haveComment)
				returnMap.put("orderComment", 1);
			returnMap.put("goods", orderDetailsList1);
			returnList.add(returnMap);
		}
		JsonResult jr = new JsonResult();
		jr.setData(returnList);
		return ResultBuilderUtil.jsonBuild(jr);
	}

	@Override
	@Transactional
	public String mobileUpdateOrder(MobileParms parms) {
		if(ValidationUtil.checkHaveNull(parms.getId(),parms.getOrderStatus())){
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		BizOrder bizOrder = new BizOrder();
		bizOrder.setIdKey(parms.getId());
		bizOrder.setOrderStatus(parms.getOrderStatus());
		if (parms.getOrderStatus() == 7) {// 买家完成付款
			BizOrder biz = bizOrderMapper.queryByIsPayment(parms.getId());
			if(biz != null)
			{
				if(biz.getOrderStatus() == 10)
				{
					return ResultBuilderUtil.statusMessage(10, "该商品已经被抢购！");
				}
			}
			List<BizOrderDetails> orderDetailsList = bizOrderDetailsMapper
					.queryGoodsNotInMallByOrderId(parms.getId());// 团购商品
			if (orderDetailsList.size() != 0) {// 有团购商品，发放团购券
				List<BizCoupon> couponList = new ArrayList<BizCoupon>();
				for (BizOrderDetails bizOrderDetails : orderDetailsList) {
					BizCoupon bizCoupon = new BizCoupon();
					bizCoupon.setOrderDetailsId(bizOrderDetails.getIdKey());
					bizCoupon.setChgDt(new Date());
					bizCoupon.setStatus(1);
					String ver = Math.random() + "";
					bizCoupon.setVerification(ver.substring(ver.length() - 8));
					couponList.add(bizCoupon);
				}
				//bizCouponMapper.insertMulti(couponList);  //暂时注释，因为没有团购模块
			}
		}else if(parms.getOrderStatus() == 3){
			BizOrder biz = bizOrderMapper.queryByIdkey(parms.getId());
			switch(biz.getOrderStatus()) {
				case 7:	//买家已付款
					BizRefund bizRefund = new BizRefund();
					bizRefund.setOrderId(biz.getIdKey());
					bizRefund.setRefundMoney(biz.getUserCost());
					bizRefundMapper.insert(bizRefund);
					//调用退款接口，初始化一条退款数据，原因不用填写，只需填写金额
					String  sellerEmail = "";	//卖家邮箱
					String detailData = "";		//详细数据   订单号^金额^退款理由
					BizOrderNum bizOrderNum = bizOrderNumMapper.queryByOrderNum(biz.getOrderNumber());
					BizPayment bizPayment = bizPaymentMapper.queryByOrderId(bizOrderNum.getAlipayOrderNum());
					if(bizPayment != null)
					{
						sellerEmail = bizPayment.getAlipaySellerEmail();
						detailData = bizOrderNum.getAlipayOrderNum() + "^" + biz.getUserCost() + "^无理由";
						bizPayRefundService.refundSubmit(sellerEmail, detailData);
						List<BizOrderDetails> listTemp2 = bizOrderDetailsMapper.queryByOrderId(biz.getIdKey());
						for (BizOrderDetails bizOrderDetails : listTemp2)
						{
							BizGoods bizTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
							BizGoods bizGoodsTemp = new BizGoods();
							bizGoodsTemp.setIdKey(bizOrderDetails.getIdKey());
							bizGoodsTemp.setQuantity(bizTemp.getQuantity() + bizOrderDetails.getGoodsQuantity());
							bizGoodsMapper.update(bizGoodsTemp);
						}
					}
					bizOrder.setOrderStatus(10);
					break;
			}
		}
		else if (parms.getOrderStatus() == 8) {
			bizOrder.setPaymentType(1);// 货到付款
		}else if(parms.getOrderStatus() == 1) {
			BizOrder biz = bizOrderMapper.queryByIdkey(parms.getId());
			switch(biz.getOrderStatus()) {
				case 6:	//未付款
				case 8:	//货到付款
					bizOrder.setOrderStatus(10);	//10交易关闭
					List<BizOrderDetails> listTemp1 = bizOrderDetailsMapper.queryByOrderId(biz.getIdKey());
					for (BizOrderDetails bizOrderDetails : listTemp1)
					{
						BizGoods bizTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
						BizGoods bizGoodsTemp = new BizGoods();
						bizGoodsTemp.setIdKey(bizOrderDetails.getGoodsId());
						bizGoodsTemp.setQuantity(bizTemp.getQuantity() + bizOrderDetails.getGoodsQuantity());
						bizGoodsMapper.update(bizGoodsTemp);
					}
					break;
				case 7:	//买家已付款
					BizRefund bizRefund = new BizRefund();
					bizRefund.setOrderId(biz.getIdKey());
					bizRefund.setRefundMoney(biz.getUserCost());
					bizRefundMapper.insert(bizRefund);
					//调用退款接口，初始化一条退款数据，原因不用填写，只需填写金额
					String  sellerEmail = "";	//卖家邮箱
					String detailData = "";		//详细数据   订单号^金额^退款理由
					BizOrderNum bizOrderNum = bizOrderNumMapper.queryByOrderNum(biz.getOrderNumber());
					BizPayment bizPayment = bizPaymentMapper.queryByOrderId(bizOrderNum.getAlipayOrderNum());
					if(bizPayment != null)
					{
						sellerEmail = bizPayment.getAlipaySellerEmail();
						detailData = bizOrderNum.getAlipayOrderNum() + "^" + biz.getUserCost() + "^无理由";
						bizPayRefundService.refundSubmit(sellerEmail, detailData);
						List<BizOrderDetails> listTemp2 = bizOrderDetailsMapper.queryByOrderId(biz.getIdKey());
						for (BizOrderDetails bizOrderDetails : listTemp2)
						{
							BizGoods bizTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
							BizGoods bizGoodsTemp = new BizGoods();
							bizGoodsTemp.setIdKey(bizOrderDetails.getIdKey());
							bizGoodsTemp.setQuantity(bizTemp.getQuantity() + bizOrderDetails.getGoodsQuantity());
							bizGoodsMapper.update(bizGoodsTemp);
						}
					}
					bizOrder.setOrderStatus(10);
					break;
				case 4:	//商家正在配送中
					bizOrder.setOrderStatus(9);		//9退款中
					break;
			}
		}else if(parms.getOrderStatus() == 11){		//11商家拒绝退款
			bizOrder.setOrderStatus(11);	//商家拒绝退款
		}else if(parms.getOrderStatus() == 12){		//12商家确认退款
			BizOrder biz = bizOrderMapper.queryByIdkey(parms.getId());
			if(biz.getPaymentType() == 0)
			{
				BizRefund bizRefund = bizRefundMapper.queryByOrderId(bizOrder.getIdKey());
				//调用退款接口，初始化一条退款数据，原因不用填写，只需填写金额
				String  sellerEmail = "";	//卖家邮箱
				String detailData = "";		//详细数据   订单号^金额^退款理由
				BizOrderNum bizOrderNum = bizOrderNumMapper.queryByOrderNum(biz.getOrderNumber());
				BizPayment bizPayment = bizPaymentMapper.queryByOrderId(bizOrderNum.getAlipayOrderNum());
				sellerEmail = bizPayment.getAlipaySellerEmail();
				detailData = bizOrderNum.getAlipayOrderNum() + "^" + bizRefund.getRefundMoney() + "^" + bizRefund.getRefundExplain();
				bizPayRefundService.refundSubmit(sellerEmail, detailData);
				//给退款商品数量加回
				List<BizOrderDetails> list = bizOrderDetailsMapper.queryByOrderId(biz.getIdKey());
				for (BizOrderDetails bizOrderDetails : list)
				{
					BizGoods bizTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
					BizGoods bizGoodsTemp = new BizGoods();
					bizGoodsTemp.setIdKey(bizOrderDetails.getIdKey());
					bizGoodsTemp.setQuantity(bizTemp.getQuantity() + bizOrderDetails.getGoodsQuantity());
					bizGoodsMapper.update(bizGoodsTemp);
				}
			}
			bizOrder.setOrderStatus(12);
		}
		if(bizOrder.getOrderStatus() == 1)
			bizOrder.setOrderStatus(10);
		bizOrderMapper.update(bizOrder);

		// 以下是推送
		BizOrder bizOrder2 = bizOrderMapper.queryByIdkey(parms.getId());
		SysUser sysUser = sysUserMapper.queryById(bizOrder2.getUserId());
		Set<String> userCdSet = new HashSet<String>();
		String notifyString = null;
		if (parms.getOrderStatus() == 1||parms.getOrderStatus()==8) {//取消订单或者货到付款
			SysUser sales = sysUserMapper.queryById(bizOrder2.getSalesId());
			if (sales != null) {
				userCdSet.add(sales.getUserCd());
			}
			String content=" 您的一条i尚商铺订单已被买家取消！";
			if(parms.getOrderStatus()==8){
				content=" 您的i尚商铺有新的订单！";
			}
			notifyString = DateTimeUtil.getDateToStrFullFormat(new Date())
					+ content;
			JPushUtil.sendPushByRegisterIds("sales", userCdSet, notifyString);// 极光推送
			return ResultBuilderUtil.statusMessage(1, "更新成功");
		} else if (bizOrder2.getOrderStatus() == 2) {
			notifyString = "您在 "
					+ DateTimeUtil.getDateToStrFullFormat(bizOrder2
							.getOrderDt()) + " 下的订单已被商家接收";
		} else if (bizOrder2.getOrderStatus() == 3) {
			notifyString = "您在 "
					+ DateTimeUtil.getDateToStrFullFormat(bizOrder2
							.getOrderDt()) + " 下的订单已被商家拒绝接收";
		} else if (bizOrder2.getOrderStatus() == 4) {
			notifyString = "您在 "
					+ DateTimeUtil.getDateToStrFullFormat(bizOrder2
							.getOrderDt()) + " 下的订单,商家已在配送途中";
		}
		if (sysUser != null) {
			userCdSet.add(sysUser.getUserCd());
		}
		if (notifyString != null) {
			JPushUtil
					.sendPushByRegisterIds("customer", userCdSet, notifyString);
		}
		return ResultBuilderUtil.statusMessage(1, "更新成功");
	}

	@Override
	public String mobileQueryOrderPage(MobileParms parms) {
		if (ValidationUtil.checkHaveNull(parms.getPage(), parms.getCount())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		Page<BizOrderVO> page = new Page<BizOrderVO>();// 订单page
		Map<String, Object> map = new HashMap<String, Object>();
		page.setPageNo(parms.getPage());
		page.setPageSize(parms.getCount());
		SessionBean sessionBean = getUserBean();
		if (sessionBean.getRoleId() == Constants.SALES_ROLE_ID)
			map.put("salesId", sessionBean.getUserId());// 获取商户id
		if (sessionBean.getRoleId() == Constants.USER_ROLE_ID)
			map.put("userId", sessionBean.getUserId());// 获取用户id
		map.put("page", parms.getPage());
		map.put("count", parms.getCount());
		page.setParams(map);
		List<BizOrderVO> orderList = bizOrderMapper.queryPage(page);
		PropertyPreFilterHaveNull filter = new PropertyPreFilterHaveNull(
				BizOrderVO.class, "idKey", "orderNumber", "receiver",
				"orderTelephone", "orderAddr", "orderStatusName",
				"orderStatus", "orderCost", "orderDt", "deliveryTime");
		JsonResult jr = new JsonResult();
		JsonReturnBizOrder jrb = new JsonReturnBizOrder();
		jrb.setTotal(page.getTotal());
		jrb.setTotalPage(page.getTotalPage());
		jrb.setList(orderList);
		jr.setData(jrb);
		return ResultBuilderUtil.jsonBuild(jr, filter);
	}

	@Override
	@Transactional
	public synchronized String mobileInsertOrder(MobileParms parms) {
		// 必要参数判断
		if (ValidationUtil.checkHaveNull(parms.getData(), parms.getReceiver(),
				parms.getOrderAddr())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		//判断一分钱活动商品用户是否只下了一单，思路：订单时间三小时以内再次提交且为则判定为第二次购买。  add by zgj 20151009
		SessionBean sessionBean = getUserBean();
		List<BizOrder> listOrderOnlyOne = bizOrderMapper.queryOrderIsOnlyOne(new Date(), sessionBean.getUserId());
		if(listOrderOnlyOne.size() > 0)
		{
			return ResultBuilderUtil.statusMessage(10, "本场活动只能参与一次"); 
		}
		// 解析前端数据
		List<JsonRequestOrder> jsonRequestOrderList = new ArrayList<JsonRequestOrder>();
		jsonRequestOrderList = JSON.parseObject(parms.getData(),
				new TypeReference<List<JsonRequestOrder>>() {
				});
		double sum = 0;
		// 查询商品 放在priceMap中
		Map<Long, BizGoods> priceMap = new HashMap<Long, BizGoods>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<String> queryList = new ArrayList<String>();
		queryMap.put("idKeys", queryList);
		for (JsonRequestOrder jsonRequestOrder : jsonRequestOrderList) {
			for (JsonRequestGoodsAndQuantity goods : jsonRequestOrder
					.getGoods()) {
				queryList.add(goods.getGoodsId() + "");
				BizGoods bizGoodsTemp = bizGoodsMapper.queryByIdKey(goods.getGoodsId());
				if(bizGoodsTemp.getQuantity() > 0 && bizGoodsTemp.getQuantity() >= goods.getGoodsQuantity())
				{
					BizGoods bizGoodsTemp1 = new BizGoods();
					bizGoodsTemp1.setIdKey(bizGoodsTemp.getIdKey());
					bizGoodsTemp1.setQuantity(bizGoodsTemp.getQuantity() - goods.getGoodsQuantity());
					bizGoodsMapper.update(bizGoodsTemp1);
				}
				else
				{
					return ResultBuilderUtil.statusMessage(9, "商品已经被抢完");
				}
			}
		}
		List<BizGoods> bizGoodsList = bizGoodsMapper
				.queryDiscountPriceByIds(queryMap);
		for (BizGoods bizGoods : bizGoodsList) {
			priceMap.put(bizGoods.getIdKey(), bizGoods);
		}
		List<BizOrderDetails> bizOrderDetailsInsertList = new ArrayList<BizOrderDetails>();
		String orderNumbers = "";
		String orderIds = "";
		// 计算每一单价格
		for (JsonRequestOrder jsonRequestOrder : jsonRequestOrderList) {
			String orderNumber = "";
			BizOrder bizOrder = new BizOrder();
			Double singleOrderSum = 0.0;// 每一单原来总价
			for (JsonRequestGoodsAndQuantity goods : jsonRequestOrder
					.getGoods()) {
				BizGoods bizGoods = priceMap.get(goods.getGoodsId());
				singleOrderSum = singleOrderSum + goods.getGoodsQuantity()
						* bizGoods.getDiscountPrice();
			}
			Double shopGiftValue = 0.0;// 优惠的价格
			Double otherGiftValue = 0.0;// 其他优惠，暂时没做
			for (Long giftId : jsonRequestOrder.getGiftId()) {
				BizUserGift bizUserGift = bizUserGiftMapper
						.queryByIdkey(giftId);
				bizUserGift.setStatus(2);// 正在使用
				bizUserGiftMapper.update(bizUserGift);// 更改优惠券状态
				shopGiftValue += bizUserGift.getValue();
			}
			bizOrder.setOrderAddr(parms.getOrderAddr() != null ? parms
					.getOrderAddr() : getUserBean().getAddr());
			bizOrder.setOrderTelephone(parms.getOrderTelephone() != null ? parms
					.getOrderTelephone() : getUserBean().getTelephone());
			bizOrder.setReceiver(parms.getReceiver() != null ? parms
					.getReceiver() : getUserBean().getUserName());
			bizOrder.setUserId(getUserBean().getUserId());
			bizOrder.setUserName(getUserBean().getUserName());
			bizOrder.setDeliveryTime(parms.getDeliveryTime());
			bizOrder.setPaymentType(0);// 等待商家接单
			bizOrder.setOrderStatus(6);// modified by ts 20150722 改成未付款
			bizOrder.setOrderDt(new Date());
			bizOrder.setMessage(parms.getRemarks());// 买家留言
			bizOrder.setDeliveryTime(parms.getDeliveryTime());
			bizOrder.setOrderAddr(parms.getOrderAddr());
			bizOrder.setOrderCost(singleOrderSum);
			SysUser sysUser = sysUserMapper.queryByShopId(jsonRequestOrder
					.getShopId());
			if (sysUser != null) {
				bizOrder.setSalesName(sysUser.getCdNm());
				bizOrder.setSalesId(sysUser.getIdKey());
			}
			bizOrder.setShopId(jsonRequestOrder.getShopId());
			bizOrder.setOtherGift(otherGiftValue);
			bizOrder.setShopGift(shopGiftValue);// 店铺优惠价格
			bizOrder.setShopGet(singleOrderSum - shopGiftValue);
			bizOrder.setUserCost(singleOrderSum - shopGiftValue
					- otherGiftValue);
			// added by ts 订单号保存 20150722
			orderNumber = GenerateRandomStringUtil.getOrderNumber();
			
			bizOrder.setOrderNumber(orderNumber);
			sum += bizOrder.getUserCost();
			bizOrderMapper.insert(bizOrder);
			orderIds += bizOrder.getIdKey() + ",";
			orderNumbers += orderNumber + ",";
			for (JsonRequestGoodsAndQuantity goods : jsonRequestOrder
					.getGoods()) {// 插入订单详情
				BizGoods bizGoods = priceMap.get(goods.getGoodsId());
				BizOrderDetails bizOrderDetails = new BizOrderDetails();
				bizOrderDetails.setGoodsId(goods.getGoodsId());
				bizOrderDetails.setGoodsName(bizGoods.getGoodsName());
				bizOrderDetails.setGoodsQuantity(goods.getGoodsQuantity());
				bizOrderDetails.setGoodsUnitPrice(bizGoods.getDiscountPrice());
				bizOrderDetails.setOrderId(bizOrder.getIdKey());
				bizOrderDetails.setShowSimages(bizGoods.getShowSimages());
				bizOrderDetails.setSimages(bizGoods.getShowSimages());
				bizOrderDetails.setGoodsDiscountPrice(bizGoods
						.getDiscountPrice());
				bizOrderDetails.setGoodsCost(bizGoods.getDiscountPrice()
						* goods.getGoodsQuantity());// 商品的花费总价是根据打折后价格计算的
				bizOrderDetailsInsertList.add(bizOrderDetails);
			}
		}
		bizOrderDetailsMapper.insertMulti(bizOrderDetailsInsertList);// 订单详情插入数据库

		/* 下面是推送消息 */
		/*
		 * modify by cqg 20150805 新增订单不推送 Set<String> userCdSet = new
		 * HashSet<String>(); String notifyString =
		 * DateTimeUtil.getDateToStrFullFormat(new Date()) + " 您的i尚商铺有一个新订单";
		 * for (JsonRequestOrder sId : jsonRequestOrderList) { SysUser sysUser =
		 * sysUserMapper.queryByShopId(sId.getShopId()); if(sysUser==null){
		 * continue; } if (!StringUtils.isNull(sysUser.getUserCd())) {
		 * userCdSet.add(sysUser.getUserCd()); } }
		 * JPushUtil.sendPushByRegisterIds("sales", userCdSet, notifyString);//
		 * 极光推送
		 */
		// 返回组装
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("sum", sum);
		returnMap.put("orderNumber",
				orderNumbers.substring(0, orderNumbers.length() - 1)); // added
																		// by ts
																		// 返回订单号
																		// 20150722
		returnMap.put("orderIds", orderIds.substring(0, orderIds.length() - 1)); // added
																					// by
																					// ts
																					// 返回订单id
																					// 20150722
		jsonResult.setData(returnMap);
		return ResultBuilderUtil.jsonBuild(jsonResult);
	}

	@Override
	public String mobileGetAlipayOrderNum(MobileParms parms)
	{
		// 必要参数判断
		if (ValidationUtil.checkHaveNull(parms.getOrderIds())) {
			return ResultBuilderUtil.RESULT_ERROR_MISSING_PARAMETER;
		}
		String alipayOrderNum = GenerateRandomStringUtil.getAlipayOrderNum();
		String[] strList = parms.getOrderIds().split(",");
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//判断订单是否被关闭，关闭则直接告知不可支付
		for (String orderNum : strList)
		{
			BizOrder bizOrderTemp = bizOrderMapper.queryByOrderNum(orderNum);
			if(bizOrderTemp != null)
			{
				if(bizOrderTemp.getOrderStatus() == 10)
				{
					return ResultBuilderUtil.statusMessage(7, "该订单已经关闭！");
				}
				List<BizOrderDetails> listBizOrderDetailsTemp = bizOrderDetailsMapper.queryByOrderId(bizOrderTemp.getIdKey());
				if(listBizOrderDetailsTemp.size() > 0)
				{
					for (BizOrderDetails bizOrderDetails : listBizOrderDetailsTemp)
					{
						BizGoods bizGoodsTemp = bizGoodsMapper.queryByIdKey(bizOrderDetails.getGoodsId());
						if(bizGoodsTemp.getGoodsStatus() == 1 || bizGoodsTemp.getGoodsStatus() == 3)	//若商品为下架和管理员强制下架则关闭该订单
						{
							BizOrder bizOrderNews = new BizOrder();
							bizOrderNews.setIdKey(bizOrderTemp.getIdKey());
							bizOrderNews.setOrderStatus(10);
							return ResultBuilderUtil.statusMessage(8, "商品已被下架，请重新下单！");
						}
					}
				}

			}
		}
		
		for (String orderNum : strList)
		{
			//添加统一订单号至数据库关联表
			BizOrderNum bizOrderNum = new BizOrderNum();
			bizOrderNum.setOrderNum(orderNum);
			bizOrderNum.setOrderDt(new Date());
			bizOrderNum.setAlipayOrderNum(alipayOrderNum);
			bizOrderNumMapper.insert(bizOrderNum);
		}

		returnMap.put("alipayOrderNum", alipayOrderNum);
		jsonResult.setData(returnMap);
		return ResultBuilderUtil.jsonBuild(jsonResult);
	}

}
