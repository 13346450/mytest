package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dnake.common.JsonDateSerializer;

/**
 * 大事记显示类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:44:25
 * @version 1.0
 */
public class SysAboutUSEventVO {

	private Long idKey;
	private String eventTitle; // 主标题
	private String eventSubtitle; // 副标题
	private String eventContent; // 内容（包含图片，用FCK）
	private Date happenTime;//更新事件

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
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}
	public String toString(){
		return JSON.toJSONString(this);
	}
	
}
