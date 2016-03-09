package com.dnake.domain.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.dnake.common.JsonDateTimeSerializer;


/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCommunityVO implements java.io.Serializable {

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
	private String remark;
	private String province;
	private String city;
	private String district;
	
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
	
	private String communityInfo;
	private Long communityInfoId;
	private String properInfo;
	private Long properInfoId;
	
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public String getCommunityInfo()
	{
		return communityInfo;
	}
	public void setCommunityInfo(String communityInfo)
	{
		this.communityInfo = communityInfo;
	}
	public Long getCommunityInfoId()
	{
		return communityInfoId;
	}
	public void setCommunityInfoId(Long communityInfoId)
	{
		this.communityInfoId = communityInfoId;
	}
	public String getProperInfo()
	{
		return properInfo;
	}
	public void setProperInfo(String properInfo)
	{
		this.properInfo = properInfo;
	}
	public Long getProperInfoId()
	{
		return properInfoId;
	}
	public void setProperInfoId(Long properInfoId)
	{
		this.properInfoId = properInfoId;
	}
	
}