package com.dnake.tasks.lhApi.bean;

import java.util.Date;

/**
 * Title string 标题<br>
 * Content string (HTML)内容<br>
 * Flag string 预留字段<br>
 * ID string 明细唯一编号<br>
 * CommunityCode string 小区代码<br>
 * OIndex string 序号<br>
 * IssuesTime DateTime 发布时间<br>
 * Issuer string 发布人<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 上午10:17:24
 * @version 1.0
 */
public class GetNotice {
	private String Title;
	private String Content;
	private String Flag;
	private String ID;
	private String CommunityCode;
	private String OIndex;
	private Date IssuesTime;
	private String Issuer;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCommunityCode() {
		return CommunityCode;
	}
	public void setCommunityCode(String communityCode) {
		CommunityCode = communityCode;
	}
	public String getOIndex() {
		return OIndex;
	}
	public void setOIndex(String oIndex) {
		OIndex = oIndex;
	}
	public Date getIssuesTime() {
		return IssuesTime;
	}
	public void setIssuesTime(Date issuesTime) {
		IssuesTime = issuesTime;
	}
	public String getIssuer() {
		return Issuer;
	}
	public void setIssuer(String issuer) {
		Issuer = issuer;
	}
	
}
