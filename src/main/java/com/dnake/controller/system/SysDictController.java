package com.dnake.controller.system;

import java.util.ArrayList;
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
import com.dnake.domain.system.SysDict;
import com.dnake.service.system.SysDictService;

@Controller
@RequestMapping("/system/SysDict/*")
@Login
public class SysDictController extends BaseController {
	
	@Resource
	private SysDictService sysDictService;

	/**
	 * 构造数据字典树
	* @Title: creatSysDeptTree 
	* @author cqx  2013-10-28
	* @Description: 
	* @param @param nodeId
	* @param @return    
	* @return DhtmlxTreeModel    
	* @throws
	 */
	@RequestMapping(value = "creatSysDictTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDictTree(@RequestParam("id") Long nodeId) {
		return sysDictService.creatSysDeptTree(nodeId);
	}
	/**
	 * 添加右边blank页面信息
	* @Title: edit 
	* @author cqx  2013-10-29
	* @Description: 
	* @param @param idKey
	* @param @param model
	* @param @return    
	* @return String    
	* @throws
	 */
	@RequestMapping(value = "edit/{dictTypeId}")
	public String edit(@PathVariable String dictTypeId, Model model) {
		model.addAttribute("dTypeId", dictTypeId);
		return "system/dictEdit";
	}
	/**
	 * 获取值传回dictEdit中的datagrid
	* @Title: editDataGrid 
	* @author cqx  2013-10-29
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return List<SysDict>    
	* @throws
	 */
	@RequestMapping(value = "editDataGrid/{dictTypeId}")
	@ResponseBody
	public List<SysDict> editDataGrid(@PathVariable String dictTypeId) {
		List<SysDict> listSysDict = new ArrayList<SysDict>();
		listSysDict = sysDictService.editList(dictTypeId);
		return listSysDict;
	}
	
	/**
	 * 批量和单个删除dict信息
	* @Title: delete 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param response
	* @param @param idKey    
	* @return void    
	* @throws
	 */
	@RequestMapping( value = "delete",method = RequestMethod.POST)
	public void delete(HttpServletResponse response, String idKey){
		outputString(response, sysDictService.delete(idKey));
	}	
	/**
	 * 新增数据字典
	* @Title: insert 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param response
	* @param @param dictTypeId    
	* @return void    
	* @throws
	 */
	@RequestMapping( value = "insert/{dictTypeId}")
	public void insert(HttpServletResponse response, SysDict sysDict,@PathVariable String dictTypeId){
		sysDict.setDictTypeId(dictTypeId);
		this.outputString(response, sysDictService.insert(sysDict)); 
	}
	/**
	 * 更新数据信息
	* @Title: update 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param sysDict
	* @param @param response
	* @return void    
	* @throws
	 */
	@RequestMapping( value = "update", method = RequestMethod.POST)
	public void update(SysDict sysDict, HttpServletResponse response){
		this.outputString(response, sysDictService.update(sysDict)); 
	}

	@RequestMapping(value="loadSelectTagData")
	@ResponseBody
	public List<SysDict> loadSelectTagData(String dictTypeId){
		return sysDictService.querySysDictByDictTypeId(dictTypeId);
	}
}
