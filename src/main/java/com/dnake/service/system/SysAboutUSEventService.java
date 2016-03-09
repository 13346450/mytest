package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSEvent;
import com.dnake.domain.system.SysAboutUSEventVO;

public interface SysAboutUSEventService {

	public Page<SysAboutUSEventVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	public List<SysAboutUSEventVO> list(SearchParam searchParam);
	
	public SysAboutUSEvent queryByIdKey(Long idKey);

	public String update(SysAboutUSEvent bizAboutUSEvent);

	public String insert(SysAboutUSEvent bizAboutUSEvent);
	
	public String delete(Long idKey);

	public String deleteMulti(String deleteIds);
	
}
