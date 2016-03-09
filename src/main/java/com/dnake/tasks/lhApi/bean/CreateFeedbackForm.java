package com.dnake.tasks.lhApi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * title 是 string 标题<br>
 * tel 是 string 手机号码<br>
 * communityCode 是 string 小区代码<br>
 * content 否 string 内容<br>
 * base64Images 否 String数组 将图片集合转为Base64形式的字符串数组(格式为”base64Images”:[“1”,”2”,”3”])<br>
 * type 是 string 类别为“故障申告”、“咨询建议” ,注意用户填写的时候，默认是自动添加的，比如当前是故障申告，type自动填写为故障申告，用户不必自己添加，此字段UI上也是不可见的）<br>
 * userID 是 string 用户登录id<br>
 * userName 是 String 用户登录昵称<br>
 * flag 是 string 故障申告的类型（公共保修、个人保修） 2014-07-31定义<br>
 * remark 否 string 备注<br>
 * address 是 string 地址<br>
 * email 否 string 邮箱<br>
 * qq 否 string Qq<br>
 * linkman 是 string 联系人<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 下午5:11:22
 * @version 1.0
 */
public class CreateFeedbackForm {
	private String title;
	private String tel;
	private String communityCode;
	private String content;
	private List<String> base64Images;
	private String type;
	private String userID;
	private String userName;
	private String flag;
	private String remark;
	private String address;
	private String email;
	private String qq;
	private String linkman;
	public CreateFeedbackForm(String title, String tel, String communityCode, String type, String userID, String userName, String flag, String address, String linkman) {
		super();
		this.title = title;
		this.tel = tel;
		this.communityCode = communityCode;
		this.type = type;
		this.userID = userID;
		this.userName = userName;
		this.flag = flag;
		this.address = address;
		this.linkman = linkman;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCommunityCode() {
		return communityCode;
	}
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getBase64Images() {
		return base64Images;
	}
	public void setBase64Images(List<String> base64Images) {
		this.base64Images = base64Images;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public void addBase64Image(String string){
		if(base64Images==null){
			base64Images = new ArrayList<String>();
		}
		base64Images.add(string);
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
}
