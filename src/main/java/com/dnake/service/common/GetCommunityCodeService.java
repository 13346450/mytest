package com.dnake.service.common;

import java.util.List;

import com.dnake.domain.system.SysCommunity;

/**
 * 获取小区列表
 *  @author zgj
 *	日期：2015年7月8日下午5:57:14
 *  描述：
 */
public interface GetCommunityCodeService
{
	/**
	 * 获取小区列表
	 * @return
	 */
	List<SysCommunity> getCommunityInfo();
	/**
	 * 获取制定小区详情
	 * @param code
	 * @return
	 */
	String getCommunityInfo(String code);
}
