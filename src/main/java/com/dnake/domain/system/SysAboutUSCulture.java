package com.dnake.domain.system;

/**
 * 企业文化实体类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:39:29
 * @version 1.0
 */
public class SysAboutUSCulture {

	private Long idKey;
	private String cultureTitle; // 主标题
	private String cultureSubtitle; // 副标题
	private String cultureContent; // 内容（包含图片，用FCK）

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getCultureSubtitle() {
		return cultureSubtitle;
	}

	public void setCultureSubtitle(String cultureSubtitle) {
		this.cultureSubtitle = cultureSubtitle;
	}

	public String getCultureContent() {
		return cultureContent;
	}

	public void setCultureContent(String cultureContent) {
		this.cultureContent = cultureContent;
	}

	public String getCultureTitle() {
		return cultureTitle;
	}

	public void setCultureTitle(String cultureTitle) {
		this.cultureTitle = cultureTitle;
	}
}
