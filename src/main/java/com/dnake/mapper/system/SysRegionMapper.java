package com.dnake.mapper.system;


import java.util.List;
import java.util.Map;

import com.dnake.domain.system.SysRegion;

/** 
 * @ClassName: SysRegionMapper 
 * @Description: 
 * @author xzm
 * @date 2013-11-14 
 *  
 */
public interface SysRegionMapper {
	/**
	 * 根据父节点parentId查找当前节点的子节点
	* @Title: queryChildrenByParentId 
	* @author xzm  2013-11-14
	* @Description: 
	* @param parentId
	* @return    
	* @return List<SysRegion>    
	* @throws
	 */
	List<SysRegion> queryChildrenByParentId(Long parentId);
	/**
	 * 根据主键查询单个区域信息
	* @Title: edit 
	* @author xzm  2013-11-14
	* @Description: 
	* @param idKey
	* @return    
	* @return SysRegion    
	* @throws
	 */
	SysRegion edit(Long idKey);
	/**
	 * 更新单个区域信息
	* @Title: update 
	* @author xzm  2013-11-14
	* @Description: 
	* @param SysRegion    
	* @return void    
	* @throws
	 */
	void update(SysRegion SysRegion);
	
	/**
	 * 更新指定区域LastMark值
	* @Title: updateLastMark 
	* @author xzm  2013-11-14
	* @Description: 
	* @param map    
	* @return void    
	* @throws
	 */
	void updateLastMark(Map<String, Object> map);
	
	/**
	 * 更新指定idKey区域的parentId值
	* @Title: updateParentIdByIdKey 
	* @author xzm  2013-11-14
	* @Description: 
	* @param map    
	* @return void    
	* @throws
	 */
	void updateParentIdByIdKey(Map<String, Object> map);
	
	/**
	 * 删除单个区域信息
	* @Title: delete 
	* @author xzm  2013-11-14
	* @Description: 
	* @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	/**
	 * 增加区域部门信息
	* @Title: insert 
	* @author xzm  2013-11-14
	* @Description: 
	* @param SysRegion    
	* @return void    
	* @throws
	 */
	void insert(SysRegion SysRegion);
}