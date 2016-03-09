package com.dnake.domain.system;

import java.util.Date;

/**
 * 新闻动态实体类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午4:48:29
 * @version 1.0
 */
public class SysNewsDynamic {

	private Long idKey;
	private String newsTitle; // 新闻主标题
	private String newsContent; // 新闻内容（包含图片，用FCK）
	private Integer newsType = 0; // 新闻类型
	private Date publishDate; // 发布日期
	private String imageUrl; // 主图片

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
