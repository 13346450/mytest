package com.dnake.domain.business;

/**
 * 订单详情类 BizOrderDetails <br/>
 * 2014年10月21日 上午9:57:01 <br/>
 * 
 * @author chen qige
 * @version
 */
public class BizOrderDetails {
	private Long idKey;
	private Long orderId;
	private Long goodsId;
	private Integer goodsQuantity;
	private Double goodsUnitPrice;
	private String goodsName;
	private Double goodsCost;// 该商品总花费
	private String simages;// 手机端商品小图
	private String showSimages;// 展示用商品小图
	private Double goodsDiscountPrice;//商品现价
	
	private Integer isReply;	//是否回复+
	private Integer goodStatus;	//商品状态 6活动商品上架
	

	public String getShowSimages() {
		return showSimages;
	}

	public void setShowSimages(String showSimages) {
		this.showSimages = showSimages;
	}

	public String getSimages() {
		return simages;
	}

	public void setSimages(String simages) {
		this.simages = simages;
	}

	public Double getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(Double goodsCost) {
		this.goodsCost = goodsCost;
	}

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public Double getGoodsUnitPrice() {
		return goodsUnitPrice;
	}

	public void setGoodsUnitPrice(Double goodsUnitPrice) {
		this.goodsUnitPrice = goodsUnitPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}

	public void setGoodsDiscountPrice(Double goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}

	public Integer getIsReply()
	{
		return isReply;
	}

	public void setIsReply(Integer isReply)
	{
		this.isReply = isReply;
	}

	public Integer getGoodStatus()
	{
		return goodStatus;
	}

	public void setGoodStatus(Integer goodStatus)
	{
		this.goodStatus = goodStatus;
	}

}
