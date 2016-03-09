package com.dnake.service.system;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.system.SysMenu;

public interface SysMenuService {
	
	/**
	 * 查询所有菜单
	* @Title: listAll 
	* @author tw  2013-10-10
	* @Description: 
	* @param @return    
	* @return List<SysUser>    
	* @throws
	 */
	public List<SysMenu> listAll();
	
	/**
	 * 查询抽屉菜单
	* @Title: listAccordionMenu 
	* @author tw  2013-10-10
	* @Description: 
	* @param @param roleId
	* @param @return    
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> listAccordionMenu(Long roleId);
	
	/**
	 * 查询抽屉菜单对应的树菜单
	* @Title: listTreeMenu 
	* @author tw  2013-10-10
	* @Description: 
	* @param @param roleId
	* @param @param accordionId
	* @param @return    
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> listTreeMenu(Long roleId, Long accordionId);
	
	/**
	 * 构造抽屉菜单对应的树菜单
	* @Title: creatMenuTree 
	* @author tw  2013-10-15
	* @Description: 
	* @param @param roleId
	* @param @param nodeId
	* @param @param accordionId
	* @param @return    
	* @return List<EasyUiTreeModel>    
	* @throws
	 */
	public List<EasyUiTreeModel> creatMenuTree(Long roleId, Long nodeId, Long accordionId);

	/**
	 * 构造菜单树
	 * @Title: creatSysMenuTree
	 * @author whm 2013-10-25
	 * @Description:
	 * @param deptId
	 * @param nodeId
	 * @return
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	public DhtmlxTreeModel creatSysMenuTree(Long nodeId);

	/**
	 * @return 
	 * 更新指定idKey菜单的parentId值
	 * @Title: updateParentIdByIdKey
	 * @author whm 2013-10-25
	 * @Description:
	 * @param sourceId
	 * @param parentId
	 * @param targetId
	 * @return String
	 * @throws
	 */
	public String updateParentIdByIdKey(Long idKey, Long oldParentId, Long targetId);

	/**
	 * 打开编辑页面
	 * @Title: edit
	 * @author whm 2013-10-25
	 * @Description:
	 * @param deptId
	 * @return
	 * @return SysMenu
	 * @throws
	 */
	public SysMenu edit(Long menuId);

	/**
	 * 插入菜单数据并且更新父节点的LastMark字段值为0
	 * @Title: insertMenuUpdateParentLaskMark
	 * @author whm 2013-10-28
	 * @Description:
	 * @param sysMenu
	 * @return
	 * @return String
	 * @throws
	 */
	public String insertMenuUpdateParentLaskMark(SysMenu sysMenu);

	/**
	 * @return 
	 * 删除菜单，并判断更新父节点lastMark
	 * @Title: deleteMenuUpdateParentLastMark
	 * @author whm 2013-10-28
	 * @Description:
	 * @param menuId
	 * @param parentId
	 * @return String
	 * @throws
	 */
	public String deleteMenuUpdateParentLastMark(Long menuId, Long parentId);

	/**
	 * @return 
	 * 更新一个菜单
	 * @Title: update
	 * @author whm 2013-10-28
	 * @Description:
	 * @param sysMenu
	 * @return String
	 * @throws
	 */
	public String update(SysMenu sysMenu);
	/**
	 * 带子节点的SysMenu
	* @Title: querySysMenu 
	* @author xzm  2013-12-9
	* @Description: 
	* @return    
	* @return SysMenu    
	* @throws
	 */
	SysMenu querySysMenu();
}
