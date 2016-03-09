package com.dnake.service.business;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizOrderDetails;
import com.dnake.domain.business.BizOrderVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 广告业务接口
 *  BizAdvertService <br/>
 *  2014年3月19日 下午4:37:57 <br/>
 * @author ts
 * @version
 */
public interface BizOrderDetailsService {

	/**
	 * 分页查询
	* @title      listPage 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param pageIndex
	* @param rows
	* @param searchParam
	* @return
	 */
	Page<BizOrderDetails> listPage(int pageIndex, int rows, SearchParam searchParam);
	/**
	 * 手机端根据订单id查询订单详情
	* @title      mobileQueryPage 
	* @author  chen qige     
	* @date      2014年9月23日 
	* @param parms
	* @return
	 */
	String mobileQueryPage(MobileParms parms);
	/**
	 * 根据商品id查询订单号
	* @title      selectByGoodsId 
	* @author  chen qige     
	* @date      2014年10月17日 
	*  @param idKey
	*  @return
	 */
	List<BizOrderDetails> selectByGoodsId(Long idKey);
}
