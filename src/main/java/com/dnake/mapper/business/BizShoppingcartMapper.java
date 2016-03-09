package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizShoppingcart;
import com.dnake.domain.business.BizShoppingcartVO;

/**
 * @author zgj 日期：2015年4月2日上午10:39:50 描述：购物车数据映射层
 */
public interface BizShoppingcartMapper
{
	void insert(BizShoppingcart bizShoopingcart);

	void delete(long idKey);

	void update(BizShoppingcart bizShoopingcart);

	BizShoppingcart queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	List<BizShoppingcartVO> queryPage(Page<BizShoppingcartVO> page);

	void insertMulti(List<BizShoppingcart> list);

	/**
	 * 商品添加进购物车
	 * 
	 * @param bizShoopingcart
	 */
	void mobileInsertGoodsShoopingCart(BizShoppingcart bizShoopingcart);
	
	/**
	 * 手机端查询购物车
	 * @return
	 */
	List<BizShoppingcartVO> mobileQueryList(Long userId);
	
	/**
	 * 根据用户ID，商铺ID，商品ID查询是否有
	 *  @author zgj
	 *	日期：2015年9月8日下午4:18:32
	 *  描述：@param bizShoopingcart
	 *  描述：@return
	 */
	List<BizShoppingcartVO> mobileQueryByUserIdAndOtherId(BizShoppingcart bizShoopingcart);
	
}
