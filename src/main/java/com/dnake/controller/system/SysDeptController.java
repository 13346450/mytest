package com.dnake.controller.system;

import java.util.List;

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
import com.dnake.domain.system.SysDept;
import com.dnake.service.system.SysDeptService;

@Controller
@RequestMapping("/system/SysDept/*")
@Login
public class SysDeptController extends BaseController {

	@Resource
	private SysDeptService sysDeptService;

	/**
	 * 进入修改页面
	 * 
	 * @Title: edit
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "edit/{deptId}")
	public String edit(@PathVariable Long deptId, Model model) {
		model.addAttribute("sysDept", sysDeptService.edit(deptId));
		return "system/deptEdit";
	}

	/**
	 * 进入新增页面
	 * 
	 * @Title: add
	 * @author ts 2013-10-14
	 * @Description:
	 * @param deptId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("add/{deptId}")
	public String add(@PathVariable Long deptId, Model model) {
		model.addAttribute("parentId", deptId);
		model.addAttribute("orderCd", sysDeptService.queryMaxOrderCdByPId(deptId));
		return "system/deptAdd";
	}

	/**
	 * 插入部门数据并且更新父节点的LastMark字段值为0
	* @Title: insert 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param sysDept
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(SysDept sysDept, HttpServletResponse response) {
		this.outputString(response, sysDeptService.insertDeptUpdateParentLaskMark(sysDept));
	}
	
	/**
	 * 更新部门数据
	 * 
	 * @Title: update
	 * @author ts 2013-10-14
	 * @Description:
	 * @param model
	 * @param sysDept
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SysDept sysDept, HttpServletResponse response) {
		outputString(response, sysDeptService.update(sysDept));
	}

	/**
	 * 删除部门，并更新父节点的lastMark
	* @Title: delete 
	* @author tw  2013-10-30
	* @Description: 
	* @param @param deptId
	* @param @param parentId
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "delete")
	public void delete(@RequestParam Long deptId, @RequestParam Long parentId, HttpServletResponse response){
		outputString(response, sysDeptService.deleteDeptUpdateParentLastMark(deptId, parentId));
	}
	
	/**
	 * 构造部门树
	* @Title: creatSysDeptTree 
	* @author tw  2013-10-24
	* @Description: 
	* @param @param request
	* @param @param nodeId
	* @param @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	@RequestMapping(value = "creatSysDeptTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDeptTree(@RequestParam("id") Long nodeId) {
		return sysDeptService.creatSysDeptTree(nodeId);
	}
	/**
	 * 保存拖拽后的部门
	* @Title: saveDragAndDrop 
	* @author tw  2013-10-24
	* @Description: 
	* @param @param response
	* @param @param sourceId
	* @param @param parentId
	* @param @param targetId    
	* @return void    
	* @throws
	 */
	@RequestMapping(value="saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response, @RequestParam Long sourceId, @RequestParam Long parentId, @RequestParam Long targetId){
		outputString(response, sysDeptService.updateParentIdByIdKey(sourceId, parentId, targetId));
	}
	/**
	 * 获取部门树子节点
	 * @param deptId
	 * @return
	 *@author cqg 
	 *2015年7月7日
	 */
	@RequestMapping(value="getDeptChildren/{deptId}")
	@ResponseBody
	public List<SysDept> getDeptChildren(@PathVariable Long deptId){
		return sysDeptService.queryChildrenByParentId(deptId);
	}
	
	
}
