package com.dnake.service.business;

import java.util.List;


import com.dnake.domain.business.BizReportForms;
import com.dnake.domain.common.MobileParms;

/**
 *  报表业务实现类
 *  @author zgj
 *	日期：2015年5月12日上午11:43:39
 *  描述：
 */
public interface BizReportFormsService
{
	/**
	 * 
	 * 查询销售日报
	 * @param parms
	 * @return
	 */
	public String mobileSearchDailySales(MobileParms parms);
}
