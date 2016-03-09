package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizProperInfo;
import com.dnake.domain.business.BizProperInfoVO;
import com.dnake.domain.common.Page;

/**
 * 物业信息接口类    映射关系
 * @ClassName BizProperInfoMapper
 * @author zgj
 * 2014年9月4日 下午4:31:08
 */
public interface BizProperInfoMapper
{
	/**
	 * 插入一条新的物业信息
	 * @param bizProperInfo
	 */
	public void insert(BizProperInfo bizProperInfo);
	/**
	 * 更新一条物业信息
	 * @param bizProperInfo
	 */
	public void update(BizProperInfo bizProperInfo);
	/**
	 * 更新物业信息状态
	 * @param bizAdvert
	 */
	public void updateStatus(Map<String,Object> map);
	/**
	 * 根据ID查询
	 * @param idKey
	 * @return
	 */
	public BizProperInfo queryById(Long idKey);
	
	/**
	 * 根据小区ID查找物业信息
	 * @param communityId
	 * @return
	 */
	public List<BizProperInfoVO> queryByCommunityId(Long communityId);
	/**
	 * 根据ID删除一条信息
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizProperInfoVO> queryPage(Page<BizProperInfoVO> page);
	/**
	 * 手机端查询物业信息
	 * @param idKey
	 * @return
	 */
	public List<BizProperInfoVO> queryForMobile(Page<BizProperInfoVO> page);
	/**
	 * 查询单条物业信息详细
	 * @param parentId
	 * @return
	 */
	public BizProperInfoVO queryForDetail(Long idKey);
	/**
	 * 删除多条
	 * 
	 * @title deleteMulti
	 * @date 2014年9月15日
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);
	/**
	 * 对接外部接口的实现
	 * @param bizProperInfo
	 */
	public void updateByDate(BizProperInfo bizProperInfo);
	/**
	 * 根据标题和发布时间来寻找物业信息
	 * @param bizProperInfo
	 * @return
	 */
	public List<BizProperInfo> queryByDate(BizProperInfo bizProperInfo);
	/**
	 * 第三方Id LH
	 * @param parms
	 * @return
	 */
	public BizProperInfo getByLhId(String id);
	
}
