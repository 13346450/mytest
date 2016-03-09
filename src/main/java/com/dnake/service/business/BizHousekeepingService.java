package com.dnake.service.business;

import com.dnake.domain.business.BizHousekeeping;
import com.dnake.domain.business.BizHousekeepingVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizHousekeepingService {

	public Page<BizHousekeepingVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	public String update(BizHousekeeping bizHousekeeping);
	public String insert(BizHousekeeping bizHousekeeping);
	public String delete(Long idKey);
	public String[] queryCategorysByCommuntityId(Long deptId);
	public Page<BizHousekeepingVO> search(int pageIndex, int rows, String names, Long communityId);
}
