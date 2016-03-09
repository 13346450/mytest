package com.dnake.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysRegion;
import com.dnake.service.system.SysRegionService;

@Controller
@RequestMapping("/system/SysRegion/*")
@Login
public class SysRegionController extends BaseController {

	@Resource
	private SysRegionService sysRegionService;

	/**
	 * 进入修改页面
	* @Title: edit 
	* @author xzm  2013-11-14
	* @Description: 
	* @param RegionId
	* @param model
	* @return    
	* @return String    
	* @throws
	 */
	@RequestMapping(value = "edit/{RegionId}")
	public String edit(@PathVariable Long RegionId, Model model) {
		model.addAttribute("sysRegion", sysRegionService.edit(RegionId));
		return "system/regionEdit";
	}

	/**
	 * 
	* @Title: add 
	* @author xzm  2013-11-14
	* @Description: 进入新增页面
	* @param RegionId
	* @param model
	* @return    
	* @return String    
	* @throws
	 */
	@RequestMapping("add/{RegionId}")
	public String add(@PathVariable Long RegionId, Model model) {
		model.addAttribute("parentId", RegionId);
		model.addAttribute("sysRegion", sysRegionService.edit(RegionId));
		return "system/regionAdd";
	}

	/**
	 * 
	* @Title: insert 
	* @author xzm  2013-11-14
	* @Description: 插入区域数据并且更新父节点的LastMark字段值为0
	* @param SysRegion
	* @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(SysRegion sysRegion, HttpServletResponse response) {
		this.outputString(response, sysRegionService.insertRegionUpdateParentLaskMark(sysRegion));
	}
	
	/**
	 * 
	* @Title: update 
	* @author xzm  2013-11-14
	* @Description: 更新区域数据
	* @param sysRegion
	* @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SysRegion sysRegion, HttpServletResponse response) {
		outputString(response, sysRegionService.update(sysRegion));
	}

	/**
	 * 
	* @Title: delete 
	* @author xzm  2013-11-14
	* @Description: 删除区域，并更新父节点的lastMark
	* @param RegionId
	* @param parentId
	* @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "delete")
	public void delete(@RequestParam Long RegionId, @RequestParam Long parentId, HttpServletResponse response){
		outputString(response, sysRegionService.deleteRegionUpdateParentLastMark(RegionId, parentId));
	}
	
	/**
	 * 
	* @Title: creatSysRegionTree 
	* @author xzm  2013-11-14
	* @Description: 构造区域树
	* @param nodeId
	* @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	@RequestMapping(value = "creatSysRegionTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDeptTree(@RequestParam("id") Long nodeId) {
		return sysRegionService.creatSysRegionTree(nodeId);
	}
    /**
     * 
    * @Title: saveDragAndDrop 
    * @author xzm  2013-11-14
    * @Description: 保存拖拽后的区域
    * @param response
    * @param sourceId
    * @param parentId
    * @param targetId    
    * @return void    
    * @throws
     */
	@RequestMapping(value="saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response, @RequestParam Long sourceId, @RequestParam Long parentId, @RequestParam Long targetId){
		outputString(response, sysRegionService.updateParentIdByIdKey(sourceId, parentId, targetId));
	}
}