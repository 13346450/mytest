package com.dnake.tasks.lhApi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * id 是 string 要追加帖子的id<br>
 * tel 是 string 手机号码<br>
 * content 否 string 内容<br>
 * base64Images 否 String数组 将图片集合转为Base64形式的字符串数组(格式为”base64Images”:[“1”,”2”,”3”])<br>
 * userID 是 string 用户登录id<br>
 * userName 是 String 用户登录昵称<br>
 * status 否 string 预留<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月6日 下午2:00:33
 * @version 1.0
 */
public class AppendFeedbackForm {
	private String id;
	private String tel;
	private String content;
	private String userID;
	private String userName;
	private String status;
	private List<String> base64Images;
	
	public AppendFeedbackForm(String id, String tel, String userID, String userName) {
		super();
		this.id = id;
		this.tel = tel;
		this.userID = userID;
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getBase64Images() {
		return base64Images;
	}
	public void setBase64Images(List<String> base64Images) {
		this.base64Images = base64Images;
	}
	public void addBase64Images(String base64Image){
		if(this.base64Images==null){
			this.base64Images = new ArrayList<String>();
		}
		this.base64Images.add(base64Image);
	}
}
