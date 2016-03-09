package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSHonor;
import com.dnake.domain.system.SysAboutUSHonorVO;

public interface SysAboutUSHonorService {

	public Page<SysAboutUSHonorVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	public List<SysAboutUSHonorVO> list(SearchParam searchParam);
	
	public SysAboutUSHonor queryByIdKey(Long idKey);

	public String update(SysAboutUSHonor bAboutUSHonor);

	public String insert(SysAboutUSHonor bAboutUSHonor);
	
	public String delete(Long idKey);

	public String deleteMulti(String deleteIds);
	
}
