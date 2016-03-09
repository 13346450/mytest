package com.dnake.tasks.lhApi.bean;

import java.util.Date;
import java.util.List;

/**
 * Title string 标题<br>
 * UserID string 用户id<br>
 * UserName string 用户名称（作者）<br>
 * Tel string 手机号码<br>
 * LastUpdated DateTime 最后更新时间<br>
 * Status string 状态<br>
 * Flag string 申报类型<br>
 * Address string 地址<br>
 * Linkman string 联系人<br>
 * Email string email<br>
 * QQ string qq<br>
 * Details Detail数组 类型为下面列表<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 下午2:59:36
 * @version 1.0
 */
public class GetFeedbackDetails {
	private String Title;
	private String UserID;
	private String UserName;
	private String Tel;
	private Date LastUpdated;
	private String Status;
	private String Flag;
	private String Address;
	private String Linkman;
	private String Email;
	private String QQ;
	private List<GetFeedbackDetails2> Details;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
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
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public List<GetFeedbackDetails2> getDetails() {
		return Details;
	}
	public void setDetails(List<GetFeedbackDetails2> details) {
		Details = details;
	}
	
}
