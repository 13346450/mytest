package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.common.Page;

/**
 * 住户信息采集映射接口类
 * @ClassName BizUserInfoMapper
 * @author zgj
 * 2014年9月11日 下午4:56:00
 */
public interface BizUinfoCollectMapper
{
	/**
	 * 住户从手机端插入一条信息
	 * @param bizUserInfoVO
	 */
	public void insertMobile(BizUinfoCollect bizUinfoCollect);
	/**
	 * 管理员删除一条住户信息
	 * @param idKey
	 */
	public void delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List<BizUinfoCollectVO> queryPage(Page<BizUinfoCollectVO> page);
	/**
	 * 手机端查询列表接口
	 * @return
	 */
	public List<BizUinfoCollectVO> queryListForMobile(Page<BizUinfoCollectVO> page);
	/**
	 * 手机端网页查询列表接口
	 * @return
	 */
	public List<BizUinfoCollectVO> queryListForWeb(Long idKey);
	/**
	 * 删除多条
	 * 
	 * @title deleteMulti
	 * @date 2014年9月15日
	 * @param map
	 */
	void deleteMulti(Map<String, Object> map);
	
	/**
	 * 根据小区的Code来查询小区列表
	 * @param communityCode
	 * @return
	 */
	List<BizUinfoCollectVO> queryByCommunityCode(String communityCode);
	/**
	 * 插入一条数据
	 * @param bizUinfoCollect
	 */
	void insert(BizUinfoCollect bizUinfoCollect);
	/**
	 * 更新一条住户信息数据
	 * @param bizUinfoCollect
	 */
	void update(BizUinfoCollect bizUinfoCollect);
	/**
	 * 手机端根据Id查询住户信息
	 * @param idKey
	 * @return
	 */
	BizUinfoCollectVO queryMobileByIdKey(Long idKey);
	
	/**
	 * 根据投诉建议的id查找出相应的追加投诉
	 * @param map -->parentId 父节点ID
	 * @return
	 */
	List<BizUinfoCollectVO> queryUinfoDetailList(Long parentId);
	
	/**
	 * 根据父类Id找出相应的物业回复
	 * @param map --->parentId 父节点ID
	 * @return
	 */
	BizUinfoCollectVO queryMobileByParentId(Long parentId);
	
	/**
	 * 	根据父节点ID查找出所有未回复的投诉集合
	 *  @author zgj
	 *	日期：2015年7月22日下午3:41:13
	 *  描述：@param parentId
	 *  描述：@return
	 */
	List<BizUinfoCollectVO> queryNoReplyListByParentId(Long parentId);
}
