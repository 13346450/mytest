package com.dnake.tasks.lhApi.bean;

import java.util.Date;

/**
 * Title string 标题<br>
 * LastUpdated DateTime 最后更新时间<br>
 * Flag string 预留字段<br>
 * ID string 主题唯一编号<br>
 * CommunityCode string 小区代码<br>
 * OIndex string 序号<br>
 * IssuesTime DateTime 发布时间<br>
 * ExpirationTime DateTime 过期时间<br>
 * 
 * @author 蔡小龙
 * @date 创建时间：2015年8月4日 上午8:58:33
 * @version 1.0
 */
public class GetNoticeList {
	private String Title;
	private Date LastUpdated;
	private String Flag;
	private String ID;
	private String CommunityCode;
	private String OIndex;
	private Date IssuesTime;
	private Date ExpirationTime;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Date getLastUpdated() {
		return LastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		LastUpdated = lastUpdated;
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

	public Date getExpirationTime() {
		return ExpirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		ExpirationTime = expirationTime;
	}

}
