package com.dnake.domain.business;

import java.util.Date;

/**
 * 支付信息类
 * 
 * @author 蔡小龙
 */
public class BizPayment {
	/** 主键 */
	private Long idKey;
	/** 支付时间 */
	private Date paymentDate;
	/** 采用何种方式支付（支付宝，微信） */
	private PaymentType type = PaymentType.ALIPAY;

	/** enum 采用何种方式支付（支付宝，微信） */
	public enum PaymentType {
		/** 支付宝 */
		ALIPAY,
		/** 微信 */
		WEIXIN
	}

	/** 支付宝回调参数 */
	private String alipayParams;
	/** 支付宝回调，商户订单号 */
	private String alipayOutTradeNo;
	/** 支付宝回调 ，支付宝交易号 */
	private String alipayTradeNo;
	/** 支付宝回调 ，支付宝交易状态 */
	private String alipayTradeStatus;
	private String alipayBody;
	/** 支付宝回调 ，买家邮箱 */
	private String alipayBuyerEmail;
	/** 支付宝回调 ，买家Id */
	private String alipayBuyerId;
	/** 支付宝回调 ， 折扣*/
	private String alipayDiscount;
	/** 支付宝回调 ， 交易创建时间*/
	private String alipayGmtCreate;
	/** 支付宝回调 ，交易付款时间 */
	private String alipayGmtPayment;
	/** 支付宝回调 ，是否调整总价 */
	private String alipayIsTotalFeeAdjust;
	/** 支付宝回调 ， 通知校验ID*/
	private String alipayNotifyId;
	/** 支付宝回调 ，通知时间*/
	private String alipayNotifyTime;
	/** 支付宝回调 ，通知类型 */
	private String alipayNotifyType;
	/** 支付宝回调 ，是否使用红包买家 */
	private String alipayUseCoupon;
	/** 支付宝回调 ， 支付类型*/
	private String alipayPaymentType;
	/** 支付宝回调 ， 商品单价*/
	private String alipayPrice;
	/** 支付宝回调 ， 购买数量*/
	private String alipayQuantity;
	/** 支付宝回调 ， 卖家支付宝账号*/
	private String alipaySellerEmail;
	/** 支付宝回调 ， 卖家支付宝用户号*/
	private String alipaySellerId;
	/** 支付宝回调 ， 签名*/
	private String alipaySign;
	/** 支付宝回调 ，签名方式 */
	private String alipaySignType;
	/** 支付宝回调 ，商品名称 */
	private String alipaySubject;
	/** 支付宝回调 ，交易金额 */
	private String alipayTotalFee;
	public enum AlipayTradeStatus {
		/** 交易创建，等待买家付款。 */
		WAIT_BUYER_PAY,
		/**
		 * <p>
		 * 在指定 时间段内未支付时关闭的交易；
		 * <p>
		 * 在交易完 成全额退款成功时关闭的交易；
		 */
		TRADE_CLOSED,
		/** 交易成功，且可对该交易做操作，如：多级分润、退款等。 */
		TRADE_SUCCESS,
		/** 交易成功且结束，即不可再做任何操作。 */
		TRADE_FINISHED
	}

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public String getAlipayParams() {
		return alipayParams;
	}

	public void setAlipayParams(String alipayParams) {
		this.alipayParams = alipayParams;
	}

	public String getAlipayOutTradeNo() {
		return alipayOutTradeNo;
	}

	public void setAlipayOutTradeNo(String alipayOutTradeNo) {
		this.alipayOutTradeNo = alipayOutTradeNo;
	}

	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public String getAlipayTradeStatus() {
		return alipayTradeStatus;
	}

	public void setAlipayTradeStatus(String alipayTradeStatus) {
		this.alipayTradeStatus = alipayTradeStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getAlipayBody() {
		return alipayBody;
	}

	public void setAlipayBody(String alipayBody) {
		this.alipayBody = alipayBody;
	}

	public String getAlipayBuyerEmail() {
		return alipayBuyerEmail;
	}

	public void setAlipayBuyerEmail(String alipayBuyerEmail) {
		this.alipayBuyerEmail = alipayBuyerEmail;
	}

	public String getAlipayBuyerId() {
		return alipayBuyerId;
	}

	public void setAlipayBuyerId(String alipayBuyerId) {
		this.alipayBuyerId = alipayBuyerId;
	}

	public String getAlipayDiscount() {
		return alipayDiscount;
	}

	public void setAlipayDiscount(String alipayDiscount) {
		this.alipayDiscount = alipayDiscount;
	}

	public String getAlipayGmtCreate() {
		return alipayGmtCreate;
	}

	public void setAlipayGmtCreate(String alipayGmtCreate) {
		this.alipayGmtCreate = alipayGmtCreate;
	}

	public String getAlipayGmtPayment() {
		return alipayGmtPayment;
	}

	public void setAlipayGmtPayment(String alipayGmtPayment) {
		this.alipayGmtPayment = alipayGmtPayment;
	}

	public String getAlipayIsTotalFeeAdjust() {
		return alipayIsTotalFeeAdjust;
	}

	public void setAlipayIsTotalFeeAdjust(String alipayIsTotalFeeAdjust) {
		this.alipayIsTotalFeeAdjust = alipayIsTotalFeeAdjust;
	}

	public String getAlipayNotifyId() {
		return alipayNotifyId;
	}

	public void setAlipayNotifyId(String alipayNotifyId) {
		this.alipayNotifyId = alipayNotifyId;
	}

	public String getAlipayNotifyTime() {
		return alipayNotifyTime;
	}

	public void setAlipayNotifyTime(String alipayNotifyTime) {
		this.alipayNotifyTime = alipayNotifyTime;
	}

	public String getAlipayNotifyType() {
		return alipayNotifyType;
	}

	public void setAlipayNotifyType(String alipayNotifyType) {
		this.alipayNotifyType = alipayNotifyType;
	}

	public String getAlipayUseCoupon() {
		return alipayUseCoupon;
	}

	public void setAlipayUseCoupon(String alipayUseCoupon) {
		this.alipayUseCoupon = alipayUseCoupon;
	}

	public String getAlipayPaymentType() {
		return alipayPaymentType;
	}

	public void setAlipayPaymentType(String alipayPaymentType) {
		this.alipayPaymentType = alipayPaymentType;
	}

	public String getAlipayPrice() {
		return alipayPrice;
	}

	public void setAlipayPrice(String alipayPrice) {
		this.alipayPrice = alipayPrice;
	}

	public String getAlipayQuantity() {
		return alipayQuantity;
	}

	public void setAlipayQuantity(String alipayQuantity) {
		this.alipayQuantity = alipayQuantity;
	}

	public String getAlipaySellerEmail() {
		return alipaySellerEmail;
	}

	public void setAlipaySellerEmail(String alipaySellerEmail) {
		this.alipaySellerEmail = alipaySellerEmail;
	}

	public String getAlipaySellerId() {
		return alipaySellerId;
	}

	public void setAlipaySellerId(String alipaySellerId) {
		this.alipaySellerId = alipaySellerId;
	}

	public String getAlipaySign() {
		return alipaySign;
	}

	public void setAlipaySign(String alipaySign) {
		this.alipaySign = alipaySign;
	}

	public String getAlipaySignType() {
		return alipaySignType;
	}

	public void setAlipaySignType(String alipaySignType) {
		this.alipaySignType = alipaySignType;
	}

	public String getAlipaySubject() {
		return alipaySubject;
	}

	public void setAlipaySubject(String alipaySubject) {
		this.alipaySubject = alipaySubject;
	}

	public String getAlipayTotalFee() {
		return alipayTotalFee;
	}

	public void setAlipayTotalFee(String alipayTotalFee) {
		this.alipayTotalFee = alipayTotalFee;
	}
	
}
