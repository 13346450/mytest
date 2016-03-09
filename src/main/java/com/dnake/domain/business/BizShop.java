package com.dnake.domain.business;

import java.util.Date;

public class BizShop {
	//
	private Long idKey;
	//
	private Long userId;
	//
	private String name;
	//
	private String telephone;
	//
	private String addr;
	//
	private Long communityId;
	//
	private Date chgDt;
	// 是否默认的店
	private Integer isDefault;
	private Integer status;// 店铺状态 1.未审核 2.营业 3.停业 4.被强制关闭

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public Date getChgDt() {
		return chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	/**
	 * 是否默认的店
	 * 
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 是否默认的店
	 * 
	 * @author 云服务工具
	 * @date 2015-03-27 15:48:35
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "BizShop [idKey=" + idKey + ", userId=" + userId + ", name="
				+ name + ", telephone=" + telephone + ", addr=" + addr
				+ ", communityId=" + communityId + ", chgDt=" + chgDt
				+ ", isDefault=" + isDefault + "]";
	}

	/**
	 * 店铺状态 1.营业 2.未审核 3.停业 4.被强制关闭
	 * 
	 * @return
	 * @author cqg 2015年5月19日
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 店铺状态 1.营业 2.未审核 3.停业 4.被强制关闭
	 * 
	 * @param status
	 * @author cqg 2015年5月19日
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
