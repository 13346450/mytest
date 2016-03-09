package com.dnake.mapper.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizGoodsVO;

/**
 * 
 * BizGoodsMapper <br/>
 * 2014年9月11日 上午11:50:42 <br/>
 * 
 * @author chen qige
 * @version
 */
public interface BizGoodsMapper {

	/**
	 * 编辑单个商品
	 * 
	 * @title edit
	 * @author chen qige
	 * @date 2014年9月11日
	 * @param idKey
	 * @return
	 */
	BizGoods edit(Long idKey);

	/**
	 * 保存修改商品
	 * 
	 * @title update
	 * @author ts
	 * @date 2014年2月19日
	 * @param bizGoods
	 */
	void update(BizGoods bizGoods);

	/**
	 * 删除一个商品
	 * 
	 * @title delete
	 * @author ts
	 * @date 2014年2月19日
	 * @param idKey
	 */
	void delete(Long idKey);

	/**
	 * 插入一个商品
	 * 
	 * @title insert
	 * @author ts
	 * @date 2014年2月19日
	 * @param bizGoods
	 */
	void insert(BizGoods bizGoods);

	/**
	 * 一个类别下的分页查询
	 * 
	 * @title queryPage
	 * @author ts
	 * @date 2014年2月19日
	 * @param page
	 * @return
	 */
	List<BizGoodsVO> queryPage(Page<BizGoodsVO> page);

	/**
	 * 手机端接口查询商品详情
	 * 
	 * @title mobileQueryGoodsDetails
	 * @author chen qige
	 * @date 2014年9月12日
	 * @return
	 */
	BizGoodsVO mobileQueryGoodsDetails(Long id);


	/**
	 * 删除多条
	 * 
	 * @title deleteMulti
	 * @author chen qige
	 * @date 2014年9月15日
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);
	/**
	 * 递归商品目录查询商品
	* @title      queryAllPageFromParentCategory 
	* @author  chen qige     
	* @date      2014年9月26日 
	* @param page
	* @return
	 */
	List<BizGoodsVO> queryAllPageFromParentCategory(Page<BizGoodsVO> page);
	/**
	 * 根据商品id查询商品价格
	 * @param map
	 * @return
	 */
	List<BizGoods> queryDiscountPriceByIds(Map<String, Object> map);
	/**
	 * 下架多个商品
	 * @param list
	 *@author cqg 
	 *2015年6月12日
	 */
	void pullOffMulti(Map<String, Object> map);
	 /**
	  *  自动上架商品,在这个区间内的商品自动上架
	  * @param startTime
	  * @param endTime
	  *@author cqg 
	  *2015年9月23日
	  */
	void autoPutOn(Date startTime,Date endTime);
	/**
	 * 根据ID查找商品
	 *  @author zgj
	 *	日期：2015年9月25日上午11:40:52
	 *  描述：@param idKey
	 *  描述：@return
	 */
	BizGoods queryByIdKey(Long idKey);
}
