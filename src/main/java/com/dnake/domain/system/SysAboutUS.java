package com.dnake.domain.system;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;

/**
 * 关于我们实体类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:06:07
 * @version 1.0
 */
public class SysAboutUS {

	private Long idKey;
	private String aboutUSTitle; // 主标题
	private String aboutUSSubtitle; // 副标题
	private String aboutUSContent; // 内容（包含图片，用FCK）

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getAboutUSSubtitle() {
		return aboutUSSubtitle;
	}

	public void setAboutUSSubtitle(String aboutUSSubtitle) {
		this.aboutUSSubtitle = aboutUSSubtitle;
	}

	public String getAboutUSContent() {
		return aboutUSContent;
	}

	public void setAboutUSContent(String aboutUSContent) {
		this.aboutUSContent = aboutUSContent;
	}

	public String getAboutUSTitle() {
		return aboutUSTitle;
	}

	public void setAboutUSTitle(String aboutUSTitle) {
		this.aboutUSTitle = aboutUSTitle;
	}
	
}
