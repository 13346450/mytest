package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSEvent;
import com.dnake.domain.system.SysAboutUSEventVO;

public interface SysAboutUSEventMapper {
	
	public List<SysAboutUSEventVO> listPage(Page<SysAboutUSEventVO> page);
	
	public List<SysAboutUSEventVO> list(SearchParam searchParam);
	
	public SysAboutUSEvent queryByIdKey(Long idKey);
	
	public void update(SysAboutUSEvent bizAboutUSEvent);
	
	public void insert(SysAboutUSEvent bizAboutUSEvent);
	
	public void insertMulti(List<SysAboutUSEvent> list);

	public void delete(Long idKey);

	public void deleteMulti(Map<String, Object> map);

}
