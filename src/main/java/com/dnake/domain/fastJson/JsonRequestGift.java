package com.dnake.domain.fastJson;

import java.util.Date;

/**
 * fastJson反编译包
 * 
 * @author Administrator
 *
 */
public class JsonRequestGift {
	private Integer total;
	private Integer giftType;// 优惠类型
	private Date startDt;
	private Date endDt;
	private Double couponMinMoney;//抵用券使用的最小金额
	private Double couponCategory;//抵用目录
	private Double value;
	private Integer goodsId;
	private Integer perMaxHave;
	private Integer oneMaxUse;
	private Integer useWithOthers;
	private String remarks;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getGiftType() {
		return giftType;
	}

	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Double getCouponMinMoney() {
		return couponMinMoney;
	}

	public void setCouponMinMoney(Double couponMinMoney) {
		this.couponMinMoney = couponMinMoney;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getPerMaxHave() {
		return perMaxHave;
	}

	public void setPerMaxHave(Integer perMaxHave) {
		this.perMaxHave = perMaxHave;
	}

	public Integer getUseWithOthers() {
		return useWithOthers;
	}

	public void setUseWithOthers(Integer userWithOthers) {
		this.useWithOthers = userWithOthers;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOneMaxUse() {
		return oneMaxUse;
	}

	public void setOneMaxUse(Integer oneMaxUse) {
		this.oneMaxUse = oneMaxUse;
	}

	public Double getCouponCategory() {
		return couponCategory;
	}

	public void setCouponCategory(Double couponCategory) {
		this.couponCategory = couponCategory;
	}

}
