package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

/**
 * 订单显示类 BizOrderVO <br/>
 * 2014年9月16日 上午11:23:17 <br/>
 * 
 * @author chen qige
 * @version
 */
public class BizOrderVO {
	private Long idKey;
	private String orderNumber;
	private Long userId;
	private Long salesId;
	private Integer paymentType;
	/**
	 * 订单状态，
	 *	0,"等待商家接单",
	 *	1,"买家取消订单",
	 *	2,"商家已接单",
	 *	3,"商家已拒绝该单",
	 *	4,"正在配送中",
	 *	5,"交易完成",
	 *	6,"买家未付款",
	 *	7,"买家已付款",
	 *	8,"货到付款",
	 *	9，退款中
	 *	10，交易关闭
	 *  11，退款失败
	 *  12，退款成功
	 * 
	 */
	private Integer orderStatus;
	private Double orderCost;
	private Date orderDt;
	private String orderTelephone;
	private String orderAddr;
	private Long shopId;
	private String userName;
	private String salesName;
	private String paymentTypeName;// 付款方式
	private String orderStatusName;
	private String receiver;// 接收人
	private Date deliveryTime;// 预约收货时间
	private String message;// 买家留言
	private Double shopGift;// 店铺优惠金额
	private Double otherGift;// 其他优惠金额
	private Double shopGet;// 商店获得现金
	private Double userCost;// 用户支付金额
	private String shopName;// 店铺名
	private Integer salesCount;	//销量
	private String salesTelephone;
	
	private Integer orderComment;	//订单是否未被评价
	private BizRefund bizRefund;	//退款详情表

	
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getOrderTelephone() {
		return orderTelephone;
	}

	public void setOrderTelephone(String orderTelephone) {
		this.orderTelephone = orderTelephone;
	}

	public String getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getOrderDt() {
		return orderDt;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getShopGift() {
		return shopGift;
	}

	public void setShopGift(Double shopGift) {
		this.shopGift = shopGift;
	}

	public Double getOtherGift() {
		return otherGift;
	}

	public void setOtherGift(Double otherGift) {
		this.otherGift = otherGift;
	}

	public Double getShopGet() {
		return shopGet;
	}

	public void setShopGet(Double shopGet) {
		this.shopGet = shopGet;
	}

	public Double getUserCost() {
		return userCost;
	}

	public void setUserCost(Double userCost) {
		this.userCost = userCost;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getSalesCount()
	{
		return salesCount;
	}

	public void setSalesCount(Integer salesCount)
	{
		this.salesCount = salesCount;
	}

	public String getSalesTelephone() {
		return salesTelephone;
	}

	public void setSalesTelephone(String salesTelephone) {
		this.salesTelephone = salesTelephone;
	}

	public Integer getOrderComment()
	{
		return orderComment;
	}

	public void setOrderComment(Integer orderComment)
	{
		this.orderComment = orderComment;
	}

}
