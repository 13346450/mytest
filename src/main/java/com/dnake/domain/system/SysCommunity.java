package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateSerializer;
import com.dnake.common.JsonDateTimeSerializer;


/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCommunity implements java.io.Serializable {

	private static final long serialVersionUID = 7532018307929787073L;
	// Fields
	
	private Long idKey;		//主键
	@NotEmpty(message = "小区名称不能为空")
	private String communityName;	//小区名称
	private Integer orderCd;	//排序码
	private Long deptId; //所属区域Id
	private String address;  //小区地址
	private Integer proUserId;  //管理员ID
	@NotEmpty(message = "物业单位不能为空")
	private String proName;  //物业单位名称
	private String cellphone;
	private String telephone;
	private Integer useMark;	//启用标志 1-启用 0-停用
	private String remark;//备注
	private Long cityId;//小区所在的市id
	private Long provinceId;//小区所在的省id
	
	//编号ID
	private String id;
	//名称
	private String name;
	//负责人
	private String leader;
	//负责人电话
	private String leaderPhone;
	//简介
	private String summary;
	//保安室电话
	private String securityRoomPhone;
	//办公室电话
	private String officePhone;
	//小区14位代码
	private String communityCode;
	//小区编号
	private String communityNo;
	//工作时间
	private String operateTime;
	//最后更新时间
	private Date lastUpdated;
	//Logo地址
	private String logoUrl;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getLeader()
	{
		return leader;
	}
	public void setLeader(String leader)
	{
		this.leader = leader;
	}
	public String getLeaderPhone()
	{
		return leaderPhone;
	}
	public void setLeaderPhone(String leaderPhone)
	{
		this.leaderPhone = leaderPhone;
	}
	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	public String getSecurityRoomPhone()
	{
		return securityRoomPhone;
	}
	public void setSecurityRoomPhone(String securityRoomPhone)
	{
		this.securityRoomPhone = securityRoomPhone;
	}
	public String getOfficePhone()
	{
		return officePhone;
	}
	public void setOfficePhone(String officePhone)
	{
		this.officePhone = officePhone;
	}
	public String getCommunityCode()
	{
		return communityCode;
	}
	public void setCommunityCode(String communityCode)
	{
		this.communityCode = communityCode;
	}
	public String getCommunityNo()
	{
		return communityNo;
	}
	public void setCommunityNo(String communityNo)
	{
		this.communityNo = communityNo;
	}
	public String getOperateTime()
	{
		return operateTime;
	}
	public void setOperateTime(String operateTime)
	{
		this.operateTime = operateTime;
	}
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getLastUpdated()
	{
		return lastUpdated;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}
	public String getLogoUrl()
	{
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}
	public Long getIdKey() {
		return idKey;
	}
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public Integer getOrderCd() {
		return orderCd;
	}
	public void setOrderCd(Integer orderCd) {
		this.orderCd = orderCd;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getProUserId() {
		return proUserId;
	}
	public void setProUserId(Integer proUserId) {
		this.proUserId = proUserId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getUseMark() {
		return useMark;
	}
	public void setUseMark(Integer useMark) {
		this.useMark = useMark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
//	@NotEmpty(message = "密码不能为空")
//	private String userPwd;	//密码
//	
//	@NotEmpty(message = "所属部门不能为空")
//	private Long deptId;	//所属部门id
//	
//	@NotEmpty(message = "所属角色不能为空")
//	private Long roleId;	//所属角色
//	private String remark;	//备注
//	private Long directDept;//下属部门
//	private Long userStatus;//在职状态
//
//	@NotEmpty(message = "登录名不能为空")
//	private String loginNm;  //登录名
//	private Date chgDt;		 //最后修改日期
//	private String roleName;
//	private String roleType;
//	private String deptName;
//	private Integer isModify;//是否修改过密码
//
//	public Long getIdKey() {
//		return this.idKey;
//	}
//
//	public void setIdKey(Long idKey) {
//		this.idKey = idKey;
//	}
//
//	public String getUserCd() {
//		return this.userCd;
//	}
//
//	public void setUserCd(String userCd) {
//		this.userCd = userCd;
//	}
//
//	public String getCdNm() {
//		return this.cdNm;
//	}
//
//	public void setCdNm(String cdNm) {
//		this.cdNm = cdNm;
//	}
//
//	public Integer getOrderCd() {
//		return this.orderCd;
//	}
//
//	public void setOrderCd(Integer orderCd) {
//		this.orderCd = orderCd;
//	}
//
//	public Integer getUseMark() {
//		return this.useMark;
//	}
//
//	public void setUseMark(Integer useMark) {
//		this.useMark = useMark;
//	}
//
//	public String getGender() {
//		return this.gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getUserPwd() {
//		return this.userPwd;
//	}
//
//	public void setUserPwd(String userPwd) {
//		this.userPwd = userPwd;
//	}
//
//	public Long getDeptId() {
//		return this.deptId;
//	}
//
//	public void setDeptId(Long deptId) {
//		this.deptId = deptId;
//	}
//
//	public Long getRoleId() {
//		return this.roleId;
//	}
//
//	public void setRoleId(Long roleId) {
//		this.roleId = roleId;
//	}
//
//	public String getRemark() {
//		return this.remark;
//	}
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//
//	public Long getDirectDept() {
//		return this.directDept;
//	}
//	
//	public void setDirectDept(Long directDept) {
//		this.directDept = directDept;
//	}
//
//	public Long getUserStatus() {
//		return this.userStatus;
//	}
//
//	public void setUserStatus(Long userStatus) {
//		this.userStatus = userStatus;
//	}
//	public String getLoginNm() {
//		return loginNm;
//	}
//	public void setLoginNm(String loginNm) {
//		this.loginNm = loginNm;
//	}
//	
//	@JsonSerialize(using = JsonDateSerializer.class)
//	public Date getChgDt() {
//		return chgDt;
//	}
//	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")  
//	public void setChgDt(Date chgDt) {
//		this.chgDt = chgDt;
//	}
//	
//	public String getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//	public String getRoleType() {
//		return roleType;
//	}
//
//	public void setRoleType(String roleType) {
//		this.roleType = roleType;
//	}
//	
//	public String getDeptName() {
//		return deptName;
//	}
//
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
//	}
//	
//	public Integer getIsModify() {
//		return this.isModify;
//	}
//
//	public void setIsModify(Integer isModify) {
//		this.isModify = isModify;
//	}

	
}