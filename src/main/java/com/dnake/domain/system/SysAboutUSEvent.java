package com.dnake.domain.system;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 大事记实体类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:42:57
 * @version 1.0
 */
public class SysAboutUSEvent {

	private Long idKey;
	private String eventTitle; // 主标题
	private String eventSubtitle; // 副标题
	private String eventContent; // 内容（包含图片，用FCK）
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date happenTime;//事件发生事件

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventSubtitle() {
		return eventSubtitle;
	}

	public void setEventSubtitle(String eventSubtitle) {
		this.eventSubtitle = eventSubtitle;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	
}
