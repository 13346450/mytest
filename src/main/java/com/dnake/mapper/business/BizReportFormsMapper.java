package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizReportForms;

public interface BizReportFormsMapper
{
	/**
	 * 手机端查询销售日报
	 * @param parms
	 * @return
	 */
	public List<BizReportForms> mobileSearchDailyReport(Map<String, Object> parms);
}
