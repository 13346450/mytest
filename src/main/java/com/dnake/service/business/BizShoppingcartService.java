package com.dnake.service.business;

import com.dnake.domain.business.BizShoppingcartVO;
import com.dnake.domain.business.BizShoppingcart;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizShoppingcartService
{
	Page<BizShoppingcartVO> listPage(int pageIndex, int rows,
			SearchParam searchParam);

	String update(BizShoppingcart bizShoppingcart);

	String insert(BizShoppingcart bizShoppingcart);

	String deleteMulti(String deleteIds);

	/**
	 * 手机端插入购物车
	 * @param parms
	 * @return
	 */
	String mobileInsertShoppingcart(MobileParms parms);
	
	/**
	 * 手机端删除购物车商品
	 * @param parms
	 * @return
	 */
	String mobileDeleteByIdKey(MobileParms parms);
	
	/**
	 * 手机端查询购物车列表
	 * @param parms
	 * @return
	 */
	String mobileQueryShoppingcart(MobileParms parms);
	
	/**
	 * 手机端更新购物车
	 * @param parms
	 * @return
	 */
	String mobileUpdateShoooingcart(MobileParms parms);
	/**
	 * 手机多行删除
	 * @param deleteIds
	 * @return
	 */
	String mobileMultiDelete(MobileParms parms);
}
