package com.dnake.mapper.business;

import java.util.List;

import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizChainVO;
import com.dnake.domain.common.Page;

/**
 * 便民服务链接管理
 * @ClassName BizChainMapper
 * @author zgj
 * 2014年9月1日 下午1:44:46
 */
public interface BizChainMapper
{
	/**
	 * 插入一条链接
	 */
	public void insert(BizChain bizChain);
	/**
	 * 更新一条链接
	 * @param bizChain
	 */
	public void update(BizChain bizChain);
	/**
	 * 根据ID查找一个链接
	 * @param idKey
	 * @return
	 */
	public BizChain queryById(Long idKey);
	/**
	 * 根据ID删除一条记录
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizChainVO> queryPage(Page<BizChainVO> page);
	/**
	 * 获取手机端便民服务链接
	 * @return
	 */
	public List<BizChain> queryMobileChainInfoList(Long communityId);
	/**
	 * 插入默认的便民服务链接
	 * @param communityId
	 *@author cqg 
	 *2015年7月14日
	 */
	public void insertDefault(Long communityId);
	/**
	 * 根据小区id删除
	 * @param communityId
	 *@author cqg 
	 *2015年7月14日
	 */
	public void deleteByCommunityId(Long communityId);
}
