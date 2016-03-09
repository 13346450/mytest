package com.dnake.domain.business;

import java.util.Date;

public class BizUserGiftVO {
	//
	private Long idKey;
	// 促销活动id
	private Long userId;
	// 优惠数量
	private Long shopId;
	// 优惠券生效时间
	private Long giftId;
	// 优惠券结束时间
	private Integer status;
	// 优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
	private Long promotionId;
	// 对应type的值
	private Date startDt;
	//
	private Date endDt;
	//
	private Date chgDt;
	//
	private String type;
	//
	private Double value;
	//
	private Long goodsId;
	// 10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
	private Long couponCategory;
	// 优惠券使用备注
	private Double couponMinMoney;
	//
	private Integer oneMaxUse;
	// 用户id
	private String useWithOthers;
	// 商铺id
	private String remarks;
	private String shopName;//店铺名
	private String categoryName;//商品目录名
	private String name;//活动名

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 * 促销活动id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 促销活动id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 优惠数量
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * 优惠数量
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * 优惠券生效时间
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getGiftId() {
		return giftId;
	}

	/**
	 * 优惠券生效时间
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	/**
	 * 优惠券结束时间
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 优惠券结束时间
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 优惠类型，1.红包，2.兑换券(兑换物品) 3.满立减；4满立送；5.满返红包 6满返兑换券
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	/**
	 * 对应type的值
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Date getStartDt() {
		return startDt;
	}

	/**
	 * 对应type的值
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Date getEndDt() {
		return endDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Date getChgDt() {
		return chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Double getValue() {
		return value;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getGoodsId() {
		return goodsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Long getCouponCategory() {
		return couponCategory;
	}

	/**
	 * 10.与任何活动都可以共用(优先级最高)；20.与本次(活动id一致)共用；30.不与任何活动共用；
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setCouponCategory(Long couponCategory) {
		this.couponCategory = couponCategory;
	}

	/**
	 * 优惠券使用备注
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Double getCouponMinMoney() {
		return couponMinMoney;
	}

	/**
	 * 优惠券使用备注
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setCouponMinMoney(Double couponMinMoney) {
		this.couponMinMoney = couponMinMoney;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public Integer getOneMaxUse() {
		return oneMaxUse;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setOneMaxUse(Integer oneMaxUse) {
		this.oneMaxUse = oneMaxUse;
	}

	/**
	 * 用户id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public String getuseWithOthers() {
		return useWithOthers;
	}

	/**
	 * 用户id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setuseWithOthers(String useWithOthers) {
		this.useWithOthers = useWithOthers;
	}

	/**
	 * 商铺id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 商铺id
	 * 
	 * @author 云服务工具
	 * @date 2015-04-02 09:00:14
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
