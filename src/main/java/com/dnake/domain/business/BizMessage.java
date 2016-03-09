package com.dnake.domain.business;

import java.util.Date;

/**
 * 推送消息的实体类 BizMessage <br/>
 * 2014年10月21日 上午9:55:47 <br/>
 * 
 * @author chen qige
 * @version
 */
public class BizMessage {
	private Long idKey;
	private String userCd;
	private String title;
	private String message;
	private Date orderDt;

	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getOrderDt() {
		return orderDt;
	}

	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}

}
