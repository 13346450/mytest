package com.dnake.controller.system;

import java.util.Date;
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
import com.dnake.common.interceptor.ClearLogin;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysUser;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysUserService;

@Controller
@RequestMapping("/system/SysUser/*")
@Login
public class SysUserController extends BaseController{
	@Resource
	private CommonService commonService;
	@Resource
	private SysUserService sysUserService;
	/**
	 * 进入修改页面
	 * 
	 * @Title: edit
	 * @author xzm 2013-11-11
	 * @Description:
	 * @param userId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "edit/{userId}")
	public String edit(@PathVariable Long userId, Model model) {
		model.addAttribute("sysUser", sysUserService.queryList(userId));
		return "system/userEdit";
	}
	
	/**
	 * 进入新增页面
	 * 
	 * @Title: add
	 * @author xzm 2013-11-11
	 * @Description:
	 * @param userId
	 * @param model
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("add/{deptId}")
	public String add(@PathVariable Long deptId, Model model) {
		model.addAttribute("deptId", deptId);
		return "system/userAdd";
	}
	/**
	 * 更新人员数据
	 * 
	 * @Title: update
	 * @author xzm 2013-11-11
	 * @Description:
	 * @param model
	 * @param sysUser
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SysUser sysUser, HttpServletResponse response) {
		this.outputString(response, sysUserService.update(sysUser));
	}
	/**
	 * 删除人员
	* @Title: delete 
	* @author xzm 2013-11-11
	* @Description: 
	* @param @param userId
	* @param @param parentId
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "delete")
	public void delete(@RequestParam Long userId,HttpServletResponse response){
		outputString(response, sysUserService.delete(userId));
	}
	
	@RequestMapping(value = "listPage/{deptID}")
	@ResponseBody
	public Page<SysUser> listPage(@RequestParam("page") int pageIndex, int rows,@PathVariable Long deptID){
		return sysUserService.listPage(pageIndex,rows,deptID);
	}
	
	@RequestMapping(value = "querySalesByCommunityId/{communityId}")
	@ResponseBody
	public List<SysUser> querySalesByCommunityId(@PathVariable Long communityId){
		return sysUserService.querySalesBydeptID(communityId);
	}
	
	@RequestMapping(value = "userListOrEdit/{dtuID}")
	public String userListOrEdit(@PathVariable String dtuID, Model model) {
		if(dtuID.substring(0, 1).equals("0"))
		{
			model.addAttribute("sysUser", sysUserService.queryList(Long.parseLong(dtuID)));
			return "system/userEdit";
		}else{
			model.addAttribute("deptID", Long.parseLong(dtuID));
			model.addAttribute("orderCd", sysUserService.queryMaxOrderCdByPId(Long.parseLong(dtuID)));
			return "system/userList";
		}
	}
	@RequestMapping(value = "userList/{dtuID}")
	public String userList(@PathVariable String dtuID, Model model) {
			model.addAttribute("deptID", Long.parseLong(dtuID));
			return "system/userList";
	}
	
	@RequestMapping(value="saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response, @RequestParam String sourceId, @RequestParam Long DeptId, @RequestParam String targetId){
		if(sourceId.substring(0, 1).equals("0")&&!targetId.substring(0, 1).equals("0"))
		{
		outputString(response, sysUserService.updateDeptIDByIdKey(Long.parseLong(sourceId), DeptId, Long.parseLong(targetId)));
		}
		else if(sourceId.substring(0, 1).equals("0")&&targetId.substring(0, 1).equals("0"))
		{
			outputString(response,"{\"successMsg\":\"不能将人员节点移动到人员节点下面！\",\"flg\":false}");
		}
		else{
			outputString(response,"{\"successMsg\":\"不能移动部门（分公司）的节点。只能移动人员节点。！\",\"flg\":false}");
		}
	}
	
	/**
	 * 插入用户数据
	* @Title: insert 
	* @author xzm  2013-11-11
	* @Description: 
	* @param @param sysUser
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "insert/{deptID}", method = RequestMethod.POST)
	public void insert(@PathVariable Long deptID,SysUser sysUser, HttpServletResponse response) {
		sysUser.setDeptId(deptID);
		sysUser.setChgDt(new Date());
		this.outputString(response, sysUserService.insert(sysUser));
	}
	/**
	 * 修密码
	 * 
	 * @Title: modifyPwd
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param userPwd
	 * @param newPwd
	 * @param surePwd
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public void modifyPwd(HttpServletResponse response,String userPwd,String newPwd,String surePwd,String types) {
		this.outputString(response, sysUserService.modifyPwd(userPwd,newPwd,surePwd,types));
	}
	
	/**
	 * 创建左侧树
	 * 
	 * @Title: creatSysDeptOrUserTree
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param nodeId
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	@RequestMapping(value = "creatSysDeptOrUserTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDeptOrUserTree(@RequestParam("id") Long nodeId) {
		return sysUserService.creatSysDeptOrUserTree(nodeId);
	}
	
	@RequestMapping(value = "initPwd/{userId}")
	public void initPwd(@PathVariable Long userId,HttpServletResponse response) {
		outputString(response, sysUserService.initPwd(userId));
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @param code
	 */
	@ClearLogin
	@RequestMapping(value="loginByPhoneNumber")
	public void loginByPhoneNumber(HttpServletRequest request,HttpServletResponse response,SysUser user){
		outputString(response,sysUserService.cmdLoginByPhoneNumber(request,user));
	}
	
}
