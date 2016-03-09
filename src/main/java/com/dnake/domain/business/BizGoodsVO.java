package com.dnake.domain.business;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;

/**
 * 
 * BizGoodsVO <br/>
 * 2014年9月11日 下午3:06:35 <br/>
 * 
 * @author chen qige
 * @version
 */
public class BizGoodsVO {
	private Long idKey;
	private String goodsName;
	private Long goodsTypeId;
	private Long goodsOrder;
	private String goodsAddr;
	private String telephone;
	private Double unitPrice;
	private Integer quantity;
	private Long salesId;
	private Long shopId;
	private Integer goodsStatus;
	private Long communityId;
	private String goodsNote;
	private String simages;
	private String limages;
	private Date uploadDt;
	private String showSimages;//展示大屏幕用小图
	private String showLimages;//展示大屏幕用大图
	private Double discountPrice;//打折后价格
	private Integer salesCount;	//销量
	
	private String salesName;
	private String goodsType;//商品类型名称
	private String goodsStatusName;//商品状态名称，
	private List<BizGoodsImage> listGoodsImage;	//商品的大图列表
	private float goodsAveScore;	//商品平均分
	private Integer commentCount;	//评论总条数
	private String shopName;		//商铺信息		
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

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getGoodsStatusName() {
		return goodsStatusName;
	}

	public void setGoodsStatusName(String goodsStatusName) {
		this.goodsStatusName = goodsStatusName;
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

	public void setGoodsName(String goods_name) {
		this.goodsName = goods_name;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsType) {
		this.goodsTypeId = goodsType;
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

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public List<BizGoodsImage> getListGoodsImage()
	{
		return listGoodsImage;
	}

	public void setListGoodsImage(List<BizGoodsImage> listGoodsImage)
	{
		this.listGoodsImage = listGoodsImage;
	}

	public Integer getSalesCount()
	{
		return salesCount;
	}

	public void setSalesCount(Integer salesCount)
	{
		this.salesCount = salesCount;
	}

	public float getGoodsAveScore()
	{
		return goodsAveScore;
	}

	public void setGoodsAveScore(float goodsAveScore)
	{
		this.goodsAveScore = goodsAveScore;
	}

	public Integer getCommentCount()
	{
		return commentCount;
	}

	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}

	public String getShopName()
	{
		return shopName;
	}

	public void setShopName(String shopName)
	{
		this.shopName = shopName;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Timestamp getAutoPutOnTime() {
		return autoPutOnTime;
	}

	public void setAutoPutOnTime(Timestamp autoPutOnTime) {
		this.autoPutOnTime = autoPutOnTime;
	}
}
