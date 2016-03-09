package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizShop;
import com.dnake.domain.business.BizShopVO;

public interface BizShopMapper
{
	void insert(BizShop bizShop);

	void delete(long idKey);

	void update(BizShop bizShop);

	BizShop queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	List<BizShopVO> queryPage(Page<BizShopVO> page);

	void insertMulti(List<BizShop> list);

	/**
	 * 手机端查询店铺
	 * 
	 * @title mobileQueryByUserId
	 * @author chen qige
	 * @date 2015年3月27日
	 * @param userId
	 */
	List<BizShopVO> mobileQueryByUserId(Long userId);

	/**
	 * 把该用户名下的默认设置为不是默认店铺
	 */
	void setAllNotDefault(Long userId);

	/**
	 * 批量更新店铺状态
	 * 
	 * @param map
	 * @author cqg 2015年5月29日
	 */
	void updateMulti(Map<String, Object> map);
	
	/**
	 * 根据店铺名查找店铺
	 *  @author zgj
	 *	日期：2015年9月29日下午5:46:13
	 *  描述：@param shopName
	 *  描述：@return
	 */
	List<BizShopVO> queryShopByName(String shopName);
}
