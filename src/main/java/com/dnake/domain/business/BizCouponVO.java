package com.dnake.domain.business;

import java.util.Date;

public class BizCouponVO {
	//
	private Long idKey;
	//
	private Long orderDetailsId;
	//
	private String verification;
	//
	private Integer status;
	//
	private Date chgDt;
	private Date useDt;// 使用时间
	private String remark;// 备注
	private String shopName;
	private String goodsName;
	private Long shopId;
	private Long goodsId;
	private Long salesId;
	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Long getOrderDetailsId() {
		return orderDetailsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public String getVerification() {
		return verification;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setVerification(String verification) {
		this.verification = verification;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Date getChgDt() {
		return chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public Date getUseDt() {
		return useDt;
	}

	public void setUseDt(Date useDt) {
		this.useDt = useDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}
	
	
}
