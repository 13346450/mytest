package com.dnake.service.system;

import java.util.List;

import com.dnake.common.DhtmlxTreeModel;
import com.dnake.domain.system.SysDict;

public interface SysDictService {
	
	List<SysDict> queryListSysDict();
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
	DhtmlxTreeModel creatSysDeptTree(Long nodeId);
	/**
	 * 根据dictTypeId查询List<SysDict>信息
	* @Title: editList 
	* @author cqx  2013-10-29
	* @Description: 
	* @param @param dictTypeId
	* @param @return    
	* @return List<SysDict>    
	* @throws
	 */
	List<SysDict> editList(String dictTypeId);
	/**
	 * 批量和单个删除dict信息
	* @Title: delete 
	* @author xzm  2013-11-13
	* @Description: 
	* @param @param response
	* @param @param idKey    
	* @return String    
	* @throws
	 */
	String delete(String idKey);
	/**
	 * 新增数据字典
	* @Title: insert 
	* @author cqx  2013-10-30
	* @Description: 
	* @param @param sysDict    
	* @return String    
	* @throws
	 */
	String insert(SysDict sysDict);
	/**
	 * 更新一条数据字典
	* @Title: update 
	* @author xzm  2013-11-12
	* @Description: 
	* @param @param sysDict    
	* @return String    
	* @throws
	 */
	String update(SysDict sysDict);
	
	/**
	 * 根据dictTypeId查询
	* @Title: querySysDictByDictTypeId 
	* @author tw  2013-11-13
	* @Description: 
	* @param @return    
	* @return List<SysDict>    
	* @throws
	 */
	List<SysDict> querySysDictByDictTypeId(String dictTypeId);
	SysDict queryIdNmByTypeID(String dictTypeId);
}
