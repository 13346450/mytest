package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizGoodsImage;

public interface BizGoodsImageMapper
{
	void insert(BizGoodsImage bizGoodsImage);

	void deleteByGoodsId(long goodsId);

	List<BizGoodsImage> queryByGoodsId(long goodsId);
	
	/**
	 * 多条删除
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);
}
