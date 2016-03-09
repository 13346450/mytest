/**
 * 
 */
package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.system.SysPopedom;

/** 
 * 权限管理的Mapper
 * @ClassName: SysPopedomMapper 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 上午11:54:02 
 *  
 */
public interface SysPopedomMapper {
	/**
	 * 查询所有权限管理信息
	* @Title: queryAll 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @return    
	* @return List<SysPopedom>    
	* @throws
	 */
	List<SysPopedom> queryAll();
	/**
	 * 根据角色roleId和菜单menuId查询权限状态
	* @Title: queryByRoleIdAndMenuId 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param roleId
	* @param @param menuId
	* @param @return    
	* @return SysPopedom    
	* @throws
	 */
	SysPopedom queryByRoleIdAndMenuId(Long roleId,Long menuId);
	/**
	 * 更新权限状态
	* @Title: update 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysPopedom    
	* @return void    
	* @throws
	 */
	void update(SysPopedom sysPopedom);
	/**
	 * 根据popedomId删除权限状态信息
	* @Title: delete 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param popedomId    
	* @return void    
	* @throws
	 */
	void delete(Long popedomId);
	/**
	 * 增加一条权限状态信息
	* @Title: insert 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysPopedom    
	* @return void    
	* @throws
	 */
	void insert(SysPopedom sysPopedom);
	
    /**
     * 根据角色ID查询相应的权限信息
    * @Title: queryByRoleId 
    * @author xzm  2013-12-9
    * @Description: 
    * @param roleId
    * @return    
    * @return List<SysPopedom>    
    * @throws
     */
	List<SysPopedom> queryByRoleId(Long roleId);
	
	/**
	 * 根据角色ID删除权限信息
	* @Title: delByRoleId 
	* @author xzm  2013-12-9
	* @Description: 
	* @param roleId    
	* @return void    
	* @throws
	 */
	void delByRoleId(Long roleId);
	
	/**
	 * 根据访问的地址栏与角色ID判断该用户是否有访问权限
	* @Title: queryByRoleIdAndUrl 
	* @author xzm  2013-12-10
	* @Description: 
	* @param map
	* @return    
	* @return List<SysPopedom>    
	* @throws
	 */
	List<SysPopedom> queryByRoleIdAndUrl(Map<String, Object> map);
	
	/**
	 * 根据访问的地址栏与角色ID判断该用户是否有访问权限
	* @Title: isPermission 
	* @author xzm  2013-12-10
	* @Description: 
	* @param roleId
	* @param linkAddress
	* @return    
	* @return Boolean    
	* @throws
	 */
	Boolean isPermission(Long roleId,String linkAddress);
	/**
	 * 根据菜单ID与角色ID查询记录
	* @Title: queryByRoleIdAndMenuId 
	* @author xzm  2014-1-22
	* @Description: 
	* @param map
	* @return    
	* @return List<SysPopedom>    
	* @throws
	 */
	List<SysPopedom> queryByRoleIdAndMenuId(Map<String, Object> map);
}
