package com.dnake.domain.business;

import java.util.Date;

/**
 * 团购券实体类
 * 
 * @ClassName: BizCoupon
 * @Description: TODO
 * @author cqg
 * @date 2015年4月11日 下午5:16:29
 *
 */
public class BizCoupon {
	//
	private Long idKey;
	//
	private Long orderDetailsId;
	// 验证码
	private String verification;
	//
	private Integer status;
	//
	private Date chgDt;
	private Date useDt;// 使用时间
	private String remark;// 备注

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Long getIdKey() {
		return idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Long getOrderDetailsId() {
		return orderDetailsId;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	/**
	 * 获取验证码
	 * 
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public String getVerification() {
		return verification;
	}

	/**
	 * 设置验证码
	 * 
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setVerification(String verification) {
		this.verification = verification;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public Date getChgDt() {
		return chgDt;
	}

	/**
	 *
	 * @author 云服务工具
	 * @date 2015-04-11 17:12:40
	 */
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public Date getUseDt() {
		return useDt;
	}

	public void setUseDt(Date useDt) {
		this.useDt = useDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
