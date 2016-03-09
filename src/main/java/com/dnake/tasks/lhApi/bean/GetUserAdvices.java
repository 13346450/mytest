package com.dnake.tasks.lhApi.bean;

import java.util.Date;

/**
 * Content string 内容<br>
 * Title string 标题<br>
 * ID string 唯一编码<br>
 * WriteTime DateTime 发布时间<br>
 * UserID string 用户id<br>
 * UserName string 用户名称（作者）<br>
 * Tel string 手机号码<br>
 * Remark string 备注<br>
 * Type string 类别<br>
 * CommunityCode long 小区14位代码<br>
 * LastUpdated DateTime 最后更新时间<br>
 * Status string 状态<br>
 * Flag string 标志位预留<br>
 * Address string 地址<br>
 * Linkman string 联系人<br>
 * Email string email<br>
 * c string qq<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月5日 下午3:05:15
 * @version 1.0
 */
public class GetUserAdvices {
	private String Content;
	private String Title;
	private String ID;
	private Date WriteTime;
	private String UserID;
	private String UserName;
	private String Tel;
	private String Remark;
	private String Type;
	private long CommunityCode;
	private Date LastUpdated;
	private String Status;
	private String Flag;
	private String Address;
	private String Linkman;
	private String Email;
	private String qq;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Date getWriteTime() {
		return WriteTime;
	}
	public void setWriteTime(Date writeTime) {
		WriteTime = writeTime;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public long getCommunityCode() {
		return CommunityCode;
	}
	public void setCommunityCode(long communityCode) {
		CommunityCode = communityCode;
	}
	public Date getLastUpdated() {
		return LastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		LastUpdated = lastUpdated;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getLinkman() {
		return Linkman;
	}
	public void setLinkman(String linkman) {
		Linkman = linkman;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
}
