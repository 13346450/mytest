package com.dnake.service.business;

import java.util.List;

import com.dnake.domain.business.BizShopVO;
import com.dnake.domain.business.BizShop;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizShopService {
	Page<BizShopVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	String update(BizShop bizShop);

	String insert(BizShop bizShop);

	String deleteMulti(String deleteIds);

	/**
	 * 根据商家查询店铺
	 * 
	 * @param userId
	 * @return
	 * @author cqg 2015年9月24日
	 */
	List<BizShopVO> queryShopByuserId(Long userId);

	/**
	 * 手机端出入一条
	 * 
	 * @title mobileInSert
	 * @author chen qige
	 * @date 2015年3月26日
	 * @param parms
	 * @return
	 */
	String mobileInSert(MobileParms parms);

	/**
	 * 手机端查询
	 * 
	 * @title mobileQuery
	 * @author chen qige
	 * @date 2015年3月27日
	 * @param parms
	 * @return
	 */
	String mobileQueryByUserId(MobileParms parms);

	/**
	 * 手机端删除店铺
	 * 
	 * @title mobileDelete
	 * @author chen qige
	 * @date 2015年3月27日
	 * @param parms
	 * @return
	 */
	String mobileDelete(MobileParms parms);

	/**
	 * 手机端修改
	 * 
	 * @title mobileUpdate
	 * @author chen qige
	 * @date 2015年3月27日
	 * @param parms
	 * @return
	 */
	String mobileUpdate(MobileParms parms);

	/**
	 * 批量更新店铺状态
	 * 
	 * @param deleteIds
	 * @param status
	 * @return
	 * @author cqg 2015年5月29日
	 */
	String updateMulti(String deleteIds, Integer status);
}
