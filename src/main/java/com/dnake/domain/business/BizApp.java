package com.dnake.domain.business;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * 
 * ClassName: BizApp <br/>
 * date: 2014年2月19日 下午4:56:34 <br/>
 *
 * @author ts
 * @version
 */
public class BizApp {
	private Long 	idKey;			//主键
	private String name; //显示给用户看的名称
	private String appVersion;	//版本号
	private String downloadUrl;	//下载地址
	private Long  makerId;		//制单人id
	private Date  makerDt;		//制单时间
	private Long  auditId;		//审核人id
	private Date  auditDt;		//审核时间
	private Long  publishId;	//发布人id
	private Date  publishDt;	//发布时间
	private Integer appStatus; //app状态
	private String improvement; //更新内容
	private String type;//app类型：商户版，用户版
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImprovement() {
		return improvement;
	}
	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
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
	public Integer getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
