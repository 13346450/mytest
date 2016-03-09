package com.dnake.controller.system;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnake.common.interceptor.Login;
import com.dnake.controller.common.BaseController;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysLogs;
import com.dnake.service.system.SysLogsService;
@Controller
@RequestMapping("/system/SysLogs/*")//通配符
@Login
public class SysLogsController extends BaseController  {
	@Resource
	private SysLogsService sysLogsService;
	
	@RequestMapping(value = "listPage")
	@ResponseBody
	public Page<SysLogs> listPage(@RequestParam("page") int pageIndex, int rows,String dataTimeBegin,String dataTimeEnd,String operNm,String sort,String order){
			return sysLogsService.listPage(pageIndex,rows,dataTimeBegin,dataTimeEnd,operNm,sort,order);
	}
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response, Long idKey){
		this.outputString(response, sysLogsService.delete(idKey)); 
	}
}
