package com.dnake.service.system;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysDept;

public interface SysDeptService {
	
	/**
	 * 根据id查询下级节点
	* @Title: queryChildrenByParentId 
	* @author ts  2013-10-14
	* @Description: 
	* @param deptId
	* @return    
	* @return List<SysDept>    
	* @throws
	 */
	List<SysDept> queryChildrenByParentId(Long deptId); 
	
	
	/**
	 * 打开编辑页面
	* @Title: edit 
	* @author ts  2013-10-14
	* @Description: 
	* @param deptId
	* @return    
	* @return SysDept    
	* @throws
	 */
	SysDept edit(Long deptId);

	/**
	 * 保存新部门
	* @Title: insert 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param sysDept
	* @param @return    
	* @return String    
	* @throws
	 */
	String insert(SysDept sysDept);

	/**
	 * 删除部门并判断更新父节点lastMark
	* @Title: deleteDeptUpdateParentLastMark 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param deptId
	* @param @param parentId
	* @param @return    
	* @return String    
	* @throws
	 */
	public String deleteDeptUpdateParentLastMark(Long deptId, Long parentId);

	/**
	 * 更新一个部门
	* @Title: update 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param sysDept
	* @param @return    
	* @return String    
	* @throws
	 */
	String update(SysDept sysDept);

	/**
	 * 更新指定idKey部门的parentId值
	* @Title: updateParentIdByIdKey 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param idKey
	* @param @param oldParentId
	* @param @param targetId
	* @param @return    
	* @return String    
	* @throws
	 */
	String updateParentIdByIdKey(Long idKey, Long oldParentId, Long targetId);
	
	/**
	 * 插入部门数据并且更新父节点的LastMark字段值为0
	* @Title: insertDeptUpdateParentLaskMark 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param sysDept
	* @param @return    
	* @return String    
	* @throws
	 */
	public String insertDeptUpdateParentLaskMark(SysDept sysDept);
	
	/**
	 * 构造部门树
	* @Title: creatSysDeptTree 
	* @author tw  2013-10-17
	* @Description: 
	* @param @param nodeId
	* @param @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	DhtmlxTreeModel creatSysDeptTree(Long nodeId);
	
	public boolean haveChildren(Long parentId);
	
	/**
	 * 查询所属的二级单位
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
	 * 手机客户端查询返回json格式
	* @title      mobileQueryByParentId 
	* @author  chen qige     
	* @date      2014年9月9日 
	* @param parentId
	* @return
	 */
	String mobileQueryByParentId(Long parentId);
	/**
	 * 根据几个小区id查询所有的父节点id用","隔开
	 * @param communityId
	 * @return
	 *@author cqg 
	 *2015年6月30日
	 */
	String queryByAllParentIdByCommunityIds(String communityIds);
		
	
}
