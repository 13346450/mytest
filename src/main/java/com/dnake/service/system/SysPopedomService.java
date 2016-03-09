/**
 * 
 */
package com.dnake.service.system;

import java.util.List;
import java.util.Map;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysPopedom;
import com.dnake.domain.system.SysTree;

/** 
 * @ClassName: SysPopedomService 
 * @Description: 
 * @author wuhuimin
 * @date 2013-10-29 下午3:11:21  
 */
public interface SysPopedomService {

	/**
	 * @Title: creatSysPopedomTree
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @param nodeId
	 * @param @return   
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	public DhtmlxTreeModel creatSysPopedomTree(Long nodeId);
	
	/**
	 * 根据角色ID查询权限
	* @Title: findAclRecord 
	* @author xzm  2013-12-9
	* @Description: 
	* @param roleId
	* @return    
	* @return Map    
	* @throws
	 */
	Map findAclRecord(Long roleId);
	
	/**
	 * 根据json加载树形结构,菜单的树形,如果有权限的话,会选择checkbox
	* @Title: loadTreeAsJSON 
	* @author xzm  2013-12-9
	* @Description: 
	* @param sysTree
	* @param roleId
	* @param menuIds
	* @param map
	* @return    
	* @return StringBuffer    
	* @throws
	 */
	StringBuffer loadTreeAsJSON(SysTree sysTree, Long roleId,
			List menuIds, Map map);
	
	/**
	 * 设置权限
	* @Title: setPopedomMultiWrite 
	* @author xzm  2013-12-9
	* @Description: 
	* @param roleId
	* @param menuIdsAndRCUD    
	* @return void    
	* @throws
	 */
	String setPopedomMultiWrite(Long roleId, String menuIdsAndRCUD);
	
	/**
	 * 通过请求的地址与角色ID判断，该用户是否有访问该页面权限
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
	 * 通过菜单ID与角色ID判断，该用户是否有访问该页面权限
	* @Title: isPermissionByMenuId 
	* @author xzm  2014-1-22
	* @Description: 
	* @param roleId
	* @param MenuId
	* @return    
	* @return Boolean    
	* @throws
	 */
	Boolean isPermissionByMenuId(Long roleId, Long MenuId);
}
