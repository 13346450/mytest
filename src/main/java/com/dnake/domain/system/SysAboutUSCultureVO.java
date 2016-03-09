package com.dnake.domain.system;

/**
 * 企业文化显示类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:40:34
 * @version 1.0
 */
public class SysAboutUSCultureVO {

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
	
	public String getCultureTitle() {
		return cultureTitle;
	}
	
	public void setCultureTitle(String cultureTitle) {
		this.cultureTitle = cultureTitle;
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
}
