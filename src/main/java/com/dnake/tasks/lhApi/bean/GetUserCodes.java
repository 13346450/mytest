package com.dnake.tasks.lhApi.bean;

/**
 * UserCode string 用户号<br>
 * CommunityName string 小区名称<br>
 * RoomNo string 房间代码<br>
 * PhoneNumber string 手机号码<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 下午5:36:11
 * @version 1.0
 */
public class GetUserCodes {
	private String UserCode;
	private String CommunityName;
	private String RoomNo;
	private String PhoneNumber;
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getCommunityName() {
		return CommunityName;
	}
	public void setCommunityName(String communityName) {
		CommunityName = communityName;
	}
	public String getRoomNo() {
		return RoomNo;
	}
	public void setRoomNo(String roomNo) {
		RoomNo = roomNo;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
}
