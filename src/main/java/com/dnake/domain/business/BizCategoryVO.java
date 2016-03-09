package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
/**
 * 目录实体类
*  BizCategory <br/> 
*  2014年10月21日 上午9:55:16 <br/> 
* @author chen qige 
* @version
 */
public class BizCategoryVO {
	private Long 	idKey;			//主键
	private String categoryName;	//类别名
	private Integer lastMark;	//是否最后节点
	private Long parentId; //父节点id
	private Integer orderCd;  //排序号
	private Integer  isUse;	//是否使用
	private Date  chgDt;	//修改时间
	private String remark;     //备注
	private String iconUrl;//类别图标
	private String simages;//小图像
	private String limages;//大图像
	private String showSimages;//展示大屏幕用小图
	private String showLimages;//展示大屏幕用大图
	private Long communityId;//小区id
	private String communityName;//小区名
	
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
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getChgDt() {
		return chgDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getLastMark() {
		return lastMark;
	}
	public void setLastMark(Integer lastMark) {
		this.lastMark = lastMark;
	}
	public Integer getOrderCd() {
		return orderCd;
	}
	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

}
