package com.dnake.tasks.lhApi.bean;

import java.util.Date;
import java.util.List;

/**
 * Type string 类别（0是居民或者业主1是物业公司）<br>
 * ID string 明细唯一编码<br>
 * WriteTime DateTime 发布时间<br>
 * UserID string 用户id<br>
 * UserName string 用户名称（作者）<br>
 * Tel string 手机号码<br>
 * ImageUrl ImageModel数组 图片数组 返回一个字符串数组，用于表示多张图片的URL地址，地址需要自己加上App.pmsas.net需注意此字符串要放在可配置位置，以后可能会有所调整<br>
 * FID string 父节点ID(指故障申告列表给的ID)<br>
 * Content string 内容<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 下午2:59:36
 * @version 1.0
 */
public class GetFeedbackDetails2 {
	private String Type;
	private String ID;
	private Date WriteTime;
	private String UserID;
	private String UserName;
	private String Tel;
	private List<String> ImageUrl;
	private String FID;
	private String Content;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
	public List<String> getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(List<String> imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getFID() {
		return FID;
	}
	public void setFID(String fID) {
		FID = fID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
