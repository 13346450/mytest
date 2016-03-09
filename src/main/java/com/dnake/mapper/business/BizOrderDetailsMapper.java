package com.dnake.mapper.business;

import java.util.List;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizOrderDetails;

/**
 * 订单管理 BizOrderMapper <br/>
 * 2014年9月16日 上午11:29:53 <br/>
 * 
 * @author chen qige
 * @version
 */
public interface BizOrderDetailsMapper {

	/**
	 * 插入一条订单详情
	 * 
	 * @title insert
	 * @author ts
	 * @date 2014年2月19日
	 * @param bstDevice
	 */
	void insert(BizOrderDetails bizOrderDetails);

	/**
	 * 分页查询
	 * 
	 * @title queryPage
	 * @author chen qige
	 * @date 2014年9月16日
	 * @param page
	 * @return
	 */
	List<BizOrderDetails> queryPage(Page<BizOrderDetails> page);

	/**
	 * 根据商品id查询订单
	 * 
	 * @title selectByGoodsId
	 * @author chen qige
	 * @date 2014年10月17日
	 * @param idKey
	 * @return
	 */
	List<BizOrderDetails> selectByGoodsId(long idKey);

	/**
	 * 插入多条
	 * 
	 * @param list
	 */
	void insertMulti(List<BizOrderDetails> list);
	/**
	 * 根据订单id查询商品详情
	 * @param idKey
	 * @return
	 *@author cqg 
	 *2015年4月13日
	 */
	List<BizOrderDetails> queryByOrderId(Long orderId);
	/**
	 * 根据订单id查询商品是否是社区商城里的
	 * @param idKey
	 * @return
	 *@author cqg 
	 *2015年4月13日
	 */
	List<BizOrderDetails> queryGoodsNotInMallByOrderId(Long orderId);
	
}
