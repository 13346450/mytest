package com.dnake.domain.system;

import java.util.Date;

public class SysUserPopedom {
	//
	private Long idKey;
	//
	private Long userId;
	// 小区权限
	private Long deptId;
	// 类型
	private Integer type;

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-03 09:31:04
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-03 09:31:04
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-03 09:31:04
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-07-03 09:31:04
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
