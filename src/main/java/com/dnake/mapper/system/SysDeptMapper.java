/**
 * 
 */
package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.system.SysDept;

/** 
 * @ClassName: SysDeptMapper 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 下午3:05:59 
 *  
 */
public interface SysDeptMapper {
	/**
	 * 根据父节点parentId查找当前节点的子节点
	* @Title: queryChildrenByParentId 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param parentId
	* @param @return    
	* @return List<SysDept>    
	* @throws
	 */
	List<SysDept> queryChildrenByParentId(Long parentId);
	/**
	 * 根据主键查询单个部门信息
	* @Title: querySingleDept
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysDept    
	* @throws
	 */
	SysDept edit(Long idKey);
	/**
	 * 更新单个部门信息
	* @Title: update 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysDept    
	* @return void    
	* @throws
	 */
	void update(SysDept sysDept);
	
	/**
	 * 更新指定部门LastMark值
	* @Title: updateLastMark 
	* @author tw  2013-10-22
	* @Description: 
	* @param @param map    
	* @return void    
	* @throws
	 */
	void updateLastMark(Map<String, Object> map);
	
	/**
	 * 更新指定idKey部门的parentId值
	* @Title: updateParentIdByIdKey 
	* @author tw  2013-10-24
	* @Description: 
	* @param @param map    
	* @return void    
	* @throws
	 */
	void updateParentIdByIdKey(Map<String, Object> map);
	
	/**
	 * 删除单个部门信息
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
	 * 增加单个部门信息
	* @Title: insert 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysDept    
	* @return void    
	* @throws
	 */
	void insert(SysDept sysDept);
	
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
	* @return List<SysDept>    
	* @throws
	 */
	List<SysDept> queryWhDeptNameById(Map<String, Object> map);
	/**
	 * 管理单位 根据单位ID查询查询自己和上级
	* @Title: queryGlDeptNameById 
	* @author xzm  2014-1-17
	* @Description: 
	* @param map
	* @return    
	* @return List<SysDept>    
	* @throws
	 */
	List<SysDept> queryGlDeptNameById(Map<String, Object> map);
	/**
	 * 手机客户端查询，剔除省份“狄耐克”
	* @title      mobileQueryByParentId 
	* @author  chen qige     
	* @date      2014年9月9日 
	* @param parentId
	* @return
	 */
	List<SysDept> mobileQueryByParentId(Long parentId);
}
