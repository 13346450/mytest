package com.dnake.domain.business;

import java.sql.Timestamp;
import java.util.Date;

public class BizPromotionVO {
	//
	private Long idKey;
	// 促销名称
	private String name;
	// 活动状态
	private Integer status;
	// 活动开始时间
	private Timestamp startDt;
	// 活动结束时间
	private Timestamp endDt;
	// 活动备注
	private String remarks;
	private Date chgDt;
	private Integer type;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 * 促销名称
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public String getName() {
		return name;
	}

	/**
	 * 促销名称
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 活动状态
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 活动状态
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 活动开始时间
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public Timestamp getStartDt() {
		return startDt;
	}

	/**
	 * 活动开始时间
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public void setStartDt(Timestamp startDt) {
		this.startDt = startDt;
	}

	/**
	 * 活动结束时间
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public Timestamp getEndDt() {
		return endDt;
	}

	/**
	 * 活动结束时间
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public void setEndDt(Timestamp endDt) {
		this.endDt = endDt;
	}

	/**
	 * 活动备注
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 活动备注
	 * 
	 * @author 云服务工具
	 * @date 2015-03-26 17:31:36
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
