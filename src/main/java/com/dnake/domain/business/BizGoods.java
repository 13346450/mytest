package com.dnake.domain.business;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

/**
 * 商品实体类 BizGoods <br/>
 * 2014年10月21日 上午9:54:32 <br/>
 * 
 * @author chen qige
 * @version
 */
public class BizGoods {
	private Long idKey;
	private String goodsName;
	private Long goodsTypeId;
	private Long goodsOrder;
	private String goodsAddr;
	private String telephone;
	private Double unitPrice;
	private Integer quantity;
	private Long salesId; // 商户id
	private Long shopId;//商铺id
	private Integer goodsStatus;
	private String goodsNote;// 商品描述
	private String simages;
	private String limages;
	private Date uploadDt;
	private String showSimages;// 展示大屏幕用小图
	private String showLimages;// 展示大屏幕用大图
	private Double discountPrice;//打折后价格
	private Long communityId;	//小区ID
	private Timestamp autoPutOnTime;//自动上架时间
	
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShowSimages() {
		return showSimages;
	}

	public void setShowSimages(String showSimages) {
		this.showSimages = showSimages;
	}

	public String getShowLimages() {
		return showLimages;
	}

	public void setShowLimages(String showLimages) {
		this.showLimages = showLimages;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public Long getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(Long goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public String getGoodsAddr() {
		return goodsAddr;
	}

	public void setGoodsAddr(String goodsAddr) {
		this.goodsAddr = goodsAddr;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsNote() {
		return goodsNote;
	}

	public void setGoodsNote(String goodsNote) {
		this.goodsNote = goodsNote;
	}

	public String getSimages() {
		return simages;
	}

	public void setSimages(String simages) {
		this.simages = simages;
	}

	public String getLimages() {
		return limages;
	}

	public void setLimages(String limages) {
		this.limages = limages;
	}

	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getUploadDt() {
		return uploadDt;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setUploadDt(Date uploadDt) {
		this.uploadDt = uploadDt;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Long getCommunityId()
	{
		return communityId;
	}

	public void setCommunityId(Long communityId)
	{
		this.communityId = communityId;
	}
	
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getAutoPutOnTime() {
		return autoPutOnTime;
	}

	public void setAutoPutOnTime(Timestamp autoPutOnTime) {
		this.autoPutOnTime = autoPutOnTime;
	}

	
}
