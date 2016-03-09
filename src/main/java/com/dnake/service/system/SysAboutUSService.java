package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUS;
import com.dnake.domain.system.SysAboutUSVO;

public interface SysAboutUSService {
	
//	public SysAboutUS queryByIdKey(Long idKey);

//	public Page<SysAboutUSVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
//	public List<SysAboutUSVO> list(SearchParam searchParam);

//	public String update(SysAboutUS bizAboutUS);
//
//	public String insert(SysAboutUS bizAboutUS);
	
//	public String delete(Long idKey);

//	public String deleteMulti(String deleteIds);

	public SysAboutUS queryFirst();

	public String createOrUpdate(SysAboutUS bizAboutUS);
	
}
