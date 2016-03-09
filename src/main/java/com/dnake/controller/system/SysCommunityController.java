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

import com.alibaba.fastjson.JSON;
import com.dnake.common.DhtmlxTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysCommunity;
import com.dnake.service.system.SysCommunityService;
import com.dnake.utils.Constants;

@Controller
@RequestMapping("/system/SysCommunity/*")
@Login
public class SysCommunityController extends BaseController{
	
	@Resource
	private SysCommunityService sysCommunityService;
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
		model.addAttribute("sysCommunity", sysCommunityService.queryList(userId));
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
	 * @param sysCommunity
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(SysCommunity sysCommunity, HttpServletResponse response) {
		this.outputString(response, sysCommunityService.update(sysCommunity));
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
		outputString(response, sysCommunityService.delete(userId));
	}
	
	@RequestMapping(value = "listPage/{deptID}")
	@ResponseBody
	public Page<SysCommunity> listPage(@RequestParam("page") int pageIndex, int rows,@PathVariable Long deptID){
		return sysCommunityService.listPage(pageIndex,rows,deptID);
	}
	
	@RequestMapping(value = "userListOrEdit/{dtuID}")
	public String userListOrEdit(@PathVariable String dtuID, Model model) {
		if(dtuID.substring(0, 1).equals("0"))
		{
			model.addAttribute("sysCommunity", sysCommunityService.queryList(Long.parseLong(dtuID)));
			return "system/communityEdit";
		}else{
			model.addAttribute("deptID", Long.parseLong(dtuID));
			model.addAttribute("orderCd", sysCommunityService.queryMaxOrderCdByPId(Long.parseLong(dtuID)));
			return "system/communityList";
		}
	}
	@RequestMapping(value = "userList/{dtuID}")
	public String userList(@PathVariable String dtuID, Model model) {
			model.addAttribute("deptID", Long.parseLong(dtuID));
			return "system/communityList";
	}
	
	@RequestMapping(value="saveDragAndDrop")
	public void saveDragAndDrop(HttpServletResponse response, @RequestParam String sourceId, @RequestParam Long DeptId, @RequestParam String targetId){
		if(sourceId.substring(0, 1).equals("0")&&!targetId.substring(0, 1).equals("0"))
		{
		outputString(response, sysCommunityService.updateDeptIDByIdKey(Long.parseLong(sourceId), DeptId, Long.parseLong(targetId)));
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
	* @param @param sysCommunity
	* @param @param response    
	* @return void    
	* @throws
	 */
	@RequestMapping(value = "insert/{deptID}", method = RequestMethod.POST)
	public void insert(@PathVariable Long deptID,SysCommunity sysCommunity, HttpServletResponse response) {
		sysCommunity.setDeptId(deptID);
		this.outputString(response, sysCommunityService.insert(sysCommunity));
	}

	
	/**
	 * 创建左侧树
	 * 
	 * @Title: creatSysDeptOrCommunityTree
	 * @author xzm 2013-11-13
	 * @Description:
	 * @param nodeId
	 * @return DhtmlxTreeModel
	 * @throws
	 */
	@RequestMapping(value = "creatSysDeptOrCommunityTree")
	@ResponseBody
	public DhtmlxTreeModel creatSysDeptOrCommunityTree(@RequestParam("id") Long nodeId) {
		return sysCommunityService.creatSysDeptOrCommunityTree(nodeId);
	}
	
	@RequestMapping(value="selectAllCommunity")
	@ResponseBody
	public List<SysCommunity> selectAllCommunity(){
		return sysCommunityService.listAll();
		
	}
	/**
	 * 获取所有权限内的小区
	 * @return
	 *@author cqg 
	 *2015年7月7日
	 */
	@RequestMapping(value="selectPopeDomCommunity")
	@ResponseBody
	public List<SysCommunity> selectPopeDomCommunity(){
		return sysCommunityService.queryPopedomCommunity();
		
	}
	
	/**
	 * 创建权限树
	 * @param nodeId
	 * @return
	 *@author cqg 
	 *2015年7月2日
	 */
	@RequestMapping(value = "creatSysPopedomTree")
	@ResponseBody
	public DhtmlxTreeModel creatTreeForPermission(@RequestParam("id") Long nodeId) {
//		if(Constants.POPEDOM_TREE!=null){
//			return Constants.POPEDOM_TREE;
//		}
		DhtmlxTreeModel dtm=sysCommunityService.creatSysPopedomTree(nodeId);
		Constants.POPEDOM_TREE=dtm;
		return dtm;
	}
}
