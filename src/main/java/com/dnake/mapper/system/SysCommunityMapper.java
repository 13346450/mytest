package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunityVO;
import com.dnake.domain.system.SysCommunity;

/**
 * 小区管理mapper
 * 
 * @ClassName: SysCommunityMapper
 * @Description:
 * @author ts
 * @date 2013-9-25 上午11:23:33
 *
 */
public interface SysCommunityMapper {

	/**
	 * 查询所有小区
	 * 
	 * @Title: selectAll
	 * @author ts 2013-9-25
	 * @Description:
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> queryAll();

	/**
	 * 查询所有小区(分页)
	 * 
	 * @Title: selectAll
	 * @author ts 2013-9-25
	 * @Description:
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> queryPage(Page page);

	// /**
	// * 按登录名查询单个小区
	// * @Title: queryByLoginNm
	// * @author ts 2013-9-25
	// * @Description:
	// * @param loginNm
	// * @return
	// * @return SysCommunity
	// * @throws
	// */
	// SysCommunity queryByLoginNm(String loginNm);

	/**
	 * 按id查询单个小区
	 * 
	 * @Title: queryById
	 * @author ts 2013-9-25
	 * @Description:
	 * @param userId
	 * @return
	 * @return SysCommunity
	 * @throws
	 */
	SysCommunity queryById(Long userId);

	/**
	 * 插入一个小区
	 * 
	 * @Title: insert
	 * @author ts 2013-9-25
	 * @Description:
	 * @param SysCommunity
	 * @return
	 * @return int
	 * @throws
	 */
	int insert(SysCommunity SysCommunity);

	/**
	 * 删除一个小区
	 * 
	 * @Title: delete
	 * @author ts 2013-9-25
	 * @Description:
	 * @param userId
	 * @return
	 * @return int
	 * @throws
	 */
	int delete(Long userId);

	/**
	 * 修改一个小区
	 * 
	 * @Title: update
	 * @author ts 2013-9-25
	 * @Description:
	 * @param SysCommunity
	 * @return
	 * @return int
	 * @throws
	 */
	void update(SysCommunity SysCommunity);

	/**
	 * 按region（区域）ID查询小区
	 * 
	 * @Title: queryById
	 * @author xzm 2013-11-8
	 * @Description:
	 * @param userId
	 * @return
	 * @return SysCommunity
	 * @throws
	 */
	List<SysCommunity> queryBydeptID(Long deptId);

	void updateDeptIDByIdKey(Map<String, Object> map);

	/**
	 * 根据主键查询单个小区信息
	 * 
	 * @Title: querySingleDept
	 * @author cqx 2013-10-11
	 * @Description:
	 * @param @param idKey
	 * @param @return
	 * @return SysDept
	 * @throws
	 */
	SysCommunity edit(Long idKey);

	// /**
	// * 修密码
	// *
	// * @Title: modifyPwd
	// * @author xzm 2013-11-13
	// * @Description:
	// * @param SysCommunity
	// * @return
	// * @return void
	// * @throws
	// */
	// void modifyPwd(SysCommunity SysCommunity);
	/**
	 * 
	 * @Title: queryByCommunityCd
	 * @author xzm 2013-11-14
	 * @Description: 插入小区信息时判断小区编号是否已添加过，防止编号重复
	 * @param userCd
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> queryByCommunityCd(String userCd);

	/**
	 * 
	 * @Title: qByLoginNm
	 * @author xzm 2013-11-14
	 * @Description: 插入小区信息时判断登录名是否已添加过，防止登录名重复
	 * @param loginNm
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> qByLoginNm(String loginNm);

	/**
	 * 
	 * @Title: queryByCommunityCdUp
	 * @author xzm 2013-11-14
	 * @Description: 更新小区信息时判断小区编号是否已添加过，防止编号重复
	 * @param SysCommunity
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> queryByCommunityCdUp(SysCommunity SysCommunity);

	/**
	 * 
	 * @Title: queryByLoginNmUp
	 * @author xzm 2013-11-14
	 * @Description: 更新小区信息时判断登录名是否已添加过，防止登录名重复
	 * @param SysCommunity
	 * @return
	 * @return List<SysCommunity>
	 * @throws
	 */
	List<SysCommunity> queryByLoginNmUp(SysCommunity SysCommunity);

	/**
	 * 查询同级中排序码最大值加10
	 * 
	 * @Title: queryMaxOrderCdByPId
	 * @author xzm 2013-12-31
	 * @Description:
	 * @param parentId
	 * @return
	 * @return Long
	 * @throws
	 */
	Long queryMaxOrderCdByPId(Long parentId);

	/**
	 * 
	 * @title queryList
	 * @author chen qige
	 * @date 2014年9月22日
	 * @param userId
	 * @return
	 */
	SysCommunity queryList(Long userId);

	/**
	 * 根据id查询小区详细信息
	 * 
	 * @title queryCommunityVOById
	 * @author chen qige
	 * @date 2014年9月22日
	 * @param id
	 * @return
	 */
	SysCommunityVO queryCommunityVOById(Long id);

	/**
	 * 根据ID查询小区
	 * 
	 * @param idKey
	 * @return
	 */
	SysCommunityVO queryCommunityByIdKey(Long idKey);

	/**
	 * 根据Id查询小区,物业查询时使用该函数
	 * 
	 * @param page
	 * @return
	 */
	List<SysCommunity> queryByIdKeyForPage(Page page);

	/**
	 * 增加补充的信息
	 * 
	 * @param sysCommunity
	 */
	void insertAppendInfo(SysCommunity sysCommunity);

	/**
	 * 更新补充的信息
	 * 
	 * @param sysCommunity
	 */
	void updateAppendInfo(SysCommunity sysCommunity);

	/**
	 * 查询小区的基本信息
	 * 
	 * @param communityId
	 * @return
	 */
	List<SysCommunityVO> mobileQueryCommunityInfo(Long communityId);

	/**
	 * 根据小区名字更新小区信息
	 * 
	 * @param sysCommunity
	 */
	void updateCommunityByName(SysCommunity sysCommunity);

	/**
	 * 根据小区名查询小区集合
	 * 
	 * @param communityName
	 * @return
	 */
	List<SysCommunity> queryCommunityByName(String communityName);

	/**
	 * 根据用户id查询小区权限
	 * 
	 * @param userId
	 * @return
	 * @author cqg 2015年7月3日
	 */
	List<SysCommunity> queryPopedomCommunityByUserId(Long userId);

	/**
	 * 查找所有communityCode(去重) 只有idKey和communityCode有值
	 * @return
	 *@author 蔡小龙
	 */
	List<SysCommunity> queryCommunityIdAndCodeByCodes();
	
	/**
	 * 根据用户权限的小区和部门查询下级节点
	 * 
	 * @param map
	 * @return
	 * @author cqg 2015年8月4日
	 */
	List<SysCommunity> queryBydeptIdAndPopedomIds(Map<String, Object> map);
}
