package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 企业荣誉显示类
 * 
 * @author suyonghe
 * @date 创建时间：2015年6月4日 下午5:06:07
 * @version 1.0
 */
public class SysAboutUSHonorVO {
	
	private Long idKey;
	private Date honorDate; // 证书获得日期，如：2015年6月
	private String honorName; // 证书名称
	private String honorBigImageUrl; // 证书图片地址（大图）
	private String honorSmallImageUrl; // 证书图片地址（小图）
	private String awardOrg; // 颁发机构
	private String remark; // 备注

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getHonorDate() {
		return honorDate;
	}

	public void setHonorDate(Date honorDate) {
		this.honorDate = honorDate;
	}

	public String getHonorName() {
		return honorName;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public String getHonorBigImageUrl() {
		return honorBigImageUrl;
	}

	public void setHonorBigImageUrl(String honorBigImageUrl) {
		this.honorBigImageUrl = honorBigImageUrl;
	}

	public String getHonorSmallImageUrl() {
		return honorSmallImageUrl;
	}

	public void setHonorSmallImageUrl(String honorSmallImageUrl) {
		this.honorSmallImageUrl = honorSmallImageUrl;
	}

	public String getAwardOrg() {
		return awardOrg;
	}

	public void setAwardOrg(String awardOrg) {
		this.awardOrg = awardOrg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
