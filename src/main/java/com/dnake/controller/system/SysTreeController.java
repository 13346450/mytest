package com.dnake.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.EasyUiTreeModel;
import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.service.system.SysTreeService;

@Controller
@RequestMapping("/system/SysTree/*")
@Login
public class SysTreeController extends BaseController {

	@Resource
	SysTreeService sysTreeService;
	
	@RequestMapping(value = "getDeptTree")
	@ResponseBody
	public List<EasyUiTreeModel> getDeptTree(){
		List<EasyUiTreeModel> list = sysTreeService.selectDeptTree();
		return list;
	}
	
	@RequestMapping(value = "getTreeTest")
	public void getTreeTest(HttpServletResponse response){
		String json = "[{\"id\":1,\"text\":\"My Documents\"," +
							"\"children\":[{\"id\":11,\"text\":\"Photos\",\"state\":\"closed\"," +
											"\"children\":[{\"id\":111,\"text\":\"Friend\"},{\"id\":112,\"text\":\"Wife\"},{\"id\":113,\"text\":\"Company\"}" +
														  "]" +
										  "}]}]";
		this.outputString(response, json);
	}
	@RequestMapping(value = "getDeptTreeNotRoot")
	@ResponseBody
	public List<EasyUiTreeModel> getDeptTreeNotRoot(){
		List<EasyUiTreeModel> list = sysTreeService.getDeptTreeNotRoot();
		return list;
	}

}
