package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunityInfo;
import com.dnake.domain.system.SysCommunityInfoVO;

public interface SysCommunityInfoMapper
{
	void insert(SysCommunityInfo sysCommunityInfo);

	void delete(long idKey);

	void update(SysCommunityInfo sysCommunityInfo);

	SysCommunityInfo queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	List<SysCommunityInfoVO> queryPage(Page<SysCommunityInfoVO> page);

	void insertMulti(List<SysCommunityInfo> list);
	
	/**
	 * 根据小区ID查询物业或者小区详情
	 * type 小区类型  idKey 小区ID
	 * @param idKey
	 */
	List<SysCommunityInfoVO> mobileQueryCommunityDetail(Map<String, Object> map);
	
	/**
	 * 根据小区名称查询集合
	 * @param displayName
	 * @return
	 */
	List<SysCommunityInfo> queryTPPDetailByName(String displayName);
	/**
	 * 根据小区或物业名称更新数据
	 * @param sysCommunityInfo
	 */
	void updateByDisplayName(SysCommunityInfo sysCommunityInfo);
	/**
	 * 根据小区的Id查找
	 * @param sysCommunityInfo
	 */
	SysCommunityInfo queryByCommiunityId(Long idKey);
	/**
	 * 根据小区号和类型查找小区
	 * @param map
	 * @return
	 */
	List<SysCommunityInfo> queryByCommunityAndType(Map<String, Object> map);
}
