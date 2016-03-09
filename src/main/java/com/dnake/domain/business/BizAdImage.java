package com.dnake.domain.business;

import java.util.Date;

public class BizAdImage {
	//
	private Long idKey;
	//
	private String imageUrl;
	// 图片排序
	private Long order;
	// 字典值，图片类型
	private Long dictKey;
	// 图片大小，例如200_200
	private String imageSize;
	//
	private Date chgDt;
	// 状态：1启用，2关闭
	private Integer status;
	// 广告对应的类别目录
	private Long categoryId;
	// 广告对应的小区ID
	private Long communityId;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 图片排序
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public Long getOrder() {
		return order;
	}

	/**
	 * 图片排序
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setOrder(Long order) {
		this.order = order;
	}

	/**
	 * 字典值，图片类型
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public Long getDictKey() {
		return dictKey;
	}

	/**
	 * 字典值，图片类型
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setDictKey(Long dictKey) {
		this.dictKey = dictKey;
	}

	/**
	 * 图片大小，例如200_200
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public String getImageSize() {
		return imageSize;
	}

	/**
	 * 图片大小，例如200_200
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setImageSize(String size) {
		this.imageSize = size;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public Date getChgDt() {
		return chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	/**
	 * 状态：1启用，2关闭
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态：1启用，2关闭
	 * 
	 * @author 云服务工具
	 * @date 2015-05-08 15:00:27
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}
}
