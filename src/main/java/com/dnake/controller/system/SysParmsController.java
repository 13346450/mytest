package com.dnake.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysParms;
import com.dnake.service.system.SysParmsService;
/**
 * 
 * @ClassName:SysParmsController
 * @Description:
 * @author whm
 * @date 2013-10-22 下午2:20:03
 *
 */
@Controller
@RequestMapping("system/SysParms/*")//通配符
@Login
public class SysParmsController extends BaseController {
	@Resource
	private SysParmsService sysParmsService;
	
	/**
	 * 查询所有参数
	 * @Title: listAll
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @return   
	 * @return List<SysParms>
	 * @throws
	 */
	@RequestMapping(value = "listAll", method = RequestMethod.POST)
	@ResponseBody
	public List<SysParms> listAll(){
		return sysParmsService.queryAllList();
	}
	
	/**
	 * 根据idKey查询单个参数
	 * @Title: queryParmsByIdKey
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @param idKey
	 * @param @param model
	 * @param @return   
	 * @return SysParms
	 * @throws
	 */
	@RequestMapping(value = "{idKey}", method = RequestMethod.POST)
	public SysParms queryParmsByIdKey(@PathVariable Long idKey, Model model){
		return sysParmsService.queryByIdKey(idKey);
	}
	
	/**
	 * 插入一个参数
	 * @Title: insert
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @param sysParms
	 * @param @param errors   
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	@ResponseBody
	public SysParms insert(@Valid @ModelAttribute("sysParms")SysParms sysParms,Errors errors){
		return sysParmsService.insert(sysParms);
	}
	
	/**
	 * @return 
	 * 更新一个参数
	 * @Title: update
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @param sysParms   
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public SysParms update(@Valid @ModelAttribute("sysParms")SysParms sysParms){
		return sysParmsService.update(sysParms);
	}
	
	/**
	 * 删除一个参数
	 * @Title: delete
	 * @author whm 2013-10-29
	 * @Description:
	 * @param @param response
	 * @param @param idKey   
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(HttpServletResponse response, Long id){
		outputString(response, sysParmsService.delete(id));
	}
}
