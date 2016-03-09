package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
import com.dnake.common.JsonDateTimeSerializer;

/**
 * 
 * ClassName: BstDevice <br/>
 * date: 2014年2月19日 下午4:56:34 <br/>
 *
 * @author ts
 * @version
 */
public class BizAdvertVO {
	private Long 	idKey;			//主键
	private String advVersion;	//版本号
	private String imagesUrl;	//图片地址(多个用,分隔)
	private String linksUrl;     //链接地址，和图片对应
	private Long  makerId;		//制单人id
	private Date  makerDt;		//制单时间
	private Long  auditId;		//审核人id
	private Date  auditDt;		//审核时间
	private Long  publishId;	//发布人id
	private Date  publishDt;	//发布时间
	private Integer advStatus; //广告状态
	
	private String makerNm;     //制单人名
	private String auditNm;       //审核人名
	private String publishNm;    //发布人名
	private String advStatusNm; //状态名
	
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getAdvVersion() {
		return advVersion;
	}
	public void setAdvVersion(String advVersion) {
		this.advVersion = advVersion;
	}
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public String getLinksUrl() {
		return linksUrl;
	}
	public void setLinksUrl(String linksUrl) {
		this.linksUrl = linksUrl;
	}
	public Long getMakerId() {
		return makerId;
	}
	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getMakerDt() {
		return makerDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setMakerDt(Date makerDt) {
		this.makerDt = makerDt;
	}
	public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getAuditDt() {
		return auditDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setAuditDt(Date auditDt) {
		this.auditDt = auditDt;
	}
	public Long getPublishId() {
		return publishId;
	}
	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPublishDt() {
		return publishDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPublishDt(Date publishDt) {
		this.publishDt = publishDt;
	}
	public Integer getAdvStatus() {
		return advStatus;
	}
	public void setAdvStatus(Integer advStatus) {
		this.advStatus = advStatus;
	}
	public String getMakerNm() {
		return makerNm;
	}
	public void setMakerNm(String makerNm) {
		this.makerNm = makerNm;
	}
	public String getAuditNm() {
		return auditNm;
	}
	public void setAuditNm(String auditNm) {
		this.auditNm = auditNm;
	}
	public String getPublishNm() {
		return publishNm;
	}
	public void setPublishNm(String publishNm) {
		this.publishNm = publishNm;
	}
	public String getAdvStatusNm() {
		return advStatusNm;
	}
	public void setAdvStatusNm(String advStatusNm) {
		this.advStatusNm = advStatusNm;
	}
	
}
