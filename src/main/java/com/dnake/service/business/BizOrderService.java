package com.dnake.service.business;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizOrder;
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
public interface BizOrderService {

	/**
	 * 保存订单
	* @title      update 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param bizOrder
	* @return
	 */
	String update(BizOrder bizOrder);
	
	/**
	 * 删除一个订单
	* @title      delete 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param response
	* @param idKey
	* @return
	 */
	String delete(HttpServletResponse response, Long idKey);
	
	/**
	 * 插入一个订单
	* @title      insert 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param bizOrder
	* @return
	 */
	String insert(BizOrder bizOrder);
	/**
	 * 手机端提交订单
	* @title      insert 
	* @author  chen qige     
	* @date      2014年9月18日 
	* @param loginName
	* @param goodsId
	* @param goodsQuantity
	* @return
	 */
	String insert(MobileParms parms);
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
	Page<BizOrderVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	/**
	 * 广告状态修改
	 * @title      updateStatus
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 * @return
	 */
	String updateStatus(Long idKey, String type);
	/**
	 * 删除多条订单
	* @title      deleteMulti 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param deleteIds
	* @return
	 */
	String deleteMulti(String deleteIds);
	/**
	 * 手机查询订单和订单详情
	* @title      mobileSalesQueryOrder 
	* @author  chen qige     
	* @date      2014年9月18日 
	* @param parms
	* @return
	 */
	String mobileQueryOrderAndDetails(MobileParms parms);
	/**
	 * 手机端修改订单
	* @title      mobileUpdateOrder 
	* @author  chen qige     
	* @date      2014年9月19日 
	* @param parms
	* @return
	 */
	String mobileUpdateOrder(MobileParms parms);
	/**
	 * 手机分页查询订单，商家和用户通用
	* @title      mobileQueryOrderPage 
	* @author  chen qige     
	* @date      2014年9月23日 
	* @param parms
	* @return
	 */
	String mobileQueryOrderPage(MobileParms parms);
	/**
	 * 手机端插入订单
	 * @param parms
	 * @return
	 */
	String mobileInsertOrder(MobileParms parms);
	/**
	 * 手机端获取提交到阿里的支付订单号
	 *  @author zgj
	 *	日期：2015年9月25日上午9:15:41
	 *  描述：@param parms
	 *  描述：@return
	 */
	String mobileGetAlipayOrderNum(MobileParms parms);
}
