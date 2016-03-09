package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;

import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.common.Page;
/**
 * 商品类目管理
*  BizCategoryMapper <br/> 
*  2014年9月9日 上午11:19:21 <br/> 
* @author chen qige 
* @version
 */
public interface BizCategoryMapper {
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 *@author cqg 
	 *2015年7月8日
	 */
	BizCategory selectById(Long id);
	List<BizCategory> selectByParentId(Long parentId);
	/**
	 * 根据父节点parentId查找当前节点的子节点
	* @Title: queryChildrenByParentId 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param parentId
	* @param @return    
	* @return List<BizCategory>    
	* @throws
	 */
	List<BizCategory> queryChildrenByParentId(Long parentId);
	/**
	 * 根据主键查询单个目录信息
	* @Title: querySingleDept
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return BizCategory    
	* @throws
	 */
	BizCategory edit(Long idKey);
	/**
	 * 更新单个目录信息
	* @Title: update 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysDept    
	* @return void    
	* @throws
	 */
	void update(BizCategory sysDept);
	
	/**
	 * 更新指定目录LastMark值
	* @Title: updateLastMark 
	* @author tw  2013-10-22
	* @Description: 
	* @param @param map    
	* @return void    
	* @throws
	 */
	void updateLastMark(Map<String, Object> map);
	
	/**
	 * 更新指定idKey目录的parentId值
	* @Title: updateParentIdByIdKey 
	* @author tw  2013-10-24
	* @Description: 
	* @param @param map    
	* @return void    
	* @throws
	 */
	void updateParentIdByIdKey(Map<String, Object> map);
	
	/**
	 * 删除单个目录信息
	 * 
	 * @Title: delete
	 * @author cqx 2013-10-11
	 * @Description:
	 * @param @param idKey
	 * @return void
	 * @throws
	 */
	void delete(Long idKey);
	/**
	 * 增加单个目录信息
	* @Title: insert 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysDept    
	* @return void    
	* @throws
	 */
	void insert(BizCategory sysDept);
	
	/**
	 * 查询当前所有下级单位Id
	* @Title: queryAllChildrenIdByDeptId 
	* @author tw  2013-11-21
	* @Description: 
	* @param deptId
	* @param @return    
	* @return List<Long>    
	* @throws
	 */
	List<Long> queryAllChildrenIdByDeptId(Long deptId);
	/**
	 * 查询该单位所属的二级单位
	* @Title: querySecondDeptId 
	* @author ts  2013-12-4
	* @Description: 
	* @param deptId
	* @return    
	* @return Long
	* @throws
	 */
	Long querySecondDeptId(Long deptId);
	/**
	 * 查询同级中排序码最大值加10
	* @Title: queryMaxOrderCdByPId 
	* @author xzm  2013-12-31
	* @Description: 
	* @param parentId
	* @return    
	* @return Long    
	* @throws
	 */
	Long queryMaxOrderCdByPId(Long parentId);
	/**
	 * 根据单位ID查询 ParentId
	* @Title: getParentIdByDeptId 
	* @author xzm  2014-1-2
	* @Description: 
	* @param deptId
	* @return    
	* @return Long    
	* @throws
	 */
	Long getParentIdByDeptId(Long deptId);
	/**
	 * 维护单位名称 根据单位ID查询自己和下级
	* @Title: queryWhDeptNameById 
	* @author xzm  2014-1-17
	* @Description: 
	* @param map
	* @return    
	* @return List<BizCategory>    
	* @throws
	 */
	List<BizCategory> queryWhDeptNameById(Map<String, Object> map);
	/**
	 * 管理单位 根据单位ID查询查询自己和上级
	* @Title: queryGlDeptNameById 
	* @author xzm  2014-1-17
	* @Description: 
	* @param map
	* @return    
	* @return List<BizCategory>    
	* @throws
	 */
	List<BizCategory> queryGlDeptNameById(Map<String, Object> map);
	/**
	 * 获取下拉目录树
	* @title      selectCategoryTree 
	* @author  chen qige     
	 * @param i 
	* @date      2014年9月13日 
	* @return
	 */
	List<EasyUiTreeModel> selectCategoryTree(long id);
	/**
	 * 获取下拉目录的子节点
	* @title      queryChildrenByParentId1 
	* @author  chen qige     
	* @date      2014年9月13日 
	* @param idKey
	* @return
	 */
	List<EasyUiTreeModel> queryChildrenByParentId1(Long idKey);
	/**
	 * 递归获取所有子节点id
	* @title      queryAllChildrenIdByCategoryId 
	* @author  chen qige     
	* @date      2014年9月26日 
	* @param idKey
	* @return
	 */
	List<Long> queryAllChildrenIdByCategoryId(Long idKey);
	/**
	 * 递归获取所有末端节点
	* @title      queryAllLastChildrenIdByCategoryId 
	* @author  chen qige     
	* @date      2014年9月26日 
	* @param idKey
	* @return
	 */
	List<Long> queryAllLastChildrenIdByCategoryId(Long idKey);
	/**
	 * 根据小区id，查询目录根节点
	 * @param communityId
	 * @return
	 *@author cqg 
	 *2015年7月8日
	 */
	BizCategory queryRootByCommunityId(Long communityId);
	/**
	 * 插入小区类目的默认根目录
	 * @param communityId
	 *@author cqg 
	 *2015年7月9日
	 */
	void insertDefaultRoot(Long communityId);
	/**
	 * 插入默认类目树（根目录除外）
	 * @param communityId 小区id
	 *@author cqg 
	 *2015年7月9日
	 */
	void insertDefault(Long communityId);
	/**
	 * 根据父节点名称和社区id
	 * @param parentId
	 * @return
	 *@author cqg 
	 *2015年7月13日
	 */
	List<BizCategory> queryByParentNameAndCommunity(Long communityId,String parentName);
	/**
	 * 根据节点名称和社区id
	 * @param parentId
	 * @return
	 *@author cqg 
	 *2015年7月13日
	 */
	BizCategory queryByNameAndCommunity(Long communityId,String parentName);
	
	/**
	 * 根据小区id删除
	 * @param communityId
	 *@author cqg 
	 *2015年7月14日
	 */
	public void deleteByCommunityId(Long communityId);
	/**
	 * 获取广告的类目
	 * @return
	 *@author cqg 
	 *2015年7月15日
	 */
	List<BizCategoryVO> queryADCategory(Page<BizCategoryVO> page);
}
