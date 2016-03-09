/**
 * 
 */
package com.dnake.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysMenu;
import com.dnake.domain.system.SysPopedom;
import com.dnake.domain.system.SysRole;
import com.dnake.domain.system.SysTree;
import com.dnake.mapper.system.SysMenuMapper;
import com.dnake.mapper.system.SysPopedomMapper;
import com.dnake.mapper.system.SysRoleMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysPopedomService;
import com.dnake.utils.Constants;
import com.dnake.utils.StringUtils;
import com.dnake.utils.ValidationUtil;
/** 
 * @ClassName: SysPopedomServiceImpl 
 * @Description: 
 * @author wuhuimin
 * @date 2013-10-29 下午3:12:00  
 */
@Service
public class SysPopedomServiceImpl extends BaseServiceImpl implements SysPopedomService{

	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysPopedomMapper sysPopedomMapper;
	@Resource
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public DhtmlxTreeModel creatSysPopedomTree(Long nodeId) {
		DhtmlxTreeModel tree = new DhtmlxTreeModel();
		tree.setId(nodeId + "");
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if(null != nodeId && nodeId == -1){
			DhtmlxTreeModel node = new DhtmlxTreeModel();
			node.setId(Constants.SYS_POPEDOM_TREE_ROOT_ID.toString());
			node.setText("角色列表");
			node.setItem(getAllRole());
			node.setOpen("1");
			children.add(node);
		}
		tree.setItem(children);
		return tree;
	}
	/**
	 * @Title: getAllRole
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @return   
	 * @return List<DhtmlxTreeModel>
	 * @throws
	 */
	private List<DhtmlxTreeModel> getAllRole() {
		List<SysRole> list = sysRoleMapper.queryAll();
		List<DhtmlxTreeModel> children = new ArrayList<DhtmlxTreeModel>();
		if(null != list && list.size()>0){
			for(SysRole sysRole : list){
				DhtmlxTreeModel node = new DhtmlxTreeModel();
				node.setId(sysRole.getIdKey().toString());
				node.setText(sysRole.getRoleName());
				children.add(node);
			}
		}
		return children;
	}
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
	public Map findAclRecord(Long roleId) {
		Map map = new HashMap();
		List<SysPopedom> list = sysPopedomMapper.queryByRoleId(roleId);
		if (null != list && list.size() > 0) {
			for (SysPopedom sysPopedom : list) {
				Integer popedom = Integer.valueOf(sysPopedom.getPopedom()
						.toString());
				int tmp = 1;
				Integer read = tmp << 0;
				map.put(sysPopedom.getMenuId(),
						new Object[] { sysPopedom.getMenuId(), popedom & read});
			}
		} 
		return map;
	}
	
    /**
     * 从MAP中查询对应的菜单ID的权限
    * @Title: findAclRecord 
    * @author xzm  2013-12-9
    * @Description: 
    * @param map
    * @param menuId
    * @return    
    * @return Object[]    
    * @throws
     */
	public Object[] findAclRecord(Map map, long menuId) {

		Object obj = map.get(menuId);
		if (obj != null) {
			Object[] objs = (Object[]) map.get(menuId);
			return objs;
		}

		return new Object[] {};
	}
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
	public StringBuffer loadTreeAsJSON(SysTree sysTree, Long roleId,
			List menuIds, Map map) {

		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("id:'" + SysTree.TREE_ID + "',");
		json.append("item:[{");
		json.append("userdata:[{name:'rcud',content:'"
				+ getRCUD(map, sysTree.getIdKey()) + "'}],");// 根节点不需要判断权限
		json.append("id:'" + sysTree.getIdKey() + "',");
		json.append("text:'" + sysTree.getCdNm() + "'");
		/*
		 * 这里没有必要check,放到界面上去check if(ischecked(sysTree.getId(), menuIds)) {
		 * json.append(",checked:'1'"); }
		 */
		// json.append(",open:'1'");//这里不要给它打开,在jsp中再通过js打开
		List list = sysTree.getChildList();

		if (list != null && list.size() > 0) {
			json.append(",item:[");
			load(json, list, roleId, menuIds, map);
			json.append("]");
		}

		json.append("}]");
		json.append("}");
		// System.out.println(json);
		return json;
	}

	private void load(StringBuffer json, List list, Long roleId, List menuIds,
			Map map) {

		int size = 0;
		for (Iterator i = list.iterator(); i.hasNext();) {
			SysTree s = (SysTree) i.next();
			json.append("{");
			json.append("userdata:[{name:'rcud',content:'"
					+ getRCUD(map, s.getIdKey()) + "'}],");

			json.append("id:'" + s.getIdKey() + "',");
			json.append("text:'" + s.getCdNm() + "'");
			/*
			 * 这里没有必要check,放到界面上去check if(ischecked(s.getId(), menuIds)) {
			 * json.append(",checked:'1'"); }
			 */
			// json.append(",open:'1'");//这里不要给它打开,在jsp中再通过js打开
			if (s.getLastMark() != null
					&& s.getLastMark() != SysTree.LAST_MARK_YES) {
				// 如果不是子节点,设置其图片为文件夹与子节点区分
				json.append(",im0:'folderClosed.gif'");
			}

			List childs = s.getChildList();
			if (childs.size() > 0) {
				json.append(",item:[");
				load(json, childs, roleId, menuIds, map);
				json.append("]");
			}

			json.append("}");
			if (size != list.size() - 1) {
				json.append(",");
			}
			size++;
		}
	}
	private String getRCUD(Map map, long menuId) {
		String rcud = "";
		Object[] obj = findAclRecord(map, menuId);
		if(obj.length > 0) {
			for(int i=1; i<obj.length; i++){
				rcud += obj[i];
				if(i != obj.length -1) {
					rcud += ",";
				}
			}
		}
		
		return rcud;
	}
	
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
	@Transactional
	public String setPopedomMultiWrite(Long roleId, String menuIdsAndRCUD) {
		if(roleId != null) {
		// 先删除该角色对应的所有权限
		sysPopedomMapper.delByRoleId(roleId);
		if (!menuIdsAndRCUD.equals("")) {
			String[] sArray = menuIdsAndRCUD.split("#");
			for (String sVar : sArray) {
				String[] sArray2 = sVar.split("@");
				// 菜单的id
				long menuId = Long.valueOf(sArray2[0]);
				String popedom = sArray2[1];
				boolean r = Boolean.valueOf(popedom);
				//String[] rcuds = popedom.split(",");
				//boolean r = Boolean.valueOf(rcuds[0]);
				/*boolean c = Boolean.valueOf(rcuds[1]);
				boolean u = Boolean.valueOf(rcuds[2]);
				boolean d = Boolean.valueOf(rcuds[3]);
				boolean a = Boolean.valueOf(rcuds[4]);// 审核
*/
				// 权限
				SysPopedom acl = new SysPopedom();
				acl.setMenuId(menuId);
				acl.setRoleId(roleId);
				acl.setPermission(0, r);
				/*acl.setPermission(Permission.CREATE, c);
				acl.setPermission(Permission.UPDATE, u);
				acl.setPermission(Permission.DELETE, d);
				acl.setPermission(Permission.AUDIT, a);*/
				acl.setChgDt(new Date());
				sysPopedomMapper.insert(acl);
			}
		}
		return "{\"msg\":\"权限设置成功！\",\"roleId\":\""+roleId+"\"}";
		}else{
			return "{\"msg\":\"权限设置失败！\",\"roleId\":\""+roleId+"\"}";
		}
	}
	//通过请求的地址与角色ID判断，该用户是否有访问该页面权限
	//TODO 重写该方法
	public Boolean isPermission(Long roleId,String linkAddress)
	{
		//去除在地址栏中输入//符号，导致在菜单表中查找不到信息,导致直接返回true
		linkAddress=StringUtils.removeNoUseString(linkAddress,"//","/");
		//从地址栏中/符号，出现的第二次所在的位置开始截取
		linkAddress="."+linkAddress.substring(ValidationUtil.getCharacterPosition(linkAddress,2));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("linkAddress", linkAddress);
		List<SysMenu> list2 = sysMenuMapper.queryMenuByUrl(linkAddress);
		if (null != list2 && list2.size() > 0) {
			//如果请求的地址在菜单表中
			//根据访问的地址与角色ID判断该用户是否有访问权限
			List<SysPopedom> list = sysPopedomMapper.queryByRoleIdAndUrl(map);
			if (null != list && list.size() > 0) {
				SysPopedom sysPopedom = (SysPopedom) list.get(0);
				Integer popedom = Integer.valueOf(sysPopedom.getPopedom()
						.toString());
				int tmp = 1;
				Integer read = tmp << 0;
				if ((popedom & read) == 0) {
					//0 没有访问权限
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			//如果请求的地址不在菜单表中，则直接返回true
			return true;
		}
	}
	//通过菜单ID与角色ID判断，该用户是否有访问该页面权限
	public Boolean isPermissionByMenuId(Long roleId, Long MenuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("menuId", MenuId);
		// 根据菜单ID与角色ID判断该用户是否有访问权限
		List<SysPopedom> list = sysPopedomMapper.queryByRoleIdAndMenuId(map);
		if (null != list && list.size() > 0) {
			SysPopedom sysPopedom = (SysPopedom) list.get(0);
			Integer popedom = Integer.valueOf(sysPopedom.getPopedom()
					.toString());
			int tmp = 1;
			Integer read = tmp << 0;
			if ((popedom & read) == 0) {
				// 0 没有访问权限
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
