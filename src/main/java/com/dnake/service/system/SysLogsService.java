package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysLogs;

public interface SysLogsService {

	/**
	 * 根据主键查询单个日志信息
	* @Title: edit 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param idKey
	* @param @return    
	* @return SysLogs    
	* @throws
	 */
	SysLogs edit(Long idKey);
	
	/**
	 * 更新单个日志信息
	* @Title: update 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param sysLogs    
	* @return String    
	* @throws
	 */
	String update(SysLogs sysLogs);
	
	/**
	 * 删除单个日志信息
	* @Title: delete 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param idKey    
	* @return String    
	* @throws
	 */
	String delete(Long idKey);
	
	/**
	 * 增加单个日志信息
	* @Title: insert 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param funcMenuNm
	* @param @param funcOperNm
	* @param @param operId
	* @param @param operNm
	* @param @param operRemark
	* @param @return    
	* @return String    
	* @throws
	 */
	public String insert(String funcMenuNm, String funcOperNm, String operRemark);
	/**
	 * 
	* @Title: listPage 
	* @author xzm  2014-1-2
	* @Description: 
	* @param page
	* @return    
	* @return List<SysLogs>    
	* @throws
	 */
	Page<SysLogs> listPage(int pageIndex,int rows,String beginTime,String endTime,String operNm,String sort,String order);
}
