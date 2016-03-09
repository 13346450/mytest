package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.business.BizPropertiesRepairVO;

public interface BizPropertiesRepairMapper
{
	void insert(BizPropertiesRepair bizPropertyRepair);

	void delete(long idKey);

	void update(BizPropertiesRepair bizPropertyRepair);

	BizPropertiesRepairVO queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	List<BizPropertiesRepairVO> queryPage(Page<BizPropertiesRepairVO> page);

	void insertMulti(List<BizPropertiesRepair> list);
	
	/**
	 * 手机端查询自身物业申告列表
	 * @return
	 */
	List<BizPropertiesRepairVO> mobileQueryList(Page<BizPropertiesRepairVO> page);
	/**
	 * 手机端查询
	 * @param idKey
	 * @return
	 */
	List<BizPropertiesRepairVO> mobileQueryDetails(Long idKey);
	/**
	 * 手机端删除
	 * @param 
	 */
	void mobileDeleteMulti(Map<String, Object> map);
	/**
	 * 手机端插入物业报修信息
	 * @param biz
	 */
	void mobileInsert(BizPropertiesRepair bizPropertiesRepair);
	
	/**
	 * 根据小区区域查询数据集合
	 * @param communityCode
	 * @return
	 */
	List<BizPropertiesRepairVO> queryByCommunityCode(String communityCode);
	/**
	 * 根据父节点ID查询出所有未回复的追加故障申告
	 *  @author zgj
	 *	日期：2015年7月22日下午4:05:05
	 *  描述：@param parentId
	 *  描述：@return
	 */
	List<BizPropertiesRepairVO> queryNoReplyRepair(Long parentId);

	BizPropertiesRepair getById(Long id);
}
