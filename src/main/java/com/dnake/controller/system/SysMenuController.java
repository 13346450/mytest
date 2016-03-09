package com.dnake.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.EasyUiTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysMenu;
import com.dnake.service.system.SysMenuService;
import com.dnake.utils.SessionBean;

@Controller
@RequestMapping("/system/SysMenu/*")
@Login
public class SysMenuController extends BaseController{

	@Resource
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="listAccordionMenu", method = RequestMethod.GET)
	@ResponseBody
	public Object listAccordionMenu(HttpServletRequest request) {
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
		return sysMenuService.listAccordionMenu(sessionBean.getRoleId());
	}
	
	@RequestMapping(value = "getMenuTree/{accordionId}")
	@ResponseBody
	public List<EasyUiTreeModel> getMenuTree(HttpServletRequest request,@PathVariable Long accordionId, Long id){
		SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
		List<EasyUiTreeModel> list = sysMenuService.creatMenuTree(sessionBean.getRoleId(), id, accordionId);
		return list;	
	}
	/**菜单管理*/
	/**
	 * 菜单管理创建菜单树
	 * @Title: creatSysMenuTree
	 * @author whm 2013-10-29
	 * @Description:
	 * @param request
	 * @param nodeId
	 * @return
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	@RequestMapping(value = "creatSysMenuTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysMenuTree(HttpServletRequest request,@RequestParam("id") Long nodeId) {
		return sysMenuService.creatSysMenuTree(nodeId);
	}
	
	/**
	 * 保存拖拽后的菜单
	 * @Title: saveDragAndDrop
	 * @author whm 2013-10-29
	 * @Description:
	 * @param response
	 * @param sourceId
	 * @param parentId
	 * @param targetId
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response, @RequestParam Long sourceId, @RequestParam Long parentId, @RequestParam Long targetId){
		outputString(response, sysMenuService.updateParentIdByIdKey(sourceId, parentId, targetId));
	}
	
	/**
	 * 进入修改菜单页面
	 * @Title: edit
	 * @author whm 2013-10-29
	 * @Description:
	 * @param menuId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "edit/{menuId}")
	public String edit(@PathVariable Long menuId, Model model) {
		model.addAttribute("sysMenu", sysMenuService.edit(menuId));
		return "system/menuEdit";
	}
	
	/**
	 * 进入新增菜单页面
	 * @Title: add
	 * @author whm 2013-10-29
	 * @Description:
	 * @param menuId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "add/{menuId}")
	public String add(@PathVariable Long menuId, Model model) {
		model.addAttribute("parentId", menuId);
		return "system/menuAdd";
	}
	
	/**
	 * 插入菜单数据，并更新父节点的LastMark字段值为0
	 * @Title: insertMenuUpdateParentLastMark
	 * @author whm 2013-10-29
	 * @Description:
	 * @param sysMenu
	 * @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "insertMenuUpdateParentLastMark", method = RequestMethod.POST)
	public void insertMenuUpdateParentLastMark(SysMenu sysMenu, HttpServletResponse response){
		this.outputString(response, sysMenuService.insertMenuUpdateParentLaskMark(sysMenu)); 
	}
	
	/**
	 * 删除菜单数据，并更新父节点的lastMark
	 * @Title: deleteMenuUpdateParentLastMark
	 * @author whm 2013-10-29
	 * @Description:
	 * @param menuId
	 * @param parentId
	 * @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "deleteMenuUpdateParentLastMark")
	public void deleteMenuUpdateParentLastMark(@RequestParam Long menuId, @RequestParam Long parentId, HttpServletResponse response){
		outputString(response, sysMenuService.deleteMenuUpdateParentLastMark(menuId, parentId));
	}
	
	/**
	 * 更新菜单数据
	 * @Title: update
	 * @author whm 2013-10-29
	 * @Description:
	 * @param sysMenu
	 * @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SysMenu sysMenu, HttpServletResponse response) {
		outputString(response, sysMenuService.update(sysMenu));
	}
	
}
