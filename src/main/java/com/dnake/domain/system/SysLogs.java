package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

public class SysLogs {
	private Long idKey; 		// 主键
	private String funcMenuNm;	//功能菜单名
	private String funcOperNm;	//操作功能(Create、Retrieve、Update、Delete、Others)
	private Long operId;		//操作人id
	private String operNm;		//操作人姓名
	private String operRemark;	//操作说明(包括id、名称)
	private Date operDt;		//操作时间
	
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getFuncMenuNm() {
		return funcMenuNm;
	}
	public void setFuncMenuNm(String funcMenuNm) {
		this.funcMenuNm = funcMenuNm;
	}
	public String getFuncOperNm() {
		return funcOperNm;
	}
	public void setFuncOperNm(String funcOperNm) {
		this.funcOperNm = funcOperNm;
	}
	public Long getOperId() {
		return operId;
	}
	public void setOperId(Long operId) {
		this.operId = operId;
	}
	public String getOperNm() {
		return operNm;
	}
	public void setOperNm(String operNm) {
		this.operNm = operNm;
	}
	public String getOperRemark() {
		return operRemark;
	}
	public void setOperRemark(String operRemark) {
		this.operRemark = operRemark;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getOperDt() {
		return operDt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	public void setOperDt(Date operDt) {
		this.operDt = operDt;
	}
}
