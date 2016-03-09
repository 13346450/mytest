package com.dnake.mapper.business;

import java.util.List;

import com.dnake.domain.business.BizHousekeeping;
import com.dnake.domain.business.BizHousekeepingVO;
import com.dnake.domain.common.Page;

public interface BizHousekeepingMapper {

	public List<BizHousekeepingVO> queryPage(Page<BizHousekeepingVO> page);
	
	public void update(BizHousekeeping domain);
	
	public void insert(BizHousekeeping domain);
	
	public void delete(Long idKey);

	public String[] queryCategorysByCommuntityId(Long idKey);

	public List<BizHousekeepingVO> searchByCategorys(Page<BizHousekeepingVO> page);


}
