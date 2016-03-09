package com.dnake.mapper.system;

import java.util.List;

import com.dnake.common.EasyUiTreeModel;

public interface SysTreeMapper {

	List<EasyUiTreeModel> selectDeptTree(Long idKey);
	List<EasyUiTreeModel> queryChildrenByParentId(Long idKey);
}
