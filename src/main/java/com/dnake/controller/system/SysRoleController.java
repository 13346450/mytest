/**
 * 
 */
package com.dnake.controller.system;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysRole;
import com.dnake.service.system.SysRoleService;

/** 
 * @ClassName: SysRoleController 
 * @Description: 
 * @author cqx
 * @date 2013-10-9 上午8:51:56 
 *  
 */
@Controller
@RequestMapping("/system/SysRole/*")//通配符
@Login
public class SysRoleController extends BaseController  {
	@Resource
	private SysRoleService sysRoleService;
	
	@RequestMapping(value = "queryAllSysRole")
	@ResponseBody
	public List<SysRole> queryAllSysRole(){
		return sysRoleService.queryAllSysRole();
	}
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysRole> listPage(@RequestParam("page") int pageIndex, int rows){
		Page<SysRole> page = new Page<SysRole>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setRows(sysRoleService.listPage(page));
		return page;
	}
	
	//编辑单个角色信息
	@RequestMapping( value = "{idKey}/edit")
	public String editSingleRole(@PathVariable Long idKey,Model model){
		model.addAttribute("roleEdit", sysRoleService.queryRoleByIdKey(idKey));
		return "system/roleEdit";
	}
	
	//更新一个角色信息
	@RequestMapping( value = "update")
	public void update(SysRole sysRole, HttpServletResponse response){
		this.outputString(response, sysRoleService.update(sysRole)); 
	}
	
	//删除单个角色信息
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response, Long idKey){
		this.outputString(response, sysRoleService.delete(idKey)); 
	}

	//增加角色信息
	@RequestMapping( value = "insert")
	public void insert(HttpServletResponse response, SysRole sysRole){
		this.outputString(response, sysRoleService.insert(sysRole)); 
	}
	
	@RequestMapping(value = "getRoleByGrade")
	@ResponseBody
	public List<SysRole> getRoleByGrade()
	{
		return sysRoleService.getRoleByGrade();
	}
}
