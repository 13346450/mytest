package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.domain.system.SysMenu;
import com.dnake.domain.system.SysTree;
import com.dnake.mapper.system.SysMenuMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysMenuService;
import com.dnake.utils.Constants;
import com.dnake.utils.TreeUtil;


/**
 * @ClassName:SysMenuServiceImpl.java
 * @Description:
 * @author whm
 * @date 2013-10-29 上午9:48:51
 * 
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl implements SysMenuService {
	
	@Resource
	private SysMenuMapper sysMenuMapper;

	public List<SysMenu> listAll() {
		return sysMenuMapper.queryAll();
	}
	
	public List<SysMenu> listAccordionMenu(Long roleId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("parentId", Constants.SYS_MENU_TREE_ROOT_ID);
		return sysMenuMapper.getTreeChildren(map);
	}
	
	public List<SysMenu> listTreeMenu(Long roleId, Long accordionId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("parentId", accordionId);
		return sysMenuMapper.getTreeChildren(map);
	}
	
	/**
	 * 构造首页抽屉菜单树List
	 */
	public List<EasyUiTreeModel> creatMenuTree(Long roleId, Long nodeId, Long accordionId){
		if(null == nodeId){
			nodeId = accordionId;
		}
		List<SysMenu> list = listTreeMenu(roleId, nodeId);
		List<EasyUiTreeModel> treeList = new ArrayList<EasyUiTreeModel>();
		for (SysMenu sysMenu : list) {
			EasyUiTreeModel node = new EasyUiTreeModel();
			node.setId(sysMenu.getIdKey().toString());
			node.setText(sysMenu.getCdNm());
			if(hasChildren(roleId, sysMenu.getIdKey())){
				node.setState("closed");
			}else {
				node.setAttributes(sysMenu.getLinkAddress());
			}
			treeList.add(node);
		}
		return treeList;	
	}
	
	/**
	 * 判断是否有孩子节点
	* @Title: hasChildren 
	* @author tw  2013-10-15
	* @Description: 
	* @param @param roleId
	* @param @param parentId
	* @param @return    
	* @return boolean    
	* @throws
	 */
	private boolean hasChildren(Long roleId,Long parentId){
		List<SysMenu> ls = listTreeMenu(roleId, parentId);
		if(ls!=null && ls.size()>0){
			return true;
		}
		return false;
	}
	
	/*构造菜单树*/
	public DhtmlxTreeModel creatSysMenuTree(Long nodeId) {
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if(null != nodeId && nodeId == -1){
			SysMenu sm = sysMenuMapper.edit(Constants.SYS_MENU_TREE_ROOT_ID);
			if(null != sm){
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sm.getIdKey().toString());
				node.setText(sm.getCdNm());
				node.setItem(getChildren(sm.getIdKey()));
				node.setOpen("1");
				children.add(node);
			}
			tree.setItem(children);
		}else{
			tree.setItem(getChildren(nodeId));
		}
		return tree;
	}

	/*获取孩子节点*/
	private List<DhtmlxTreeModel> getChildren(Long parentId){
		List<SysMenu> list = sysMenuMapper.queryChildrenByParentId(parentId);
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if(null != list && list.size()>0){
			for(SysMenu sysMenu : list){
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysMenu.getIdKey().toString());
				node.setText(sysMenu.getCdNm());
				node.setChild((sysMenu.getLastMark()^1)+"");
				children.add(node);
			}
		}
		return children;
	}

	/*更新指定idKey菜单的parentId值*/
	@Transactional
	public String updateParentIdByIdKey(Long idKey, Long oldParentId, Long targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", idKey);
		map.put("parentId", targetId);
		sysMenuMapper.updateParentIdByIdKey(map);
		map = new HashMap<String, Object>();
		map.put("idKey", targetId);
		map.put("lastMark", "0");
		sysMenuMapper.updateLastMark(map);
		if(!haveChildren(oldParentId)){
			map = new HashMap<String, Object>();
			map.put("idKey", oldParentId);
			map.put("lastMark", "1");
			sysMenuMapper.updateLastMark(map);
		}
		SysMenu sysMenu = sysMenuMapper.edit(idKey);
		writeLog(Constants.FUNC_MENU_NM_MENU, Constants.FUNC_OPER_NM_TREEDRAG,"idKey:" + sysMenu.getIdKey()
				+ ",cdNm:" + sysMenu.getCdNm() + ",oldParentId:" + oldParentId + ",newParentId:" + targetId);
		return "{\"successMsg\":\"移动成功！\"}";
	}

	
	 /*判断是否有子节点*/
	private boolean haveChildren(Long parentId) {
		List<SysMenu> list = sysMenuMapper.queryChildrenByParentId(parentId);
		if(null != list && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	/*查询一条菜单数据*/
	@Override
	public SysMenu edit(Long menuId) {
		return sysMenuMapper.edit(menuId);
	}

	/*插入菜单数据并且更新父节点的LastMark字段值为0*/
	@Transactional
	public String insertMenuUpdateParentLaskMark(SysMenu sysMenu) {
		sysMenu.setChgDt(new Date());
		sysMenu.setLastMark(1);
		sysMenuMapper.insert(sysMenu);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idKey", sysMenu.getParentId());
		map.put("lastMark", 0);
		sysMenuMapper.updateLastMark(map);
		writeLog(Constants.FUNC_MENU_NM_MENU, Constants.FUNC_OPER_NM_CREATE,"idKey:" + sysMenu.getIdKey()
				+ ",cdNm:" + sysMenu.getCdNm() + ",parentId:" + sysMenu.getParentId());
		return "{\"successMsg\":\"添加菜单成功！\",\"idKey\":\"" + sysMenu.getIdKey()
				+ "\",\"parentId\":\"" + sysMenu.getParentId() + "\",\"cdNm\":\"" + sysMenu.getCdNm() + "\"}";
	}

	/*删除菜单，并判断更新父节点lastMark*/
	@Transactional
	public String deleteMenuUpdateParentLastMark(Long menuId, Long parentId) {
		SysMenu sysMenu = sysMenuMapper.edit(menuId);
		String cdNm = sysMenu.getCdNm();
		sysMenuMapper.delete(menuId);
		if(!haveChildren(parentId)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idKey", parentId);
			map.put("lastMark", 1);
			sysMenuMapper.updateLastMark(map);
		}
		writeLog(Constants.FUNC_MENU_NM_MENU, Constants.FUNC_OPER_NM_DELETE,"idKey:" + menuId
				+ ",cdNm:" + cdNm + ",oldParentId:" + parentId);
		return "{\"successMsg\":\"删除菜单成功！\",\"idKey\":\"" + menuId + "\"}";
	}

	/*更新一个菜单*/
	@Override
	public String update(SysMenu sysMenu) {
		sysMenu.setChgDt(new Date());
		sysMenuMapper.update(sysMenu);
		writeLog(Constants.FUNC_MENU_NM_MENU, Constants.FUNC_OPER_NM_UPDATE,"idKey:" + sysMenu.getIdKey()
				+ ",cdNm:" + sysMenu.getCdNm() + ",parentId:" + sysMenu.getParentId());
		return "{\"successMsg\":\"更新菜单成功！\",\"idKey\":\"" 
						+ sysMenu.getIdKey() + "\",\"parentId\":\"" 
						+ sysMenu.getParentId() + "\",\"cdNm\":\"" + sysMenu.getCdNm() + "\"}";
	}
	
	/**
	 * 带子节点的SysMenu
	* @Title: querySysMenu 
	* @author xzm  2013-12-9
	* @Description: 
	* @return    
	* @return SysMenu    
	* @throws
	 */
	public SysMenu querySysMenu() {
		List<SysMenu> list = sysMenuMapper.querySysMenu();
		SysTree tree = TreeUtil.populateMenu(list);
		return (SysMenu)tree;
	}
}
