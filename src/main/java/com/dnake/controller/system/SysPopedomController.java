/**
 * 
 */
package com.dnake.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysMenu;
import com.dnake.service.system.SysMenuService;
import com.dnake.service.system.SysPopedomService;

/** 
 * @ClassName: SysPopedomController 
 * @Description: 
 * @author wuhuimin
 * @date 2013-10-29 下午3:03:57  
 */

@Controller
@RequestMapping("/system/SysPopedom/*")
@Login
public class SysPopedomController extends BaseController{
	
	@Resource
	private SysPopedomService sysPopedomService;
	@Resource
	private SysMenuService sysMenuService;
	
	@RequestMapping(value = "creatSysPopedomTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysPopedomTree(HttpServletRequest request, @RequestParam("id") Long nodeId){
		return sysPopedomService.creatSysPopedomTree(nodeId);
	}
	@RequestMapping(value = "edit/{roleId}")
	public String edit(@PathVariable Long roleId, Model model) {
		model.addAttribute("roleId", roleId);
		return "system/popedomEdit";
	}
	/**
	 * 设置角色对应的权限
	* @Title: update 
	* @author xzm  2013-12-9
	* @Description: 
	* @param roleId
	* @param menuIds
	* @param model
	* @return    
	* @return String    
	* @throws
	 */
	@RequestMapping(value = "update")
	public void update(Long roleId,String menuIds,Model model, HttpServletResponse response){
		this.outputString(response,sysPopedomService.setPopedomMultiWrite(roleId, menuIds));
	}
	/**
	 * 加载树形结构
	* @Title: loadTreeAsXML 
	* @author xzm  2013-12-9
	* @Description: 
	* @param roleId
	* @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "loadTreeAsXML")
	public void loadTreeAsXML(Long roleId,HttpServletResponse response) {
		//存放有权限菜单的id
		List menuIds = new ArrayList();
		//带子节点的菜单
		SysMenu menuBean =sysMenuService.querySysMenu();
		//获得所有菜单的权限
		Map map = sysPopedomService.findAclRecord(roleId);
		outputString(response,sysPopedomService.loadTreeAsJSON(menuBean, roleId, menuIds, map).toString());
	}
}
