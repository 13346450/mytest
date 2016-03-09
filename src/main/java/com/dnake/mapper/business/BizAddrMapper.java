package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizAddr;
import com.dnake.domain.business.BizAddrVO;

/**
 *  @author zgj
 *	日期：2015年3月24日下午6:51:45
 *  描述：地址数据映射文件
 */
public interface BizAddrMapper
{
	/**
	 * 插入一条地址
	 * @param bizAddr
	 */
	public void insert(BizAddr bizAddr);

	/**
	 * 删除一个地址
	 * @param idKey
	 */
	public void delete(long idKey);

	/**
	 * 更新一个地址
	 * @param bizAddr
	 */
	public void update(BizAddr bizAddr);

	/**
	 * 根据ID查询
	 * @param idKey
	 * @return
	 */
	public BizAddrVO queryByIdkey(long idKey);

	/**
	 * 多行删除
	 * @param map
	 */
	public void deleteMulti(Map<String, Object> map);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizAddrVO> queryPage(Page<BizAddrVO> page);

	/**
	 * 多条插入
	 * @param list
	 */
	public void insertMulti(List<BizAddr> list);
	
	/**
	 * 根据用户ID查询地址
	 * @param userId
	 * @return
	 */
	public List<BizAddrVO> queryByUserId(long userId);
	
	/**
	 * 根据default查询
	 * @return
	 */
	public List<BizAddrVO> queryByDefault(BizAddr biz);
	
	/**
	 * 
	 * @param idKey
	 */
	public void updateDefault(BizAddr biz);
}
