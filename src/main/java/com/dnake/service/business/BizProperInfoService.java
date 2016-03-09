package com.dnake.service.business;


import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 物业信息发布业务层
 * @ClassName BizProperInfoService
 * @author zgj
 * 2014年9月4日 下午5:19:52
 */
public interface BizProperInfoService
{
	/**
	 * 保存修改物业信息
	 * @title   update
	 */
	String update(BizProperInfo bizProperInfo);
	
	/**
	 * 删除一条物业信息
	* @Title: delete 
	 */
	String delete(HttpServletResponse response, Long idKey);
	
	/**
	 * 插入一条物业信息
	 * @title      insert
	 */
	String insert(BizProperInfo bizProperInfo);
	
	/**
	 * 分页查询
	 * @title      listPage
	 */
	Page<BizProperInfoVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	/**
	 * 物业信息状态修改
	 * @title      updateStatus
	 */
	String updateStatus(Long idKey, String type);
	/**
	 * 手机查询物业信息列表
	 * @param idKey
	 * @return
	 */
	String queryForMobile(MobileParms parms);
	/**
	 * 手机端查询物业详细信息
	 * @param idKey
	 * @return
	 */
	BizProperInfoVO queryDetailForMobile(Long idKey);
	/**
	 * 删除多条
	 * 
	 * @title deleteMulti
	 * @date 2014年9月15日
	 * @param deleteIds
	 * @return
	 */
	String deleteMulti(String deleteIds);
	
	/**
	 * 根据日期和标题更新内容
	 * @param biz
	 * @return
	 */
	String updateByDate(BizProperInfo biz);
	/**
	 * 获取领航的物业通知数据列表
	 * @param pageIndex
	 * @param pageSize
	 * @param searchParam
	 * @return
	 */
	String queryOtherPlatformNoticeList(MobileParms parms);
	/**
	 * 获取领航的物业通知详细数据
	 * @param idKey
	 * @return
	 */
	String queryOtherPlatformNoticeDetail(MobileParms parms);
	
	/**
	 * 手机端查询通知公告详情
	 * @param parms
	 * @return
	 */
	String mobileQueryProperDetail(MobileParms parms);
	
	
}
