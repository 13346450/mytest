package com.dnake.service.system;

import com.dnake.domain.system.SysCommunityInfoVO;
import com.dnake.domain.system.SysCommunityInfo;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 小区详细信息的业务层
 *  @author zgj
 *	日期：2015年7月10日上午9:39:54
 *  描述：
 */
public interface SysCommunityInfoService
{
	Page<SysCommunityInfoVO> listPage(int pageIndex, int rows,
			SearchParam searchParam);

	String update(SysCommunityInfo sysCommunityInfo);

	String insert(SysCommunityInfo sysCommunityInfo);

	String deleteMulti(String deleteIds);
	
	/**
	 * 手机端查询小区或者物业详情
	 * @param parms
	 * @return
	 */
	String mobileQueryCommunityDetail(MobileParms parms);
	
	/**
	 * 查询小区信息
	 * @param parms
	 * @return
	 */
	String mobileQueryCommuntiyInfo(MobileParms parms);
	
	/**
	 * 查询三方接口数据
	 * @param parms
	 * @return
	 */
	String queryTPP(MobileParms parms);
	
	/**
	 * 查询三方接口数据小区详情
	 * @param parms
	 * @return
	 */
	String queryTPPComDetail(MobileParms parms);
	
	/**
	 * 查询三方接口数据物业详情
	 * @param parms
	 * @return
	 */
	String queryTPPProDetail(MobileParms parms);

	SysCommunityInfo queryByCommiunityId(Long id);
}
