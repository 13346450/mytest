package com.dnake.utils;

import java.util.Date;

/**
 *说明：
 *@创建：作者:Administrator		创建时间：Apr 29, 2009
 *@修改历史：
 *		[序号](Administrator	Apr 29, 2009)<修改说明>
 */
@SuppressWarnings("serial")
public class SessionBean implements java.io.Serializable {
	
	private Long   userId;		//用户id
	private String userCd;		//用户编码(登录名)
	private String userName;	//用户名称
	private Long   deptId;		//小区id
	private String deptName;	//部门名称
	private String deptCode;	//部门代码
	private Long   roleId;	    //角色id
	private String roleName;	//角色名
	private String roleMaker;   //角色编号
	private String roleType;	//角色类型
	private String userDate;	//登录日期
	private Date   loginDate;   //登录日期
	private String loginNm;     //登录名
	private Integer isModify;//判断是否修改过密码
	private String addr;//用户地址
	private String telephone;//用户电话
	private String popedomCommunityIds;//用户权限的小区ids，普通商户和住户没有此选项
	private String bindTel;//绑定的手机号码
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 小区id
	 * @return
	 *@author cqg 
	 *2015年4月8日
	 */
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getRoleMaker() {
		return roleMaker;
	}
	public void setRoleMaker(String roleMaker) {
		this.roleMaker = roleMaker;
	}
	public String getLoginNm() {
		return loginNm;
	}
	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}
	public Integer getIsModify() {
		return this.isModify;
	}

	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}
	/**
	 * 获取用户权限的小区ids，普通商户和住户没有此选项
	 * @return
	 *@author cqg 
	 *2015年7月13日
	 */
	public String getPopedomCommunityIds() {
		return popedomCommunityIds;
	}
	/**
	 * 设置用户权限的小区ids，普通商户和住户没有此选项
	 * 
	 *@author cqg 
	 *2015年7月13日
	 */
	public void setPopedomCommunityIds(String popedomCommunityIds) {
		this.popedomCommunityIds = popedomCommunityIds;
	}
	public String getBindTel() {
		return bindTel;
	}
	public void setBindTel(String bindTel) {
		this.bindTel = bindTel;
	}
	 
}

