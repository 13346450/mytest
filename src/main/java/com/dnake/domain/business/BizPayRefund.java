package com.dnake.domain.business;

import java.util.Date;

/**
 * 支付退款信息类
 * 
 * @author 蔡小龙
 */
public class BizPayRefund {
	/** 主键 */
	private Long idKey;
	/** 收到退款时间 */
	private Date receiveDate;
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
	/** 支付宝回调，批次号 */
	private String alipayBatchNo;
	/** 支付宝回调 ，批量退款数据中转账成功的笔数 */
	private Long alipaySuccessNum;
	/** 支付宝回调 ，批量退款数据中的详细信息 */
	private String alipayResultDetails;
	/** 支付宝回调 ，通知时间 */
	private String alipayNotifyTime;
	/** 支付宝回调 ，通知类型 */
	private String alipayNotifyType;
	/** 支付宝回调 ，通知校验ID */
	private String alipayNotifyId;
	/** 支付宝回调 ， 签名方式*/
	private String alipaySignType;
	/** 支付宝回调 ，签名 */
	private String alipaySign;

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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

	public String getAlipayBatchNo() {
		return alipayBatchNo;
	}

	public void setAlipayBatchNo(String alipayBatchNo) {
		this.alipayBatchNo = alipayBatchNo;
	}

	public Long getAlipaySuccessNum() {
		return alipaySuccessNum;
	}

	public void setAlipaySuccessNum(Long alipaySuccessNum) {
		this.alipaySuccessNum = alipaySuccessNum;
	}

	public String getAlipayResultDetails() {
		return alipayResultDetails;
	}

	public void setAlipayResultDetails(String alipayResultDetails) {
		this.alipayResultDetails = alipayResultDetails;
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

	public String getAlipayNotifyId() {
		return alipayNotifyId;
	}

	public void setAlipayNotifyId(String alipayNotifyId) {
		this.alipayNotifyId = alipayNotifyId;
	}

	public String getAlipaySignType() {
		return alipaySignType;
	}

	public void setAlipaySignType(String alipaySignType) {
		this.alipaySignType = alipaySignType;
	}

	public String getAlipaySign() {
		return alipaySign;
	}

	public void setAlipaySign(String alipaySign) {
		this.alipaySign = alipaySign;
	}

}
