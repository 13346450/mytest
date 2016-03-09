package com.dnake.controller.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.system.SysUserPopedom;
import com.dnake.domain.system.SysUserPopedomVO;
import com.dnake.service.system.SysUserPopedomService;
/**
 * 权限管理
 */
@Controller
@RequestMapping("/system/SysUserPopedom/*")
@Login
public class SysUserPopedomController extends BaseController {

	@Resource
	private SysUserPopedomService sysUserPopedomService;
	@RequestMapping(value = "update")
	public void update(HttpServletResponse response, SysUserPopedom sysUserPopedom) {
		outputString(response, sysUserPopedomService.update(sysUserPopedom));
	}

	@RequestMapping(value = "insert")
	public void insert(HttpServletResponse response, String depts, long userId, int type) {
		outputString(response, sysUserPopedomService.insert(depts, userId, type));
	}

	@RequestMapping(value = "deleteMulti")
	public void deleteMulti(HttpServletResponse response, SearchParam searchParam) {
		outputString(response, sysUserPopedomService.deleteMulti(searchParam.getDeleteIds()));
	}

	/**
	 * 不分页
	 * 
	 * @param sp
	 * @return
	 * @author cqg 2015年7月10日
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysUserPopedomVO> listPage(SearchParam sp) {
		return sysUserPopedomService.listPage(1, 9999, sp);
	}

	/**
	 * 获取该账户的最高权限的区域类型
	 * 
	 * @param response
	 * @author cqg 2015年7月10日
	 */
	@RequestMapping(value = "getMinType")
	public void getMinType(HttpServletResponse response) {
		outputString(response, sysUserPopedomService.getMinType() + "");
	}

	/**
	 * 获取该用户可以授权的省
	 * 
	 * @param response
	 * @return
	 * @author cqg 2015年7月11日
	 */
	@RequestMapping(value = "getProvince")
	@ResponseBody
	public List<SysUserPopedomVO> getProvince(HttpServletResponse response) {
		return sysUserPopedomService.getProvince();
	}

	/**
	 * 获取该用户可以授权的城市
	 * 
	 * @param response
	 * @return
	 * @author cqg 2015年7月11日
	 */
	@RequestMapping(value = "getCity")
	@ResponseBody
	public List<SysUserPopedomVO> getCity(HttpServletResponse response, String depts) {
		return sysUserPopedomService.getCity(depts);
	}

	/**
	 * 获取该用户可以授权的城市
	 * 
	 * @param response
	 * @return
	 * @author cqg 2015年7月11日
	 */
	@RequestMapping(value = "getCommunity")
	@ResponseBody
	public List<SysUserPopedomVO> getCommunity(HttpServletResponse response, String depts) {
		return sysUserPopedomService.getCommunity(depts);
	}
}
