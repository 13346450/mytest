package com.dnake.service.common;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dnake.domain.system.SysLogs;
import com.dnake.mapper.system.SysLogsMapper;
import com.dnake.utils.SessionBean;

/**
 * 业务层实现的基类，所有ServiceImpl都继承该类
* @ClassName: BaseServiceImpl 
* @Description: 
* @author ts 
* @date 2013-10-9 上午10:30:55 
*
 */
public class BaseServiceImpl {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired  
	protected HttpSession session;  
	
	@Autowired  
	protected HttpServletRequest request; 

	@Resource
	private SysLogsMapper sysLogsMapper;
	
	/**
	 * 往日志表插入记录
	 * @title      writeLog
	 * @author  ts    
	 * @date      2014年3月4日 
	 * @param funcMenuNm
	 * @param funcOperNm
	 * @param operRemark
	 */
	protected void writeLog(String funcMenuNm, String funcOperNm, String operRemark){
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		SysLogs sysLogs = new SysLogs();
		sysLogs.setFuncMenuNm(funcMenuNm);
		sysLogs.setFuncOperNm(funcOperNm);
		sysLogs.setOperId(sessionBean.getUserId());
		sysLogs.setOperNm(sessionBean.getUserName());
		sysLogs.setOperRemark(operRemark);
		sysLogs.setOperDt(new Date());
		sysLogsMapper.insert(sysLogs);
	}
	
	/**
	 * 获取登录用户的sessionBean信息
	 * @title      getUserBean
	 * @author  ts    
	 * @date      2014年9月24日 
	 * @return
	 */
	protected SessionBean getUserBean(){
		return  (SessionBean) session.getAttribute("sessionBean");
	}
	
}
