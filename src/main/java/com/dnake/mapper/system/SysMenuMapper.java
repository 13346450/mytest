/**
 * 
 */
package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.system.SysMenu;

/** 
 * @ClassName: SysMenu 
 * @Description: 
 * @author cqx
 * @date 2013-10-11 下午4:06:04 
 *  
 */
public interface SysMenuMapper {
	/**
	 * 查询所有菜单信息
	* @Title: queryAll 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @return    
	* @return List<SysMenu>    
	* @throws
	 */
	List<SysMenu> queryAll();
	/**
	 * 根据主键idKey查询菜单信息
	* @Title: querySingleMenuByIdKey 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysMenu    
	* @throws
	 */
	SysMenu querySingleMenuByIdKey(Long idKey);
	/**
	 * 根据唯一标识menuMarker查询单个菜单信息
	* @Title: querySingleMenuByMarker 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param menuMarker
	* @param @return    
	* @return SysMenu    
	* @throws
	 */
	SysMenu querySingleMenuByMarker(String menuMarker);
	/**
	 * 更新单个菜单信息
	* @Title: update 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysMenu    
	* @return void    
	* @throws
	 */
	void update(SysMenu sysMenu);
	/**
	 * 根据主键idKey删除菜单信息
	* @Title: delete 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	void delete(Long idKey);
	/**
	 * 新增菜单信息
	* @Title: insert 
	* @author cqx  2013-10-11
	* @Description: 
	* @param @param sysMenu    
	* @return void    
	* @throws
	 */
	void insert(SysMenu sysMenu);

	/**
	 * 查询菜单树孩子节点
	* @Title: getTreeChildren 
	* @author tw  2013-10-28
	* @Description: 
	* @param @param map
	* @param @return    
	* @return List<SysMenu>    
	* @throws
	 */
	List<SysMenu> getTreeChildren(Map<String,Object> map);
	
	
	
	/**
	 * @Title: queryChildrenByParentId
	 * @author whm 2013-10-25
	 * @Description:
	 * @param parentId
	 * @return
	 * @return List<SysMenu>
	 * @throws
	 */
	List<SysMenu> queryChildrenByParentId(Long parentId);
	/**
	 * 更新指定idKey菜单的parentId值
	 * @Title: updateParentIdByIdKey
	 * @author whm 2013-10-25
	 * @Description:
	 * @param map
	 * @return void
	 * @throws
	 */
	void updateParentIdByIdKey(Map<String, Object> map);
	/**
	 * 更新指定菜单的LastMark值
	 * @Title: updateLastMark
	 * @author whm 2013-10-25
	 * @Description:
	 * @param map
	 * @return void
	 * @throws
	 */
	void updateLastMark(Map<String, Object> map);
	/**
	 * @Title: edit
	 * @author whm 2013-10-25
	 * @Description:
	 * @param menuId
	 * @return
	 * @return SysMenu
	 * @throws
	 */
	SysMenu edit(Long menuId);
	/**
	 * 带子节点的菜单
	* @Title: querySysMenu 
	* @author xzm  2013-12-9
	* @Description: 
	* @return    
	* @return List<SysMenu>    
	* @throws
	 */
	List<SysMenu> querySysMenu();
	
	/**
	 * 根据链接地址查询对应的记录
	* @Title: queryMenuByUrl 
	* @author xzm  2013-12-10
	* @Description: 
	* @param linkAddress
	* @return    
	* @return List<SysMenu>    
	* @throws
	 */
	List<SysMenu> queryMenuByUrl(String linkAddress);
}
