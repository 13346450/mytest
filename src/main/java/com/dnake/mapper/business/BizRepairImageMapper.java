package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizRepairImage;

public interface BizRepairImageMapper
{
	void insert(BizRepairImage bizRepairImage);

	void deleteByRepairId(long repairId);

	List<BizRepairImage> queryByRepairId(Map<String, Object> map);
	
	/**
	 * 多条删除
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);
}
