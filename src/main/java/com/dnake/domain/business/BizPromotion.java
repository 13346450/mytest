package com.dnake.domain.business;
import java.util.Date;
public class BizPromotion{
//
private Long idKey;
//促销名称
private String name;
//活动状态
private Integer status;
//活动开始时间
private Date startDt;
//活动结束时间
private Date endDt;
//活动备注
private String remarks;
private Date chgDt;
private Integer type;
/**
*获取
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public Long getIdKey() {
return idKey;
}

/**
*设置
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setIdKey(Long idKey) {
this.idKey = idKey;
}
/**
*获取促销名称
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public String getName() {
return name;
}

/**
*设置促销名称
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setName(String name) {
this.name = name;
}
/**
*获取活动状态
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public Integer getStatus() {
return status;
}

/**
*设置活动状态
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setStatus(Integer status) {
this.status = status;
}
/**
*获取活动开始时间
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public Date getStartDt() {
return startDt;
}

/**
*设置活动开始时间
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setStartDt(Date startDt) {
this.startDt = startDt;
}
/**
*获取活动结束时间
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public Date getEndDt() {
return endDt;
}

/**
*设置活动结束时间
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setEndDt(Date endDt) {
this.endDt = endDt;
}
/**
*获取活动备注
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public String getRemarks() {
return remarks;
}

/**
*设置活动备注
* @author 云服务工具
* @date 2015-03-26 17:27:55
*/
public void setRemarks(String remarks) {
this.remarks = remarks;
}

public Date getChgDt() {
	return chgDt;
}

public void setChgDt(Date chgDt) {
	this.chgDt = chgDt;
}

public Integer getType() {
	return type;
}

public void setType(Integer type) {
	this.type = type;
}
}
