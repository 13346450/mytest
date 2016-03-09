package com.dnake.mapper.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizOrder;
import com.dnake.domain.business.BizOrderVO;
/**
 * 订单管理
*  BizOrderMapper <br/> 
*  2014年9月16日 上午11:29:53 <br/> 
* @author chen qige 
* @version
 */
public interface BizOrderMapper {
	
	/**
	 * 更新广告状态
	 * @title      updateStatus
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param bizOrder
	 */
	void updateStatus(Map<String,Object> map);
	
	/**
	 * 删除一条广告
	 * @title      delete
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param idKey
	 */
	void delete(Long idKey);
	
	/**
	 * 插入一个广告
	 * @title      insert
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param bstDevice
	 */
	void insert(BizOrder bizOrder);
	
	/**
	 * 按id查询单条广告
	 * @title      queryByIdkey
	 * @author  ts    
	 * @date      2014年3月28日 
	 * @param idKey
	 * @return
	 */
	BizOrder queryByIdkey(Long idKey);

	/**
	 * 查询广告最新发布版本
	 * @title      queryMaxVersion
	 * @author  ts    
	 * @date      2014年4月8日 
	 * @return
	 */
	BizOrder queryMaxPublishedVersion();

	/**
	 * 获得最大版本号
	 * @title      getMaxVersionNo
	 * @author  ts    
	 * @date      2014年4月12日 
	 * @return
	 */
	String getMaxVersionNo();
	
	/**
	 * 分页查询
	 * @title      queryPage
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param page
	 * @return
	 */
	List<BizOrderVO> queryPage(Page<BizOrderVO> page);
	/**
	 * 删除多条
	* @title      deleteMulti 
	* @author  chen qige     
	* @date      2014年9月16日 
	* @param map
	 */
	void deleteMulti(Map<String, Object> map);
	/**
	 * 修改订单状态
	* @title      update 
	* @author  chen qige     
	* @date      2014年9月19日 
	* @param bizorder
	 */
	void update(BizOrder bizOrder);
	/**
	 * 查询商家所有未处理的订单
	* @title      queryOrderBySalesId 
	* @author  chen qige     
	* @date      2014年9月23日 
	* @param idKey
	* @return
	 */
	List<BizOrderVO> queryOrderBySalesId(Long idKey);
	/**
	 * 插入多条
	 * @param list
	 */
	void insertMulti(List<BizOrder> list);
	
	/**
	 * 手机端查询销量
	 * @param goodsId
	 * @return
	 */
	BizOrderVO mobileQuerySalesCount(Long goodsId);
	/**
	 *  @author zgj
	 *	日期：2015年9月24日上午10:23:15
	 *  描述：定时更改未付款的活动商品
	 */
	void updateOrderForTimeTask(Map<String, Object> map);
	
	/**
	 * 将超时的活动商品订单全部查询出来
	 *  @author zgj
	 *	日期：2015年9月29日上午11:55:03
	 *  描述：@return
	 */
	List<BizOrder> queryOrderForTimeTask(Date date);
	
	/**
	 * 查询用户购买的商品在同一场活动内只能购买一个
	 *  @author zgj
	 *	日期：2015年10月9日上午9:43:03
	 *  描述：@return
	 */
	List<BizOrder> queryOrderIsOnlyOne(Date date, Long userId);
	
	/**
	 * 根据订单号查询订单
	 *  @author zgj
	 *	日期：2015年10月10日上午11:58:11
	 *  描述：@param orderNum
	 *  描述：@return
	 */
	BizOrder queryByOrderNum(String orderNum);
	
	/**
	 * 根据是否付款查询该订单是否存在
	 *  @author zgj
	 *	日期：2015年10月12日下午4:18:44
	 *  描述：@param orderId
	 *  描述：@return
	 */
	BizOrder queryByIsPayment(Long orderId);
}
