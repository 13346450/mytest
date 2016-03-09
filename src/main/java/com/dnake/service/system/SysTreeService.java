package com.dnake.service.system;

import java.util.List;

import com.dnake.common.EasyUiTreeModel;

public interface SysTreeService {

	List<EasyUiTreeModel> selectDeptTree();
	/**
	 * 不含省级单位的树
	* @Title: getDeptTreeNotRoot 
	* @author xzm  2014-1-8
	* @Description: 
	* @return    
	* @return List<EasyUiTreeModel>    
	* @throws
	 */
	List<EasyUiTreeModel> getDeptTreeNotRoot();
}
