package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;

/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 7532018307929787073L;
	// Fields
	private Long idKey; // 主键
	private String userCd; // 用户编码
	private String cdNm; // 用户名称
	private Integer orderCd; // 排序码
	private Integer useMark; // 启用标志 1-启用 0-停用
	private String gender; // 性别 M-男 F-女

	@NotEmpty(message = "密码不能为空")
	private String userPwd; // 密码

	@NotEmpty(message = "所属部门不能为空")
	private Long deptId; // 所属部门id

	@NotEmpty(message = "所属角色不能为空")
	private Long roleId; // 所属角色
	private String remark; // 备注
	private Long directDept;// 下属部门
	private Long userStatus;// 在职状态

	private String building; // 楼栋号
	private String unit;// 单元号
	private String floor;// 楼层号
	private String room;// 房号
	private String telephone;// 手机号
	private String addr;

	@NotEmpty(message = "登录名不能为空")
	private String loginNm; // 登录名
	private Date chgDt; // 最后修改日期
	private String roleName;
	private String roleType;
	private String deptName;
	private Integer isModify;// 是否修改过密码
	private String bindTel; // 绑定手机号
	private Long regionId; // 区域ID
	private String popedomCommunityIds;// 该专户所能控制的ids

	/*
	 * 智慧社区
	 */
	private String houseType; // 户型：单间、一房一厅、两房一厅...

	public Long getIdKey() {
		return this.idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getUserCd() {
		return this.userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public Integer getOrderCd() {
		return this.orderCd;
	}

	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}

	public Integer getUseMark() {
		return this.useMark;
	}

	public void setUseMark(Integer useMark) {
		this.useMark = useMark;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * 得到用户所在的小区id
	 * 
	 * @title getDeptId
	 * @author chen qige
	 * @date 2015年3月26日
	 * @return
	 */
	public Long getDeptId() {
		return this.deptId;
	}

	/**
	 * 设置用户所在的小区id
	 * 
	 * @title setDeptId
	 * @author chen qige
	 * @date 2015年3月26日
	 * @param deptId
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDirectDept() {
		return this.directDept;
	}

	public void setDirectDept(Long directDept) {
		this.directDept = directDept;
	}

	public Long getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginNm() {
		return loginNm;
	}

	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getChgDt() {
		return chgDt;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getIsModify() {
		return this.isModify;
	}

	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void hehe() {
		addr = null;
	}

	public String getBindTel() {
		return bindTel;
	}

	public void setBindTel(String bindTel) {
		this.bindTel = bindTel;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getPopedomCommunityIds() {
		return popedomCommunityIds;
	}

	public void setPopedomCommunityIds(String popedomCommunityIds) {
		this.popedomCommunityIds = popedomCommunityIds;
	}

	

}