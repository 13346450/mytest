package com.dnake.service.system;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysRegion;

public interface SysRegionService {
	
	/**
	 * 根据id查询下级节点
	* @Title: queryChildrenByParentId 
	* @author xzm  2013-11-14
	* @Description: 
	* @param RegionId
	* @return    
	* @return List<SysRegion>    
	* @throws
	 */
	List<SysRegion> queryChildrenByParentId(Long RegionId); 
	
	
	/**
	 * 打开编辑页面
	* @Title: edit 
	* @author xzm  2013-11-14
	* @Description: 
	* @param RegionId
	* @return    
	* @return SysRegion    
	* @throws
	 */
	SysRegion edit(Long RegionId);

	/**
	 * 保存新区域
	* @Title: insert 
	* @author xzm  2013-11-14
	* @Description: 
	* @param SysRegion
	* @return    
	* @return String    
	* @throws
	 */
	String insert(SysRegion SysRegion);

	/**
	 * 删除一个区域
	* @Title: delete 
	* @author xzm  2013-11-14
	* @Description: 
	* @param RegionId    
	* @return void    
	* @throws
	 */
	void delete(Long RegionId);
	
	/**
	 * 删除区域并判断更新父节点lastMark
	* @Title: deleteRegionUpdateParentLastMark 
	* @author xzm  2013-11-14
	* @Description: 
	* @param RegionId
	* @param parentId
	* @return    
	* @return String    
	* @throws
	 */
	public String deleteRegionUpdateParentLastMark(Long RegionId, Long parentId);

	/**
	 * 更新一个区域
	* @Title: update 
	* @author xzm  2013-11-14
	* @Description: 
	* @param SysRegion
	* @return    
	* @return String    
	* @throws
	 */
	String update(SysRegion SysRegion);

	/**
	 * 更新指定idKey区域的parentId值
	* @Title: updateParentIdByIdKey 
	* @author xzm  2013-11-14
	* @Description: 
	* @param idKey
	* @param oldParentId
	* @param targetId
	* @return    
	* @return String    
	* @throws
	 */
	String updateParentIdByIdKey(Long idKey, Long oldParentId, Long targetId);
	
	/**
	 * 插入区域数据并且更新父节点的LastMark字段值为0
	* @Title: insertRegionUpdateParentLaskMark 
	* @author xzm  2013-11-14
	* @Description: 
	* @param SysRegion
	* @return    
	* @return String    
	* @throws
	 */
	public String insertRegionUpdateParentLaskMark(SysRegion SysRegion);
	
	/**
	 * 构造区域树
	* @Title: creatSysRegionTree 
	* @author xzm  2013-11-14
	* @Description: 
	* @param nodeId
	* @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	DhtmlxTreeModel creatSysRegionTree(Long nodeId);
	public boolean haveChildren(Long parentId);
}
